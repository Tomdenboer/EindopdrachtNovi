package com.tomdenboer.composercloud.controller;

import com.tomdenboer.composercloud.model.Song;
import com.tomdenboer.composercloud.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
@RequestMapping("/songs")
public class SongController {

    @Autowired
    private SongService songService;

    /***TODO User moet gekoppeld worden aan song... ***/
    @PostMapping("/")
    public String uploadSong(@RequestParam("song") MultipartFile song, RedirectAttributes redirectAttributes,
                             @RequestParam("artist") String artist, @RequestParam("name") String name) {
        long id = songService.createSong(song, artist, name);

        redirectAttributes.addFlashAttribute("message", "uploaded " + song.getOriginalFilename());
        return "Successfully uploaded song with id: " + id;
    }


}
