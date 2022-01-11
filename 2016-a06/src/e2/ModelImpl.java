package e2;

import java.util.Optional;


public class ModelImpl implements Model {
	private static final String EMPTY = "-";
	private Optional<Integer> start;
	private Optional<Integer> stop;
	
	public ModelImpl() {
		this.start = Optional.empty();
		this.stop = Optional.empty();
	}
	
	@Override
	public void addPosition(int position) {
		if(this.start.isEmpty()) {
			this.start = Optional.of(position);
		} else if(this.stop.isEmpty() && position != this.start.get()) {
			this.stop = Optional.of(position);
		}
	}
	
	private int evaluateRelativePosition(int position) {
		return position-this.start.get();
	}
	
	@Override
	public String setText(int position) {
		if(this.start.isPresent() && this.stop.isPresent() && this.isEnabled(position)) {
			return ""+this.evaluateRelativePosition(position);
		} else {
			return ModelImpl.EMPTY;
		}
	}

	@Override
	public boolean isEnabled(int position) {
        if (this.start.isPresent() && position <= this.start.get()) {
            return false;
        }
        if (this.stop.isPresent() && position >= this.stop.get()) {
            return false;
        }
        return true;
	}	
	
}
