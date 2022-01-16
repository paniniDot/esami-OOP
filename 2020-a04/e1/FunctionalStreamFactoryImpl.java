package e1;

import java.util.List;
import java.util.function.Function;
import java.util.function.UnaryOperator;

public class FunctionalStreamFactoryImpl implements FunctionalStreamFactory {

	@Override
	public <E> FunctionalStream<E> fromListRepetitive(List<E> list) {
		return new FunctionalStreamImpl<>(list);
	}

	@Override
	public <E> FunctionalStream<E> iterate(E initial, UnaryOperator<E> op) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <A, B> FunctionalStream<B> map(FunctionalStream<A> fstream, Function<A, B> mapper) {
		// TODO Auto-generated method stub
		return null;
	}

}
