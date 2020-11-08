package com.tomdenboer.composercloud.service;

import com.tomdenboer.composercloud.model.Song;
import com.tomdenboer.composercloud.repository.SongRepository;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import sun.nio.ch.IOUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

@Service
public class SongServiceImpl implements SongService {

    @Value("${app.upload.dir:${user.home}}")
    public String uploadDir;

    @Autowired
    SongRepository songRepository;


    public @ResponseBody byte[] getSong(String artist, String name) throws IOException{
        List<Song> list = songRepository.findByArtistAndName(artist, name);
        Song s = list.get(0);
        String location = s.getLocation();
//        InputStream i = getClass().getResourceAsStream(location);
        InputStream i = new FileInputStream(location);
        return IOUtils.toByteArray(i);
    }

    public long createSong(MultipartFile song, String artist, String name)  {
        Song newSong;

        /* Have to init something here, else it wont work */
        Path copyLocation = Paths.get(uploadDir);
        try {
            copyLocation = Paths
                    .get(uploadDir + File.separator + StringUtils.cleanPath(song.getOriginalFilename()));
            Files.copy(song.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);

        } catch (Exception e) {
            e.printStackTrace();
        }
        newSong = new Song(artist, name, copyLocation.toString());
        songRepository.save(newSong);
        return newSong.getId();
    }
}
