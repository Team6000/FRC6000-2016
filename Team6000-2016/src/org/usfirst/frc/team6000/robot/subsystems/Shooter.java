package org.usfirst.frc.team6000.robot.subsystems;

import org.usfirst.frc.team6000.robot.RobotMap;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Shooter extends Subsystem {
    
    
    public void shoot(double speed){
    	RobotMap.leftShooterMotor.set(speed);
    	RobotMap.rightShooterMotor.set(-speed);
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

