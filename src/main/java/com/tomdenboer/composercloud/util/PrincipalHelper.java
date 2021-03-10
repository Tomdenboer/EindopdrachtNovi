package com.tomdenboer.composercloud.util;

import com.tomdenboer.composercloud.exceptions.UsernameNotFoundException;
import com.tomdenboer.composercloud.model.User;
import com.tomdenboer.composercloud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PrincipalHelper {

    @Autowired
    private UserService userService;

    public PrincipalHelper(){}

    public User getCurrentUser() {
        Object o = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> optionalUser = userService.getUserByName(((UserDetails) o).getUsername());

        if(optionalUser.isEmpty()) {
            throw new UsernameNotFoundException();
        } else {
            return optionalUser.get();
        }
    }

    public boolean isIdSameAsUserId(long id) {
        User user = getCurrentUser();
        return user.getId() == id;
    }

    public boolean isAdmin() {
        User user = getCurrentUser();
        boolean isAdmin = false;

        for(int i = 0; i < user.getRoles().size(); i ++) {
            if(user.getRoles().toArray()[i] == "ROLE_ADMIN") {
                isAdmin = true;
            }
        } return isAdmin;
    }
}
