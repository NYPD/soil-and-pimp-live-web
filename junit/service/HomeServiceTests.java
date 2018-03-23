package service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import live.soilandpimp.domain.Event;
import live.soilandpimp.domain.Schedule;
import live.soilandpimp.model.HomeEvents;
import live.soilandpimp.repository.EventsRepository;
import live.soilandpimp.service.DefaultMainService;

public class HomeServiceTests {

    private DefaultMainService homeService = new DefaultMainService();

    @Test
    public void shouldGetHomeEventsObject() throws Exception {

        Schedule earlySchedule = mock(Schedule.class);
        when(earlySchedule.getDate()).thenReturn(LocalDate.of(2018, 1, 1));

        Schedule lateSchedule = mock(Schedule.class);
        when(lateSchedule.getDate()).thenReturn(LocalDate.of(2018, 1, 2));

        Event activeEvent = mock(Event.class);
        when(activeEvent.isEventActive()).thenReturn(true);

        Event upcomingEvent = mock(Event.class);
        when(upcomingEvent.isEventUpcoming()).thenReturn(true);
        when(upcomingEvent.getSchedules()).thenReturn(Arrays.asList(lateSchedule));

        Event upcomingEvent2 = mock(Event.class);
        when(upcomingEvent2.isEventUpcoming()).thenReturn(true);
        when(upcomingEvent2.getSchedules()).thenReturn(Arrays.asList(earlySchedule));

        EventsRepository eventsRepository = mock(EventsRepository.class);
        when(eventsRepository.findAll()).thenReturn(Arrays.asList(activeEvent, upcomingEvent, upcomingEvent2));

        Field field = homeService.getClass().getDeclaredField("eventsRepository");
        field.setAccessible(true);
        field.set(homeService, eventsRepository);

        HomeEvents homeEvents = homeService.getHomeEvents();

        List<Event> activeEvents = homeEvents.getActiveEvents();
        List<Event> upcomingEvents = homeEvents.getUpcomingEvents();

        assertThat(activeEvents.size(), is(1));
        assertThat(upcomingEvents.size(), is(2));

        assertThat(upcomingEvents.get(0), is(upcomingEvent2));

    }

    @Test
    public void shouldGetPastEvents() throws Exception {

        Event pastEvent = mock(Event.class);

        Event upcomingEvent2 = mock(Event.class);
        when(upcomingEvent2.isEventUpcoming()).thenReturn(true);

        EventsRepository eventsRepository = mock(EventsRepository.class);
        when(eventsRepository.findAll()).thenReturn(Arrays.asList(pastEvent, upcomingEvent2));

        Field field = homeService.getClass().getDeclaredField("eventsRepository");
        field.setAccessible(true);
        field.set(homeService, eventsRepository);

        List<Event> pastEvents = homeService.getPastEvents();
        assertThat(pastEvents.size(), is(1));

        assertThat(pastEvents.get(0), is(pastEvent));

    }
}
