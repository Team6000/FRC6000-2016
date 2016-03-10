package org.usfirst.frc.team6000.robot.subsystems;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team6000.robot.OI;
import org.usfirst.frc.team6000.robot.Robot;
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
	
	public DriveTrain() {
		RobotMap.leftWheelEncoder.setMaxPeriod(0.1);
		RobotMap.leftWheelEncoder.setMinRate(10);
		RobotMap.leftWheelEncoder.setDistancePerPulse(18.85/360);
		RobotMap.leftWheelEncoder.setSamplesToAverage(50);
		
		RobotMap.rightWheelEncoder.setMaxPeriod(0.1);
		RobotMap.rightWheelEncoder.setMinRate(10);
		RobotMap.rightWheelEncoder.setDistancePerPulse(18.85/360);
		RobotMap.rightWheelEncoder.setSamplesToAverage(50);
	}
    
    public Victor makeInverted(Victor victor){
    	 victor.setInverted(true);
    	 return victor;
    }
    
    RobotDrive robotDrive = new RobotDrive(makeInverted(RobotMap.leftMotor), makeInverted(RobotMap.rightMotor));
    
    
    public void drive(){
    	robotDrive.tankDrive(Math.signum(OI.leftStick.getY())*(OI.leftStick.getY()*OI.leftStick.getY()), 
    			Math.signum(OI.rightStick.getY())*(OI.rightStick.getY()*OI.rightStick.getY()));
    }
    
    
    public void rotate(double speed) {
    	RobotMap.leftMotor.set(-speed);
    	RobotMap.rightMotor.set(-speed);
    }
	
    public void leftDrive(double inchesPerSecond) {
    	double error = inchesPerSecond - RobotMap.leftWheelEncoder.getRate();
    	RobotMap.leftMotor.set(-KfLeft*inchesPerSecond - KpLeft*error);
    }
    
    public void rightDrive(double inchesPerSecond) {
    	double error = inchesPerSecond - RobotMap.rightWheelEncoder.getRate();
    	RobotMap.rightMotor.set(KfRight*inchesPerSecond + KpRight*error);
    }
    
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new DriveWithJoysticks());
	}
    
}

