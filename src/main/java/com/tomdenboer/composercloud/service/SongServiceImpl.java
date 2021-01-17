package com.tomdenboer.composercloud.service;

import com.tomdenboer.composercloud.model.Song;
import com.tomdenboer.composercloud.model.User;
import com.tomdenboer.composercloud.repository.SongRepository;
import javassist.NotFoundException;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
//@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
public class SongServiceImpl implements SongService {

    @Value("${app.upload.dir:${user.home}}")
    public String uploadDir;

    @Autowired
    SongRepository songRepository;

    public Collection<Song> getAllSongs() {
        return songRepository.findAll();
    }

    //TODO: checken for exceptions
    public @ResponseBody byte[] getSongById(long id) throws IOException{
        Optional<Song> optionalSong = songRepository.findById(id);
        Song song = optionalSong.get();

        String location = song.getLocation();
        InputStream i = new FileInputStream(location);
        return IOUtils.toByteArray(i);
    }

    public long createSong(MultipartFile song, String name)  {
        Song newSong;
        Path copyLocation = Paths.get(uploadDir);
        try {
            copyLocation = Paths
                    .get(uploadDir + File.separator + StringUtils.cleanPath(song.getOriginalFilename()));
            Files.copy(song.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);

        } catch (Exception e) {
            e.printStackTrace();
        }
        newSong = new Song(name, copyLocation.toString());
        songRepository.save(newSong);
        return newSong.getId();
    }
}
