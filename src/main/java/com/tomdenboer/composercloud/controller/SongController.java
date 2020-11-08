package com.tomdenboer.composercloud.controller;

import com.tomdenboer.composercloud.model.Song;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/songs")
public class SongController {


    @PostMapping("/")
    public ResponseEntity<Song> uploadSong() {

    }



}
