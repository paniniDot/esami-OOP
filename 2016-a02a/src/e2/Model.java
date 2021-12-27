package e2;

public interface Model {

	void setDisabled(int position);

	void step();

	int getCurrent();

	void reset();

}