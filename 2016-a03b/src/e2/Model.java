package e2;

public interface Model {

	void move();

	int getPosition();
	
	void disable(int position);
	
	boolean isOver();

}