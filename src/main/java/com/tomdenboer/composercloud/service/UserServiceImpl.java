package com.tomdenboer.composercloud.service;


import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.tomdenboer.composercloud.model.User;
import com.tomdenboer.composercloud.repository.UserRepository;
import com.tomdenboer.composercloud.util.PrincipalHelper;
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

    @Autowired
    private final PrincipalHelper principalHelper;

    public UserServiceImpl() { this.principalHelper = new PrincipalHelper(); }

    public Collection<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(long id) {
        Optional<User> requestedUser = userRepository.findById(id);

        if (requestedUser.isEmpty()) {
            throw new UsernameNotFoundException();
        } else {
            return requestedUser;
        }
    }

    public Optional<User> getUserByName(String name) {
        Optional<User> requestedUser = userRepository.findByUserName(name);

        if (requestedUser.isEmpty()) {
            throw new UsernameNotFoundException(name);
        } else {
            return requestedUser;
        }
    }

    @PreAuthorize("permitAll()")
    public long createUser(User user) {
        Optional<User> userThatAlreadyExists = userRepository.findByUserName(user.getUserName());

        if (userThatAlreadyExists.isPresent() && userThatAlreadyExists.get().getUserName().equals(user.getUserName())) {
            throw new UsernameExistsException(user.getUserName());
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            User newUser = userRepository.save(user);
            return newUser.getId();
        }
    }

    public User updateUser(User newUser, long id) {
        Optional<User> userToUpdate = userRepository.findById(id);

        if (userToUpdate.isEmpty()) {
            throw new UsernameNotFoundException();
        } else if (!principalHelper.isIdSameAsUserId(userToUpdate.get().getId()) && !principalHelper.isAdmin()) {
            throw new NotAllowedException();
        } else {
            newUser.setId(userToUpdate.get().getId());
            return userRepository.save(newUser);
        }
    }

    public long deleteUser(long id) {
        Optional<User> userToDelete = userRepository.findById(id);

        if (userToDelete.isEmpty()) {
            throw new UsernameNotFoundException();
        } else if (!principalHelper.isIdSameAsUserId(userToDelete.get().getId()) && !principalHelper.isAdmin()) {
            throw new NotAllowedException();
        } else {
            userRepository.deleteById(id);
        }
        return id;
    }
}

