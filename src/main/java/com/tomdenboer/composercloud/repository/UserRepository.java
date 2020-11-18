package com.tomdenboer.composercloud.repository;

import com.tomdenboer.composercloud.model.Song;
import com.tomdenboer.composercloud.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserName(String name);
}
