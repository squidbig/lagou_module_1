package com.foton.pojo;

public class User {

    private int u_id;
    private String username;



    public int getU_id() {
		return u_id;
	}

	public void setU_id(int u_id) {
		this.u_id = u_id;
	}

	public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

	@Override
	public String toString() {
		return "User [u_id=" + u_id + ", username=" + username + "]";
	}


}
