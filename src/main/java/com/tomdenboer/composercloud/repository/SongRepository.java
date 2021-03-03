package com.tomdenboer.composercloud.repository;

import com.tomdenboer.composercloud.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SongRepository extends JpaRepository<Song, Long> {
    public Optional<Song> findById(long id);

}
