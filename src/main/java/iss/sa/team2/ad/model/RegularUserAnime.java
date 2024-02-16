package iss.sa.team2.ad.model;

import java.time.LocalDateTime;
import java.util.Objects;

import iss.sa.team2.ad.enums.MyType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class RegularUserAnime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private RegularUser regularUser;

    @ManyToOne
    private Anime anime;
    
    private float rating;
    
    private String name;
    
    private LocalDateTime time;
    
    private MyType type;
    
    private String description;
    
    // Constructors
    public RegularUserAnime() {
    }

    public RegularUserAnime(RegularUser regularUser, Anime anime, String name, LocalDateTime time, MyType type, String description) {
        this.regularUser = regularUser;
        this.anime = anime;
        this.name = name;
        this.time = time;
        this.type = type;
        this.description = description;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RegularUser getRegularUser() {
        return regularUser;
    }

    public void setRegularUser(RegularUser regularUser) {
        this.regularUser = regularUser;
    }

    public Anime getAnime() {
        return anime;
    }

    public void setAnime(Anime anime) {
        this.anime = anime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public MyType getType() {
        return type;
    }

    public void setType(MyType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // hashCode() method
    @Override
    public int hashCode() {
        return Objects.hash(regularUser, type, anime, name, time);
    }

    // equals() method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegularUserAnime that = (RegularUserAnime) o;
        return Objects.equals(regularUser, that.regularUser) &&
                type == that.type &&
                Objects.equals(anime, that.anime) &&
                Objects.equals(name, that.name) &&
                Objects.equals(time, that.time);
    }

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}
}
