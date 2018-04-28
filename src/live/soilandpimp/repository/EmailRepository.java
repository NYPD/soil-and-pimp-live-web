package live.soilandpimp.repository;

import org.springframework.data.repository.CrudRepository;

import live.soilandpimp.domain.EmailSubscription;

public interface EmailRepository extends CrudRepository<EmailSubscription, String> {

}
