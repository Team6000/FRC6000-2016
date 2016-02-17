package org.usfirst.frc.team6000.robot.commands;

import org.usfirst.frc.team6000.robot.Robot;
import org.usfirst.frc.team6000.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveForOneSec extends Command {

	private double speed;
	private Timer moveTimer;
	
    public MoveForOneSec(double s) {
        requires(Robot.ShooterArticulator);
        
        speed = s;
        moveTimer = new Timer();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	moveTimer.reset();
    	moveTimer.start();
    	Robot.ShooterArticulator.rotate(speed);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return moveTimer.get() >= 1.0;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.ShooterArticulator.rotate(0.0);
    	moveTimer.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
