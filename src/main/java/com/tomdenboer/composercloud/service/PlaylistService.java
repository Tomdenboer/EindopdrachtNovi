package com.tomdenboer.composercloud.service;

import com.tomdenboer.composercloud.model.Playlist;

import java.util.Collection;
import java.util.Optional;

public interface PlaylistService {
    public Collection<Playlist> getAllPlaylists();
    public Optional<Playlist> getPlaylistById(long id);

    long createPlaylist(Playlist playlist);
}
