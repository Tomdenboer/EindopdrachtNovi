package com.tomdenboer.composercloud.service;


import com.tomdenboer.composercloud.model.User;
import com.tomdenboer.composercloud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    public Collection<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional getUserById(long id) {
        return userRepository.findById(id);
    }

    public long createUser(User user){
       User newUser = userRepository.save(user);
        return newUser.getId();
    }

    public long deleteUser(long id) {
        userRepository.deleteById(id);
        return id;
    }
}
