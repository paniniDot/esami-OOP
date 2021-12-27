package e2;

public interface Model {

	boolean setDisabled(int position);

	void step();

	int getCurrent();

	void reset();

}