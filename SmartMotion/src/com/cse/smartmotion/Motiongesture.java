package com.cse.smartmotion;

public class Motiongesture {
	int _id;
    String _name;
    String _gest;
    String _user;
    String _pattern;
    
    
    public Motiongesture(){
  	  
    }
    
    //public Motiongesture(int id, String name, String gest){
    	
    //}
    public Motiongesture(int id, String name,String gest, String user, String pattern){
  	  this._id=id;
  	  this._name=name;
  	  this._gest=gest;
  	  this._user=user;
  	  this._pattern=pattern;
  	  
    }
    
    public Motiongesture(String name, String gest, String user, String pattern){
  	  this._name=name;
  	  this._gest=gest;
  	  this._user=user;
  	  this._pattern=pattern;
  	  
  	  
    }
    public int getID(){
  	  return this._id;
    }
    public void setID(int id){
  	  this._id=id;
  	  
    }
    public String getName(){
  	  return this._name;
  	  
    }
    
    public void setName(String name){
  	  this._name=name;
  	  
    }
    public String getGest(){
  	  return this._gest;
    }
    public void setGest(String A){
  	  this._gest=A;
  	  
    }
    
    public String getUser(){
    	return this._user;
    	
    }
    
    public void setUser(String user){
    	this._user=user;
    }
    
    public String getPattern(){
    	return this._pattern;
    }
    
    public void setPattern(String pattern){
    	this._pattern=pattern;
    	
    }

}
