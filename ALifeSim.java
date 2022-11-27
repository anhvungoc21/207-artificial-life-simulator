package lifesim;

/**
 * The ALifeSim class simulates artificial life
 */
public class ALifeSim {
	
	/**
	 * Main method for running the main ALifeSim program
	 */
	public static void main(String[] args) {
		try {
			int iters = Integer.parseInt(args[0]);
			int coops = Integer.parseInt(args[1]);
			int defs = Integer.parseInt(args[2]);
			int partials = Integer.parseInt(args[3]);
		} catch(Exception e) {
			
		}

	}
	
	/**
	 * Static method for printing a help message
	 */
	private static void printHelp() {
		System.out.println("Invalid program usage. Usage:");
		System.out.println("java ALifeSim <#/iterations> <#/cooperators> <#/defectors> <#/partial cooperators>");
	}
}
