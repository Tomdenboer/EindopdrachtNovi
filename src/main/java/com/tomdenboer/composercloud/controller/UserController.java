package com.tomdenboer.composercloud.controller;

import com.tomdenboer.composercloud.model.User;
import com.tomdenboer.composercloud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("")
    public ResponseEntity<Object> getAllUsers() {
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUser(@PathVariable("id") long id ) {
        return ResponseEntity.ok().body(userService.getUserById(id));
    }

    @PostMapping("")
    public ResponseEntity<Object> createUser(@RequestBody User user){
        long newId = userService.createUser(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newId).toUri();

        return ResponseEntity.created(location).body(location);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
