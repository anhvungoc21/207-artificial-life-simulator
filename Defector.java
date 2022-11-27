package lifesim;

/**
 * A Defector is an Organism that never cooperates
 */
public class Defector extends Organism {
	
	/**
	 * String for the Type of this Organism
	 */
	private String type;
	
	/**
	 * Double for the cooperation probability of this Organism
	 */
	private double coopProbability;
	
	/**
	 * Constructor for a Defector object
	 */
	public Defector() {
		super();
		this.type = Organism.DEF_NAME;
		this.coopProbability = Organism.DEF_PROB;
	}
	
	/**
	 * Getter for the type of this Organism
	 * @return String of the type of this Organism
	 */
	@Override
	String getType() {
		return this.type;
	}

	/**
	 * Getter for the cooperation probability of this Organism
	 * @return Double for the cooperation probability of this Organism
	 */
	@Override
	double getCooperationProbability() {
		return this.coopProbability;
	}
	
	/**
	 * Simulates reproduction of a Defector
	 * @return a new Defector
	 */
	@Override 
	Organism reproduce() {
		return new Defector();
	}

	/**
	 * Gets whether this Defector cooperates, which is never.
	 * @return Boolean value "false"
	 */
	@Override
	boolean cooperates() {
		return false;
	}
}
