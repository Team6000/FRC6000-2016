package org.usfirst.frc.team6000.robot.subsystems;

import org.usfirst.frc.team6000.robot.RobotMap;
import org.usfirst.frc.team6000.robot.commands.SetShooterSpeed;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Shooter extends Subsystem {
    
    private Victor leftShooterMotor;
    private Victor rightShooterMotor;
    private Encoder leftShooterEncoder;
    private Encoder rightShooterEncoder;
    
	public Shooter() {
	    leftShooterMotor = new Victor(RobotMap.SHOOTER_MOTOR_LEFT);
	    rightShooterMotor = new Victor(RobotMap.SHOOTER_MOTOR_RIGHT);
	    
	    leftShooterEncoder = new Encoder(RobotMap.SHOOTER_ENCODER_LEFT_A, RobotMap.SHOOTER_ENCODER_LEFT_B, false, Encoder.EncodingType.k2X);
	    rightShooterEncoder = new Encoder(RobotMap.SHOOTER_ENCODER_RIGHT_A, RobotMap.SHOOTER_ENCODER_RIGHT_B, true, Encoder.EncodingType.k2X);
	    
		leftShooterEncoder.setMaxPeriod(0.1);
		leftShooterEncoder.setMinRate(10);
		leftShooterEncoder.setDistancePerPulse(1.0/1024.0);
		leftShooterEncoder.setSamplesToAverage(50);
		
		rightShooterEncoder.setMaxPeriod(0.1);
		rightShooterEncoder.setMinRate(10);
		rightShooterEncoder.setDistancePerPulse(1.0/1024.0);
		rightShooterEncoder.setSamplesToAverage(50);
	}
    
    public void rawShoot(double speed){
        leftShooterMotor.set(-speed);
    	rightShooterMotor.set(speed);
    	
    	SmartDashboard.putNumber("LeftShooterSpeed", leftShooterEncoder.getRate());
    	SmartDashboard.putNumber("RightShooterSpeed", rightShooterEncoder.getRate());
    }

    
    public void setLeft(double rps) {
    	if (Math.abs(leftShooterEncoder.getRate()) < Math.abs(rps)) {
    		leftShooterMotor.set(Math.signum(rps) * 1.0);
    	} else {
    		leftShooterMotor.set(0.0);
    	}
    	SmartDashboard.putNumber("LeftShooterSpeed", leftShooterEncoder.getRate());
    }
    
    public void setRight(double rps) {
    	if (Math.abs(rightShooterEncoder.getRate()) < Math.abs(rps)) {
    		rightShooterMotor.set(Math.signum(rps) * 1.0);
    		
    	} else {
    		rightShooterMotor.set(0.0);
    	}
    	SmartDashboard.putNumber("RightShooterSpeed", rightShooterEncoder.getRate());
    }
    
    public void initDefaultCommand() {
        setDefaultCommand(new SetShooterSpeed(0.0));
    }
}

