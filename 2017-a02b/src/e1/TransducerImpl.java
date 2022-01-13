package e1;

import java.util.ArrayList;
import java.util.List;

public abstract class TransducerImpl<X, Y> implements Transducer<X, Y> {

	private final List<X> input = new ArrayList<>(); 
	private boolean inputOver = false; 
	private final int size;
	
	protected TransducerImpl(final int size) {
		this.size = size;
	}	
	
	@Override
	public void provideNextInput(X x) {
		if(!this.inputOver) {
			this.input.add(x);
		}
	}

	@Override
	public void inputIsOver() {
		if(this.inputOver) {
			throw new IllegalStateException();
		}
		this.inputOver = true;
	}

	@Override
	public boolean isNextOutputReady() {
		return this.input.size() >= this.size || this.inputOver && this.input.size() > 0;
	}

	protected int getLeft() {
		return this.inputOver ? this.input.size() : this.size;
	}
	
	protected List<X> getInputList() {
		return this.input;
	}	
	
	@Override
	public Y getOutputElement() {
		if(!this.isNextOutputReady()) {
			throw new IllegalStateException();
		} 
		return this.onGetOutputElement();
	}
	
	protected abstract Y onGetOutputElement();	
	
	@Override
	public boolean isOutputOver() {
		return !this.isNextOutputReady() && this.inputOver;
	}

}
