package com.tomdenboer.composercloud.service;

import com.tomdenboer.composercloud.model.Song;
import org.springframework.web.multipart.MultipartFile;

public interface SongService {

    public long createSong(MultipartFile song, String artist, String name);
}
