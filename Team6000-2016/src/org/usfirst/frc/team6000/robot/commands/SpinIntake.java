package org.usfirst.frc.team6000.robot.commands;

import org.usfirst.frc.team6000.robot.Robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SpinIntake extends Command {

	private double velocity;
	Joystick stick;
	int buttonNum;
	
	Timer spinTimer;
	double spinTime;
	
    public SpinIntake(double s, Joystick j, int b) {
       velocity = s;
       stick = j;
       buttonNum = b;
       requires(Robot.intake);
    }
    
    public SpinIntake(double s, double t) {
    	velocity = s;
    	spinTime = t;
    	requires(Robot.intake);
    	
    	spinTimer = new Timer();
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.intake.spinPickup(velocity);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if (stick != null) {
        	return !stick.getRawButton(buttonNum);
        } else {
        	return spinTimer.get() >= spinTime;
        }
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.intake.spinPickup(0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
