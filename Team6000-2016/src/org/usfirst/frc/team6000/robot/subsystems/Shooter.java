package org.usfirst.frc.team6000.robot.subsystems;

import org.usfirst.frc.team6000.robot.RobotMap;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Shooter extends Subsystem {
    
	public Shooter() {
		RobotMap.leftShooterEncoder.setMaxPeriod(0.1);
		RobotMap.leftShooterEncoder.setMinRate(10);
		RobotMap.leftShooterEncoder.setDistancePerPulse(1.0/1024.0);
		RobotMap.leftShooterEncoder.setSamplesToAverage(50);
		
		RobotMap.rightShooterEncoder.setMaxPeriod(0.1);
		RobotMap.rightShooterEncoder.setMinRate(10);
		RobotMap.rightShooterEncoder.setDistancePerPulse(1.0/1024.0);
		RobotMap.rightShooterEncoder.setSamplesToAverage(50);
	}
    
    public void roughShoot(double speed){
    	RobotMap.leftShooterMotor.set(-speed);
    	RobotMap.rightShooterMotor.set(speed);
    	
    	SmartDashboard.putNumber("LeftShooterSpeed", RobotMap.leftShooterEncoder.getRate());
    	SmartDashboard.putNumber("RightShooterSpeed", RobotMap.rightShooterEncoder.getRate());
    }

    
    public void setSetpointLeft(double rps) {
    	if (rps > 0) {
    		
    		if(RobotMap.leftShooterEncoder.getRate() < rps) {
    			RobotMap.leftShooterMotor.set(1.0);
    		} else {
    			RobotMap.leftShooterMotor.set(0.0);
    		}
    		
    	} else if (rps < 0) {
    		if(RobotMap.leftShooterEncoder.getRate() > rps) {
    			RobotMap.leftShooterMotor.set(-1.0);
    		} else {
    			RobotMap.leftShooterMotor.set(0.0);
    		}
    		
    		
    	} else {
    		RobotMap.leftShooterMotor.set(0.0);
    	}
    }
    
    public void setSetpointRight(double rps) {
    	if (rps > 0) {
    		
    		if(RobotMap.rightShooterEncoder.getRate() < rps) {
    			RobotMap.rightShooterMotor.set(1.0);
    		} else {
    			RobotMap.rightShooterMotor.set(0.0);
    		}
    		
    	} else if (rps < 0) {
    		if(RobotMap.rightShooterEncoder.getRate() > rps) {
    			RobotMap.rightShooterMotor.set(-1.0);
    		} else {
    			RobotMap.rightShooterMotor.set(0.0);
    		}
    		
    		
    	} else {
    		RobotMap.rightShooterMotor.set(0.0);
    	}
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

