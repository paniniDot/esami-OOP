package e2;

import java.util.HashMap;
import java.util.Map;

public class ModelImpl implements Model {

	private final static int MIN = 1;
	private final static int MAX = 5;
	private final static int NUM_OBSTACLES = 4;
	
	private final Map<Integer, Integer> obstacles;
	private final int size;
	private int currentPos;
	
	public ModelImpl(final int size) {
		this.size = size;
		this.reset();
		this.obstacles = new HashMap<>();
		while(this.obstacles.size() < NUM_OBSTACLES) {
			this.obstacles.put(this.getRandom(1, this.size-1), this.getRandom(MIN, MAX));
		}
	}
	
	private int getRandom(final int min, final int max) {
		return min + (int)(Math.random() * max); 
	}
	
	@Override
	public void move() {
		this.currentPos++;
		if(this.obstacles.containsKey(this.currentPos)) {
			this.obstacles.replace(this.currentPos, this.obstacles.get(this.currentPos)-1);
			if(this.obstacles.get(currentPos) == 0) {
				this.obstacles.remove(currentPos);
			}
			this.reset();
		}
		System.out.print(this.obstacles);
	}
	
	@Override
	public int getCurrentPosition() {
		return this.currentPos;
	}
	
	@Override
	public Map<Integer, Integer> getObstaclePositions() {
		return this.obstacles;
	}
	
	private void reset() {
		this.currentPos = 0;
	}

	@Override
	public boolean gameOver() {
		return this.obstacles.isEmpty();
	}
}
