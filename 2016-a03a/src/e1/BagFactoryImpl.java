package e1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;
import java.util.function.UnaryOperator;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class BagFactoryImpl implements BagFactory {

	@Override
	public <X> Bag<X> empty() {
		return new BagImpl<X>(Collections.emptyList());
	}

	@Override
	public <X> Bag<X> fromSet(Set<X> set) {
		return new BagImpl<X>(List.copyOf(set));
	}

	@Override
	public <X> Bag<X> fromList(List<X> list) {
		return new BagImpl<X>(List.copyOf(list));
	}
	
	private <X> void addMany(final List<X> list, final X elem, final int nElem) {
		IntStream.range(0, nElem).forEach(i -> list.add(elem));
	}
	
	private <X> Bag<X> fromIterator(final Iterator<X> iterator, final ToIntFunction<X> copies) {
		final List<X> l = new ArrayList<>();
		iterator.forEachRemaining(el -> addMany(l, el, copies.applyAsInt(el)));
		return new BagImpl<>(l);
	}

	@Override
	public <X> Bag<X> bySupplier(Supplier<X> supplier, int nElements, ToIntFunction<X> copies) {
		return this.fromIterator(Stream.generate(supplier).limit(nElements).iterator(), copies);
	}

	@Override
	public <X> Bag<X> byIteration(X first, UnaryOperator<X> next, int nElements, ToIntFunction<X> copies) {
		return this.fromIterator(Stream.iterate(first, next).limit(nElements).iterator(), copies);
	}

}
