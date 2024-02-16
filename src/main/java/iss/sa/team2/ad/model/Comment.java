package iss.sa.team2.ad.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private RegularUser regularUser;

    @ManyToOne
    private Anime anime;

    private String content;
    
    private LocalDateTime comment_time;

    public Comment() {
    }

    // Constructor
    public Comment(RegularUser regularUser, Anime anime, String content, LocalDateTime comment_time) {
        this.regularUser = regularUser;
        this.anime = anime;
        this.content = content;
        this.comment_time = comment_time;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getComment_time() {
        return comment_time;
    }

    public void setComment_time(LocalDateTime comment_time) {
        this.comment_time = comment_time;
    }
}
