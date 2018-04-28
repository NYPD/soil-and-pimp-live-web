package live.soilandpimp.repository;

import org.springframework.data.repository.CrudRepository;

import live.soilandpimp.domain.Event;

public interface EventsRepository extends CrudRepository<Event, String> {

}
