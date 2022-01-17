package e2;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ModelImpl implements Model {
	
	private final static int SNAKE_INITIAL_LENGTH = 2;

	private final List<Pair<Integer, Integer>> snake;
	private int currentLength;
	private Direction lastDirection;
	private final int size;
	
	public ModelImpl(int size) {
		this.size = size;
		this.currentLength = SNAKE_INITIAL_LENGTH;
		this.lastDirection = Direction.SOUTH;
		int rand = this.random(0, size-1);
		this.snake = IntStream.range(0, SNAKE_INITIAL_LENGTH).mapToObj(i -> new Pair<>(i, rand)).collect(Collectors.toList());
		System.out.println(this.snake);
	}
	
	private boolean checkBounds(int pos) {
		return pos >= 0 && pos < this.size;
	}
	
	private boolean checkPosition(int row, int column) {
		int deltaRow = Math.abs(row - this.snake.get(this.currentLength-1).getX());
		int deltaCol = Math.abs(column - this.snake.get(this.currentLength-1).getY());
		return !this.snake.contains(new Pair<>(row, column)) && ((deltaRow <= 1 && deltaCol <= 0) || (deltaRow <= 0 && deltaCol <= 1)) && this.checkBounds(row) && this.checkBounds(column);
	}
	
	private void setLastDirection() {
		Direction dir;
		if(this.snake.get(this.currentLength-1).getX() < this.snake.get(this.currentLength-2).getX()) {
			dir = Direction.NORTH;
		} else if(this.snake.get(this.currentLength-1).getX() > this.snake.get(this.currentLength-2).getX()) {
			dir = Direction.SOUTH;
		} else if(this.snake.get(this.currentLength-1).getY() < this.snake.get(this.currentLength-2).getY()) {
			dir = Direction.WEST;
		} else {
			dir = Direction.EAST;
		}
		this.lastDirection = dir;
	}
	
	private void updateSnake(int row, int column) {
		this.currentLength++;
		this.snake.add(this.currentLength-1, new Pair<>(row, column));
		this.setLastDirection();
	}
	
	@Override
	public void hitManually(int row, int column) {
		if(this.checkPosition(row, column)) {
			this.updateSnake(row, column);
		}
	}
	
	@Override
	public void hitFromButton() {
		if(this.lastDirection.equals(Direction.NORTH)) {
			this.hitManually(this.snake.get(this.currentLength-1).getX()-1, this.snake.get(this.currentLength-1).getY());
		} else if(this.lastDirection.equals(Direction.SOUTH)) {
			this.hitManually(this.snake.get(this.currentLength-1).getX()+1, this.snake.get(this.currentLength-1).getY());
		} else if(this.lastDirection.equals(Direction.WEST)) {
			this.hitManually(this.snake.get(this.currentLength-1).getX(), this.snake.get(this.currentLength-1).getY()-1);
		} else {
			this.hitManually(this.snake.get(this.currentLength-1).getX(), this.snake.get(this.currentLength-1).getY()+1);
		}
	}
	
	@Override
	public List<Pair<Integer,Integer>> getGridStatus() {
		return this.snake;
	}
	
	private int random(int min, int max) {
		return min + (int)(Math.random() * max);
	}
	
}
