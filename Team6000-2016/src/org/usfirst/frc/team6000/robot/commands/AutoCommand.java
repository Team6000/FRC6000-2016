package org.usfirst.frc.team6000.robot.commands;

import org.usfirst.frc.team6000.robot.commands.DriveStraight.StraightDriveParent;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoCommand extends Command implements StraightDriveParent {

    public AutoCommand() {
        
    }

    protected void initialize() {
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }

    // Used for straight driving class... Implement check of end of driving here
    public boolean isDoneDriving() {
        return false;
    }
}
