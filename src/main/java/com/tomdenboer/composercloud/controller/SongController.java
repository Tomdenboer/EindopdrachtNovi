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

<<<<<<< HEAD
=======

>>>>>>> cdad5cd6d26c3e8c1befb35a087d4be1f0d4e690

    @GetMapping(value = "",
                produces = "audio/mp3")
    public @ResponseBody byte[] getSong(@RequestParam("artist") String artist, @RequestParam("name") String name) throws IOException {

        return songService.getSong(artist, name);

    }



    /***TODO User moet gekoppeld worden aan song... ***/

<<<<<<< HEAD
    @PostMapping("/")
=======
    @PostMapping("")
>>>>>>> cdad5cd6d26c3e8c1befb35a087d4be1f0d4e690
    public String uploadSong(@RequestParam("song") MultipartFile song, RedirectAttributes redirectAttributes,
                             @RequestParam("artist") String artist, @RequestParam("name") String name) {
        long id = songService.createSong(song, artist, name);

        redirectAttributes.addFlashAttribute("message", "uploaded " + song.getOriginalFilename());
        return "Successfully uploaded song with id: " + id;
    }


}
