package starwars.actions;

import edu.monash.fit2099.simulator.space.Direction;
import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.SWActionInterface;
import starwars.SWActor;
import starwars.SWWorld;
import starwars.SWAffordance;

/* 
 * <code>Affordance<code> for actors to move each other using the force
 * 
 * 
 */

public class ForceMove extends SWAffordance implements SWActionInterface {

	/**Direction in which this <code>ForceMove</code> action must be performed*/
	Direction whichDirection;
	
	/**The world in which this <code>ForceMove</code> action should occur*/
	SWWorld world;
	
	SWActor actor;

	/**
	 * Constructor for <code>ForceMove</code> class. Will initialize the direction and the world for the <code>Move</code>.
	 * 
	 * @param d the <code>Direction</code> in which the Entity is supposed to move
	 * @param m <code>MessageRenderer</code> to display messages
	 * @param world the world in which the <code>Move</code> action needs to happen
	 */
	
	public ForceMove(SWActor a, Direction d, MessageRenderer m, SWWorld world) {
		super(a, m);
		//CompassBearing dir;
		this.world = world;
		this.actor = a;
		this.whichDirection = d;
	}

	/**
	 * Perform the <code>Move</code> action.
	 * <p>
	 * If it is possible for <code>SWActor a</code> to move in the given direction, tell the world to move them
	 * and then reset <code>a</code>'s move commands to take into account a possible new set of available <code>Moves</code>. 
	 * If it is not possible for <code>a</code> to move in that direction, this method does nothing.
	 * <p>
	 * This method will only be called if the <code>SWActor a</code> is alive
	 * 
	 * @author 	ram
	 * @param 	a the <code>SWActor</code> who is moving
	 */
	public void act(SWActor a) {
				
		if (world.canMove(a, whichDirection)) {
			world.moveEntity((SWActor) this.getTarget(), whichDirection);
			((SWActor) this.getTarget()).setForceableLocations();

		}
		
	}		


		
		// replace command list of this SWActor
	//	actor.actions = newActions;		
		
		// TODO: This assumes that the only actions are the Move actions. This will clobber any others. Needs to be fixed.
		/* Actually, that's not the case: all non-movement actions are transferred to newActions before the movements are transferred. --ram */
	//}
	
	/**
	 * This is a wrapper for getDescription().
	 * 
	 * @author ram
	 * @return a String describing this <code>Move</code>, suitable for display to the user
	 */
	@Override
	public String toString() {
		return getDescription();
	}

	
	@Override
	public String getDescription() {
		return ("force " + this.target.getShortDescription() + " to move " + whichDirection);
	}

	/**
	 * Returns true, since this is a move command.  
	 * 
	 * TODO: This may be able to be replaced with a Capability.
	 * 
	 * @author ram
	 * @return true
	 */
	public boolean isMoveCommand() {
		return true;
	}

	public boolean isForceCommand() {
		return true;
	}
	
	/**
	 *Returns the time taken to perform this <code>Move</code> action.
	 *
	 *@return the duration of the <code>Move</code> action. Currently hard coded to return 1
	 */
	@Override
	public int getDuration() {
		return 1;
	}

	/**
	 * Returns if or not a <code>SWActor a</code> can perform a <code>Move</code> command.
	 * <p>
	 * This method returns true if <code>actor<code> is not dead and has enough force to use this ability 
	 * <p>
	 * We assume that actors don't get movement commands attached to them unless they can
	 * in fact move in the appropriate direction.  If this changes, then this method will
	 * need to be altered or overridden.
	 * 
	 * @author 	ram
	 * @param 	a the <code>SWActor</code> doing the moving
	 * @return 	true if and only if <code>a</code> is not dead, false otherwise.
	 * @see 	{@link starwars.SWActor#isDead()}
	 */
	@Override
	public boolean canDo(SWActor a) {
		//can be performed if target isnt dead and hasn't got enough force to resist
		return (!a.isDead() && a.getForce() > 8);
	}
	
	
	/**
	 * Returns the <code>Direction</code> in which this <code>Move</code> is directed.
	 * 
	 * @author 	Asel
	 * @return 	The <code>Direction</code> of this <code>Move</code>
	 * @see 	{@link #whichDirection}
	 */
	public Direction getWhichDirection() {
		return whichDirection;
	}


}
