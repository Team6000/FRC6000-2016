package org.usfirst.frc.team6000.robot.subsystems;

import org.usfirst.frc.team6000.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem {
  
	public static void anglePickup(boolean isAngled) {
		if (isAngled) {
			RobotMap.intakeAngler.set(DoubleSolenoid.Value.kForward);
		} else {
			RobotMap.intakeAngler.set(DoubleSolenoid.Value.kReverse);
		}
	}
	
	public static void spinPickup(double velocity) {
		RobotMap.intakeWheels.set(velocity);
	}
	
	
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

