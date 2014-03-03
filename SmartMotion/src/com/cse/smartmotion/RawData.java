package com.cse.smartmotion;

/*
 * A class to represent and manipulate raw data.
 */
public class RawData {
	/*
	 * Fields
	 */
	private float[] time;
	private float[] x;
	private float[] y;
	private float[] z;
	
	/*
	 * Constructors
	 */
	//Initialize an empty data set
	public RawData() {
		time = null;
		x = null;
		y = null;
		z = null;
	}
	//Initialize a time and 2D data set
	public RawData(float[] time, float[] x, float[] y){
		this.time = time.clone();
		this.x = x.clone();
		this.y = y.clone();
		this.z = null;
	}
	//Initialize a time and 3D data set
	public RawData(float[] time, float[] x, float[] y, float[] z) {
		this.time = time.clone();
		this.x = x.clone();
		this.y = y.clone();
		this.z = z.clone();
	}
	
	/*
	 * Get/Set
	 */
	public float[] getTime() {
		return time;
	}
	public void setTime(float[] time) {
		this.time = time;
	}
	public float[] getX() {
		return x;
	}
	public void setX(float[] x) {
		this.x = x;
	}
	public float[] getY() {
		return y;
	}
	public void setY(float[] y) {
		this.y = y;
	}
	public float[] getZ() {
		return z;
	}
	public void setZ(float[] z) {
		this.z = z;
	}
	
	/*
	 * Utility
	 */
	//Very basic integration routine. The initial value is set to zero.
	private static float[] integrate(float[] time, float[] space) throws Error{
		int length = time.length;
		if(length != space.length && length!=0){
			throw new Error();
		}
		float[] integral = new float[length];
		integral[0]=0;
		for(int i=1; i<length; i++){
			integral[i]=integral[i-1]+space[i]*time[i];
		}
		return integral;
	}
}
