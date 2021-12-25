package e1;

import java.util.ArrayList;
import java.util.List;

public class BasicBuilder<X> implements ListBuilder<X> {

	private final List<X> l = new ArrayList<>();
	private boolean builded = false;
	
	private void checkIfBuilded() {
		if(this.builded) {
			throw new IllegalStateException();
		}
	}
	
	@Override
	public void addElement(X x) {
		this.checkIfBuilded();
		this.l.add(x);
	}

	@Override
	public List<X> build() {
		this.checkIfBuilded();
		this.builded = true;
		return this.l;
	}


}
