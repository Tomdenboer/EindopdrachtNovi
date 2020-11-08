package com.tomdenboer.composercloud.service;

import com.tomdenboer.composercloud.model.User;

import java.util.Collection;
import java.util.Optional;

public interface UserService {

    public Collection<User> getAllUsers();
    public Optional<User> getUserById(long id);
    public long createUser(User user);
    public long deleteUser(long id);
}
