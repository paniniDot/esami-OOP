package e1;

public class SecureBattery extends BatteryDecorator {

	private boolean inUse = false;
	
	public SecureBattery(AbstractBattery base) {
		super(base);
	}
		
	@Override
	public void startUse() {
		if(super.getEnergy() <= 0 || this.inUse) {
			throw new IllegalStateException();
		}
		this.inUse = true;
		super.startUse();
	}
	
	@Override
	public void stopUse(double duration) {
		if(!this.inUse) {
			throw new IllegalStateException();
		}
		this.inUse = false;
		super.stopUse(duration);
	}
}
