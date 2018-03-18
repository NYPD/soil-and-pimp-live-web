package service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import live.soilandpimp.domain.Event;
import live.soilandpimp.model.HomeEvents;
import live.soilandpimp.repository.EventsRepository;
import live.soilandpimp.service.DefaultHomeService;

public class HomeServiceTests {

    private DefaultHomeService homeService = new DefaultHomeService();

    @Test
    public void shouldGetHomeEventsObject() throws Exception {

        Event activeEvent = mock(Event.class);
        when(activeEvent.isEventActive()).thenReturn(true);
        Event upcomingEvent = mock(Event.class);
        when(upcomingEvent.isEventUpcoming()).thenReturn(true);
        Event pastEvent = mock(Event.class);

        EventsRepository eventsRepository = mock(EventsRepository.class);
        when(eventsRepository.findAll()).thenReturn(Arrays.asList(activeEvent, upcomingEvent, pastEvent));

        Field field = homeService.getClass().getDeclaredField("eventsRepository");
        field.setAccessible(true);
        field.set(homeService, eventsRepository);

        HomeEvents homeEvents = homeService.getHomeEvents();

        List<Event> activeEvents = homeEvents.getActiveEvents();
        List<Event> upcomingEvents = homeEvents.getUpcomingEvents();
        List<Event> pastEvents = homeEvents.getPastEvents();

        assertThat(activeEvents.size(), is(1));
        assertThat(upcomingEvents.size(), is(1));
        assertThat(pastEvents.size(), is(1));

    }
}
