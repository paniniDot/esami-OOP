package e2;

import java.util.List;

public interface Model {
	
	static enum Pawn {
		KING("\u265A"), HORSE("\u265E"), PAWN("\u2659"); 
		
		private final String unicode;
		
		private Pawn(final String unicode) {
			this.unicode = unicode;
		}
		
		@Override
		public String toString() {
			return this.unicode;
		}
	}
	
	Pair<Pawn, Pair<Integer, Integer>> getMyPawnStatus();
	
	List<Pair<Integer, Integer>> getPawnsStatus();
	
	void move(int row, int col);
	
	int getLeftedPawns();
	
	boolean gameOver();
	
}