package org.usfirst.frc.team6000.robot.commands;

import org.usfirst.frc.team6000.robot.subsystems.Intake;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class BallIntake extends CommandGroup {
    
    public  BallIntake(double speed, Joystick stick, int buttonNum) {
    	addSequential(new IntakeAngle(Intake.IntakeAngle.DOWN));
    	
    	addParallel(new SpinIntake(speed, stick, buttonNum));
    	addSequential(new Shoot(speed, stick, buttonNum));
        
    }
    
    
}
