package e1;

import java.util.Collection;
import java.util.List;

public class BuilderFrom<X> implements ListBuilder<X> {
	
	private final ListBuilder<X> builder;
	private final Collection<X> from;
	
	public BuilderFrom(final ListBuilder<X> builder, final Collection<X> from) {
		this.builder = builder;
		this.from = from;
	}
	
	@Override
	public void addElement(X x) {
		if(!this.from.contains(x)) {
			throw new IllegalArgumentException();
		}
		this.builder.addElement(x);
	}

	@Override
	public List<X> build() {
		return this.builder.build();
	}

}
