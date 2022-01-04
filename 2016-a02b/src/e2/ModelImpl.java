package e2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class ModelImpl implements Model {
	
	private final int startPos;
	private final int endPos; 
	private final int nPositions;
	private final List<Integer> currentPositions = new ArrayList<>();
	
	public ModelImpl(final int nPositions, final int size) {
		this.startPos = 0;
		this.endPos = size-1;
		this.nPositions = nPositions;
		if(nPositions > size) {
			throw new IllegalArgumentException("Unable to have more crosses than cells");
		}
		IntStream.range(startPos, this.nPositions).forEach(i -> this.currentPositions.add(i));
	}
	
	private int nextPos(final int currentPos) {		
		return (currentPos == this.endPos ? this.startPos : currentPos+1);
	}
	
	@Override
	public void updateCurrentPositions() {
		IntStream.range(0, this.nPositions).forEach(pos -> this.currentPositions.set(pos, this.nextPos(this.currentPositions.get(pos))));
	}
	
	@Override
	public List<Integer> getPositions() {
		return Collections.unmodifiableList(this.currentPositions);
	}
	
	
	
}
