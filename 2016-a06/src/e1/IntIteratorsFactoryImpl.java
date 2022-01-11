package e1;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.IntStream;

public class IntIteratorsFactoryImpl implements IntIteratorsFactory {

	@Override
	public Iterator<Integer> empty() {
		return Collections.emptyIterator();
	}

	@Override
	public Iterator<Integer> zeros() {
		return IntStream.generate(() -> 0).iterator();
	}

	@Override
	public Iterator<Integer> alternateOneAndZeroIndefinitely() {
		return this.fromToIndefinitely(0, 1);
	}

	@Override
	public Iterator<Integer> fromTo(int start, int end) {
		return IntStream.rangeClosed(start, end).iterator();
	}

	@Override
	public Iterator<Integer> fromToIndefinitely(int start, int stop) {
		//return IntStream.generate(() -> this.fromTo(start, stop).next()).generate();
		return new Iterator<Integer>() {
			Iterator<Integer> it = fromTo(start, stop);
			@Override
			public boolean hasNext() {
				return true;
			}

			@Override
			public Integer next() {
				if(it.hasNext()) {
					return it.next();
				} else {
					it = fromTo(start, stop);
					return it.next();
				}
			}
			
		};
	}

	@Override
	public Iterator<Integer> fromList(List<Integer> list) {
		return list.iterator();
	}

}
