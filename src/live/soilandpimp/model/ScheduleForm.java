package live.soilandpimp.model;

import java.time.LocalDate;

public class ScheduleForm {

    private LocalDate date;
    private String enterTime;
    private String startTime;
    private String prefecture;
    private String place;
    private String memo;
    private String link;

    // Default Accessors *********************************************
    public LocalDate getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = LocalDate.parse(date);
    }

    public String getEnterTime() {
        return enterTime;
    }

    public void setEnterTime(String enterTime) {
        this.enterTime = enterTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getPrefecture() {
        return prefecture;
    }

    public void setPrefecture(String prefecture) {
        this.prefecture = prefecture;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "ScheduleForm [date=" + date + ", enterTime=" + enterTime + ", startTime=" + startTime + ", prefecture="
                + prefecture + ", place=" + place + ", memo=" + memo + ", link=" + link + "]";
    }

}
