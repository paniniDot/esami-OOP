package e1;

import java.util.List;

public class BuilderWithSize<X> implements ListBuilder<X> {

	private final ListBuilder<X> builder;
	private int size;
	
	public BuilderWithSize(final ListBuilder<X> builder, final int size) {
		this.builder = builder;
		this.size = size;
	}
	
	@Override
	public void addElement(X x) {
		this.builder.addElement(x);
		this.size--;
	}

	@Override
	public List<X> build() {
		if(this.size != 0) {
			throw new IllegalStateException();
		}
		return this.builder.build();
	}

}
