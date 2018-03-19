package live.soilandpimp.model;

import java.util.List;

import live.soilandpimp.domain.Event;

public class HomeEvents {

    private List<Event> activeEvents;
    private List<Event> upcomingEvents;
    private List<Event> pastEvents;

    public HomeEvents(List<Event> activeEvents, List<Event> upcomingEvents, List<Event> pastEvents) {
        this.activeEvents = activeEvents;
        this.upcomingEvents = upcomingEvents;
        this.pastEvents = pastEvents;
    }

    // Modified Accessors ********************************************
    public boolean hasActiveEvents() {
        return (activeEvents == null || activeEvents.size() == 0)? false : true;
    }

    public boolean hasUpcomingEvents() {
        return (upcomingEvents == null || upcomingEvents.size() == 0)? false : true;
    }

    public boolean hasPastEvents() {
        return (pastEvents == null || pastEvents.size() == 0)? false : true;
    }

    // Default Accessors ********************************************
    public List<Event> getActiveEvents() {
        return activeEvents;
    }

    public List<Event> getUpcomingEvents() {
        return upcomingEvents;
    }

    public List<Event> getPastEvents() {
        return pastEvents;
    }

}
