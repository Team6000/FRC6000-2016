package org.usfirst.frc.team6000.robot.commands;

import org.usfirst.frc.team6000.robot.Robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Shoot extends Command {

	double setVelocity;
	Joystick stick;
	int buttonNum;
	
    public Shoot(double v, Joystick s, int num) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.shooter);
        setVelocity = v;
        stick = s;
        buttonNum = num;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.shooter.roughShoot(setVelocity);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (!stick.getRawButton(buttonNum));
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.shooter.roughShoot(0.0);
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
