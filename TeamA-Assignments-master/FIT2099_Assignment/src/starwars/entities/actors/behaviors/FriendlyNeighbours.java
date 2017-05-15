package starwars.entities.actors.behaviors;

import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.simulator.matter.Affordance;
import edu.monash.fit2099.simulator.matter.EntityManager;
import starwars.SWActor;
import starwars.SWEntityInterface;
import starwars.SWLegend;
import starwars.SWLocation;
import starwars.SWWorld;
import starwars.actions.Train;

/*
 * Gets a list of friendly entities who are in the same location
 * 
 * used for finding <code>actor<code>s to <code>Train<code>, if they have the Train affordance
 * based on <code>AttackNeighbours<code>, used by Tuskens to find enemies
 * 
 * currently, it is only possible to find one option, so helpables(0) is the only selection
 * 
 * 
 */

public class FriendlyNeighbours {

	
	public static FriendlyInformation helpLocals(SWActor actor, SWWorld world) {
		SWLocation location = world.getEntityManager().whereIs(actor);
		EntityManager<SWEntityInterface, SWLocation> em = world.getEntityManager();
		List<SWEntityInterface> entities = em.contents(location);

		// select the friendly things that are here
		ArrayList<FriendlyInformation> helpables = new ArrayList<FriendlyInformation>();
		for (SWEntityInterface e : entities) {
			// Figure out if we can help this entity
			if (e != actor && // if not self and...
					((e instanceof SWActor) || (e instanceof SWLegend)) && // entity is an actor or legend, and... 
					((SWActor)e).getTeam() == actor.getTeam()) { // they are on the same team, then...
				for (Affordance a : e.getAffordances()) {
					if (a instanceof Train) {

						helpables.add(new FriendlyInformation(e, a));
						break;
					}
				}
			}
		}

		if (helpables.size() > 0) {
			return helpables.get(0);
		}
		else {
			return null;
		}

	}
}
