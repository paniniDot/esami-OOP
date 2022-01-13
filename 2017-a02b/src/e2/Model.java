package e2;

public interface Model {

	void concat(int line);

	void remove(int line);

	void add(int line);

	int getLines();
	
	String getLine(int line);

}