package domain;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import live.soilandpimp.domain.Event;
import live.soilandpimp.domain.Schedule;

public class EventTests {

    private static Event event;

    @SuppressWarnings("unchecked")
    @BeforeClass
    public static void setUp() throws Exception {

        Constructor<Event> constructor = (Constructor<Event>) Event.class.getDeclaredConstructors()[0];
        constructor.setAccessible(true);

        event = constructor.newInstance();
    }

    @Test
    public void eventShouldBeActive() throws Exception {

        LocalDate currentDate = LocalDate.now();

        Schedule earlySchedule = mock(Schedule.class);
        when(earlySchedule.getDate()).thenReturn(LocalDate.of(currentDate.getYear(),
                                                              currentDate.getMonth(),
                                                              currentDate.getDayOfMonth()));

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
        when(earlySchedule.getDate()).thenReturn(LocalDate.of(currentDate.getYear() + 1,
                                                              currentDate.getMonth(),
                                                              currentDate.getDayOfMonth()));

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
        when(earlySchedule.getDate()).thenReturn(LocalDate.of(currentDate.getYear() - 1,
                                                              currentDate.getMonth(),
                                                              currentDate.getDayOfMonth()));

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
        when(pastSchedule.getDate()).thenReturn(LocalDate.of(currentDate.getYear() - 1,
                                                             currentDate.getMonth(),
                                                             currentDate.getDayOfMonth()));
        Schedule activeSchedule = mock(Schedule.class);
        when(activeSchedule.getDate()).thenReturn(LocalDate.of(currentDate.getYear(),
                                                               currentDate.getMonth(),
                                                               currentDate.getDayOfMonth()));

        List<Schedule> schedules = new ArrayList<>(Arrays.asList(pastSchedule, activeSchedule));

        Field field = event.getClass().getDeclaredField("schedules");
        field.setAccessible(true);
        field.set(event, schedules);

        assertThat(event.isEventActive(), is(true));
        assertThat(event.getActiveSchedule(), is(activeSchedule));
    }
}
