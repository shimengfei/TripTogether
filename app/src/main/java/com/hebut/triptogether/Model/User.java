package com.hebut.triptogether.Model;
import java.io.Serializable;
import java.util.Date;

public class User implements Serializable{
    private int id;
    private String phoneNumber;
    private String password;
    private String userName;
    private String emailAddress;
    private Date registerTime;
    public static String EMAIL = "emailAddress";
    public static String PASSWORD = "passWord";
    public static String USERNAME = "userName";

    public User(String userName,String password,String emailAddress){
        this.password = password;
        this.userName = userName;
        this.emailAddress=emailAddress;
    }
    public User(String user, String pwd){
        this.userName=user;
        this.password=pwd;

    }
    public User(){}
    public String getPassword() {
		return password;
	}
    public String getUserName() {
		return userName;
	}
    public String getEmailAddress() {
		return emailAddress;
	}
	public void setUserName(String userName){
        this.userName=userName;
    }
    public void setPassword(String password){
        this.password=password;
    }
    public void setEmailAddress(String emailAddress){
        this.emailAddress=emailAddress;
    }

}