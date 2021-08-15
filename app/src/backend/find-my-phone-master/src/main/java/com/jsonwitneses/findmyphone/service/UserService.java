package com.jsonwitneses.findmyphone.service;

import com.jsonwitneses.findmyphone.dao.UserRepository;
import com.jsonwitneses.findmyphone.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserById(Long id){
        return userRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public  void save(User user){
        userRepository.save(user);
    }

    public void insertUsers(List<User> users) {
        List<User> currUsers = userRepository.findAll();
        for (User user:users) {
            currUsers.stream().filter(o -> o.getId().equals(user.getId())).forEach(
                    usr -> {
                        usr.setDevices(user.getDevices());
                        usr.setName(usr.getName());
                        users.remove(user);
                        userRepository.save(usr);
                    }
            );

        }
        for (User user:users) {
            userRepository.save(new User(user.getId(),user.getName(),user.getDevices()));
        }

    }
}
