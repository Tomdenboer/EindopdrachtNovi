package com.tomdenboer.composercloud.service;

import com.tomdenboer.composercloud.exceptions.NotAllowedException;
import com.tomdenboer.composercloud.exceptions.PlaylistNotFoundException;
import com.tomdenboer.composercloud.model.Playlist;
import com.tomdenboer.composercloud.model.User;
import com.tomdenboer.composercloud.repository.PlaylistRepository;
import com.tomdenboer.composercloud.util.PrincipalHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class PlaylistServiceImpl implements PlaylistService {

    @Autowired
    private final PrincipalHelper principalHelper;

    @Autowired
    private PlaylistRepository playlistRepository;

    public PlaylistServiceImpl() {
        this.principalHelper = new PrincipalHelper();
    }

    @Override
    public Collection<Playlist> getAllPlaylists() {
        return playlistRepository.findAll();
    }

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
        User currentUser = principalHelper.getCurrentUser();
        playlist.setUser(currentUser);

        Playlist newPlaylist = playlistRepository.save(playlist);
        return newPlaylist.getId();
    }

    public Playlist updatePlaylist(Playlist newPlaylist, long id) {
        Optional<Playlist> optionalPlaylist = playlistRepository.findById(id);
        User currentUser = principalHelper.getCurrentUser();

        if (optionalPlaylist.isEmpty()) {
            throw new PlaylistNotFoundException();
        } else {
            long playlistUserId = optionalPlaylist.get().getUser().getId();

            if (!principalHelper.isIdSameAsUserId(playlistUserId) && !principalHelper.isAdmin()) {
                throw new NotAllowedException();
            } else {
                newPlaylist.setUser(currentUser);
                newPlaylist.setId(id);
                return playlistRepository.save(newPlaylist);
            }
        }
    }

    public long deletePlaylist(long id) {
        Optional<Playlist> optionalPlaylist = playlistRepository.findById(id);

        if (optionalPlaylist.isEmpty()) {
            throw new PlaylistNotFoundException();
        } else if (principalHelper.isIdSameAsUserId(optionalPlaylist.get().getUser().getId()) || principalHelper.isAdmin()) {
            playlistRepository.deleteById(id);
        }
        return id;
    }
}
