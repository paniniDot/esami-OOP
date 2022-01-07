package e1;


public class BasicElevatorImpl extends GenericElevatorImpl {

	@Override
	protected int onMoveToNext() {
		return getNearSmallest(super.getCurrentFloor());
	}

	@Override
	protected boolean onCall() {
		return this.getNearSmallest(super.getCurrentFloor()) > super.getCurrentFloor();
	}
	
	private int getNearSmallest(int currentFloor) {
		int nearest = this.pendingCalls().stream().max(Integer::compareTo).get();
		for(var floor: this.pendingCalls()) {
			if(Math.abs(currentFloor - floor) < Math.abs(currentFloor - nearest)) {
				nearest = floor;
			}
		}
		return nearest;
	}

}
