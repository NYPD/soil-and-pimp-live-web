package live.soilandpimp.model;

import java.time.LocalDate;

public class ScheduleForm {

    private LocalDate date;
    private String enterTime;
    private String startTime;
    private String prefecture;
    private String place;
    private String call;
    private String memo;
    private String link;

    // Default Accessors *********************************************
    public LocalDate getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = LocalDate.parse(date);
    }

    public String getEnterTime() {
        return this.enterTime;
    }

    public void setEnterTime(String enterTime) {
        this.enterTime = enterTime;
    }

    public String getStartTime() {
        return this.startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getPrefecture() {
        return this.prefecture;
    }

    public void setPrefecture(String prefecture) {
        this.prefecture = prefecture;
    }

    public String getPlace() {
        return this.place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getCall() {
        return this.call;
    }

    public void setCall(String call) {
        this.call = call;
    }

    public String getMemo() {
        return this.memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getLink() {
        return this.link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "ScheduleForm [date=" + this.date + ", enterTime=" + this.enterTime + ", startTime=" + this.startTime
               + ", prefecture="
               + this.prefecture + ", place=" + this.place + ", call=" + this.call + ", memo=" + this.memo + ", link="
               + this.link + "]";
    }

}
