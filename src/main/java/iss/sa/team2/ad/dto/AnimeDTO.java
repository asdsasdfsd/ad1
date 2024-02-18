package iss.sa.team2.ad.dto;

import java.time.LocalDateTime;

public class AnimeDTO {

	private Long id;
	private String name;
	private float averageRating;
	private int followed;
	private int favorites;
	private LocalDateTime releaseDate;
	
	public AnimeDTO() {}
	
	public AnimeDTO(Long id, String name, float averageRating, int followed, int favorites, LocalDateTime realeaseDate) {
		this.id = id;
        this.name = name;
        this.averageRating = averageRating;
        this.followed = followed;
        this.favorites = favorites;
        this.releaseDate = releaseDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getAverageRating() {
		return averageRating;
	}

	public void setAverageRating(float averageRating) {
		this.averageRating = averageRating;
	}

	public int getFollowed() {
		return followed;
	}

	public void setFollowed(int followed) {
		this.followed = followed;
	}

	public int getFavorites() {
		return favorites;
	}

	public void setFavorites(int favorites) {
		this.favorites = favorites;
	}

	public LocalDateTime getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(LocalDateTime releaseDate) {
		this.releaseDate = releaseDate;
	}
	
}
