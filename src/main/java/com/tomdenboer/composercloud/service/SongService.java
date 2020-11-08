package com.tomdenboer.composercloud.service;

import com.tomdenboer.composercloud.model.Song;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public interface SongService {

    public long createSong(MultipartFile song, String artist, String name);
    public @ResponseBody byte[] getSong(String artist, String name) throws IOException;
}
