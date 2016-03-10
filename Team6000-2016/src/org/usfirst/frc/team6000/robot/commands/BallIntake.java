package org.usfirst.frc.team6000.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class BallIntake extends CommandGroup {
    
    public  BallIntake(double speed, Joystick stick, int buttonNum) {
    	addSequential(new IntakeAngle(true));
    	
    	addParallel(new SpinIntake(speed, stick, buttonNum));
    	addSequential(new Shoot(speed, stick, buttonNum));
        
    }
    
    
}
