package iss.sa.team2.ad.repository;

import iss.sa.team2.ad.model.Anime;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimeRepository extends JpaRepository<Anime, Long> {
	// 按平均评分降序查询前20个动漫
    @Query(value = "SELECT * FROM anime ORDER BY average_rating DESC LIMIT 20", nativeQuery = true)
    List<Anime> findTop20ByOrderByAverageRatingDesc();
    
    // 按平均评分降序查询前6个动漫
    @Query(value = "SELECT * FROM anime ORDER BY average_rating DESC LIMIT 6", nativeQuery = true)
    List<Anime> findTop6ByOrderByAverageRatingDesc();
    
    // 按发布日期降序查询前20个动漫
    @Query(value = "SELECT * FROM anime ORDER BY release_date DESC LIMIT 20", nativeQuery = true)
    List<Anime> findTop20ByOrderByReleaseDateDesc();
    
    // 按发布日期降序查询前6个动漫
    @Query(value = "SELECT * FROM anime ORDER BY release_date DESC LIMIT 6", nativeQuery = true)
    List<Anime> findTop6ByOrderByReleaseDateDesc();
    
    @Query(value = "SELECT * FROM anime ORDER BY average_rating DESC", nativeQuery = true)
    List<Anime> findAllByOrderByAverageRatingDesc();
    
    @Query(value = "SELECT * FROM anime ORDER BY release_date DESC", nativeQuery = true)
    List<Anime> findAllByOrderByReleaseDateDesc();
}
