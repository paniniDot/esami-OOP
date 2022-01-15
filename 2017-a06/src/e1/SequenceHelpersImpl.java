package e1;

import java.util.Iterator;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class SequenceHelpersImpl implements SequenceHelpers {

	@Override
	public <X> Sequence<X> of(X x) {
		return () -> x;
	}

	@Override
	public <X> Sequence<X> cyclic(List<X> l) {
		return new Sequence<X>() {
			Iterator<X> it = l.iterator();
			@Override
			public X nextElement() {
				if(it.hasNext()) {
					return it.next();
				} else {
					it = l.iterator();
					return it.next();
				}
			}
			
		};
	}

	@Override
	public Sequence<Integer> incrementing(int start, int increment) {
		Iterator<Integer> it = Stream.iterate(start, t -> t+= increment).iterator();
		return () -> it.next();
	}

	@Override
	public <X> Sequence<X> accumulating(Sequence<X> input, BinaryOperator<X> op) {
		return new Sequence<X>() {

			X old = input.nextElement();
			
			@Override
			public X nextElement() {
				X temp = old;
				old = op.apply(old, input.nextElement());
				return temp;
			}
			
		};
	}

	@Override
	public <X> Sequence<Pair<X, Integer>> zip(Sequence<X> input) {
		Iterator<Pair<X, Integer>> it = IntStream.iterate(0, i -> ++i).mapToObj(i -> new Pair<>(input.nextElement(), i)).iterator();
		return () -> it.next();
	}

}
