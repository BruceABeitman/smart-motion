package com.cse.smartmotion;

/*
 * Filename: Pattern.java
 * Author: Hudson Smith
 * Creation Date: 2 March 2014
 * 
 *  Encapsulates a pattern and methods for creating, comparing, and editing patterns. 
 *  Patterns are stored as a list of integers:
 *  r represents a right flick
 *  l represents a left flick
 *  u represents an up flick
 *  d represents a down flick
 *  Thus, a given pattern will be represented e.g. {'r','r','d','l',...} which corresponds with 
 *  {right, right, down, left, ...}
 *  
 *  TODO:
 * 
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Pattern {	
	/*
	 * Constants
	 */
	private static final int FILTER_PERIOD = 3;
	private static final float THRESHOLD = 1.5f;
	private static final char UP = 'u';
	private static final char DOWN = 'd';
	private static final char LEFT = 'l';
	private static final char RIGHT = 'r';

	/*
	 * Class fields
	 */
	private List<Character> patternCode;
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
		this.patternLength = this.patternCode.size();
	}
	
	/*
	 * Get/Set functions
	 */
	public List<Character> getPatternCode() {
		return patternCode;
	}
	public void setPatternCode(List<Character> patternCode) {
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
		
		List<Character> tempPatternCode = pattern.getPatternCode();
		for(int i=0; i<this.patternLength; i++){
			if(this.patternCode.get(i) != tempPatternCode.get(i)){
				isEqual = false;
			}
		}
		
		return isEqual;
	}

	//Takes a RawData object and generates a pattern
	public static List<Character> generatePatternCodeXY(RawData rawData) {
		List<Character> patternCode = new ArrayList<Character>();
		
		float[] pitch = rawData.getX();
		float[] yaw = rawData.getY();
		int[] xExtrema = rawData.findExtremaX();
		int[] yExtrema = rawData.findExtremaY();

		int lengthData = pitch.length;
//		int lengthData = rawData.getSize();
		
		float pitchTemp;
		float yawTemp;
		
		int iter =0;
		while(iter<lengthData){
			pitchTemp=pitch[iter];
			yawTemp=yaw[iter];
			
			//y above threshold, x below threshold
			if(Math.abs(yawTemp)>=THRESHOLD && Math.abs(pitchTemp)<THRESHOLD){
				if(Pattern.isInSet(iter, yExtrema)){
					//test if up or down
					if(yawTemp > THRESHOLD)
					{
						patternCode.add(LEFT);
					} 
					else
					{
						patternCode.add(RIGHT);
					}
				}
			}
			//x above threshold, y below threshold
			else if(Math.abs(pitchTemp)>=THRESHOLD && Math.abs(yawTemp)<THRESHOLD){
				if(Pattern.isInSet(iter, xExtrema)){
					//test if up or down
					if(pitchTemp > THRESHOLD)
					{
						patternCode.add(UP);
					} 
					else
					{
						patternCode.add(DOWN);
					}
				}
			}
			else if(Math.abs(pitchTemp)>=THRESHOLD && Math.abs(yawTemp)>=THRESHOLD){
				if(Math.abs(yawTemp)>=Math.abs(pitchTemp)){
					if(Pattern.isInSet(iter, yExtrema)){
						//test if up or down
						if(yawTemp > THRESHOLD)
						{
							patternCode.add(LEFT);
						} 
						else
						{
							patternCode.add(RIGHT);
						}
					}
				}
				else{
					if(Pattern.isInSet(iter, xExtrema)){
						//test if up or down
						if(pitchTemp > THRESHOLD)
						{
							patternCode.add(UP);
						} 
						else
						{
							patternCode.add(DOWN);
						}
					}
				}
			}
			
			iter++;
		}
		return patternCode;
	}
	private static boolean isInSet(int elem, int[] set) {
		int index = Arrays.binarySearch(set, elem);
		if(index>=0){
			return true;
		}
		else{
			return false;
		}
	}
}
