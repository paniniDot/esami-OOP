package ex2016.a01.t4.e1;

import java.util.List;

public class BuilderWithSize<X> implements ListBuilder<X> {

	private final ListBuilder<X> builder;
	private int remaining;
	
	public BuilderWithSize(final ListBuilder<X> builder, final int size) {
		this.builder = builder;
		this.remaining = size;
	}
	
	@Override
	public void add(X x) {
		this.builder.add(x);
		this.remaining--;
	}

	@Override
	public List<X> build() {
		if(this.remaining != 0) {
			throw new IllegalStateException();		
		}
		return this.builder.build();
	}

}
