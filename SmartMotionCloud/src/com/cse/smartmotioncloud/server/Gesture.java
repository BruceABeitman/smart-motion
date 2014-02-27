package com.cse.smartmotioncloud.server;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.gwt.user.client.rpc.IsSerializable;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Gesture implements IsSerializable {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;
	@Persistent
	private String account;
	@Persistent
	private String password;
	@Persistent
	private String gestures;
 
	public Gesture() {
		this.id = this.id + 1;
	}
	
	public Gesture(String account, String password, String gestures) {
		this.account = account;
		this.password = password;
		this.gestures = gestures;
	}
	
	public boolean equals(String account) {
		return (account == this.account);
	}
 
//	public Long getId() {
//		return this.id;
//	}
	
	public String getAccount() {
		return this.account;
	}
 
	public String getPassword() {
		return this.password;
	}
 
	public String getGestures() {
		return this.gestures;
	}
 
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setGestures(String gestures) {
		this.gestures = gestures;
	}
	
	public void addGesture(String gesture) {
		this.gestures += ";" + gesture;
	}
	
	public void removeGesture(String gesture) {
		
//		String[] gestureArray = this.gestures.split(";");
//		int index = this.gestures.indexOf(gesture);
//		this.gestures.remove(index);
	}
	
	public boolean hasGesture(String gesture) {
		
		return this.gestures.contains(gesture);
	}
}
