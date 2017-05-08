package starwars.entities;

import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.Capability;
import starwars.SWEntity;
import starwars.actions.Take;

/**
 * A Oil Can that can be used to contain oil (non-refillable).
 *
 *
 * @author Tristan Honeychurch
 *
 */
public class Parts extends SWEntity {

	private int capacity;
	private int level;

	public Parts(MessageRenderer m, int capacity, int initialLevel)  {
		super(m);
		this.shortDescription = "an oil can";
		this.longDescription = "an oil can, which would theoretically be useful for fixing robots";

		this.capacity = capacity;
		this.level= initialLevel;
	}

	/**
	 * A symbol that is used to represent the OilCan on a text based user interface
	 *
	 * @return 	A String containing a single character.
	 * @see 	{@link starwars.SWEntityInterface#getSymbol()}
	 */
	@Override
	public String getSymbol() {
		return "p";
	}


	public void fill() {

		level = capacity;
	}
	@Override
	public String getShortDescription() {
		return shortDescription + " [" + level + "/" + capacity + "]";
	}

	@Override
	public String getLongDescription () {
		return longDescription + " [" + level + "/" + capacity + "]";
	}
}
