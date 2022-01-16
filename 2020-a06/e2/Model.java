package e2;

import java.util.Map;

public interface Model {

	void move();

	int getCurrentPosition();

	Map<Integer, Integer> getObstaclePositions();
	
	boolean gameOver();

}