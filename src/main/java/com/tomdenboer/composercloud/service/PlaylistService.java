package com.tomdenboer.composercloud.service;

import com.tomdenboer.composercloud.model.Playlist;

import java.util.Collection;
import java.util.Optional;

public interface PlaylistService {
    Collection<Playlist> getAllPlaylists();
    Optional<Playlist> getPlaylistById(long id);
    long createPlaylist(Playlist playlist);
    Playlist updatePlaylist(Playlist playlist, long id);
    long deletePlaylist(long id);
}
