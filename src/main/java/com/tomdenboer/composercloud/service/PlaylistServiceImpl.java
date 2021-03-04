package com.tomdenboer.composercloud.service;

import com.tomdenboer.composercloud.exceptions.PlaylistNotFoundException;
import com.tomdenboer.composercloud.exceptions.UsernameNotFoundException;
import com.tomdenboer.composercloud.model.Playlist;
import com.tomdenboer.composercloud.model.Song;
import com.tomdenboer.composercloud.model.User;
import com.tomdenboer.composercloud.repository.PlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class PlaylistServiceImpl implements PlaylistService{

    @Autowired
    private PlaylistRepository playlistRepository;

    @Autowired
    private UserService userService;

    @Override
    public Collection<Playlist> getAllPlaylists() { return playlistRepository.findAll(); }

    @Override
    public Optional<Playlist> getPlaylistById(long id) {
        Optional<Playlist> optionalPlaylist = playlistRepository.findById(id);

        if (optionalPlaylist.isEmpty()) {
            throw new PlaylistNotFoundException(id);
        } else {
            return optionalPlaylist;
        }
    }

    @Override
    public long createPlaylist(Playlist playlist) {
        Object o = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> optionalUser = userService.getUserByName(((UserDetails) o).getUsername());

        if(optionalUser.isEmpty()) {
          throw new UsernameNotFoundException();
        }
        else {
            playlist.setUser(optionalUser.get());
        }
        Playlist newPlaylist = playlistRepository.save(playlist);
        return newPlaylist.getId();
    }

    public Playlist updatePlaylist(Playlist newPlaylist, long id) {
        Optional<Playlist> optionalPlaylist = playlistRepository.findById(id);

        if (optionalPlaylist.isEmpty()) {
            throw new PlaylistNotFoundException();
        } else {
            newPlaylist.setId(optionalPlaylist.get().getId());
            return playlistRepository.save(newPlaylist);
        }
    }

    public long deletePlaylist(long id) {
        Optional<Playlist> optionalPlaylist = playlistRepository.findById(id);

        if (optionalPlaylist.isEmpty()) {
            throw new PlaylistNotFoundException();
        } else {
            playlistRepository.deleteById(id);
        }
        return id;
    }
}
