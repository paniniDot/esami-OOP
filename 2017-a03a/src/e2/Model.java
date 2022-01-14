package e2;

import java.util.List;

public interface Model {

	void disableLine(int line);

	List<String> getLines();

	void writeFile();

}