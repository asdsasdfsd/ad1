package iss.sa.team2.ad.repository;

import iss.sa.team2.ad.model.Animetag;
import iss.sa.team2.ad.model.Blacklist;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimetagRepository extends JpaRepository<Animetag, Long> {

    @Query("Select t from Animetag t where t.tagName like %:tagName%")
    List<Animetag> findByNameContaining(@Param("tagName") String tagName);
//    
//    @Query("SELECT COUNT(a) FROM Anime a JOIN a.tags t WHERE t.id = :animetagId")
//    int countAnimesByAnimetagId(@Param("animetagId") Long animetagId);

}
