package org.usfirst.frc.team6000.robot.commands;

import org.usfirst.frc.team6000.robot.Robot;
import org.usfirst.frc.team6000.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ZeroShooter extends Command {

    public ZeroShooter() {
        requires(Robot.shooterArticulator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.shooterArticulator.rotate(-0.2);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
       return !RobotMap.shooterZero.get();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.shooterArticulator.rotate(0.0);
    	RobotMap.articulatorEncoder.reset();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
