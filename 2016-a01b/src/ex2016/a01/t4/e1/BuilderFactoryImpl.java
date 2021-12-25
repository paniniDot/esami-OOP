package ex2016.a01.t4.e1;

import java.util.Collection;

public class BuilderFactoryImpl implements BuilderFactory {
	
	@Override
	public <X> ListBuilder<X> makeBasicBuilder() {
		return new BasicBuilder<>();
	}

	@Override
	public <X> ListBuilder<X> makeBuilderWithSize(int size) {
		return new BuilderWithSize<>(this.makeBasicBuilder(), size);
	}

	@Override
	public <X> ListBuilder<X> makeBuilderWithoutElements(Collection<X> out) {
		return new BuilderWithoutElements<>(this.makeBasicBuilder(), out);
	}

	@Override
	public <X> ListBuilder<X> makeBuilderWithoutElementsAndWithSize(Collection<X> out, int size) {
		return new BuilderWithoutElements<>(this.makeBuilderWithSize(size), out);
	}

}
