package live.soilandpimp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import live.soilandpimp.domain.Event;
import live.soilandpimp.model.HomeEvents;
import live.soilandpimp.repository.EventsRepository;

@Service
public class DefaultHomeEvents implements HomeService {

    @Autowired
    private EventsRepository eventsRepository;

    @Override
    public HomeEvents getHomeEvents() {

        List<Event> activeEvents = new ArrayList<>();
        List<Event> upcomingEvents = new ArrayList<>();
        List<Event> pastEvents = new ArrayList<>();

        Iterable<Event> allEvents = eventsRepository.findAll();

        for (Event event : allEvents) {

            boolean eventActive = event.isEventActive();
            if (eventActive) {
                activeEvents.add(event);
                continue;
            }

            boolean eventUpcoming = event.isEventUpcoming();

            if (eventUpcoming)
                upcomingEvents.add(event);
            else
                pastEvents.add(event);
        }

        return new HomeEvents(activeEvents, upcomingEvents, pastEvents);
    }

}
