package org.usfirst.frc.team6000.robot.subsystems;

import org.usfirst.frc.team6000.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem {
    
    private Victor intakeWheels;
    private DoubleSolenoid intakePistonLeft;
    private DoubleSolenoid intakePistonRight;
    
    public Intake()
    {
        intakeWheels = new Victor(RobotMap.INTAKE_WHEELS);
        intakePistonLeft = new DoubleSolenoid(RobotMap.INTAKE_PISTON_LEFT_A, RobotMap.INTAKE_PISTON_LEFT_B);
        intakePistonRight = new DoubleSolenoid(RobotMap.INTAKE_PISTON_RIGHT_A, RobotMap.INTAKE_PISTON_RIGHT_B);
    }
  
	public void anglePickup(IntakeAngle a) {
		switch (a) {
		case DOWN:
		    intakePistonLeft.set(DoubleSolenoid.Value.kForward);
		    intakePistonRight.set(DoubleSolenoid.Value.kForward);
			break;
		case UP:
		    intakePistonLeft.set(DoubleSolenoid.Value.kReverse);
		    intakePistonRight.set(DoubleSolenoid.Value.kReverse);
			break;
		}
	}
	
	public void spinPickup(double velocity) {
		intakeWheels.set(velocity);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public enum IntakeAngle {
        UP, DOWN
    }
}

