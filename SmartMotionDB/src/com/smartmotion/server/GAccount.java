package com.smartmotion.server;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
 
@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class GAccount {
 
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;
	@Persistent
	private String account;
	@Persistent
	private String password;
	@Persistent
	private String gesture;
	@Persistent
	private Date createDate;
 
	private static final Logger LOG = Logger.getLogger(GestureServiceImpl.class.getName());
	
	//a good way to use constructor
	public GAccount() {
		this.createDate = new Date();
	}
 
	public GAccount(String[] account) {
		this();
		
		LOG.log(Level.SEVERE, "Note adding account: " + account[0]);
		this.account = account[0];
		
		this.password = account[1];
		this.gesture = account[2];
	}
 
	public Long getId() {
		return this.id;
	}
 
	public String getAccount() {
		return this.account;
	}
	public String getPassword() {
		return this.password;
	}
	public String getGesture() {
		return this.gesture;
	}
	
	public void updateGesture(String gesture) {
		this.gesture = gesture;
	}
 
	public Date getCreateDate() {
		return this.createDate;
	}
 
	public void setNote(String note) {
//		this.note = note;
	}
}