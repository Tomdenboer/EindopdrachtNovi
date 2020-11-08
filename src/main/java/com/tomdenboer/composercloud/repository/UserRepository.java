package com.tomdenboer.composercloud.repository;

import com.tomdenboer.composercloud.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
