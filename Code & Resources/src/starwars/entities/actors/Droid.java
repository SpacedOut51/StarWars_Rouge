package starwars.entities.actors;

import edu.monash.fit2099.simulator.space.Direction;
import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.SWActor;
import starwars.SWLocation;
import starwars.SWWorld;
import starwars.Team;
import starwars.actions.Move;
import starwars.entities.actors.behaviors.AttackInformation;
import starwars.entities.actors.behaviors.AttackNeighbours;
import starwars.entities.actors.behaviors.Patrol;

public class Droid extends SWActor {

	private boolean isimmobile;
	private String name;
	private Patrol path;
	private Droid owner;

	/**
	 * Droid description ::
	 *
	 * @param team the <code>Team</code> to which the this <code>Player</code> belongs to

	 * @param hitpoints
	 *            the number of hit points of this Tusken Raider. If this
	 *            decreases to below zero, the Raider will die.
	 * @param name
	 *            this droids's name. Used in displaying descriptions.
	 * @param m
	 *            <code>MessageRenderer</code> to display messages.
	 * @param world
	 *            the <code>SWWorld</code> world to which this
	 *            <code>TuskenRaider</code> belongs to
	 * @param isimmobile
	 *        	  boolean value to identify whether a droid is immobile
	 *
	 */
	public Droid(Team team, int hitpoints, MessageRenderer m, SWWorld world, boolean isimmbole) {
		super(team, hitpoints, m, world);
		this.isimmobile = isimmobile;
		this.name = "droid"; // set default name
	}

	public void setName(String n) {
		this.name = n;
	}

	public void setPath(Direction[] moves){
		path = new Patrol(moves);
	}

	public void setOwner(Droid owner){
		owner = owner;
	}

	@Override
	public void act() {

		if (isDead()) {
			this.isimmobile = true;
			return;
		}
		say(describeLocation());

		AttackInformation attack = AttackNeighbours.attackLocals(this, this.world, false, false);
		if (attack != null) {
			say(getShortDescription() + " has attacked " + attack.entity.getShortDescription());
			scheduler.schedule(attack.affordance, this, 1);
		}
		else {
			Direction newdirection = path.getNext();
			say(getShortDescription() + " moves " + newdirection);
			Move myMove = new Move(newdirection, messageRenderer, world);
			scheduler.schedule(myMove, this, 1);
		}
	}

	@Override
	public String getShortDescription() {
		return name;
	}

	@Override
	public String getLongDescription() {
		return this.getShortDescription();
	}

	private String describeLocation() {
		SWLocation location = this.world.getEntityManager().whereIs(this);
		return this.getShortDescription() + " [" + this.getHitpoints() + "] is at " + location.getShortDescription();

	}
}
