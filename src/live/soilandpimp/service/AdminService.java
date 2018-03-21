package live.soilandpimp.service;

import java.util.Collection;
import java.util.UUID;

import live.soilandpimp.domain.User;
import live.soilandpimp.domain.enums.ApiType;

public interface AdminService {

    public Collection<User> getAllUsers();

    public User getUser(UUID userId);
    public User getUser(ApiType apiType, String apiUserId);
}
