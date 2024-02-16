package iss.sa.team2.ad.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

import iss.sa.team2.ad.enums.Gender;
import iss.sa.team2.ad.enums.UserPosition;
import iss.sa.team2.ad.enums.UserStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class RegularUser extends User {
    
    private Gender gender;
    
    @NotBlank(message = "Motto is required")
    private String motto;
    
    private String userName;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;
    
    @Lob
    private String profileImage; 
    
    private UserStatus status;
    
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_tags",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Usertag> tags = new HashSet<>();
    
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_backgrounds",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "background_id"))
    private Set<Background> backgrounds = new HashSet<>();
    
    @OneToMany(mappedBy = "me", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Friendship> friendships = new HashSet<>();
    
    @OneToMany(mappedBy = "friend", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Friendship> reviseFriendships = new HashSet<>();
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Attendance> attendances = new HashSet<>();

    @OneToMany(mappedBy = "regularUser", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<RegularUserAnime> regularUserAnimes = new HashSet<>();
    
    @OneToMany(mappedBy = "regularUser", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();
    
	public RegularUser() {
        super(); 
    }

	public RegularUser(String id, String account, String password, UserPosition position, Gender gender, String motto, String userName, LocalDate birthday, String profileImage, UserStatus status) {
        super(id,account,password,position);
        this.setGender(gender);
        this.setMotto(motto);
        this.setUserName(userName);
        this.setBirthday(birthday);
        this.setProfileImage(profileImage);
        this.setStatus(status);
    }

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getMotto() {
		return motto;
	}

	public void setMotto(String motto) {
		this.motto = motto;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate localDate) {
		this.birthday = localDate;
	}

	public UserStatus getStatus() {
		return status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}

	public String getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}
	
	public void addTagToUser(Usertag tag) {
        tags.add(tag);
        tag.getRegularUsers().add(this);
    }

    public void removeTagToUser(Usertag tag) {
        tags.remove(tag);
        tag.getRegularUsers().remove(this);
    }
    
    public void addBackgroundToUser(Background background) {
    	backgrounds.add(background);
    	background.getRegularUsers().add(this);
    }

    public void removeBackgroundToUser(Background background) {
    	backgrounds.remove(background);
    	background.getRegularUsers().remove(this);
    }
    
    public void addFriendshipToUser(Friendship friendship) {
    	friendships.add(friendship);
    }

    public void removeFriendshipToUser(Friendship friendship) {
    	friendships.remove(friendship);
    }

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
