package com.cse.smartmotion;
import java.util.Arrays;
import java.util.List;

/*
 * Filename: RawData.java
 * Author: Hudson Smith
 * Creation Date: 2 March 2014
 * 
 * A class to represent and manipulate raw data.
 * 
 * ChangeLog: 
 * 2Mar2014: Started
 * 3Mar2014: Added zeroes and extrema methods
 * 4Mar2014: 
 * 	--Added constructor for input from List type objects.
 *  --Added max/min functions
 * 5Mar2014: 
 * 	--Added dimension and size fields for security, but did not fully implement
 * 	--Reorganized utility code into public and private sections
 *  --Added comments throughout
 */
public class RawData {
	/*
	 * Static variables
	 */
	private static int MAX_ZEROES = 20;
	
	/*
	 * Fields
	 */
	private float[] time;
	private float[] x;
	private float[] y;
	private float[] z;
	private int dimension; 
	private int size;
	
	/*
	 * Constructors
	 */
	//Initialize an empty data set
	public RawData() {
		time = null;
		x = null;
		y = null;
		z = null;
		dimension = 0;
		size =0;
	}
	//Initialize a time and 1D data set
	public RawData(float[] time, float[] x){
		this.time = time.clone();
		this.x = x.clone();
		this.y = null;
		this.z = null;
		
		dimension = 1;
		
		int[] sizes = {time.length, x.length};
		size = RawData.min(sizes);
	}
	//Initialize a time and 1D data set
	public RawData(List<Float> time, List<Float> x){
		//Pick the minimum size
		int[] sizes = {time.size(), x.size()};
		int size = RawData.min(sizes);
		
		this.time = new float[size];
		this.x = new float[size];
		for(int i=0; i<size; i++){
			this.time[i] = time.get(i);
			this.x[i] = x.get(i);
		}
		this.y = null;
		this.z = null;
		
		this.dimension =1;
		this.size = size;
	}
	//Initialize a time and 2D data set
	public RawData(float[] time, float[] x, float[] y){
		this.time = time.clone();
		this.x = x.clone();
		this.y = y.clone();
		this.z = null;
		
		dimension = 2;
		
		int[] sizes = {time.length, x.length, y.length};
		size = RawData.min(sizes);
	}
	//Initialize a time and 2D data set
	public RawData(List<Float> time, List<Float> x, List<Float> y){
		//Pick the minimum size
		int[] sizes = {time.size(), x.size(), y.size()};
		int size = RawData.min(sizes);
		
		this.time = new float[size];
		this.x = new float[size];
		this.y = new float[size];
		for(int i=0; i<size; i++){
			this.time[i] = time.get(i);
			this.x[i] = x.get(i);
			this.y[i] = y.get(i);
		}
		this.z = null;
		
		dimension = 2;
		this.size = size;
	}
	//Initialize a time and 3D data set
	public RawData(float[] time, float[] x, float[] y, float[] z) {
		this.time = time.clone();
		this.x = x.clone();
		this.y = y.clone();
		this.z = z.clone();
		
		this.dimension = 3;
		
		int[] sizes = {time.length, x.length, y.length, z.length};
		size = RawData.min(sizes);
	}
	//Initialize a time and 3D data set
	public RawData(List<Float> time, List<Float> x, List<Float> y, List<Float> z){
		//Pick the minimum size
		int[] sizes = {time.size(), x.size(), y.size(), z.size()};
		int size = RawData.min(sizes);
		
		this.time = new float[size];
		this.x = new float[size];
		this.y = new float[size];
		for(int i=0; i<size; i++){
			this.time[i] = time.get(i);
			this.x[i] = x.get(i);
			this.y[i] = y.get(i);
			this.z[i] = z.get(i);
		}
		
		this.dimension = 3;
		this.size = size;
	}	
	
	/*
	 * Get
	 */
	public float[] getTime() {
		return time;
	}
	public float[] getX(){
		return x;
	}
	public float[] getY() {
		return y;
	}
	public float[] getZ() {
		return z;
	}
	public int getDimension() {
		return dimension;
	}
	public int getSize() {
		return size;
	}
	
	/*
	 * Set
	 * Warning: as written, these methods are dangerous. It's recommended that you use a constructor to update the fields.
	 * TODO: size and dimension should be updated consistently with time, x, y, and z
	 */
	public void setTime(float[] time) {
		this.time = time;
	}
	public void setX(float[] x) {
		this.x = x;
	}
	public void setY(float[] y) {
		this.y = y;
	}
	public void setZ(float[] z) {
		this.z = z;
	}
	
	/*
	 * Public Utility
	 */
	//Returns the running integration of the elements x,y, or z.
	public float[] integrateX(){
		if(this.time!=null && this.x!=null){
			return integrate(this.time,this.x);
		}
		else
			return null;
	}
	public float[] integrateY(){
		if(this.time!=null && this.y!=null){
			return integrate(this.time,this.y);
		}
		else
			return null;
	}
	public float[] integrateZ(){
		if(this.time!=null && this.z!=null){
			return integrate(this.time,this.z);
		}
		else
			return null;
	}
	//Returns the indices for the elements in x, y, or z immediately to the left of a zero crossing.
	public int[] findZeroesX(){
		return findZeroes(this.x);
	}
	public int[] findZeroesY(){
		return findZeroes(this.y);
	}
	public int[] findZeroesZ(){
		return findZeroes(this.z);
	}
		
