package iss.sa.team2.ad.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;

@Entity
public class Background {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Lob
    private String photo;

    @ManyToMany(mappedBy = "backgrounds")
    private List<RegularUser> regularUsers = new ArrayList<>();

    public Background() {
    }

    public Background(String name, String photo) {
        this.setName(name);
        this.setPhoto(photo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getPhoto()); 
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Background otherTag = (Background) obj;
        return Objects.equals(getName(), otherTag.getName());
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
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
