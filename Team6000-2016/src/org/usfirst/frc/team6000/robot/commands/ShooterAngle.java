package org.usfirst.frc.team6000.robot.commands;

import org.usfirst.frc.team6000.robot.Robot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ShooterAngle extends Command {

	private static final double kP = 0.019;
	private static final double kI = 0.00012;
	private static final double DB = 0.2;
	
	private double setpoint;
	private Joystick userStick;
	private int buttonNum;
	
	private double errorI;
	
	private Timer angleTime;
	private double setTime;
	
	public ShooterAngle(double s, double t) {
		angleTime = new Timer();
		
		setTime = t;
		setpoint = s + DB*Math.signum(s);
		
		setpoint = setpoint > 90 ? 90 : setpoint;
		
		errorI = 0;
		requires(Robot.shooterArticulator);
		
		angleTime.reset();
		angleTime.start();
	}
	
    public ShooterAngle(double s, Joystick j, int b) {
        setpoint = s + DB*Math.signum(s);
        userStick = j;
        buttonNum = b;
        errorI = 0;
        
        if (setpoint > 90) setpoint = 90;
        
        requires(Robot.shooterArticulator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double errorP = setpoint - Robot.shooterArticulator.getDistance();
    	
    	if (Math.abs(errorP) > 0.75) {
    		errorI = errorI + kI*errorP;
    	}
    	
    	if (Math.abs(errorI) > 1) {
    		errorI = Math.signum(errorI) * 1;
    	}
    	
    	SmartDashboard.putNumber("Error_I", errorI);
    	
    	Robot.shooterArticulator.rotate(kP*errorP + errorI);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if (userStick != null) {
        	return !(userStick.getRawButton(buttonNum));
        } else {
        	return angleTime.get() >= setTime;
        }
    }

    // Called once after isFinished returns true
    protected void end() {
    	errorI = 0;
    	if (angleTime != null) {
    		angleTime.stop();
    		angleTime.reset();
    	}
    	Robot.shooterArticulator.rotate(0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
