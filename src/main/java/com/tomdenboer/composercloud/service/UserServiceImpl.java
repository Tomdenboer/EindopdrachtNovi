package com.tomdenboer.composercloud.service;


import com.tomdenboer.composercloud.model.User;
import com.tomdenboer.composercloud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Collection<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional getUserById(long id) {
        return userRepository.findById(id);
    }

    public long createUser(User user) {
        User newUser = userRepository.save(user);
        return newUser.getId();
    }

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    public long deleteUser(long id) {
        userRepository.deleteById(id);
        return id;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public User updateUser(User newUser, long id) {
        Optional<User> user = userRepository.findById(id);
        user.orElseThrow(() -> new UsernameNotFoundException("We konden de gebruiker met de naam " + newUser.getUserName
                () + " niet vinden."));

        return user.map(u -> {
            u.setUserName(newUser.getUserName());
            return userRepository.save(u);
        })
                .orElseGet(() -> {
                    newUser.setId(id);
                    return userRepository.save(newUser);
                });
    }
}

