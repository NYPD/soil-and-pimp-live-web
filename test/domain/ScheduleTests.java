package domain;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import live.soilandpimp.domain.Schedule;

public class ScheduleTests {

    @Test
    public void shouldSortSchedulesByDate() {

        Schedule earlySchedule = mock(Schedule.class);
        when(earlySchedule.getDate()).thenReturn(LocalDate.of(2018, 1, 1));

        Schedule middleSchedule = mock(Schedule.class);
        when(middleSchedule.getDate()).thenReturn(LocalDate.of(2018, 1, 2));

        Schedule lateSchedule = mock(Schedule.class);
        when(lateSchedule.getDate()).thenReturn(LocalDate.of(2018, 1, 3));

        List<Schedule> schedules = new ArrayList<>(Arrays.asList(middleSchedule, earlySchedule, lateSchedule));

        Collections.sort(schedules, Schedule.DATE_ORDER);

        assertThat(schedules.get(0).getDate(), is(earlySchedule.getDate()));
        assertThat(schedules.get(schedules.size() - 1).getDate(), is(lateSchedule.getDate()));

    }

}
