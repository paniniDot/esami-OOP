package e1;

import java.util.Iterator;
import java.util.List;

import e1.FunctionalStream.NextResult;

public class NextResultImpl<E> implements NextResult<E> {

	List<E> list;
	Iterator<E> it;
	
	public NextResultImpl(final List<E> list) {
		this.list = list;
		this.it = list.iterator();
	}
	
	@Override
	public E getElement() {
		if(!this.it.hasNext()) {
			this.it = this.list.iterator();
		}
		return this.it.next();
	}

	@Override
	public FunctionalStream<E> getStream() {
		return new FunctionalStreamImpl<>(this.list);
	}

}
