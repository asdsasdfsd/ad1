package iss.sa.team2.ad.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Friendship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private RegularUser me;
    
    @ManyToOne
    private RegularUser friend;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public RegularUser getMe() {
		return me;
	}

	public void setMe(RegularUser me) {
		this.me = me;
	}

	public RegularUser getFriend() {
		return friend;
	}

	public void setFriend(RegularUser friend) {
		this.friend = friend;
	}


}
