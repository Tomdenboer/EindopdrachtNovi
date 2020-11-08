package com.tomdenboer.composercloud.repository;

import com.tomdenboer.composercloud.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository extends JpaRepository<Song, Long> {

}
