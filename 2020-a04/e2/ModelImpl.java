package e2;

import java.util.ArrayList;
import java.util.List;

public class ModelImpl implements Model {
	
	private final static int NUM_PAWNS = 5;
	
	private int gridSize;
	private Pair<Pawn, Pair<Integer, Integer>> myPawn;
	private List<Pair<Integer, Integer>> pawns;
	
	public ModelImpl(final int size) {
		this.gridSize = size;
		this.myPawn = new Pair<>(Pawn.KING, new Pair<>(this.getRandom(0, this.gridSize-1), this.getRandom(0, this.gridSize-1)));
		this.pawns = new ArrayList<>();
		while(this.pawns.size() < NUM_PAWNS) {
			var coordinates = new Pair<>(this.getRandom(0, this.gridSize-1), this.getRandom(0, this.gridSize-1));
			if(!this.pawns.contains(coordinates) && !this.myPawn.getY().equals(coordinates)) {
				this.pawns.add(coordinates);
			}
		}
		System.out.println(pawns);
	}
	
	private boolean checkMove(int row, int col) {
		int deltaRow = Math.abs(row - this.myPawn.getY().getX());
		int deltaCol = Math.abs(col - this.myPawn.getY().getY());
		return this.myPawn.getX().equals(Pawn.KING) ? deltaRow <= 1 && deltaCol <= 1 : Math.pow(deltaRow, 2) + Math.pow(deltaCol, 2) == 5;
	}
	
	@Override
	public void move(int row, int col) {
		if(this.checkMove(row, col)) {
			Pawn newOne = this.myPawn.getX();
			if(this.pawns.contains(new Pair<>(row, col))) {
				this.pawns.remove(new Pair<>(row, col));
				newOne = newOne.equals(Pawn.KING) ? Pawn.HORSE : Pawn.KING;
			}
			this.myPawn = new Pair<>(newOne, new Pair<>(row, col));
		}
	}
		
	@Override
	public Pair<Pawn, Pair<Integer, Integer>> getMyPawnStatus() {
		return this.myPawn;
	}

	@Override
	public List<Pair<Integer, Integer>> getPawnsStatus() {
		return this.pawns;
	}
	
	private int getRandom(final int min, final int max) {
		return min + (int)(Math.random() * max);
	}

	@Override
	public int getLeftedPawns() {
		return this.pawns.size();
	}

	@Override
	public boolean gameOver() {
		return this.pawns.isEmpty();
	}
	
	
	
}
