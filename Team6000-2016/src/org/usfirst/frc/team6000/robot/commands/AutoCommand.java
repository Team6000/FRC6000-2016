package org.usfirst.frc.team6000.robot.commands;

import org.usfirst.frc.team6000.robot.Robot;
import org.usfirst.frc.team6000.robot.commands.DriveStraight.StraightDriveParent;
import org.usfirst.frc.team6000.robot.subsystems.Intake.IntakeAngle;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoCommand extends Command implements StraightDriveParent {

    DriveStraight drive;
    long startTime;
    long startDriveTime;
    
    public AutoCommand() {
        //requires(Robot.intake);
        startTime = 0;
        startDriveTime = 0;
        drive = new DriveStraight(this);
    }
    
    protected void initialize() {
        startTime = System.currentTimeMillis();
        Robot.intake.anglePickup(IntakeAngle.DOWN);
    }

    protected void execute() {
        if(System.currentTimeMillis() - startTime >= 1000 && startDriveTime == 0)
        {
            startDriveTime = System.currentTimeMillis();
            drive.start();
        }
        if(System.currentTimeMillis() - startDriveTime >= 2000 && startDriveTime != 0)
        {
            Robot.intake.anglePickup(IntakeAngle.UP);
        }
    }

    protected boolean isFinished() {
        return drive.isFinished() && startDriveTime != 0;
    }

    protected void end() {
        
    }

    protected void interrupted() {
    }

    // Used for straight driving class... Implement check of end of driving here
    public boolean isDoneDriving() {
        return System.currentTimeMillis() - startDriveTime >= 3500;
    }
}
