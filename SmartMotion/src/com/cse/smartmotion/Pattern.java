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
		this.patternCode = Pattern.generatePatternCode(rawData);
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
	private static List<Integer> generatePatternCode(RawData rawData) {
		
		//Get the critical values from the x and y data sets in rawData
		int[][] critValsAndTypeX = rawData.findCriticalPointsX();
		int[] critValsX = critValsAndTypeX[0];
		int[] typeX = critValsAndTypeX[1];
		int[][] critValsAndTypeY = rawData.findCriticalPointsY();
		int[] critValsY = critValsAndTypeY[0];
		int[] typeY = critValsAndTypeY[1];
		
		
		
		return null;
	}
	
}
