package live.soilandpimp.domain;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.xml.bind.DatatypeConverter;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import live.soilandpimp.model.EventForm;

/**
 * Represents a single Soil and "Pimp" sessions event
 * 
 * @author NYPD
 *
 */
@Table(value = "events")
public class Event {

    public static final Comparator<Event> FIRST_SCHEDULE_DATE_ORDER = new EventFirstScheduleComparator();
    public static final Comparator<Event> FIRST_SCHEDULE_DATE_ORDER_DESC = new EventFirstScheduleComparatorDesc();

    @PrimaryKey(value = "event_key")
    private String eventKey;

    private String name;

    @Column("social_networking_title")
    private String socialNetworkingTitle;

    private String memo;

    @Column("event_url")
    private String eventUrl;

    @Column("jvc_url")
    private String jvcUrl;

    @Column("open_date")
    private LocalDateTime openDate;

    private List<Schedule> schedules;

    @Column("schedule_change")
    private boolean scheduleChange;
    private boolean broadcast;

    // Cassandra constructor
    protected Event() {};

    public Event(EventForm eventForm) {

        this.updateEvent(eventForm);

        if (this.name == null || this.eventUrl == null)
            throw new IllegalArgumentException("name nor event url can be null");

        String tempCompositeKey = name + eventUrl;
        // This exception should never happen, MD5 should always be present
        try {

            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(tempCompositeKey.getBytes());
            byte[] digest = messageDigest.digest();

            String eventKeyHash = DatatypeConverter.printHexBinary(digest);

            this.eventKey = eventKeyHash;
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError(e);
        }

    }

    // Modified Accessors ********************************************
    public void updateEvent(EventForm eventForm) {

        this.name = eventForm.getName();
        this.eventUrl = eventForm.getEventUrl();
        this.socialNetworkingTitle = eventForm.getName();
        this.memo = eventForm.getMemo();
        this.eventUrl = eventForm.getEventUrl();
        this.jvcUrl = eventForm.getJvcUrl();
        this.openDate = eventForm.getOpenDate();

        this.schedules = Schedule.createSchedules(eventForm.getSchedules());
    }

    /**
     * Crude way of determining if an {@link Event} is active or not. Gets the current date for both the Line Islands
     * (Location with the latest TimeZone) and the Baker Islands (Location with the earliest TimesZone) and uses them to see
     * if the {@link Event}'s schedule dates have past or not. For an {@link Event} to not have happened the Line Islands
     * current date must be before the {@link Event}'s first schedule. For an {@link Event} to already have happened the
     * Baker Islands current date must be past the {@link Event}'s last schedule.
     * 
     * @return boolean true if event is active, false otherwise.
     */
    public boolean isEventActive() {

        if (this.getSchedules() == null || this.getSchedules().size() == 0) return false;

        Collections.sort(this.getSchedules(), Schedule.DATE_ORDER);

        Date now = new Date();

        //Baker Islands, EE.UU lol
        LocalDate earliestCountryDate = now.toInstant().atZone(ZoneId.of("UTC-12")).toLocalDate();

        //Line Islands islands lol
        LocalDate latestCountryDate = now.toInstant().atZone(ZoneId.of("UTC+14")).toLocalDate();

        LocalDate firstScheduleDate = schedules.get(0).getDate();
        LocalDate lastScheduleDate = schedules.get(schedules.size() - 1).getDate();

        boolean eventIsActiveInBakerIsland =
                (earliestCountryDate.isEqual(firstScheduleDate) || earliestCountryDate.isAfter(firstScheduleDate))
                &&
                (earliestCountryDate.isEqual(lastScheduleDate) || earliestCountryDate.isBefore(lastScheduleDate));

        boolean eventIsActiveInLineIslands =
                (latestCountryDate.isEqual(firstScheduleDate) || latestCountryDate.isAfter(firstScheduleDate))
                &&
                (latestCountryDate.isEqual(lastScheduleDate) || latestCountryDate.isBefore(lastScheduleDate));

        if (eventIsActiveInBakerIsland || eventIsActiveInLineIslands)
            return true;
        else
            return false;

    }

