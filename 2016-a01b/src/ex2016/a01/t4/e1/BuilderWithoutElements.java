package ex2016.a01.t4.e1;

import java.util.Collection;
import java.util.List;

public class BuilderWithoutElements<X> implements ListBuilder<X> {
	
	private final ListBuilder<X> builder;
	private final Collection<X> bannedElements;
	
	public BuilderWithoutElements(final ListBuilder<X> builder, final Collection<X> banned) {
		this.builder = builder;
		this.bannedElements = banned;
	}

	@Override
	public void add(X x) {
		if(this.bannedElements.contains(x)) {
			throw new IllegalArgumentException();
		}
		this.builder.add(x);
	}

	@Override
	public List<X> build() {
		return this.builder.build();
	}
}
