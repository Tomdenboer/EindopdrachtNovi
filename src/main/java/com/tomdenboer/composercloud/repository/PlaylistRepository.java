package com.tomdenboer.composercloud.repository;

import com.tomdenboer.composercloud.model.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
}
