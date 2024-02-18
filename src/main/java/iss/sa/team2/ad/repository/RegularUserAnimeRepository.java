package iss.sa.team2.ad.repository;

import iss.sa.team2.ad.enums.MyType;
import iss.sa.team2.ad.model.Anime;
import iss.sa.team2.ad.model.RegularUserAnime;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RegularUserAnimeRepository extends JpaRepository<RegularUserAnime, Long> {
	@Query("SELECT COUNT(ra) FROM RegularUserAnime ra WHERE ra.anime = :anime AND ra.type = iss.sa.team2.ad.enums.MyType.Subscription")
	int countFollowedByAnime(@Param("anime") Anime anime);

	@Query("SELECT COUNT(ra) FROM RegularUserAnime ra WHERE ra.anime = :anime AND ra.type = iss.sa.team2.ad.enums.MyType.Collection_Content")
	int countFavoritesByAnime(@Param("anime") Anime anime);
	
	@Query("SELECT rua FROM RegularUserAnime rua WHERE rua.regularUser.id = :userId AND rua.type = :type")
    List<RegularUserAnime> findAllByUserIdAndType(@Param("userId") String userId, @Param("type") MyType type);
}