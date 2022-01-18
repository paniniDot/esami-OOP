package e2;

import java.util.List;
import java.util.ArrayList;

public class ModelImpl implements Model {

	private final static int NUM_PAWNS = 10;
	
	private	Pair<Integer, Integer> tower;
	private final List<Pair<Integer, Integer>> pawns;
	private final int size;
	
	public ModelImpl(final int size) {
		this.size = size;
		this.tower = new Pair<>(this.getRandom(0, size), this.getRandom(0, size));
		this.pawns = new ArrayList<>();
		while(pawns.size() < NUM_PAWNS) {
			var coord = new Pair<>(this.getRandom(0, size), this.getRandom(0, size));
			if(!this.pawns.contains(coord) && !this.tower.equals(coord)) {
				this.pawns.add(coord);
			}
		}
	}
		
	@Override
	public Pair<Integer, Integer> getTowerCoords() {
		return this.tower;
	}
	
	@Override
	public List<Pair<Integer, Integer>> getPawnsCoords() {
		return this.pawns;
	}
	
	@Override
	public List<Pair<Integer, Integer>> getTowerMoves() {
		List<Pair<Integer, Integer>> coords = new ArrayList<>();
		 for (int i=this.tower.getX(), j=this.tower.getY(); i<this.size; i++) {
			 var p = new Pair<>(i, j);
			 coords.add(p);
	        if (this.pawns.contains(p)) {
		        break;
	        }
	    }
	    for (int i=this.tower.getX(), j=this.tower.getY(); i>=0; i--) {
	    	var p = new Pair<>(i, j);
			coords.add(p);
	        if (this.pawns.contains(p)) {
		        break;
	        }
	    }
	    for (int i=this.tower.getX(), j=this.tower.getY(); j >= 0; j--) {
	    	var p = new Pair<>(i, j);
	    	coords.add(p);
	        if (this.pawns.contains(p)) {
		        break;
	        }
	    }
	    for (int i=this.tower.getX(), j=this.tower.getY(); j<this.size; j++) {
	    	var p = new Pair<>(i, j);
	    	coords.add(p);
	        if (this.pawns.contains(p)) {
	        	break;
	        }
	    }
		return coords;
	}
	
	private int getRandom(int min, int max) {
		return min + (int)(Math.random() * max);
	}

	@Override
	public void hit(Pair<Integer, Integer> coordinates) {
		if(this.getTowerMoves().contains(coordinates)) {
			this.tower = coordinates;
			if(this.pawns.contains(coordinates)) {
				this.pawns.remove(coordinates);
			}
		}
	}	
	
}
