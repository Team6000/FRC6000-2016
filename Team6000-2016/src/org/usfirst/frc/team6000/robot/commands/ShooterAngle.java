package org.usfirst.frc.team6000.robot.commands;

import org.usfirst.frc.team6000.robot.Robot;
import org.usfirst.frc.team6000.robot.RobotMap;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ShooterAngle extends Command {

	private static final double kP = 0.02;
	private static final double kI = 0.00012;
	private static final double DB = 0.2;
	
	double setpoint;
	Joystick userStick;
	int buttonNum;
	
	double errorI;
	
    public ShooterAngle(double s, Joystick j, int b) {
        setpoint = s + DB*Math.signum(s);
        userStick = j;
        buttonNum = b;
        errorI = 0;
        requires(Robot.ShooterArticulator);
        
        
        
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double errorP = setpoint - RobotMap.articulatorEncoder.getDistance();
    	
    	if (Math.abs(errorP) > 0.75) {
    		errorI = errorI + kI*errorP;
    	}
    	
    	
    	
    	
    	
    	SmartDashboard.putNumber("Error_I", errorI);
    	
    	Robot.ShooterArticulator.rotate(kP*errorP + errorI);
    	
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return !userStick.getRawButton(buttonNum);
        
    }

    // Called once after isFinished returns true
    protected void end() {
    	errorI = 0;
    	Robot.ShooterArticulator.rotate(0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}