package e2;

import java.util.List;

public interface Model {
	
	static enum Piece {
		KING("\u265A"), HORSE("\u265E"), PAWN("\u2659"); 
		
		private final String unicode;
		
		private Piece(final String unicode) {
			this.unicode = unicode;
		}
		
		@Override
		public String toString() {
			return this.unicode;
		}
	}
	
	Pair<Piece, Pair<Integer, Integer>> getPlayerPosition();
	
	List<Pair<Integer, Integer>> getPawnPositions();
	
	void move(int row, int col);
	
	int getLeftedPawns();
	
	boolean gameOver();
	
}