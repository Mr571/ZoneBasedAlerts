package model;

import java.util.*;
import controllers.*;

public class User
{
    private String username;
    private String password;
    private int ID;
    private boolean admin;
    
    public User() {}

    public User(String username)
    {
        this.username = username;
    }

    public User(String username, int ID, boolean admin) {
        this.username = username;
        this.ID = ID;
        this.admin = admin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


}

