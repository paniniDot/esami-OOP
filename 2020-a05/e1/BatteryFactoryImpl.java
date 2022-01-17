package e1;

public class BatteryFactoryImpl implements BatteryFactory {

private static class AbstractBatteryFactory {
		
		public static AbstractBattery defaulBattery() {
			return AbstractBatteryFactory.simple(1);
		}

		public static AbstractBattery simple(double value) {
			return new SimpleBattery(value);
		}

		public static AbstractBattery secure(AbstractBattery base) {
			return new SecureBattery(base);
		}

		public static AbstractBattery rechargable(AbstractBattery base) {
			return new RechargableBattery(base);
		}
	}
	
	@Override
	public Battery createSimpleBattery() {
		return AbstractBatteryFactory.defaulBattery();
	}

	@Override
	public Battery createSimpleBatteryByDrop(double energyPerDurationDrop) {
		return AbstractBatteryFactory.simple(energyPerDurationDrop);
	}

	@Override
	public Battery createSimpleBatteryWithExponentialDrop() {
		return new Battery() {
			
			private double batteryLevel = 1.0;
			
			@Override
			public void startUse() {}

			@Override
			public void stopUse(double duration) {
				this.batteryLevel /= 2;
			}

			@Override
			public double getEnergy() {
				return this.batteryLevel;
			}

			@Override
			public void recharge() {}
			
		};
	}

	@Override
	public Battery createSecureBattery() {
		return AbstractBatteryFactory.secure(AbstractBatteryFactory.defaulBattery());
	}

	@Override
	public Battery createRechargeableBattery() {
		return AbstractBatteryFactory.rechargable(AbstractBatteryFactory.defaulBattery());
	}

	@Override
	public Battery createSecureAndRechargeableBattery() {
		return AbstractBatteryFactory.rechargable(AbstractBatteryFactory.secure(AbstractBatteryFactory.simple(1)));
	}

}
