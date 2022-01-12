package e2;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ModelImpl implements Model {
	
	private final int limit;
	private final List<Integer> positions;
	
	public ModelImpl(final int size) {
		this.limit = size;
		this.positions = IntStream.range(0, size).mapToObj(i -> 0).collect(Collectors.toList());
	}
	
	private void checkPosition(final int position) {
		if(position<0 || position>=this.limit) {
			throw new IllegalArgumentException();
		}
	}
	
	@Override
	public String getText(int position) {
		this.checkPosition(position);
		return this.positions.get(position) >= this.limit ? 
				""+this.positions.get(position) 
				: ""+(this.positions.set(position, this.positions.get(position)+1)+1);
	}
	
	@Override
	public boolean shouldQuit() {
		return this.positions.stream().allMatch(i -> i.equals(this.positions.get(0)));
	}

	@Override
	public String print() {
		return this.positions.stream().map(String::valueOf).collect(Collectors.joining("|", "<<", ">>"));
	}

	@Override
	public boolean checkIfEnabled(int position) {
		this.checkPosition(position);
		return this.positions.get(position) < this.limit;
	}
	
	
	
}
