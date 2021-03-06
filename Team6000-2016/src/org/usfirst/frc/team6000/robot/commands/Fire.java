package org.usfirst.frc.team6000.robot.commands;

import org.usfirst.frc.team6000.robot.Robot;
import org.usfirst.frc.team6000.robot.subsystems.BallPusher;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Fire extends Command {
	BallPusher.State targetState;
	Timer fireTime;
	
    public Fire(BallPusher.State s) {
        targetState = s;
    	requires(Robot.ballPusher);
    	fireTime = new Timer();
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    	fireTime.reset();
    	fireTime.start();
    	Robot.ballPusher.setState(targetState);
    	
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return fireTime.get() >= 0.5;
    }

    // Called once after isFinished returns true
    protected void end() {
    	fireTime.stop();
    	
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
