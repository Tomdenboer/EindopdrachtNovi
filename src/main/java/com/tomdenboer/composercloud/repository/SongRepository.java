package com.tomdenboer.composercloud.repository;

import com.tomdenboer.composercloud.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SongRepository extends JpaRepository<Song, Long> {
//    @Query("SELECT DISTINCT s.location FROM song s WHERE s.artist = artist &")
    public List<Song> findByArtistAndName(String artist, String name);

}
