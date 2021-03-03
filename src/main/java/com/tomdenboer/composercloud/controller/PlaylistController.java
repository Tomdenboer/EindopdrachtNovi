package com.tomdenboer.composercloud.controller;

import com.tomdenboer.composercloud.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/playlists")
public class PlaylistController {

    @Autowired
    private PlaylistService playlistService;

    @GetMapping("")
    public ResponseEntity<Object> getAllPlaylists() {
        return ResponseEntity.ok().body(playlistService.getAllPlaylists());
    }
}
