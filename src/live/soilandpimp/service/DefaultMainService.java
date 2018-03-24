package live.soilandpimp.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import live.soilandpimp.domain.EmailSubscription;
import live.soilandpimp.domain.Event;
import live.soilandpimp.model.HomeEvents;
import live.soilandpimp.repository.EmailRepository;
import live.soilandpimp.repository.EventsRepository;

@Service
public class DefaultMainService implements MainService {

    @Autowired
    private EventsRepository eventsRepository;
    @Autowired
    private EmailRepository emailRepository;

    @Override
    public HomeEvents getHomeEvents() {

        List<Event> activeEvents = new ArrayList<>();
        List<Event> upcomingEvents = new ArrayList<>();

        Iterable<Event> allEvents = eventsRepository.findAll();

        for (Event event : allEvents) {

            boolean eventActive = event.isEventActive();
            if (eventActive) {
                activeEvents.add(event);
                continue;
            }

            boolean eventUpcoming = event.isEventUpcoming();

            if (eventUpcoming) upcomingEvents.add(event);
        }

        return new HomeEvents(activeEvents, upcomingEvents);
    }

    @Override
    public List<Event> getPastEvents() {

        Iterable<Event> allEvents = eventsRepository.findAll();

        List<Event> pastEvents = new ArrayList<>();

        for (Event event : allEvents)
            if (!event.isEventUpcoming()) pastEvents.add(event);

        Collections.sort(pastEvents, Event.FIRST_SCHEDULE_DATE_ORDER_DESC);

        return pastEvents;
    }

    @Override
    public List<Event> getAllEvents() {

        List<Event> allEvents = new ArrayList<>();
        eventsRepository.findAll().forEach(event -> allEvents.add(event));

        return allEvents;
    }

    @Override
    public Event getEvent(String eventKey) {
        Optional<Event> event = eventsRepository.findById(eventKey);
        return event.isPresent()? event.get() : null;
    }

    @Override
    public void addEmailSubscription(String emailAddress) {
        EmailSubscription emailSubscription = new EmailSubscription(emailAddress);
        emailRepository.save(emailSubscription);
    }


}
