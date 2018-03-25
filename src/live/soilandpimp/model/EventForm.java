package live.soilandpimp.model;

import java.time.LocalDateTime;
import java.util.List;

public class EventForm {

    private String eventKey;
    private String name;
    private String socialNetworkingTitle;
    private String memo;
    private String eventUrl;
    private String jvcUrl;
    private LocalDateTime openDate;

    private List<ScheduleForm> schedules;

    // Default Accessors *********************************************
    public String getEventKey() {
        return eventKey;
    }

    public void setEventKey(String eventKey) {
        this.eventKey = eventKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSocialNetworkingTitle() {
        return socialNetworkingTitle;
    }

    public void setSocialNetworkingTitle(String socialNetworkingTitle) {
        this.socialNetworkingTitle = socialNetworkingTitle;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getEventUrl() {
        return eventUrl;
    }

    public void setEventUrl(String eventUrl) {
        this.eventUrl = eventUrl;
    }

    public String getJvcUrl() {
        return jvcUrl;
    }

    public void setJvcUrl(String jvcUrl) {
        this.jvcUrl = jvcUrl;
    }

    public LocalDateTime getOpenDate() {
        return openDate;
    }

    public void setOpenDate(String openDate) {
        this.openDate = LocalDateTime.parse(openDate);
    }

    public List<ScheduleForm> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<ScheduleForm> schedules) {
        this.schedules = schedules;
    }

    @Override
    public String toString() {
        return "EventForm [eventKey=" + eventKey + ", name=" + name + ", socialNetworkingTitle=" + socialNetworkingTitle
                + ", memo=" + memo + ", eventUrl=" + eventUrl + ", jvcUrl=" + jvcUrl + ", openDate=" + openDate
                + ", schedules=" + schedules + "]";
    }

}