    public boolean isEventUpcoming() {

        if (schedules == null || schedules.size() == 0) return false;

        Collections.sort(this.getSchedules(), Schedule.DATE_ORDER);

        Date now = new Date();

        //Line Islands islands lol
        LocalDate latestCountryDate = now.toInstant().atZone(ZoneId.of("UTC+14")).toLocalDate();

        LocalDate firstScheduleDate = schedules.get(0).getDate();

        return latestCountryDate.isBefore(firstScheduleDate);

    }

    public Schedule getActiveSchedule() {

        if (this.getSchedules() == null || this.getSchedules().size() == 0) return null;

        Date now = new Date();

        //Baker Islands, EE.UU lol
        LocalDate earliestCountryDate = now.toInstant().atZone(ZoneId.of("UTC-12")).toLocalDate();

        //Line Islands islands lol
        LocalDate latestCountryDate = now.toInstant().atZone(ZoneId.of("UTC+14")).toLocalDate();

        for (Schedule schedule : schedules) {

            LocalDate scheduleDate = schedule.getDate();

            boolean eventIsActiveInBakerIsland = earliestCountryDate.isEqual(scheduleDate);
            boolean eventIsActiveInLineIslands = latestCountryDate.isEqual(scheduleDate);

            if (eventIsActiveInBakerIsland || eventIsActiveInLineIslands) return schedule;

        }

        return null;

    }

    // Default Accessors *********************************************
    public String getEventKey() {
        return eventKey;
    }

    public String getName() {
        return name;
    }

    public String getSocialNetworkingTitle() {
        return socialNetworkingTitle;
    }

    public String getMemo() {
        return memo;
    }

    public String getEventUrl() {
        return eventUrl;
    }

    public String getJvcUrl() {
        return jvcUrl;
    }

    public LocalDateTime getOpenDate() {
        return openDate;
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public boolean isScheduleChange() {
        return scheduleChange;
    }

    public boolean isBroadcast() {
        return broadcast;
    }

    @Override
    public String toString() {
        return "Event [eventKey=" + eventKey + ", name=" + name + ", schedules=" + schedules + "]";
    }

    // Comparators ***************************************************
    private static class EventFirstScheduleComparator implements Comparator<Event> {

        @Override
        public int compare(Event e1, Event e2) {

            List<Schedule> e1Schedules = e1.getSchedules();
            List<Schedule> e2Schedules = e2.getSchedules();

            Collections.sort(e1Schedules, Schedule.DATE_ORDER);
            Collections.sort(e2Schedules, Schedule.DATE_ORDER);

            if ((e1Schedules == null || e1Schedules.size() == 0) && (e2Schedules != null && e2Schedules.size() > 0))
                return 1;
            else if ((e1Schedules != null && e1Schedules.size() > 0) && (e2Schedules == null || e2Schedules.size() == 0))
                return -1;

            return e1Schedules.get(0).getDate().compareTo(e2Schedules.get(0).getDate());
        }
    }

    private static class EventFirstScheduleComparatorDesc implements Comparator<Event> {

        @Override
        public int compare(Event e1, Event e2) {

            List<Schedule> e1Schedules = e1.getSchedules();
            List<Schedule> e2Schedules = e2.getSchedules();

            Collections.sort(e1Schedules, Schedule.DATE_ORDER);
            Collections.sort(e2Schedules, Schedule.DATE_ORDER);

            if ((e1Schedules == null || e1Schedules.size() == 0) && (e2Schedules != null && e2Schedules.size() > 0))
                return -1;
            else if ((e1Schedules != null && e1Schedules.size() > 0) && (e2Schedules == null || e2Schedules.size() == 0))
                return 1;

            return e2Schedules.get(0).getDate().compareTo(e1Schedules.get(0).getDate());
        }
    }

}
