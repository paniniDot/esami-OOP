package e1;

import java.util.function.Supplier;
import java.util.stream.IntStream;

public class TraceFactoryImpl implements TraceFactory {

	@Override
	public <X> Trace<X> fromSuppliers(Supplier<Integer> sdeltaTime, Supplier<X> svalue, int size) {
		return new TraceImpl<>(IntStream.iterate(0, time -> time + sdeltaTime.get())
				.<Event<X>>mapToObj(time -> new EventImpl<>(time, svalue.get()))
				.limit(size)
				.iterator());
	}

	@Override
	public <X> Trace<X> constant(Supplier<Integer> sdeltaTime, X value, int size) {
		return new TraceImpl<>(IntStream.iterate(0, time -> time + sdeltaTime.get())
				.<Event<X>>mapToObj(time -> new EventImpl<>(time, value))
				.limit(size)
				.iterator());
	}

	@Override
	public <X> Trace<X> discrete(Supplier<X> svalue, int size) {
		return new TraceImpl<>(IntStream.range(0, size)
				.<Event<X>>mapToObj(i -> new EventImpl<>(i, svalue.get()))
				.iterator());
	}

}
