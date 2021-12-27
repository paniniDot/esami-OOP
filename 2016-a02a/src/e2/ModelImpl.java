package e2;

import java.util.HashSet;
import java.util.Set;

public class ModelImpl implements Model {
	
	private Direction dir;
	private int current;
	private final int size;
	private Set<Integer> disabled;
	
	public ModelImpl(final int size) {
		this.size = size;
		this.reset();
	}
	
	@Override
	public boolean setDisabled(final int position) {
		if(this.current != position) {
			this.disabled.add(position);
			return true;
		}	
		return false;
	}
	
	private int delta() {
		return this.dir == Direction.right ? +1 : -1;
	}
	
	private void changeDirection() {
		this.dir = this.dir == Direction.right ? Direction.left : Direction.right;
	}
	
	@Override
	public void step() {
		if(!this.updateDirection()) {
			this.changeDirection();
			this.updateDirection();
		}
	}
	
	private boolean updateDirection() {
		if(!this.disabled.contains(this.current + this.delta())) {
			this.current += this.delta();
			return true;
		}
		return false;
	}
	
	@Override
	public int getCurrent() {
		return this.current;
	}
	
	@Override
	public void reset() {
		this.dir = Direction.right;
		this.current = 0;
		this.disabled = new HashSet<>(size);
		this.disabled.add(-1);
		this.disabled.add(size);
	}
	
}
