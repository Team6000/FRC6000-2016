package org.usfirst.frc.team6000.robot.commands;

import org.usfirst.frc.team6000.robot.Robot;
import org.usfirst.frc.team6000.robot.RobotMap;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;


/**
 *
 */
public class RobotAngle extends Command {

	private static final double kP = .015;
	
	private double setpointAngle;
	private double currentError;
	
	private Joystick stick;
	private int buttonNum;
	
	private Timer angleTime; 
	private double setTime;
	
	public RobotAngle(double s, double t) {
		requires(Robot.DriveTrain);
		
		setTime = t;
		setpointAngle = s;
		
		angleTime.reset();
		angleTime.start();
	}
	
    public RobotAngle(double s, Joystick j, int n) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.DriveTrain);
    	setpointAngle = s;
    	stick = j;
    	buttonNum = n;
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	RobotMap.ahrs.reset();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	currentError = setpointAngle - RobotMap.ahrs.getYaw();
    	Robot.DriveTrain.rotate(kP * currentError);
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if (stick != null) {
        	return !stick.getRawButton(buttonNum);
        } else {
        	return angleTime.get() >= setTime;
        }
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.DriveTrain.rotate(0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
