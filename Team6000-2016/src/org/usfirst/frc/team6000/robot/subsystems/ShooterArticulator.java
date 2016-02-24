package org.usfirst.frc.team6000.robot.subsystems;

import org.usfirst.frc.team6000.robot.Robot;
import org.usfirst.frc.team6000.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ShooterArticulator extends Subsystem {
	
	
	
	public ShooterArticulator() {
		RobotMap.articulatorEncoder.setMaxPeriod(0.25);
		RobotMap.articulatorEncoder.setMinRate(10);
		RobotMap.articulatorEncoder.setDistancePerPulse(360.0/1024);
		RobotMap.articulatorEncoder.setSamplesToAverage(10);
		
	}
	
    public void rotate(double velocity){
    	RobotMap.shooterAngler.set(-velocity);
    }
    
   
   
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}
