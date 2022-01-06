package e2;

import java.util.Arrays;
import java.util.List;

public class ModelImpl implements Model {
	
	private final List<String> sequence;
	private int pos;
	
	public ModelImpl(final String elements) {
		this.sequence = Arrays.asList(elements.split(",", -1));
		System.out.println(this.sequence);
		this.pos = 0;
	}
	
	private boolean isLegit(final String str) {
		return this.sequence.get(this.pos).equals(str);
	}
	
	@Override
	public void move(final String str) {
		if(this.isLegit(str)) {
			this.pos++;
		} else {
			this.pos = 0;
		}
		System.out.println("Current pos = " + this.pos);
	}
	
	@Override
	public boolean win() {
		return this.pos == this.sequence.size();
	}
		
}
