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
	//The maximum number of zeroes to look for
	private static int MAX_ZEROES = 20;
	//The maximum number of iterations to run through when sorting the critical points
	private static final int MAX_ITERATIONS = 100;
	
	/*
	 * Fields
	 */
	private long[] t; //millis
	private double[] x;
	private double[] y;
	private double[] z;
	private int dimension; 
	
	/*
	 * Constructors
	 */
	//Initialize an empty data set
	public RawData() {
		t = new long[0];
		x = new double[0];
		y = new double[0];
		z = new double[0];
		dimension = 0;
	}

	//Initialize a time and 1D data set
	public RawData(long[] time, double[] x){
		this.t = time.clone();
		this.x = x.clone();
		this.y = null;
		this.z = null;
		
		dimension = 1;
	}
	//Initialize a time and 1D data set
	public RawData(List<Long> time, List<Double> x){
		//Pick the minimum size
		int[] sizes = {time.size(), x.size()};
		int size = RawData.min(sizes);
		
		this.t = new long[size];
		this.x = new double[size];
		for(int i=0; i<size; i++){
			this.t[i] = time.get(i);
			this.x[i] = x.get(i);
		}
		this.y = null;
		this.z = null;
		
		this.dimension =1;
	}
	//Initialize a time and 2D data set
	public RawData(long[] time, double[] x, double[] y){
		this.t = time.clone();
		this.x = x.clone();
		this.y = y.clone();
		this.z = null;
		
		dimension = 2;
	}
	//Initialize a time and 2D data set
	public RawData(List<Long> time, List<Double> x, List<Double> y){
		//Pick the minimum size
		int[] sizes = {time.size(), x.size(), y.size()};
		int size = RawData.min(sizes);
		
		this.t = new long[size];
		this.x = new double[size];
		this.y = new double[size];
		for(int i=0; i<size; i++){
			this.t[i] = time.get(i);
			this.x[i] = x.get(i);
			this.y[i] = y.get(i);
		}
		this.z = null;
		
		dimension = 2;
	}
	//Initialize a time and 3D data set
	public RawData(long[] time, double[] x, double[] y, double[] z) {
		this.t = time.clone();
		this.x = x.clone();
		this.y = y.clone();
		this.z = z.clone();
		
		this.dimension = 3;
	}
	//Initialize a time and 3D data set
	public RawData(List<Long> time, List<Double> x, List<Double> y, List<Double> z){
		//Pick the minimum size
		int[] sizes = {time.size(), x.size(), y.size(), z.size()};
		int size = RawData.min(sizes);
		
		this.t = new long[size];
		this.x = new double[size];
		this.y = new double[size];
		for(int i=0; i<size; i++){
			this.t[i] = time.get(i);
			this.x[i] = x.get(i);
			this.y[i] = y.get(i);
			this.z[i] = z.get(i);
		}
		
		this.dimension = 3;
	}	
	
	public RawData(RawData rawData) {
		int length = rawData.getTime().length;
		this.t = Arrays.copyOf(rawData.getTime(), length);
		this.x = Arrays.copyOf(rawData.getX(), length);
		this.y = Arrays.copyOf(rawData.getY(), length);
		this.z = Arrays.copyOf(rawData.getZ(), length);
	}

	/*
	 * Get
	 */
	public long[] getTime() {
		return t;
	}
	public double[] getX(){
		return x;
	}
	public double[] getY() {
		return y;
	}
	public double[] getZ() {
		return z;
	}
	public int getDimension() {
		return dimension;
	}
	
	/*
	 * Set
	 * Warning: as written, these methods are dangerous. It's recommended that you use a constructor to update the fields.
	 * TODO: size and dimension should be updated consistently with time, x, y, and z
	 */
	public void setTime(long[] time) {
		this.t = time;
	}
	public void setX(double[] x) {
		this.x = x;
	}
	public void setY(double[] y) {
		this.y = y;
	}
	public void setZ(double[] z) {
		this.z = z;
	}
	
	/*
	 * Public Utility
	 */
	//Returns the running integration of the elements x,y, or z.
