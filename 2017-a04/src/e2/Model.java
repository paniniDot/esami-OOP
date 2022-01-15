package e2;

import java.util.List;

public interface Model {

	void hit(int pos);

	void draw();
	
	int getSize();

	List<Integer> getStatus();

}