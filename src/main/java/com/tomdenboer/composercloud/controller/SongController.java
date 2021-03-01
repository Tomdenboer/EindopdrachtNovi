package com.tomdenboer.composercloud.controller;

import com.tomdenboer.composercloud.model.Song;
import com.tomdenboer.composercloud.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@RestController
@RequestMapping("/songs")
public class SongController {

    @Autowired
    private SongService songService;

    @GetMapping("")
    public ResponseEntity<Object> getAllSongs() {
        return ResponseEntity.ok().body(songService.getAllSongs());
    }

    @GetMapping(value = "/{id}",
                produces = "audio/mp3")
    public @ResponseBody byte[] getSong(@PathVariable("id") long id) throws IOException {

        return songService.getSongById(id);

    }

    /***TODO User moet gekoppeld worden aan song... ***/
    @PostMapping("")
    public String uploadSong(@RequestParam("song") MultipartFile song, RedirectAttributes redirectAttributes,
                             @RequestParam("title") String title) {
        long id = songService.createSong(song, title);

        redirectAttributes.addFlashAttribute("message", "uploaded " + song.getOriginalFilename());
        return "Successfully uploaded song with id: " + id;
    }


}
