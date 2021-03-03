package com.tomdenboer.composercloud.controller;

import com.tomdenboer.composercloud.model.Playlist;
import com.tomdenboer.composercloud.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/playlists")
public class PlaylistController {

    @Autowired
    private PlaylistService playlistService;

    @GetMapping("")
    public ResponseEntity<Object> getAllPlaylists() {
        return ResponseEntity.ok().body(playlistService.getAllPlaylists());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getPlaylist(@PathVariable("id") long id) {
        return ResponseEntity.ok().body(playlistService.getPlaylistById(id));
    }

    @PostMapping("")
    public ResponseEntity<Object> createPlaylist(@RequestBody Playlist playlist) {
        long newId = playlistService.createPlaylist(playlist);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newId).toUri();

        return ResponseEntity.created(location).body(location);
    }
}
