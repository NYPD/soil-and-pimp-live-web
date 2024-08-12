package domain;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import live.soilandpimp.domain.Event;
import live.soilandpimp.domain.Schedule;
import live.soilandpimp.model.EventForm;

public class EventTests {

    private static Event event;
    private static Event event2;

    @SuppressWarnings("unchecked")
    @BeforeClass
    public static void setUp() throws Exception {

        Constructor<Event>[] constructors = (Constructor<Event>[]) Event.class.getDeclaredConstructors();

        for (int i = 0; i < constructors.length; i++) {
            Constructor<Event> constructor = constructors[i];
            int parameterCount = constructor.getParameterCount();

            if (parameterCount > 0) continue;

            constructor.setAccessible(true);
            event = constructor.newInstance();
            event2 = constructor.newInstance();
        }

    }

    @Test
    public void eventShouldBeActive() throws Exception {

        LocalDate currentDate = LocalDate.now();

        Schedule earlySchedule = mock(Schedule.class);
        when(earlySchedule.getDate()).thenReturn(LocalDate.of(currentDate.getYear(), currentDate.getMonth(), currentDate.getDayOfMonth()));

        List<Schedule> schedules = new ArrayList<>(Arrays.asList(earlySchedule));

        Field field = event.getClass().getDeclaredField("schedules");
        field.setAccessible(true);
        field.set(event, schedules);

        assertThat(event.isEventActive(), is(true));
    }

    @Test
    public void eventShouldBeUpcoming() throws Exception {

        LocalDate currentDate = LocalDate.now();

        Schedule earlySchedule = mock(Schedule.class);
        when(earlySchedule.getDate()).thenReturn(LocalDate.of(currentDate.getYear() + 1, currentDate.getMonth(), currentDate.getDayOfMonth()));

        List<Schedule> schedules = new ArrayList<>(Arrays.asList(earlySchedule));

        Field field = event.getClass().getDeclaredField("schedules");
        field.setAccessible(true);
        field.set(event, schedules);

        assertThat(event.isEventUpcoming(), is(true));

    }

    @Test
    public void eventShouldBePast() throws Exception {

        LocalDate currentDate = LocalDate.now();

        Schedule earlySchedule = mock(Schedule.class);
        when(earlySchedule.getDate()).thenReturn(LocalDate.of(currentDate.getYear() - 1, currentDate.getMonth(), currentDate.getDayOfMonth()));

        List<Schedule> schedules = new ArrayList<>(Arrays.asList(earlySchedule));

        Field field = event.getClass().getDeclaredField("schedules");
        field.setAccessible(true);
        field.set(event, schedules);

        assertThat(event.isEventUpcoming(), is(false));

    }

    @Test
    public void eventShouldGetActiveSchedule() throws Exception {

        LocalDate currentDate = LocalDate.now();

        Schedule pastSchedule = mock(Schedule.class);
        when(pastSchedule.getDate()).thenReturn(LocalDate.of(currentDate.getYear() - 1, currentDate.getMonth(), currentDate.getDayOfMonth()));
        Schedule activeSchedule = mock(Schedule.class);
        when(activeSchedule.getDate()).thenReturn(LocalDate.of(currentDate.getYear(), currentDate.getMonth(), currentDate.getDayOfMonth()));

        List<Schedule> schedules = new ArrayList<>(Arrays.asList(pastSchedule, activeSchedule));

        Field field = event.getClass().getDeclaredField("schedules");
        field.setAccessible(true);
        field.set(event, schedules);

        assertThat(event.isEventActive(), is(true));
        assertThat(event.getActiveSchedule(), is(activeSchedule));
    }

    @Test
    public void shouldCreateNewEvent() throws Exception {

        EventForm eventForm = new EventForm();
        eventForm.setName("a");
        eventForm.setEventUrl("");

        Event newEvent = new Event(eventForm);
        assertThat(newEvent.getEventKey(), is(notNullValue()));

    }

    @Test
    public void shouldUpdateEvent() throws Exception {

        Field field = event.getClass().getDeclaredField("eventKey");
        field.setAccessible(true);
        field.set(event, "beans");

        EventForm eventForm = new EventForm();
        eventForm.setName("a");
        eventForm.setEventUrl("");

        assertThat(event.getEventKey(), is("beans"));
        event.updateEvent(eventForm);
        assertThat(event.getEventKey(), is("beans"));

    }

    @Test
    public void shouldSortEventsDesc() throws Exception {

        LocalDate currentDate = LocalDate.now();

        Schedule oldSchedule = mock(Schedule.class);
        when(oldSchedule.getDate()).thenReturn(LocalDate.of(currentDate.getYear() - 1, currentDate.getMonth(), currentDate.getDayOfMonth()));

        Schedule currentSchedule = mock(Schedule.class);
        when(currentSchedule.getDate()).thenReturn(currentDate);

        List<Schedule> oldSchedules = new ArrayList<>(Arrays.asList(oldSchedule));
        List<Schedule> currentSchedules = new ArrayList<>(Arrays.asList(currentSchedule));

        Field field = event.getClass().getDeclaredField("schedules");
        field.setAccessible(true);
        field.set(event, oldSchedules);

        Field field1 = event.getClass().getDeclaredField("eventKey");
        field1.setAccessible(true);
        field1.set(event, "beans");

        Field field2 = event2.getClass().getDeclaredField("schedules");
        field2.setAccessible(true);
        field2.set(event2, currentSchedules);

        Field field22 = event2.getClass().getDeclaredField("eventKey");
        field22.setAccessible(true);
        field22.set(event2, "butt");

        List<Event> events = Arrays.asList(event, event2);

        Collections.sort(events, Event.FIRST_SCHEDULE_DATE_ORDER_DESC);

        assertThat(event.getEventKey(), is("beans"));

    }

    @Test
    public void shouldSortEventsDescWithNoSchedulesWithoutBlowingUp() throws Exception {

        List<Schedule> oldSchedules = new ArrayList<>();
        List<Schedule> currentSchedules = new ArrayList<>();

        Field field = event.getClass().getDeclaredField("schedules");
        field.setAccessible(true);
        field.set(event, oldSchedules);

        Field field1 = event.getClass().getDeclaredField("eventKey");
        field1.setAccessible(true);
        field1.set(event, "beans");

        Field field2 = event2.getClass().getDeclaredField("schedules");
        field2.setAccessible(true);
        field2.set(event2, currentSchedules);

        Field field22 = event2.getClass().getDeclaredField("eventKey");
        field22.setAccessible(true);
        field22.set(event2, "butt");

        List<Event> events = Arrays.asList(event, event2);

        Collections.sort(events, Event.FIRST_SCHEDULE_DATE_ORDER_DESC);

    }
}
