package e2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ModelImpl implements Model {

	private final List<Pair<Integer, Integer>> pressed;
	private final List<Pair<Integer, Integer>> lastPressed;
	private final int size;
	
	public ModelImpl(final int size) {
		this.size = size;
		this.pressed = new ArrayList<>();
		this.lastPressed = new ArrayList<>();
	}
 	
	private void addToLastPressed(final Pair<Integer, Integer> coordinates) {
		if(this.lastPressed.size() == 4) {
			this.lastPressed.remove(0);
		}
		this.lastPressed.add(coordinates);
	}
	
	@Override
	public void hit(final Pair<Integer, Integer> coordinates) {
		if(!this.pressed.contains(coordinates)) {
			this.pressed.add(coordinates);
			this.addToLastPressed(coordinates);
		}
	}
	
	@Override
	public List<Pair<Integer, Integer>> getPressed() {
		return Collections.unmodifiableList(this.pressed);
	}
	
	
	private boolean checkIfAdjacent(Pair<Integer, Integer> coordA, Pair<Integer, Integer> coordB) {
		int modX = Math.abs(coordA.getX() - coordB.getX());
		int modY = Math.abs(coordA.getY() - coordB.getY());
		return modX <= 1 && modY <= 1;
	}
	
	private boolean checkIfOnEdges(Pair<Integer, Integer> coords) {
		return coords.getX() <= 0 || coords.getX() >= this.size-1 || coords.getY() <= 0 || coords.getY() >= this.size-1;
	}
	
	@Override
	public boolean gameOver() {
		if(this.lastPressed.size() < 4) {
			return false;
		}
		for(int i=0; i<this.lastPressed.size(); i++) {
			for(int j=0; j<this.lastPressed.size(); j++) {
				if(!this.checkIfAdjacent(this.lastPressed.get(i), this.lastPressed.get(j)) 
						|| this.checkIfOnEdges(this.lastPressed.get(i)) 
						|| this.checkIfOnEdges(this.lastPressed.get(j))) {
					return false;
				}
			}
		}
		return true;
	}
	
}
