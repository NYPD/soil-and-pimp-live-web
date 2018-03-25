package live.soilandpimp.service;

import java.util.List;

import live.soilandpimp.domain.Event;
import live.soilandpimp.model.EventForm;
import live.soilandpimp.model.HomeEvents;

public interface MainService {

    public HomeEvents getHomeEvents();

    public List<Event> getPastEvents();

    public List<Event> getAllEvents();

    public Event getEvent(String eventKey);

    public void deleteEvent(String eventKey);

    public Event saveEvent(EventForm eventForm);

    public void addEmailSubscription(String emailAddress);
}
