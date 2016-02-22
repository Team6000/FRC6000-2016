package org.usfirst.frc.team6000.robot.commands;

import org.usfirst.frc.team6000.robot.Robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetShooterSpeed extends Command {

	private double rps;

    public SetShooterSpeed(double s) {
    	requires(Robot.Shooter);
    	rps = s;
       
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.Shooter.setSetpointLeft(rps);
    	Robot.Shooter.setSetpointRight(-rps);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
       return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}