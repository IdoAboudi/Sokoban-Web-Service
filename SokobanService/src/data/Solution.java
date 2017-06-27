package data;

import java.io.Serializable;

public class Solution implements Serializable{
	
	private String levelName;
	private String solution;
	private int levelHash;
	
	public Solution(){
		levelName="";
		solution="";
	}

	public Solution(String levelName, String solution, int levelHash){
		this.solution = solution;
		this.levelName = levelName;
		this.levelHash = levelHash;
	}
	
	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}



	public String getSolution() {
		return solution;
	}

	public void setSolution(String solution) {
		this.solution = solution;
	}
	

	public int getLevelHash() {
		return levelHash;
	}

	public void setLevelHash(int levelHash) {
		this.levelHash = levelHash;
	}

	public boolean equals(Solution s) {
		return this.solution.equals(s.getSolution()) && this.levelHash == s.getLevelHash();
	}
	
}
