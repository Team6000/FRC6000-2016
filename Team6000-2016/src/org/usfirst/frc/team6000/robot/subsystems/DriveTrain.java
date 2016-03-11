package org.usfirst.frc.team6000.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team6000.robot.OI;
import org.usfirst.frc.team6000.robot.RobotMap;
import org.usfirst.frc.team6000.robot.commands.DriveWithJoysticks;

/**
 *
 */
public class DriveTrain extends Subsystem {
    
	private static double KfRight = 0.008;
	private static double KpRight = 0.012;
	
	private static double KfLeft = 0.0064;
	private static double KpLeft = 0.003;
	
	private Victor leftMotor;
	private Victor rightMotor;
	private Encoder leftWheelEncoder;
	private Encoder rightWheelEncoder;
	
	private RobotDrive robotDrive;
	
	public DriveTrain() {
	    leftMotor = new Victor(RobotMap.DRIVE_LEFT);
	    rightMotor = new Victor(RobotMap.DRIVE_RIGHT);
	    
	    leftWheelEncoder = new Encoder(RobotMap.DRIVE_ENCODER_LEFT_A, RobotMap.DRIVE_ENCODER_LEFT_B, true, Encoder.EncodingType.k2X);
	    rightWheelEncoder = new Encoder(RobotMap.DRIVE_ENCODER_RIGHT_A, RobotMap.DRIVE_ENCODER_RIGHT_B, true, Encoder.EncodingType.k2X);
	    
		robotDrive = new RobotDrive(makeInverted(leftMotor), makeInverted(rightMotor));
		
		leftWheelEncoder.setMaxPeriod(0.1);
		leftWheelEncoder.setMinRate(10);
		leftWheelEncoder.setDistancePerPulse(18.85/360);
		leftWheelEncoder.setSamplesToAverage(50);
		
		rightWheelEncoder.setMaxPeriod(0.1);
		rightWheelEncoder.setMinRate(10);
		rightWheelEncoder.setDistancePerPulse(18.85/360);
		rightWheelEncoder.setSamplesToAverage(50);
	}
    
    public Victor makeInverted(Victor victor){
    	 victor.setInverted(true);
    	 return victor;
    }
    
    
    public void drive(){
    	robotDrive.tankDrive(Math.signum(OI.leftStick.getY())*(OI.leftStick.getY()*OI.leftStick.getY()), 
    			Math.signum(OI.rightStick.getY())*(OI.rightStick.getY()*OI.rightStick.getY()));
    }
    
    
    public void rotate(double speed) {
    	leftMotor.set(-speed);
    	rightMotor.set(-speed);
    }
	
    public void leftDrive(double inchesPerSecond) {
    	double error = inchesPerSecond - leftWheelEncoder.getRate();
    	leftMotor.set(-KfLeft*inchesPerSecond - KpLeft*error);
    }
    
    public void rightDrive(double inchesPerSecond) {
    	double error = inchesPerSecond - rightWheelEncoder.getRate();
    	rightMotor.set(KfRight*inchesPerSecond + KpRight*error);
    }
    
    public void stop()
    {
        leftMotor.set(0);
        rightMotor.set(0);
    }
    
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new DriveWithJoysticks());
	}
    
}

