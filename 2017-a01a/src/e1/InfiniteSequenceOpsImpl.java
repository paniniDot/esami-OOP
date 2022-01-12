package e1;

import java.util.Iterator;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class InfiniteSequenceOpsImpl implements InfiniteSequenceOps {

	@Override
	public <X> InfiniteSequence<X> ofValue(X x) {
		return () -> x;
	}

	@Override
	public <X> InfiniteSequence<X> ofValues(List<X> l) {
		Iterator<X> it = Stream.generate(l::stream).flatMap(e -> e).iterator(); 
		return () -> it.next();
	}

	@Override
	public InfiniteSequence<Double> averageOnInterval(InfiniteSequence<Double> iseq, int intervalSize) {
		return () -> iseq.nextListOfElements(intervalSize)
				.stream()
				.mapToDouble(i -> i)
				.average()
				.getAsDouble();
	}

	@Override
	public <X> InfiniteSequence<X> oneEachInterval(InfiniteSequence<X> iseq, int intervalSize) {
		return () -> iseq.nextListOfElements(intervalSize).get(intervalSize-1);
	}

	@Override
	public <X> InfiniteSequence<Boolean> equalsTwoByTwo(InfiniteSequence<X> iseq) {
		return () -> iseq.nextElement().equals(iseq.nextElement());
	}

	@Override
	public <X, Y extends X> InfiniteSequence<Boolean> equalsOnEachElement(InfiniteSequence<X> isx,
			InfiniteSequence<Y> isy) {
		return () -> isx.nextElement().equals(isy.nextElement());
	}

	@Override
	public <X> Iterator<X> toIterator(InfiniteSequence<X> iseq) {
		return Stream.generate(iseq::nextElement).iterator();
	}

}
