package e2;

public interface Model {

	void hit(final int row, final int col);
	
	boolean getGridStatus(final int row, final int col);
	
	int getSize();
	
}