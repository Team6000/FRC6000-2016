package org.usfirst.frc.team6000.robot.commands;

import org.usfirst.frc.team6000.robot.Robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ArticulateShooter extends Command {
    
	double velocity;
	Joystick joystick;
	int buttonNum;
		
    public ArticulateShooter(double s, Joystick j, int num) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.ShooterArticulator);
        velocity = s;
        joystick = j;
        buttonNum = num;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.ShooterArticulator.rotate(velocity);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (!joystick.getRawButton(buttonNum));
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.ShooterArticulator.rotate(0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
