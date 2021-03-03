package com.tomdenboer.composercloud.service;

import com.tomdenboer.composercloud.model.Playlist;
import com.tomdenboer.composercloud.repository.PlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class PlaylistServiceImpl implements PlaylistService{

    @Autowired
    private PlaylistRepository playlistRepository;

    @Override
    public Collection<Playlist> getAllPlaylists() { return playlistRepository.findAll(); }
}
