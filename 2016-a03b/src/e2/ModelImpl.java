package e2;

import java.util.Optional;

public class ModelImpl implements Model {
	
	private Optional<Integer> disabled;
	private int currentPos;
	private final int size;
	private boolean toRight;
	
	public ModelImpl(int positions) {
		this.disabled = Optional.empty();
		this.currentPos = 0;
		this.size = positions;
		this.toRight = true;
	}
	
	private void checkDirection() {
		if(this.currentPos+1 >= this.size) {
			this.toRight = false;
		} else if(this.currentPos-1 < 0) {
			this.toRight = true;
		}
	}
	
	@Override
	public void move() {
		System.out.print("[" + this.currentPos + " -> ");
		this.currentPos = (this.toRight ? this.currentPos + 1 : this.currentPos - 1);
		System.out.println( this.currentPos + "]");
		this.checkDirection();
	}
	
	@Override
	public int getPosition() {
		return this.currentPos;
	}
	
	@Override
	public void disable(int position) {
		this.disabled = Optional.of(position);
	}

	@Override
	public boolean isOver() {
		return this.currentPos == this.disabled.orElse(-1);
	}	
}
