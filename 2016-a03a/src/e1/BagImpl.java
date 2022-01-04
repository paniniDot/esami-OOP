package e1;

import java.util.List;

public class BagImpl<X> implements Bag<X> {
	
	private final List<X> l;
	
	public BagImpl(final List<X> l) {
		this.l = l;
	}
	
	@Override
	public int numberOfCopies(X x) {
		return this.l.stream()
				.filter(el -> el.equals(x))
				.map(el -> 1)
				.reduce((occA, occB) -> occA + occB)
				.orElse(0);
	}

	@Override
	public int size() {
		return this.l.size();
	}

	@Override
	public List<X> toList() {
		return this.l;
	}

}
