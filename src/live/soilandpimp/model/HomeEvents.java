package live.soilandpimp.model;

import java.util.Collections;
import java.util.List;

import live.soilandpimp.domain.Event;

public class HomeEvents {

    private List<Event> activeEvents;
    private List<Event> upcomingEvents;

    public HomeEvents(List<Event> activeEvents, List<Event> upcomingEvents) {
        this.activeEvents = activeEvents;
        this.upcomingEvents = upcomingEvents;

        Collections.sort(this.activeEvents, Event.FIRST_SCHEDULE_DATE_ORDER);
        Collections.sort(this.upcomingEvents, Event.FIRST_SCHEDULE_DATE_ORDER);
    }

    // Modified Accessors ********************************************
    public boolean hasActiveEvents() {
        return (activeEvents == null || activeEvents.size() == 0)? false : true;
    }

    public boolean hasUpcomingEvents() {
        return (upcomingEvents == null || upcomingEvents.size() == 0)? false : true;
    }

    // Default Accessors ********************************************
    public List<Event> getActiveEvents() {
        return activeEvents;
    }

    public List<Event> getUpcomingEvents() {
        return upcomingEvents;
    }

}
