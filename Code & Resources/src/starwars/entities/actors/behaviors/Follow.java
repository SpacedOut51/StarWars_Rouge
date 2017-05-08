package starwars.entities.actors.behaviors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import edu.monash.fit2099.simulator.space.Direction;

public class Follow {

	private ArrayList<Direction> moves;
	private int position = 0;

	public Follow(Direction [] moves) {
		this.moves = new ArrayList<Direction>(Arrays.asList(moves));

		// TODO Auto-generated constructor stub
	}

	public Follow(Collection<Direction> moves) {
		this.moves = new ArrayList<Direction>(moves);
	}

	public Direction getNext() {
			Direction nextMove =  moves.get(position);
			position++;
			if (position >= moves.size()) {
				position = 0;
			}
			return nextMove;
	}
}