	//Returns a list of extrema in the x, y, or z data sets
	public int[] findExtremaX(){
		return findExtrema(this.x);
	}
	public int[] findExtremaY(){
		return findExtrema(this.y);
	}
	public int[] findExtremaZ(){
		return findExtrema(this.z);
	}
	

	/*
	 * Private utility
	 */
	//Gives the maximum value in a float array
	private static float max(float[] values) {
		int length = values.length;
		float maxSize=0;
		if(length!=0){
			maxSize = values[0];
			for(int i=1; i< length; i++){
				if(values[i]>maxSize){
					maxSize = values[i];
				}
			}
		}
		return maxSize;
	}
	//Gives the maximum value in an int array
	private static int max(int[] values) {
		int length = values.length;
		int maxSize=0;
		if(length!=0){
			maxSize = values[0];
			for(int i=1; i< length; i++){
				if(values[i]>maxSize){
					maxSize = values[i];
				}
			}
		}
		return maxSize;
	}	
	//Gives the minimum value in an float array
	private static float min(float[] values) {
		int length = values.length;
		float minSize=0;
		if(length!=0){
			minSize = values[0];
			for(int i=1; i< length; i++){
				if(values[i]<minSize){
					minSize = values[i];
				}
			}
		}
		return minSize;
	}
	//Gives the minimum value in an int array
	private static int min(int[] values) {
		int length = values.length;
		int minSize=0;
		if(length!=0){
			minSize = values[0];
			for(int i=1; i< length; i++){
				if(values[i]<minSize){
					minSize = values[i];
				}
			}
		}
		return minSize;
	}
	
	//Very basic integration routine. The initial value is set to zero.
	private static float[] integrate(float[] time, float[] space){
		int[] sizes = {time.length, space.length};
		int length = RawData.min(sizes);
		
		float[] integral = new float[length];
		integral[0]=0;
		for(int i=1; i<length; i++){
			integral[i]=integral[i-1]+space[i]*time[i];
		}
		return integral;
	}
	//Returns the indices for the elements in the input array.
	//If a zero crossing does not have a point exactly equal to zero,
	//this function returns the point directly to the left of the zero crossing.
	private static int[] findZeroes(float[] data) throws NullPointerException{
		if(data==null){
			throw new NullPointerException();
		}
		else if(data.length>1){
			//the array for storing the zero indices
			int[] zeroes = new int[RawData.MAX_ZEROES];
			int numZeroes = 0;
			
			int size = data.length;
			int currentSign = (int) Math.signum(data[0]);
			int nextSign = (int) Math.signum(data[1]);
			//Loop through data, if the sign changes, then data crosses zero. Store the element index to the left of the zero crossing.
			for(int i=0; i<size-1; i++){
				//Stop looking for zeroes if MAX_ZEROES is exceeded.
				//This sets a limit on how many zero crossings can be stored.
				if(numZeroes<RawData.MAX_ZEROES){
					currentSign = nextSign;
					nextSign = (int) Math.signum(data[i+1]);
					
					if(nextSign==0){
						zeroes[numZeroes] = i+1;
						numZeroes++;
					}
					else if(currentSign!=nextSign && currentSign!=0){
						zeroes[numZeroes] = i;
						numZeroes++;
					}
				}
			}	
			return Arrays.copyOf(zeroes, numZeroes);
		}
		else
			return null;
	}
	//Gives the extrema of the absolute value between the initial point, each sequential zero, and the final point in data.
	//Zeroes should contain all zero crossings in "data", otherwise, findExtrema will miss local extrema.
	private static int[] findExtrema(float[] data) {
		if(data==null){
			throw new NullPointerException();
		}
		else{
			int[] zeroes = RawData.findZeroes(data);
			int numData = data.length;
			int numZeroes = zeroes.length;
			int[] extrema = new int[numZeroes+1];
			
			if(numZeroes==0){
				extrema[0] = RawData.findExtremum(data, 0, numData);
			}
			else{
				extrema[0] = RawData.findExtremum(data, 0, zeroes[0]);
				for(int i=0; i<numZeroes-1; i++){
					extrema[i+1]=RawData.findExtremum(data, zeroes[i], zeroes[i+1]);
				}
				extrema[numZeroes] = RawData.findExtremum(data, zeroes[numZeroes-1], numData-1);
			}
			return extrema;
		}
	}
	//Gives the index in data between the indices minIndex and maxIndex that holds the largest absolute value
	private static int findExtremum(float[] data, int minIndex, int maxIndex) {
		float extremumAbs = Math.abs(data[minIndex]);
		int extremumIndex = minIndex;
		for(int i=minIndex; i<maxIndex; i++){
			if(Math.abs(data[i])>extremumAbs){
				extremumAbs = Math.abs(data[i]);
				extremumIndex = i;
			}
		}
		
		return extremumIndex;
	}
}
