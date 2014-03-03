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
public class Pattern {	
	/*
	 * Class fields
	 */
	private int[] patternCode;
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
		this.patternCode.clone();
		this.patternLength=pattern.getPatternLength();
	}
	//Construct a pattern from raw rotation data
	public Pattern(float[] time, float[] theta, float[] phi, float[] psi){
		this.patternCode = Pattern.generatePatternCode(time, theta, phi, psi);
		this.patternLength = this.patternCode.length;
	}
	//Construct a pattern from a RawData object
	public Pattern(RawData rawData){
		this.patternCode = Pattern.generatePatternCode(rawData);
	}
	
	/*
	 * Get/Set functions
	 */
	public int[] getPatternCode() {
		return patternCode;
	}
	public void setPatternCode(int[] patternCode) {
		this.patternCode = patternCode;
		this.patternLength = patternCode.length;
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
		
		int[] tempPatternCode = pattern.getPatternCode();
		for(int i=0; i<this.patternLength; i++){
			if(this.patternCode[i] != tempPatternCode[i]){
				isEqual = false;
			}
		}
		
		return isEqual;
	}
	//Takes raw input rotation data and generates the pattern code.
	private static int[] generatePatternCode(float[] time, float[] theta,
			float[] phi, float[] psi) {
		// TODO Auto-generated method stub
		return null;
	}
	//Takes a RawData object and generates a pattern
	private static int[] generatePatternCode(RawData rawData) {
		// TODO Auto-generated method stub
		return null;
	}
}
