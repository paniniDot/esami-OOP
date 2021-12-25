package ex2016.a01.t4.e1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BasicBuilder<X> implements ListBuilder<X> {
	
	private final List<X> l = new ArrayList<>();
	private boolean alreadyBuilded = false;
	
	@Override
	public void add(X x) {
		if(this.alreadyBuilded) {
			throw new IllegalStateException();
		}
		this.l.add(x);
	}

	@Override
	public List<X> build() {
		if(this.alreadyBuilded) {
			throw new IllegalStateException();
		}
		this.alreadyBuilded = true;
		return Collections.unmodifiableList(this.l);
	}
	
};

