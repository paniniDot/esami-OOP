package e2;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ModelImpl implements Model {
	
	private final static String ACTIVATED = "*";
	private final static String NOT_ACTIVATED = " ";
	
	private final List<Boolean> list;
	private final int size;
	
	public ModelImpl(final int size) {
		this.list = IntStream.range(0, size*size)
				.mapToObj(i -> false)
				.collect(Collectors.toList());
		this.size = size;
	}
	
	private void checkPosition(final int position) {
		if(position < 0 && position >= this.list.size()) {
			throw new IllegalArgumentException();
		}
	}
	
	@Override
	public void hit(final int position) {
		this.checkPosition(position);
		this.list.set(position, !this.list.get(position));
	}
	
	@Override
	public String getText(final int position) {
		this.checkPosition(position);
		return this.list.get(position) ? ACTIVATED : NOT_ACTIVATED;
	}
	
	private boolean checkForRaws() {
		boolean quit = true;
		for(int i=0; i<this.list.size(); i+=this.size) {
			quit = true;
			for(int j=0; j<this.size; j++) {
				if(this.list.get(i + j).equals(false)) {
					quit = false;
				}
			}
			if(quit == true) {
				break;
			}
		}
		return quit;
	}
	
	private boolean checkForColumns() {
		boolean quit = true;
		for(int i=0; i<this.size; i++) {
			quit = true;
			for(int j=0; j<this.size; j++) {
				if(this.list.get(i + j * this.size).equals(false)) {
					quit = false;
				}
			}
			if(quit == true) {
				break;
			}
		}
		return quit;
	}
	
	@Override
	public boolean shouldQuit() {
		return this.checkForRaws() || this.checkForColumns();
	}
	
	

}
