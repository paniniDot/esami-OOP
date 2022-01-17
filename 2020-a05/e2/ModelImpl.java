package e2;

import java.util.HashMap;
import java.util.Map;

public class ModelImpl implements Model {
	
	private final static Pair<Integer, Integer> RANGE = new Pair<>(0, 9);
	
	private final Map<Pair<Integer, Integer>, Integer> grid;
	
	public ModelImpl(final int size) {
		this.grid = new HashMap<>();
		for(int i=0; i<size; i++) {
			for(int j=0; j<size; j++) {
				this.grid.put(new Pair<>(i, j), this.getRandom(RANGE.getX(), RANGE.getY()));
			}
		}
	}
	
	@Override
	public void hit(final int row, final int col) {
		var cellHitted = new Pair<>(row, col);
		int sum = 0;
		for(int i=row-1; i<=row+1; i++) {
			for(int j=col-1; j<=col+1; j++) {
				if(this.grid.containsKey(new Pair<>(i, j))) {
					if(this.grid.containsKey(cellHitted)) {
						this.grid.replace(new Pair<>(i, j), this.grid.get(cellHitted));
					} else {
						sum += this.grid.get(new Pair<>(i, j));
						this.grid.remove(new Pair<>(i, j));
					}
				}
			}
		}
		if(this.grid.containsKey(cellHitted)) {
			this.grid.remove(cellHitted);
		}else {
			this.grid.put(cellHitted, sum);
		}
		
	}
	
	@Override
	public Map<Pair<Integer, Integer>, Integer> getGridStatus() {
		return this.grid;
	}
	
	private int getRandom(final int min, final int max) {
		return min + (int)(Math.random() * max);
	}

	@Override
	public boolean gameOver() {
		return this.grid.isEmpty();
	}
	

}
