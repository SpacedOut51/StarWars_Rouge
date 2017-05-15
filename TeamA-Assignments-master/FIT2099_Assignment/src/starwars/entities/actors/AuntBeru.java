package starwars.entities.actors;

import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.SWLegend;
import starwars.SWWorld;
import starwars.Team;

/*
 * Stagnant entity to be used for testing Luke's forceMove abilities. 
 * Extends SWLegend as she is a singleton in the SWWorld
 * 
 */

public class AuntBeru extends SWLegend {

	public AuntBeru(MessageRenderer m, SWWorld world) {
		super(Team.GOOD, 25, m, world); 
		this.setShortDescription("Aunt Beru");
		this.setLongDescription("Like a mother to Luke, a kind and caring woman.");
	}

	@Override
	protected void legendAct() {
		say(this.getShortDescription() + " is standing still at " + this.world.getEntityManager().whereIs(this).getShortDescription());
	}
}
