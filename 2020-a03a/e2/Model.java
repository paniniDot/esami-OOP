package e2;

import java.util.List;

public interface Model {

	Pair<Integer, Integer> getTowerCoords();

	List<Pair<Integer, Integer>> getPawnsCoords();

	List<Pair<Integer, Integer>> getTowerMoves();
	
	void hit(Pair<Integer, Integer> coordinates);

}