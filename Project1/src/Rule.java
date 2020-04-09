	/**
	 * 
	 * @author Toby Griffin collaborators: Peter Nguyen, Rohan Gaur, Tayo Dina, Bryce McDaniel
	 * 
	 * 
	 *
	 */
	public class Rule {
	
	/*
	 * Boolean array that represent the binary representation of 
	 * a rule number as boolean values
	 */
	private boolean[] ruleArr;
	
	/**
	 * Two parameter constructor that creates a Rule object of boolean
	 * values
	 * @param ruleNum
	 * @param offSymbol
	 */
	public Rule(int ruleNum, char offSymbol) {
		ruleArr = new boolean[8];
		for(int i = 0; i < 8; i++) {
			ruleArr[i] = toBoolean(calculateBinary(ruleNum).charAt(i), offSymbol);
		}
	}
	
	/**
	 * Returns a boolean value if a given character of an array
	 * is the same as or different than the specified false symbol
	 * @param a
	 * @param falseSymbol
	 * @return
	 */
	public boolean toBoolean(char a, char falseSymbol) {
		if(a == falseSymbol) {
			return false;
		}
		else {
			return true;
		}
	}
	
	/**
	 * Method that returns the binary string of a given rule number
	 * @param ruleNumber
	 * @return
	 */
	public String calculateBinary(int ruleNumber) {
		String ruleNum = Integer.toBinaryString(ruleNumber);
		while(ruleNum.length()!=8) {
				ruleNum = "0" +ruleNum;
		}
		return ruleNum;
	}
	
	/**
	 * Method that calculates the rule using three given cells which 
	 * are represented in the boolean array neighbors. Based on the 
	 * state of all three of those cells and what the rule specifies
	 * is what the cell of the next generation under the middle cell will
	 * @param neighbors
	 * @return
	 */
	public boolean calculateRule(boolean[] neighbors) {
		boolean left = neighbors[0];
		boolean mid = neighbors[1];
		boolean right = neighbors[2];
		if(left && mid && right)	{
			return ruleArr[0];
		}
		else if(left && mid && !right) {
			return ruleArr[1];
		}
		else if(left && !mid && right) {
			return ruleArr[2];
		}
		else if(left && !mid && !right) {
			return ruleArr[3];
		}
		else if(!left && mid && right) {
			return ruleArr[4];
		}
		else if(!left && mid && !right) {
			return ruleArr[5];
		}
		else if(!left && !mid && right) {
			return ruleArr[6];
		}
		else if(!left && !mid && !right) {
			return ruleArr[7];
		}
		
		return true;
	}
	
	/**
	 * Returns the state of an element at a given index
	 * @param idx
	 * @return
	 */
	public boolean findElement(int idx) {
		return ruleArr[idx];
	}
	
}

