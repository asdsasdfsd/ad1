package iss.sa.team2.ad.repository;

import iss.sa.team2.ad.model.Blacklist;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BlacklistRepository extends JpaRepository<Blacklist, Integer> {

    @Query("SELECT b FROM Blacklist b WHERE b.words LIKE %:words%")
	List<Blacklist> findByNameContaining(@Param("words") String words);

}
