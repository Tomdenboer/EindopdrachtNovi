package com.tomdenboer.composercloud.service;

import com.tomdenboer.composercloud.model.Song;
import com.tomdenboer.composercloud.model.User;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

public interface SongService {

    public Collection<Song> getAllSongs();
    public @ResponseBody byte[] getSongById(long id) throws IOException;
    public long createSong(MultipartFile song, String name);
}
