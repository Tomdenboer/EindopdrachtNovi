package com.tomdenboer.composercloud.repository;

import com.tomdenboer.composercloud.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
//TODO: find by artist -> find by id
public interface SongRepository extends JpaRepository<Song, Long> {
//    public List<Song> findByArtistAndName(String artist, String name);
    public Optional<Song> findById(long id);

}
