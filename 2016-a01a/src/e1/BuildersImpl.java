package e1;

import java.util.Collection;

public class BuildersImpl implements Builders {

	@Override
	public <X> ListBuilder<X> makeBasicBuilder() {
		return new BasicBuilder<>();
	}

	@Override
	public <X> ListBuilder<X> makeBuilderWithSize(int size) {
		return new BuilderWithSize<>(this.makeBasicBuilder(), size);
	}

	@Override
	public <X> ListBuilder<X> makeBuilderFromElements(Collection<X> from) {
		return new BuilderFrom<>(this.makeBasicBuilder(), from);
	}

	@Override
	public <X> ListBuilder<X> makeBuilderFromElementsAndWithSize(Collection<X> from, int size) {
		return new BuilderFrom<>(this.makeBuilderWithSize(size), from);
	}

}
