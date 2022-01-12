package e2;

public interface Model {

	String getText(int position);
	
	boolean checkIfEnabled(int position);
	
	boolean shouldQuit();
	
	String print();

}