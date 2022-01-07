package e2;

public class ModelImpl implements Model {
	
	private int pos;
	private final int size;
	private boolean direction;
	
	public ModelImpl(int size) {
		this.size = size;
		this.pos = 0;
		this.direction = true;
	}
	
	@Override
	public void move() {
		if(this.direction && this.pos+1 < this.size) {
			this.pos++;
		} else if (!this.direction && this.pos-1 >= 0) {
			this.pos--;
		}
	}
	
	@Override
	public int getPosition() {
		return this.pos;
	}
	
	@Override
	public void changeDirection() {
		this.direction = !this.direction;
	}
	
}
