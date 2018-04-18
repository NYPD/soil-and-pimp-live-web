package live.soilandpimp.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import live.soilandpimp.domain.User;
import live.soilandpimp.domain.enums.ApiType;

public interface UsersRepository extends CrudRepository<User, Integer> {

    @Query("SELECT new live.soilandpimp.domain.User(u.userId, u.nickname, u.userRole)"
           + " FROM User u"
           + " INNER JOIN UserApiIdentity i ON u.userId = i.userId"
           + " WHERE i.apiUserId = :apiUserId"
           + " AND i.apiType = :apiType")
    public User findByApitIndentity(@Param("apiType") ApiType apiType, @Param("apiUserId") String apiUserId);
}
