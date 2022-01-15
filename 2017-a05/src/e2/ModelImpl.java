package e2;

public class ModelImpl implements Model {
	
	private final static int LOWER = 1;
	private final static int GREATER = 6;
	
	private int pointsOfA;
	private int pointsOfB;
	
	public ModelImpl(final int points) {
		this.pointsOfA = points-1;
		this.pointsOfB = points-1;
	}
	
	private int throwDice() {
		return LOWER + (int)(Math.random() * GREATER);
	}
	
	@Override
	public void play() {
		int resultA = this.throwDice();
		int resultB = this.throwDice();
		if(resultA <= resultB) {
			this.pointsOfA--;
		} 
		if(resultA >= resultB) {
			this.pointsOfB--;
		}		
		System.out.println("{A=" + resultA + ", " + "B=" + resultB + "}");
	}

	@Override
	public int getPointsOfA() {
		return pointsOfA;
	}

	@Override
	public int getPointsOfB() {
		return pointsOfB;
	}
	
	@Override
	public boolean gameOver() {
		return this.pointsOfA < 0 || this.pointsOfB < 0;
	}
	
}
