package live.soilandpimp.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import live.soilandpimp.model.ScheduleForm;

/**
 * Represents a single performance in a Soil and "Pimp" Sessions {@link Event}
 * 
 * @author NYPD
 *
 */
@Entity
@Table(name = "schedules")
public class Schedule {

    public static final Comparator<Schedule> DATE_ORDER = new ScheduleDateComparator();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id", unique = true)
    private Long scheduleId;
    @Column(name = "`date`")
    private LocalDate date;
    @Column(name = "enter_time")
    private String enterTime;
    @Column(name = "start_time")
    private String startTime;
    private String prefecture;
    private String place;
    @Column(name = "`call`")
    private String call;
    private String memo;
    private String link;

    //JPA Constructor
    protected Schedule() {}

    public Schedule(ScheduleForm scheduleForm) {
        date = scheduleForm.getDate();
        enterTime = scheduleForm.getEnterTime();
        startTime = scheduleForm.getStartTime();
        prefecture = scheduleForm.getPrefecture();
        place = scheduleForm.getPlace();
        call = scheduleForm.getCall();
        memo = scheduleForm.getMemo();
        link = scheduleForm.getLink();
    }

    // Modified Accessors *********************************************
    public static List<Schedule> createSchedules(Collection<ScheduleForm> scheduleForms) {

        if (scheduleForms == null) return null;

        List<Schedule> schedules = new ArrayList<>();

        for (ScheduleForm scheduleForm : scheduleForms) {

            boolean noSchedule = scheduleForm == null;
            boolean noDate = !noSchedule && scheduleForm.getDate() == null;
            boolean noPlace = !noSchedule && scheduleForm.getPlace() == null;

            if (noSchedule || noDate || noPlace) continue;

            schedules.add(new Schedule(scheduleForm));
        }

        return schedules;
    }

    // Default Accessors *********************************************
    public LocalDate getDate() {
        return date;
    }

    public String getEnterTime() {
        return enterTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getPrefecture() {
        return prefecture;
    }

    public String getPlace() {
        return place;
    }
    
    public String getCall() {
        return call;
    }

    public String getMemo() {
        return memo;
    }

    public String getLink() {
        return link;
    }

    @Override
    public String toString() {
        return "Schedule [startTime=" + startTime + ", prefecture=" + prefecture + ", place=" + place + "]";
    }

    // Comparators ***************************************************
    private static class ScheduleDateComparator implements Comparator<Schedule> {

        @Override
        public int compare(Schedule s1, Schedule s2) {

            LocalDate s1Date = s1.getDate();
            LocalDate s2Date = s2.getDate();

            if (s1Date == null && s2Date != null)
                return -1;
            else if (s1Date != null && s2Date == null)
                return 1;

            return s1Date.compareTo(s2Date);
        }
    }

}
