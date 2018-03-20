package live.soilandpimp.service;

import java.util.List;

import live.soilandpimp.domain.Event;
import live.soilandpimp.model.HomeEvents;

public interface EventService {

    public HomeEvents getHomeEvents();

    public List<Event> getPastEvents();
}
