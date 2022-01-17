package e1;

public class BatteryDecorator extends AbstractBattery {
	
	private AbstractBattery battery;
	
	public BatteryDecorator(AbstractBattery base) {
		this.battery = base;
	}
	
	@Override
	public void startUse() {
		this.battery.startUse();
	}

	@Override
	public void stopUse(double duration) {
		this.battery.stopUse(duration);
	}

	@Override
	public double getEnergy() {
		return this.battery.getEnergy();
	}

	@Override
	public void recharge() {
		this.battery.recharge();
	}

	protected void setEnergy(double energy) {
		this.battery.setEnergy(energy);
	}
	
}
