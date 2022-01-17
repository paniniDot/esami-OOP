package e2;

import java.util.Map;

public interface Model {

	void hit(int row, int col);

	Map<Pair<Integer, Integer>, Integer> getGridStatus();
	
	boolean gameOver();

}