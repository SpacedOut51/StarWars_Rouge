package starwars.actions;

import edu.monash.fit2099.simulator.matter.ActionInterface;
import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.SWActor;
import starwars.SWAffordance;
import starwars.SWEntityInterface;

/*
 * Allows users who can train to offer training to those who can accept it
 * In thise world, the only combination of this is Ben Kenobi training Luke Skywalker
 * 
 */

public class Train extends SWAffordance {
	
	public static ActionInterface affordance;

	public Train(SWEntityInterface theTarget, MessageRenderer m) {
		super(theTarget, m);	
		priority = 1;
	}
	
	/*
	 * Returns a String describing this <code>Train</code>, suitable for display to the user.
	 * 
	 * @see Action.getDescription()
	 * 
	 */
	
	@Override
	public String getDescription() {
		return "accept training from " + this.target.getShortDescription();
	}
	
	@Override
	public boolean canDo(SWActor a) {
		return true;
	}

	
	/*
	 * perform the <code>Train<code> action
	 * 
	 * set the target's force value to increase by 1, using get and set force methods
	 * if the force power has been maxed out for the actor, display a message and remove the Train Affordance
	 * @see starwars.SWActionInterface#act(starwars.SWActor)
	 */
	public void act(SWActor a) {
			
		a.setForce(a.getForce()+1);
		a.say(a.getShortDescription() + " can feel the power of the force growing inside him!");
		// once Luke is at maximum Force (11), remove the option to continue training
		if (a.getForce() >= 11) {
			a.say(a.getShortDescription() + " is now fully trained in the Force!");
			a.removeAffordance(this);
			this.target.removeAffordance(this);		
		
		}
	}

	@Override
	public boolean isForceCommand() {
		return false;
	}
	
}
