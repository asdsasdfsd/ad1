package iss.sa.team2.ad.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Usertag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tagName;

    @ManyToMany(mappedBy = "tags")
    private List<RegularUser> regularUsers = new ArrayList<>();

    public Usertag() {
    }

    public Usertag(String tagName) {
        this.setTagName(tagName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tagName); 
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Usertag otherTag = (Usertag) obj;
        return Objects.equals(tagName, otherTag.tagName);
    }
    
	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public List<RegularUser> getRegularUsers() {
		return regularUsers;
	}

	public void setRegularUsers(List<RegularUser> regularUsers) {
		this.regularUsers = regularUsers;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}    
}
