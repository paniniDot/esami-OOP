package e2;

import java.util.List;

public interface Model {
	
	static enum Direction {
		NORTH, SOUTH, WEST, EAST;
	}

	void hitManually(int row, int column);
	
	void hitFromButton();

	List<Pair<Integer,Integer>> getGridStatus();

}