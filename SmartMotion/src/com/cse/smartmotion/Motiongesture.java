package com.cse.smartmotion;

public class Motiongesture {
	 int _id;
     String _name;
     String _gest;
     
     public Motiongesture(){
   	  
     }
     public Motiongesture(int id, String name,String _gest){
   	  this._id=id;
   	  this._name=name;
   	  this._gest=_gest;
     }
     
     public Motiongesture(String name, String _gest){
   	  this._name=name;
   	  this._gest=_gest;
   	  
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
}
