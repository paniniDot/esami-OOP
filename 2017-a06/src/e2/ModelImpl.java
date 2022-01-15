package e2;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class ModelImpl implements Model {
		
	private final List<Integer> numbers;
	private final int size; 
	
	public ModelImpl(int size) {
		this.size = size;
		this.numbers = new ArrayList<>();
		this.reset();
	}
	
	@Override
	public void hit(int position) {
		if(position == 0) {
			this.numbers.set(position, this.numbers.get(position) + this.numbers.get(position+1));
		} else if(position == this.size-1) {
			this.numbers.set(position, this.numbers.get(position) + this.numbers.get(position-1));
		} else {
			this.numbers.set(position, this.numbers.get(position) + this.numbers.get(position+1) + this.numbers.get(position-1));
		}
	}
	
	@Override
	public String getStatusAt(int position) {
		return this.numbers.get(position).toString();
	}

	@Override
	public void reset() {
		IntStream.range(0, this.size).forEach(i -> this.numbers.add(i, 1));
	}

}
