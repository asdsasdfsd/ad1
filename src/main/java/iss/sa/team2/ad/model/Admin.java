package iss.sa.team2.ad.model;

import jakarta.persistence.Entity;
import iss.sa.team2.ad.enums.UserPosition;

@Entity
public class Admin extends User {
	public Admin() {
        super(); 
    }

    public Admin(String id,String account, String password,UserPosition position) {
    	super(id,account,password,position);      
    }
}
