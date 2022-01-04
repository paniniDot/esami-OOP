package e2;

public class ModelImpl implements Model {
	
	
	private int ACurrentPos;
	private int BCurrentPos;
	private final int limit;

	public ModelImpl(final int limit) {
		this.reset();
		this.limit = limit;
	}
	
	@Override
	public void moveA() {
		if(this.ACurrentPos + 1 < this.BCurrentPos) {
			this.ACurrentPos++;
		}
	}
	
	@Override
	public void moveB() {
		if(this.BCurrentPos + 1 < this.limit) {
			this.BCurrentPos++;
		}
	}
	
	@Override
	public int getACurrentPosition() {
		return this.ACurrentPos;
	}
	
	@Override
	public int getBCurrentPosition() {
		return this.BCurrentPos;
	}
	
	@Override
	public void reset() {
		this.ACurrentPos = 0;
		this.BCurrentPos = 1;
	}
	
	@Override
	public boolean isOver() {
		return this.ACurrentPos == this.limit - 2 && this.BCurrentPos == this.limit - 1;
	}
	
}
