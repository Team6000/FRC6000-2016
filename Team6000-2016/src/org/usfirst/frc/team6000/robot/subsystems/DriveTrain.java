package org.usfirst.frc.team6000.robot.subsystems;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team6000.robot.OI;
import org.usfirst.frc.team6000.robot.Robot;
import org.usfirst.frc.team6000.robot.RobotMap;
import org.usfirst.frc.team6000.robot.commands.DriveWithJoysticks;

/**
 *
 */
public class DriveTrain extends Subsystem {
    

    
    public Victor makeInverted(Victor victor){
    	 victor.setInverted(true);
    	 return victor;
    }
    
    RobotDrive robotDrive = new RobotDrive(makeInverted(RobotMap.leftMotor), makeInverted(RobotMap.rightMotor));
    
    
    public void drive(){
    	robotDrive.tankDrive(OI.leftStick, OI.rightStick);
    }
    
    
	public void driveInverted(){
		new RobotDrive(RobotMap.leftMotor, RobotMap.rightMotor);
	}
    
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new DriveWithJoysticks());
	}
    
}

