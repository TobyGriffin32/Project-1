	import java.io.*;
	/**
	 * 
	 * @author Toby Griffin collaborators: Peter Nguyen, Rohan Gaur, Tayo Dina, Bryce McDaniel
	 * 
	 * 
	 *
	 */
	public class Automaton {
		/*
		 * Stores the number of the specified rule as an integer
		 */
		private int ruleNum;
		
		/*
		 * Rule object representing the rule number as an array of 
		 * boolean values.
		 */
		private Rule rule;
		
		/*
		 * Represents the initial state of an automaton with a 
		 * boolean array.
		 */
		private boolean[] initialState;
		
		/*
		 * Represents the Automaton as a whole with the first part
		 * of the array representing the generation and the second 
		 * part being the position of the boolean value in that 
		 * generation
		 */
		private boolean[][] Automaton;
		
		/*
		 * Specific false symbol for Automaton(int ruleNum, boolean[] initState)
		 */
		private char falseSymbol ='0';
		
		/*
		 * Specific true symbol for Automaton(int ruleNum, boolean[] initState)
		 */
		private char trueSymbol ='1';
		
		/*
		 * Represents the number of steps an Automaton has been through
		 */
		private int stepCounter = 0;

		/**
		 * Two parameter constructor that creates an Automaton object with a given 
		 * rule and initial state of the Automaton
		 * @param ruleNum
		 * @param initState
		 */
		Automaton(int ruleNum, boolean[] initState){
			this.ruleNum = ruleNum; 
			rule = new Rule(ruleNum, falseSymbol);
			initialState = initState;
			Automaton = new boolean[1][initialState.length];
			Automaton[0] = initialState;
		}
		
		/**
		 * Single parameter constructor that reads in a rule number, specific
		 * false and true symbols, and the initial state of the Automaton 
		 * from a given file to create an Automaton object. 
		 * @param fileName
		 * @throws IOException
		 */
		Automaton(String fileName) throws IOException {
			BufferedReader buffW = new BufferedReader(new FileReader(fileName));
			
			ruleNum = Integer.parseInt(buffW.readLine()); 
			rule = new Rule(ruleNum,falseSymbol);
			String trueOrFalse = buffW.readLine();
			falseSymbol = trueOrFalse.charAt(0);
			trueSymbol = trueOrFalse.charAt(2);
			String initStateString = buffW.readLine();
			
			initialState = new boolean[initStateString.length()];	
			for(int i =0; i < initialState.length; i++) {
				if(initStateString.charAt(i) == trueSymbol) {
					initialState[i]= true;
				}
				else if (initStateString.charAt(i) == falseSymbol) {
					initialState[i] = false;
				}
			}
			Automaton = new boolean[1][initialState.length];
			Automaton[0] = initialState;
			
			buffW.close();
		}
		
		/*
		 * Getter method that returns the rule number as an integer
		 */
		public int getRuleNum() {
			return ruleNum;
		}
		
		/**
		 * Method that evolves the Automaton using a two dimensional Automaton object array
		 * to represent the number of steps the Automaton has gone through
		 * the boolean value at each position in each step.
		 * @param numSteps
		 */
		public void evolve(int numSteps) {
			boolean[][] updatedCA = new boolean[Automaton.length + numSteps][initialState.length];
			for (int i =0; i < Automaton.length; i++) {
				updatedCA[i]= Automaton[i];
			}
			for (int j = Automaton.length; j < updatedCA.length; j++) {
				updatedCA[j] = Generation.nextGeneration(rule, updatedCA[j-1]);
			}
			Automaton = updatedCA;
		}
		
		/*
		 * Getter method that returns the total number of steps an
		 * Automaton has evolved
		 */
		public int getTotalSteps() {
			if (stepCounter >= 1) {
				return stepCounter - 1;
			}
			return stepCounter;
		}
		
		/**
		 * Getter method that returns the state of an Automaton
		 * object at a given step
		 * @param stepNum
		 * @return
		 */
		public boolean[] getState(int stepNum) 
		{
			return Automaton[stepNum];
		}
		
		/**
		 * Getter method that returns the state of a given step number
		 * of an Automaton as a string
		 * @param stepNum
		 * @return
		 */
		public String getStateString(int stepNum) {
			String stepString = "";
			for (int i =0; i < Automaton[stepNum].length; i++) {
				if (Automaton[stepNum][i] == true) {
					stepString += trueSymbol;
				}
				else if (Automaton[stepNum][i] == false) {
					stepString += falseSymbol;
				}
			}
			return stepString;
		}
		
		/*
		 * Returns the whole Automaton as a string
		 */
		public String toString() {
			String ca = "";
			for (int i = 0; i < Automaton.length; i++) {
				if (i == Automaton.length - 1) {
					ca += getStateString(i);
				}
				else {
					ca += getStateString(i) + "\n";
				}
			}
			return ca;
		}
		
		/**
		 * Saves the Automaton as string to a file
		 * @param fileName
		 * @throws IOException
		 */
		public void save(String fileName) throws IOException{
			BufferedWriter buffW = new BufferedWriter(new FileWriter(fileName));
			buffW.write(toString());
			buffW.close();
		}
		
		/**
		 * Setter method that sets the false symbol used in boolean 
		 * arrays when creating the Automaton object
		 * @param falseSymbol
		 */
		public void setFalseSymbol(char falseSymbol) {
			this.falseSymbol = falseSymbol;
		}
		
		/*
		 * Getter method that returns the character of the 
		 * false symbol
		 */
		public char getFalseSymbol() {
			return falseSymbol;
		}
		
		/**
		 * Setter method that sets the true symbol used in boolean 
		 * arrays when creating the Automaton object
		 * @param trueSymbol
		 */
		public void setTrueSymbol(char trueSymbol) {
			this.trueSymbol = trueSymbol;
		}
		
		/**
		 * Getter method that returns the character of the
		 * true symbol
		 * @return
		 */
		public char getTrueSymbol() {
			return trueSymbol;
		}
		
		
		
		
		
		
		
		

	}



