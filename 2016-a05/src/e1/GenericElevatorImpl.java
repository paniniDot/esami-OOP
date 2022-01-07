package e1;

import java.util.HashSet;
import java.util.Set;


public abstract class GenericElevatorImpl implements Elevator {
	
	public enum Direction {
		UP,
		DOWN;
	}
	
	private int currentFloor;
	private final Set<Integer> pendingCalls;
	private boolean isMoving;
	private Direction direction; /* true for up, false for down */
	
	public GenericElevatorImpl() {
		this.currentFloor = 0;
		this.pendingCalls = new HashSet<>();
		this.isMoving = false;
		this.direction = Direction.UP;
	}

	@Override
	public int getCurrentFloor() {
		return this.currentFloor;
	}
	
	@Override
	public boolean isMoving() {
		return !this.pendingCalls.isEmpty();
	}

	@Override
	public boolean isMovingUp() {
		return this.isMoving && this.direction == Direction.UP;
	}

	@Override
	public boolean isMovingDown() {
		return this.isMoving && this.direction == Direction.DOWN; 
	}

	@Override
	public void call(int floor) {
		this.pendingCalls.add(floor);
		this.isMoving = true;
		this.direction = (this.onCall() ? Direction.UP : Direction.DOWN);
	}
	
	protected abstract boolean onCall();
	
	@Override
	public void moveToNext() {
		int nextFloor = this.onMoveToNext();
		this.currentFloor = nextFloor;
		this.pendingCalls().remove(nextFloor);
		if(this.pendingCalls.size() == 0) {
			this.isMoving = false;
		} else if(this.pendingCalls.stream().max(Integer::compareTo).get() < this.currentFloor) {
			this.direction = Direction.DOWN;
		}
	}
	
	protected void setCurrentFloor(int floor) {
		this.currentFloor = floor;
	}
	
	protected abstract int onMoveToNext();

	@Override
	public Set<Integer> pendingCalls() {
		return this.pendingCalls;
	}
}
