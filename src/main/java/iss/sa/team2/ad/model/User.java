package iss.sa.team2.ad.model;

import iss.sa.team2.ad.enums.UserPosition;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class User {
    @Id
    private String id;

    private String account;
    
    private String password;
    
    private UserPosition position;
    
    public User() {
    	
    }
    
    public User(String id, String account, String password,UserPosition position) {
        this.id = id;
        this.account = account;
        this.password = password;
        this.position = position;
    }

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public UserPosition getPosition() {
		return position;
	}

	public void setPosition(UserPosition position) {
		this.position = position;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}
}

