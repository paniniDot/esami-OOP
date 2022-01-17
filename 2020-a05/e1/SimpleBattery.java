package e1;

public class SimpleBattery extends AbstractBattery {
	
	private double energyPerDurationDrop;
	
	public SimpleBattery(double energyPerDurationDrop) {
		super.setEnergy(1.0);
		this.energyPerDurationDrop = energyPerDurationDrop;
	}

	@Override
	public void startUse() {}

	@Override
	public void stopUse(double duration) {
		double batteryConsumption = this.energyPerDurationDrop * duration;
		super.setEnergy(super.getEnergy() < batteryConsumption ? 0 : super.getEnergy() - batteryConsumption);
	}

	@Override
	public void recharge() {}
	
}
