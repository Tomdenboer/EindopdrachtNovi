package com.tomdenboer.composercloud.service;

import com.tomdenboer.composercloud.model.MyUserDetails;
import com.tomdenboer.composercloud.model.User;
import com.tomdenboer.composercloud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUserName(name);

        user.orElseThrow(() -> new UsernameNotFoundException("We konden de gebruiker met de naam " + name + " niet vinden."));

        return user.map(MyUserDetails::new).get();
    }
}
