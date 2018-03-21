package live.soilandpimp.repository;

import java.util.UUID;

import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import live.soilandpimp.domain.User;
import live.soilandpimp.domain.enums.ApiType;

public interface UsersRepository extends CrudRepository<User, UUID> {

    @Query("SELECT * FROM USERS WHERE api_identities[:apiType] = :apiUserId")
    public User findByApitIndentity(@Param("apiType") ApiType apiType, @Param("apiUserId") String apiUserId);
}
