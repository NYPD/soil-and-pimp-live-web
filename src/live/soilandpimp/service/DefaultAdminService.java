package live.soilandpimp.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import live.soilandpimp.domain.User;
import live.soilandpimp.domain.enums.ApiType;
import live.soilandpimp.repository.UsersRepository;

@Service
public class DefaultAdminService implements AdminService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public Collection<User> getAllUsers() {

        ArrayList<User> users = new ArrayList<User>();
        usersRepository.findAll().forEach(user -> users.add(user));

        return users;
    }

    @Override
    public User getUser(int id) {
        return usersRepository.findById(id).orElse(null);
    }

    @Override
    public User getUser(ApiType apiType, String apiUserId) {
        return usersRepository.findByApitIndentity(apiType, apiUserId);
    }

}
