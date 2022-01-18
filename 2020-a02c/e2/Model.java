package e2;

import java.util.List;

public interface Model {

	void hit(Pair<Integer, Integer> coordinates);
	
	List<Pair<Integer, Integer>> getPressed();
	
	boolean gameOver();
	
}