package live.soilandpimp.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

/**
 * Represents a single Soil and "Pimp" sessions event
 * 
 * @author NYPD
 *
 */
@Table(value = "events")
public class Event {

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

    // Modified Accessors ********************************************

    /**
     * Crude way of determining if an {@link Event} is active or not. Gets the current date for both the Line Islands
     * (Location with the latest TimeZone) and the Baker Islands (Location with the earliest TimesZone) and uses them to see
     * if the {@link Event}'s schedule dates have past or not. For an {@link Event} to not have happened the Line Islands
     * current date must be before the {@link Event}'s first schedle. For an {@link Event} to already have happened the Baker
     * Islands current date must be past the {@link Event}'s last schedule.
     * 
     * @return boolean True if event is active, false otherwise.
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

        boolean eventIsActiveInBakerIsland = (earliestCountryDate.isEqual(firstScheduleDate) || earliestCountryDate.isAfter(
                                                                                                                            firstScheduleDate))
                &&
                (earliestCountryDate.isEqual(lastScheduleDate) || earliestCountryDate.isBefore(lastScheduleDate));

        boolean eventIsActiveInLineIslands = (latestCountryDate.isEqual(firstScheduleDate) || latestCountryDate.isAfter(
                                                                                                                        firstScheduleDate))
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

}
