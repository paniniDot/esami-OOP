package e1;

public class AlternateElevatorImpl extends GenericElevatorImpl {

	@Override
	protected int onMoveToNext() {
		int elem;
		if(super.isMovingUp()) {
			elem = this.smallerBetweenGreaterThen(super.getCurrentFloor());
		} else {
			elem = this.greaterBetweenSmallersThen(super.getCurrentFloor());
		}
		return elem;
	}

	@Override
	protected boolean onCall() {
		for(var floor: super.pendingCalls()) {
			if(super.getCurrentFloor() < floor) {
				return true;
			}
		}
		return false;
	}
	
	private int greaterBetweenSmallersThen(int current) {
		int smaller = 0;
		for(var floor: this.pendingCalls()) {
			if(floor > smaller && floor < current) {
				smaller = floor;
			}
		}
		return smaller;
	}
	
	private int smallerBetweenGreaterThen(int current) {
		int smaller = this.pendingCalls().stream().max(Integer::compareTo).get();
		for(var floor: this.pendingCalls()) {
			if(floor < smaller && floor > current) {
				smaller = floor;
			}
		}
		return smaller;
	}
}
