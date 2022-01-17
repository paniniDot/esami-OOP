package e2;

import java.util.ArrayList;
import java.util.List;

public class ModelImpl implements Model {
	
	private final static int NUM_PAWNS = 5;
	
	private Pair<Integer, Integer> bishop;
	private final List<Pair<Integer, Integer>> pawns;
	private final int size;
	
	public ModelImpl(final int size) {
		this.bishop = new Pair<>(this.getRandom(0, size), this.getRandom(0, size));
		this.pawns = new ArrayList<>();
		while(this.pawns.size() < NUM_PAWNS) {
			var pawnCoord = new Pair<>(this.getRandom(0, size), this.getRandom(0, size));
			if(!pawnCoord.equals(bishop) && !this.pawns.contains(pawnCoord)) {
				this.pawns.add(pawnCoord);
			}
		}
		this.size = size;
	}
	
	@Override
	public void hit(final Pair<Integer, Integer> pos) {
		if(this.getBishopMoves().contains(pos)) {
			this.bishop = pos;
			if(this.pawns.contains(pos)) {
				this.pawns.remove(pos);
			}
		}
	}	

	@Override
	public List<Pair<Integer, Integer>> getBishopMoves() {
		final List<Pair<Integer, Integer>> bishopMoves = new ArrayList<>();
		for(int i=bishop.getX(), j=bishop.getY(); i<size && j<size; i++, j++) {
			var p = new Pair<>(i, j);
	        bishopMoves.add(p);
	        if(this.pawns.contains(p)) {
	        	break;
	        }
	    }
	    for(int i=bishop.getX(), j=bishop.getY(); i>=0 && j>=0; i--, j--) {
	    	var p = new Pair<>(i, j);
	    	bishopMoves.add(p);
	    	if(this.pawns.contains(p)) {
	        	break;
	        }
	    }
	    for(int i=bishop.getX(), j=bishop.getY(); i<size && j>=0; i++, j--) {
	    	var p = new Pair<>(i, j);
	    	bishopMoves.add(p);
	    	if(this.pawns.contains(p)) {
	        	break;
	        }
	    }
	    for(int i=bishop.getX(), j=bishop.getY(); i>=0 && j<size; i--, j++) {
	    	var p = new Pair<>(i, j);
	    	bishopMoves.add(p);
	    	if(this.pawns.contains(p)) {
	        	break;
	        }
	    }
	    return bishopMoves;
	}
	
	@Override
	public Pair<Integer, Integer> getBishopCoords() {
		return this.bishop;
	}

	@Override
	public List<Pair<Integer, Integer>> getPawnsCoords() {
		return this.pawns;
	}
	
	private int getRandom(int min, int max) {
		return min + (int)(Math.random() * max);
	}
	
}
