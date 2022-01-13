package e1;

import java.util.Iterator;
import java.util.Optional;
import java.util.stream.Stream;

public class SequenceAcceptorImpl implements SequenceAcceptor {
	
	private Iterator<Integer> iterator;
	private Optional<Sequence> sequence = Optional.empty();
	
	@Override
	public void reset(Sequence sequence) {
		this.sequence = Optional.of(sequence);
		if(sequence.equals(Sequence.POWER2)) {
			this.iterator = Stream.iterate(1, t -> t*2).iterator();
		} else if(sequence.equals(Sequence.FLIP)) {
			this.iterator = Stream.iterate(1, t -> 1-t).iterator();
		} else if(sequence.equals(Sequence.RAMBLE)) {
			this.iterator = Stream.iterate(0, t -> t+1).flatMap(t -> Stream.of(0, t+1)).iterator();
		} else {
			this.iterator = Stream
					.iterate(new Pair<>(1,1), t-> new Pair<>(t.getY(), t.getX()+t.getY()))
					.map(Pair::getX)
					.iterator();
		}
	}

	@Override
	public void reset() {
		if(this.sequence.isPresent()) {
			this.reset(this.sequence.get());
		} else {
			throw new IllegalStateException();
		}
	}

	@Override
	public void acceptElement(int i) {
		if(this.sequence.isEmpty() || !this.iterator.next().equals(i)) {
			throw new IllegalStateException();
		}
	}

}
