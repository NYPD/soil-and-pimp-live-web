package live.soilandpimp.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import live.soilandpimp.domain.Event;
import live.soilandpimp.model.HomeEvents;
import live.soilandpimp.repository.EventsRepository;

@Service
public class DefaultEventService implements EventService {

    @Autowired
    private EventsRepository eventsRepository;

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

}
