package e1;

import java.util.Iterator;
import java.util.List;

public class FunctionalStreamImpl<E> implements FunctionalStream<E> {
	
	private final List<E> list;
	
	public FunctionalStreamImpl(List<E> list) {
		this.list = list;
	}

	@Override
	public NextResult<E> next() {
		return new NextResultImpl<>(this.list);
	}

	@Override
	public List<E> toList(int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<E> toIterator() {
		// TODO Auto-generated method stub
		return null;
	}

}
