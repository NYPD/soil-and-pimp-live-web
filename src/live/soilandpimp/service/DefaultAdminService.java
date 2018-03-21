package live.soilandpimp.service;

import java.util.Collection;

import org.springframework.stereotype.Service;

import live.soilandpimp.domain.User;
import live.soilandpimp.domain.enums.ApiType;

@Service
public class DefaultAdminService implements AdminService {

    @Override
    public Collection<User> getAllUsers() {
        return null;
    }

    @Override
    public User getUser(int userId) {
        return null;
    }

    @Override
    public User getUser(ApiType apiType, String apiUserId) {
        return null;
    }

}
