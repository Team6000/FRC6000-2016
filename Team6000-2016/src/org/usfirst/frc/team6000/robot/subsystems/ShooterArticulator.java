package org.usfirst.frc.team6000.robot.subsystems;

import org.usfirst.frc.team6000.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ShooterArticulator extends Subsystem {
	
    private Victor shooterAngler;
    private Encoder articulatorEncoder;
    private DigitalInput shooterZero;
    
	public ShooterArticulator() {
	    shooterAngler = new Victor(RobotMap.SHOOTER_ANGLER);
	    articulatorEncoder = new Encoder(RobotMap.ARTICULATOR_ENCODER_A, RobotMap.ARTICULATOR_ENCODER_B, false, Encoder.EncodingType.k4X);
	    shooterZero = new DigitalInput(RobotMap.SHOOTER_ZERO_SWITCH);
	    
		articulatorEncoder.setMaxPeriod(0.25);
		articulatorEncoder.setMinRate(10);
		articulatorEncoder.setDistancePerPulse(360.0/1024);
		articulatorEncoder.setSamplesToAverage(10);
	}
	
    public void rotate(double velocity){
    	shooterAngler.set(-1 * velocity);
    }
    
    public boolean isZero()
    {
        return !shooterZero.get();
    }
    
    public double getDistance()
    {
        return articulatorEncoder.getDistance();
    }
    
    public double getRate()
    {
        return articulatorEncoder.getRate();
    }
    
    public void resetDistance()
    {
        articulatorEncoder.reset();
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}
