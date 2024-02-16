package iss.sa.team2.ad.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
public class Anime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    
    private String cover;
    
    @Lob
    @Column(columnDefinition="TEXT")
    private String description;
    
    private String videoPlaybackSource;
    
    private int clicks;
    
    private float averageRating;
    
    private LocalDateTime releaseDate;
    
    private int episodes;
    
    private String director;
    
    private String script;
    
    private String characterDesign;
    
    private String sakugaDirector;
    
    private String productionCompany;

    @OneToMany(mappedBy = "anime", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<RegularUserAnime> regularUserAnimes = new ArrayList<>();
    
    @OneToMany(mappedBy = "anime", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Comment> comments = new ArrayList<>();
    
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "animes_tags",
            joinColumns = @JoinColumn(name = "anime_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Animetag> tags = new HashSet<>();
   
    // Constructors
    public Anime() {
    }

    public Anime(String name, String cover, String description, String videoPlaybackSource, int clicks, float averageRating, LocalDateTime releaseDate, int episodes, String director, String script, String characterDesign, String sakugaDirector, String productionCompany) {
        this.name = name;
        this.cover = cover;
        this.description = description;
        this.videoPlaybackSource = videoPlaybackSource;
        this.clicks = clicks;
        this.averageRating = averageRating;
        this.releaseDate = releaseDate;
        this.episodes = episodes;
        this.director = director;
        this.script = script;
        this.characterDesign = characterDesign;
        this.sakugaDirector = sakugaDirector;
        this.productionCompany = productionCompany;
    }

    // Getters and setters
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

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVideoPlaybackSource() {
        return videoPlaybackSource;
    }

    public void setVideoPlaybackSource(String videoPlaybackSource) {
        this.videoPlaybackSource = videoPlaybackSource;
    }

    public int getClicks() {
        return clicks;
    }

    public void setClicks(int clicks) {
        this.clicks = clicks;
    }

    public float getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(float averageRating) {
        this.averageRating = averageRating;
    }

    public LocalDateTime getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDateTime releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getEpisodes() {
        return episodes;
    }

    public void setEpisodes(int episodes) {
        this.episodes = episodes;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }

    public String getCharacterDesign() {
        return characterDesign;
    }

    public void setCharacterDesign(String characterDesign) {
        this.characterDesign = characterDesign;
    }

    public String getSakugaDirector() {
        return sakugaDirector;
    }

    public void setSakugaDirector(String sakugaDirector) {
        this.sakugaDirector = sakugaDirector;
    }

    public String getProductionCompany() {
        return productionCompany;
    }

    public void setProductionCompany(String productionCompany) {
        this.productionCompany = productionCompany;
    }

    public List<RegularUserAnime> getRegularUserAnimes() {
        return regularUserAnimes;
    }

    public void setRegularUserAnimes(List<RegularUserAnime> regularUserAnimes) {
        this.regularUserAnimes = regularUserAnimes;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Set<Animetag> getTags() {
        return tags;
    }

    public void setTags(Set<Animetag> tags) {
        this.tags = tags;
    }
}