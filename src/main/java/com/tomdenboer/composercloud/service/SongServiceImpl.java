package com.tomdenboer.composercloud.service;

import com.tomdenboer.composercloud.exceptions.SongNotFoundException;
import com.tomdenboer.composercloud.exceptions.UsernameNotFoundException;
import com.tomdenboer.composercloud.model.MyUserDetails;
import com.tomdenboer.composercloud.model.Song;
import com.tomdenboer.composercloud.model.User;
import com.tomdenboer.composercloud.repository.SongRepository;
import com.tomdenboer.composercloud.util.PrincipalHelper;
import javassist.NotFoundException;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.text.html.Option;
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
import java.util.Objects;
import java.util.Optional;

@Service
//@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
public class SongServiceImpl implements SongService {

    @Value("${app.upload.dir:${user.home}}")
    public String uploadDir;

    @Autowired
    SongRepository songRepository;

    @Autowired
    MyUserDetailsService userDetailsService;

    @Autowired
    UserService userService;

    @Autowired
    private final PrincipalHelper principalHelper;

    public SongServiceImpl() { this.principalHelper = new PrincipalHelper(); }

    public Collection<Song> getAllSongs() {
        return songRepository.findAll();
    }

    public @ResponseBody byte[] getSongById(long id) throws IOException {
        Optional<Song> optionalSong = songRepository.findById(id);

        if (optionalSong.isEmpty()) {
            throw new SongNotFoundException(id);
        } else {
            Song song = optionalSong.get();
            String location = song.getLocation();
            InputStream i = new FileInputStream(location);
            return IOUtils.toByteArray(i);
        }
    }

    public Optional<Song> getSongDataById(long id) {
        return songRepository.findById(id);
    }

    public long createSong(MultipartFile song, String title) {
        Song newSong;
        User user = principalHelper.getCurrentUser();
        Path copyLocation = Paths.get(uploadDir);

        try {
            copyLocation = Paths
                .get(uploadDir + File.separator + StringUtils.cleanPath(Objects.requireNonNull(song.getOriginalFilename())));
            Files.copy(song.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            e.printStackTrace();
        }
        newSong = new Song(title, copyLocation.toString(), user);
        songRepository.save(newSong);
        return newSong.getId();
    }
}
