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

    /**
     * Taking an email address and a verification token, the method attempts to validate the token and email match and if so,
     * set it as a valid email in the database.
     * 
     * @param emailAddress
     * @param verificationToken
     * @return A boolean were true is if the emailAdress was successfully verified, false otherwise
     */
    public boolean verifyEmailSubscription(String emailAddress, String userVerificationToken);

    public void emailUnsubscribe(String emailAddress);
}
