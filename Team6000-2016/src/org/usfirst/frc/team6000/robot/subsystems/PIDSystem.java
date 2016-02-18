package org.usfirst.frc.team6000.robot.subsystems;

public class PIDSystem {
	private double kP;
	private double kI;
	private double kD;
	private double DB;
	
	private double currentError;
	private double cumulativeError;
	private double setpoint;
	private double lockedIntegralThreshold;
	
	public PIDSystem(double p, double i, double d, double db) {
		kP = p;
		kI = d;
		kD = d;
		DB = db;
		cumulativeError = 0;
		setpoint = 0;
	}
	
	public PIDSystem(double p, double i, double d) {
		this(p, i, d, 0.0);
	}
	
	public void reset() {
		currentError = 0;
		cumulativeError = 0;
		setpoint = 0;
	}
	
	public double computeUpdate(double s, double current) {
		double setpoint += DB*Math.signum(s);
		currentError = setpoint - current;
		if (Math.abs(currentError > lockedIntegralThreshold) incrementCumulative(currentError);
		
		return kP*currentError + cumulativeError + DB;
	}
	
	private void incrementCumulative(double error) {
		cumulativeError += kI*currentError;
	}
	
	public void setThreshold(double threshold) {
		lockedIntegralThreshold = threshold;
	}
}