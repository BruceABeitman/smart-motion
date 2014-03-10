package com.cse.smartmotion;
/*
 * Filename: Pattern.java
 * Author: Hudson Smith
 * Creation Date: 2 March 2014
 * 
 *  Encapsulates a pattern and methods for creating, comparing, and editing patterns. 
 *  Patterns are stored as a list of integers:
 *  1 represents a right flick
 *  2 represents a left flick
 *  3 represents an up flick
 *  4 represents a down flick
 *  Thus, a given pattern will be represented e.g. {1,1,4,2,...} which corresponds with 
 *  {right, right, down, left, ...}
 *  
 *  TODO:
 * 
 */
import java.util.List;

public class Pattern {	
	/*
	 * Constants
	 */
	private static final int FILTER_PERIOD = 10;
	private static final float THRESHOLD = 3;

	
	/*
	 * Class fields
	 */
	private List<Integer> patternCode;
	private int patternLength;
	
	/*
	 * Constructors
	 */
	//Create an empty pattern;
	public Pattern(){
		this.patternCode = null;
		this.patternLength = 0;
	}
	//Make an independent copy of the input pattern.
	public Pattern(Pattern pattern){
		this.patternCode = pattern.getPatternCode();
		this.patternLength=pattern.getPatternLength();
	}
	//Construct a pattern from a RawData object
	public Pattern(RawData rawData){
		rawData.filterNoiseXY(FILTER_PERIOD);
		this.patternCode = Pattern.generatePatternCodeXY(rawData);
	}
	
	/*
	 * Get/Set functions
	 */
	public List<Integer> getPatternCode() {
		return patternCode;
	}
	public void setPatternCode(List<Integer> patternCode) {
		this.patternCode = patternCode;
		this.patternLength = patternCode.size();
	}
	public int getPatternLength() {
		return patternLength;
	}
	
	/*
	 * Utility
	 */
	//Compares this Pattern with the input pattern.
	//Returns true if they match.
	//Else returns false.
	public boolean isEqual(Pattern pattern){
		boolean isEqual = true;
		if(this.patternLength!=pattern.getPatternLength()){
			return false;
		}
		
		List<Integer> tempPatternCode = pattern.getPatternCode();
		for(int i=0; i<this.patternLength; i++){
			if(this.patternCode.get(i) != tempPatternCode.get(i)){
				isEqual = false;
			}
		}
		
		return isEqual;
	}

	//Takes a RawData object and generates a pattern
	private static List<Integer> generatePatternCodeXY(RawData rawData) {
		float[] time = rawData.getTime();
		float[] x = rawData.getX();
		float[] y = rawData.getY();
		int lengthData = rawData.getSize();
		
		float xTemp;
		float yTemp;
		
		int iter =0;
		while(iter<lengthData){
			xTemp=x[iter];
			yTemp=y[iter];
			
			if(xTemp<THRESHOLD && yTemp<THRESHOLD){
				//do nothing; iterate
			} 
			else if(xTemp<THRESHOLD){
				boolean isCriticalY = false;
				//test y for critical value
				if(isCriticalY){
					boolean isUp = false;
					//test if up or down
					if(isUp)
					{
						//add an up to the pattern
					} 
					else
					{
						//add a down to the pattern
					}
					//iterate
				}
				else{
					//do nothing; iterate
				}
			}
			else if(yTemp<THRESHOLD){
				boolean isCriticalX = false;
				//test x for critical value
				if(isCriticalX){
					boolean isRight = false;
					//test if right or left
					if(isRight)
					{
						//add a Right to the pattern
					} 
					else
					{
						//add a left to the pattern
					}
					//iterate
				}
				else{
					//do nothing; iterate
				}
			}
			else{
				//Both signals are above threshold, consider the signal with larger absolute value
				if(xTemp>yTemp){
					boolean isCriticalX = false;
					//test x for critical value
					if(isCriticalX){
						boolean isRight = false;
						//test if right or left
						if(isRight)
						{
							//add a Right to the pattern
						} 
						else
						{
							//add a left to the pattern
						}
						//iterate
					}
					else{
						//do nothing; iterate
					}
				}
				else{
					boolean isCriticalY = false;
					//test y for critical value
					if(isCriticalY){
						boolean isUp = false;
						//test if up or down
						if(isUp)
						{
							//add an up to the pattern
						} 
						else
						{
							//add a down to the pattern
						}
						//iterate
					}
					else{
						//do nothing; iterate
					}
				}
			}
		}
		return null;
	}
	
	
}
