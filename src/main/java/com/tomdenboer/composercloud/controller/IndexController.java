package com.tomdenboer.composercloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "Welkom bij Composer Cloud!";
    }
//TODO: REMOVE

//    @GetMapping("/usertest")
//    public String user() {
//        return "user!";
//    }
//
//    @GetMapping("/admintest")
//    public String admin() {
//        return "user!";
//    }
}
