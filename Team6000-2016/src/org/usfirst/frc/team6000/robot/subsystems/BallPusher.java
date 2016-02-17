package org.usfirst.frc.team6000.robot.subsystems;

import org.usfirst.frc.team6000.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class BallPusher extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.


	public void setServoAngle(double angle) {
    	RobotMap.fireServo.setAngle(angle);
    }

	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

