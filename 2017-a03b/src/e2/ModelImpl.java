package e2;

import java.util.ArrayList;
import java.util.List;

public class ModelImpl implements Model {
	
	private final List<List<Boolean>> grid;
	private final int size;
	
	public ModelImpl(final int size) {
		this.size = size;
		this.grid = new ArrayList<>();
		for(int i = 0; i < this.size; i++) {
			this.grid.add(i, new ArrayList<>());
			for(int j = 0; j < this.size; j++) {
				this.grid.get(i).add(j, false);
			}
		}
	}
	
	@Override
	public void hit(final int row, final int col) {
		for(int i=0; i<this.size; i++) {
			for(int j=0; j<this.size; j++) {
				if(i+j == row+col) {
					this.grid.get(i).set(j, !this.grid.get(i).get(j));
				}
			}
		}
		System.out.println(this.grid);
	}
	
	@Override
	public boolean getGridStatus(final int row, final int col) {
		return this.grid.get(row).get(col);
	}

	@Override
	public int getSize() {
		return this.size;
	}
	
}
