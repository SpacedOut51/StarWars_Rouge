package starwars.entities.actors.behaviors;

import edu.monash.fit2099.simulator.matter.Affordance;
import starwars.SWEntityInterface;

public class FriendlyInformation {

	public SWEntityInterface entity;
	public Affordance affordance;
	public FriendlyInformation(SWEntityInterface e, Affordance a) {
		entity = e;
		affordance = a;
	}
}
