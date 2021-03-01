package com.tomdenboer.composercloud.service;


import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.tomdenboer.composercloud.model.User;
import com.tomdenboer.composercloud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import com.tomdenboer.composercloud.exceptions.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Collection<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(long id) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException();
        } else {
            return optionalUser;
        }
    }

    public Optional<User> getUserByName(String name) {
        Optional<User> optionalUser = userRepository.findByUserName(name);

        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException(name);
        } else {
            return optionalUser;
        }
    }

    @PreAuthorize("permitAll()")
    public long createUser(User user) {
        Optional<User> optionalUser = userRepository.findByUserName(user.getUserName());

        if (optionalUser.isPresent() && optionalUser.get().getUserName().equals(user.getUserName())) {
            throw new UsernameExistsException(user.getUserName());
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            User newUser = userRepository.save(user);
            return newUser.getId();
        }
    }

    public long deleteUser(long id) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException();
        } else {
            userRepository.deleteById(id);
        }
        return id;
    }

    public User updateUser(User newUser, long id) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException();
        } else {
            newUser.setId(optionalUser.get().getId());
            return userRepository.save(newUser);
        }
    }
}

