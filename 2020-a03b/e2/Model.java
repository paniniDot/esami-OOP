package e2;

import java.util.List;

public interface Model {

	Pair<Integer, Integer> getBishopCoords();

	List<Pair<Integer, Integer>> getPawnsCoords();
	
	List<Pair<Integer, Integer>> getBishopMoves();

	void hit(final Pair<Integer, Integer> pos);
	
}