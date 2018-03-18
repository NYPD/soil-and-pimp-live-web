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