//	public double[] integrateX(){
//		if(this.t!=null && this.x!=null){
//			return integrate(this.t,this.x);
//		}
//		else
//			return null;
//	}
//	public double[] integrateY(){
//		if(this.t!=null && this.y!=null){
//			return integrate(this.t,this.y);
//		}
//		else
//			return null;
//	}
//	public double[] integrateZ(){
//		if(this.t!=null && this.z!=null){
//			return integrate(this.t,this.z);
//		}
//		else
//			return null;
//	}
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
	//Returns a list of extrema in the x, y, or z data sets
	public int[][] findCriticalPointsX(){
		return findCriticalPoints(this.x);
	}
	public int[][] findCriticalPointsY(){
		return findCriticalPoints(this.y);
	}
	public int[][] findCriticalPointsZ(){
		return findCriticalPoints(this.z);
	}

	//Gives the maximum value in a double array
	public static double max(double[] values) {
		int length = values.length;
		double maxSize=0;
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
	public static int max(int[] values) {
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
	//Gives the minimum value in an double array
	public static double min(double[] values) {
		int length = values.length;
		double minSize=0;
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
	public static int min(int[] values) {
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
	//Appends the time, x, and y data point to this RawData object
	public void appendData2D(long time, double x, double y){		
		long[] newTime = {time};
		double[] newX = {x};
		double[] newY = {y};
		
		this.t = appendArrays(this.t, newTime);
		this.x = appendArrays(this.x, newX);
		this.y = appendArrays(this.y, newY);
	}	
	//Appends the time, x, and y data Lists to this RawData object
	public void appendData2D(List<Long> time, List<Double> x, List<Double> y){
		int timeSize = time.size();
		int xSize = x.size();
		int ySize = y.size();
		
		long[] addTime = new long[timeSize];
		for(int i=0; i<timeSize; i++){
			addTime[i] = time.get(i);
		}
		this.t = appendArrays(this.t, addTime);
		
		double[] addX = new double[xSize];
		for(int i=0; i<xSize; i++){
			addX[i] = x.get(i);
		}
		this.x = appendArrays(this.x, addX);
		
		double[] addY = new double[ySize];
		for(int i=0; i<ySize; i++){
			addY[i] = y.get(i);
		}
		this.y = appendArrays(this.y, addY);
	}
	
	private long[] appendArrays(long[] array1, long[] array2){
		long[] newArray = new long[array1.length+array2.length];
		for(int i=0; i<array1.length;i++){
			newArray[i]=array1[i];
		}
		for(int i=0; i<array2.length; i++){
			newArray[array1.length+i]=array2[i];
		}	
		return newArray;
	}
	
	private double[] appendArrays(double[] array1, double[] array2){
		double[] newArray = new double[array1.length+array2.length];
		for(int i=0; i<array1.length;i++){
			newArray[i]=array1[i];
		}
		for(int i=0; i<array2.length; i++){
			newArray[array1.length+i]=array2[i];
		}	
		return newArray;
	}
	
	/*
	 * Private utility
	 */
	//Very basic integration routine. The initial value is set to zero.
	private static double[] integrate(double[] time, double[] space){
		int[] sizes = {time.length, space.length};
		int length = RawData.min(sizes);
		
		double[] integral = new double[length];
		integral[0]=0;
		for(int i=1; i<length; i++){
			integral[i]=integral[i-1]+space[i]*time[i];
		}
		return integral;
	}
	//Returns the indices for the elements in the input array.
	//If a zero crossing does not have a point exactly equal to zero,
	//this function returns the point directly to the left of the zero crossing.
	private static int[] findZeroes(double[] data) throws NullPointerException{
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
			return new int[0];
	}
	//Gives the extrema of the absolute value between the initial point, each sequential zero, and the final point in data.
	//Zeroes should contain all zero crossings in "data", otherwise, findExtrema will miss local extrema.
	private static int[] findExtrema(double[] data) {
		if(data==null){
			throw new NullPointerException();
		}
		else if(data.length>0){
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
		else
			return new int[0];
	}
	//Gives the index in data between the indices minIndex and maxIndex that holds the largest absolute value
	private static int findExtremum(double[] data, int minIndex, int maxIndex) {
			double extremumAbs = Math.abs(data[minIndex]);
			int extremumIndex = minIndex;
			for(int i=minIndex; i<maxIndex; i++){
				if(Math.abs(data[i])>extremumAbs){
					extremumAbs = Math.abs(data[i]);
					extremumIndex = i;
				}
			}
			
			return extremumIndex;
	}
	
	//Get the zeroes and extrema store in list of integers {{index1, index2,...},{type1, type2,...}}
	private static int[][] findCriticalPoints(double[] data){
		int[] zeroes = RawData.findZeroes(data);
		int[] extrema = RawData.findExtrema(data);
		
		int sizeZeroesX = zeroes.length;
		int sizeExtremaX= extrema.length;
		int[] criticalPoints = new int[sizeZeroesX+sizeExtremaX];
		int[] type = new int[sizeZeroesX+sizeExtremaX];
		
		int iterZeroes = 0;
		int iterExtrema = 0;
		int maxIterations =0;
		while(iterZeroes+iterExtrema< sizeZeroesX+sizeExtremaX && maxIterations < MAX_ITERATIONS){
			if(iterZeroes < sizeZeroesX && iterExtrema < sizeExtremaX){
				if(zeroes[iterZeroes]<extrema[iterExtrema]){
					criticalPoints[iterZeroes+iterExtrema] = zeroes[iterZeroes];
					type[iterZeroes+iterExtrema]=0;
					iterZeroes++;

				}
				else{
					criticalPoints[iterZeroes+iterExtrema] = extrema[iterExtrema];
					type[iterZeroes+iterExtrema]= (int)Math.signum(data[extrema[iterExtrema]]);
					iterExtrema++;

				}
			} 
			else if(iterZeroes < sizeZeroesX && iterExtrema >= sizeExtremaX){
				criticalPoints[iterZeroes+iterExtrema] = zeroes[iterZeroes];
				type[iterZeroes+iterExtrema]=0;
				iterZeroes++;
			}
			else if(iterZeroes >= sizeZeroesX && iterExtrema < sizeExtremaX){
				criticalPoints[iterZeroes+iterExtrema] = extrema[iterExtrema];
				type[iterZeroes+iterExtrema]= (int)Math.signum(data[extrema[iterExtrema]]);
				iterExtrema++;
			}
			maxIterations++;
		}
		
		int[][] critPointsAndTyp = {criticalPoints, type}; 
		
		return critPointsAndTyp;
	}
	
	
	public void filterNoiseXY(int filterPeriod){
		this.x = RawData.smoothData(this.t, this.x, filterPeriod);
		this.y = RawData.smoothData(this.t, this.y, filterPeriod);
	}
	
	//time: the time values associated with data
	//data: the data to be smoothed
	//filterPeriod: the period below which signals are reduced by 3dB or more
	private static double[] smoothData(long[] time, double[] data, int filterPeriod){
		double value = data[0]; //start with the first input
		double[] values = new double[data.length]; //the smoothed data
		values[0] = value;
		
		for(int i=1; i<data.length; i++){
			double currentValue = data[i];
			double delT = time[i] - time[i-1];
			double alpha = (double) (delT / (filterPeriod / (2*Math.PI) + delT));
			value = value + (currentValue - value) * alpha;
			values[i] = value;
			
			if(alpha>1 || alpha <0)
				throw new NumberFormatException();
		}
		return values;
	}
	
//	//Filters out modes with periods shorter than the input period (input in millis)
//	public void filterNoiseXY(int period){
//		//if the data length is not a multiple of period, truncate from the end
//		int dataLength=this.t.length;
//		int newLength =(int) ((this.t[this.t.length-1]-this.t[0])/period);
//		if(newLength!=0){			
//			long[] smoothT = new long[newLength];
//			double[] smoothX = new double[newLength];
//			double[] smoothY = new double[newLength];
//	
//			int datIter=0;
//			int avgIter=0;
//			int numPeriods=0;
//			long zeroTime = this.t[0];
//			while(datIter < dataLength && numPeriods < newLength){
//				long sumT =0;
//				double sumX =0;
//				double sumY =0;
//				avgIter=0;
//				while(datIter < dataLength && (this.t[datIter]-zeroTime)<(numPeriods+1)*period){
//					sumT+=this.t[datIter];
//					sumX+=this.x[datIter];
//					sumY+=this.y[datIter];
//					avgIter++;
//				}
//				
//				smoothT[numPeriods]=sumT/avgIter;
//				smoothX[numPeriods]=sumX/avgIter;
//				smoothY[numPeriods]=sumY/avgIter;
//				datIter+=avgIter;
//				numPeriods++;
//			}
//			
//			this.t=smoothT;
//			this.x=smoothX;
//			this.y=smoothY;
//		}
//	}

	public static int idleTime() {
		// TODO Auto-generated method stub
		return 0;
	}
	 
}
