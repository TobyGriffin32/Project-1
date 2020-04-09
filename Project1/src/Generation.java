	/**
	 * 
	 * @author Toby Griffin collaborators: Peter Nguyen, Rohan Gaur, Tayo Dina, Bryce McDaniel
	 * 
	 * 
	 *
	 */
	public class Generation {
	
	/**
	 * Method that returns the next generation of an Automaton as a boolean 
	 * array based on the state of the a given cell in the previous step's
	 * state. When iterating through the currState boolean array, inside the for
	 * loop another boolean array is created. The array, cellNeighborhood, 
	 * represents an array with a size of three that holds the current states 
	 * of one cell and the cell on the left and right of that cell.
	 * @param rule
	 * @param currState
	 * @return
	 */
	public static boolean[] nextGeneration(Rule rule, boolean[] currState) {
		boolean[] nextGeneration = new boolean[currState.length];
		for(int i =0; i < nextGeneration.length; i++) {
			boolean[] cellNeighborhood = new boolean[3];
			cellNeighborhood[1] = currState[i];
			try {
				cellNeighborhood[0] = currState[i-1];
			}
			catch(Exception ArrayOutOfBounds) {
				cellNeighborhood[0] = currState[currState.length-1];
			}
			
			try {
				cellNeighborhood[2] = currState[i+1];
			}
			catch(Exception ArrayOutOfBounds) {
				cellNeighborhood[2] = currState[0];
			}
			
			nextGeneration[i] = rule.calculateRule(cellNeighborhood);
		}
		return nextGeneration;
	}
}

