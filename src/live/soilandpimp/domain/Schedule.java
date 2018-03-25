package live.soilandpimp.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

import live.soilandpimp.model.ScheduleForm;

/**
 * Represents a single performance in a Soil and "Pimp" Sessions {@link Event}
 * 
 * @author NYPD
 *
 */
@UserDefinedType("schedule")
public class Schedule {

    public static final Comparator<Schedule> DATE_ORDER = new ScheduleDateComparator();

    private LocalDate date;
    @Column("enter_time")
    private String enterTime;
    @Column("start_time")
    private String startTime;
    private String prefecture;
    private String place;
    private String memo;
    private String link;

    //Ms.Jackson Constructor
    protected Schedule() {}

    public Schedule(ScheduleForm scheduleForm) {
        this.date = scheduleForm.getDate();
        this.enterTime = scheduleForm.getEnterTime();
        this.startTime = scheduleForm.getStartTime();
        this.prefecture = scheduleForm.getPrefecture();
        this.place = scheduleForm.getPlace();
        this.memo = scheduleForm.getMemo();
        this.link = scheduleForm.getLink();
    }

    // Modified Accessors *********************************************
    public long getDateAsTimeStamp() {
        return date.toEpochDay() * 24 * 60 * 60 * 1000;
    }

    public static List<Schedule> createSchedules(Collection<ScheduleForm> scheduleForms) {

        if (scheduleForms == null) return null;

        List<Schedule> schedules = new ArrayList<>();

        for (ScheduleForm scheduleForm : scheduleForms)
            schedules.add(new Schedule(scheduleForm));

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
