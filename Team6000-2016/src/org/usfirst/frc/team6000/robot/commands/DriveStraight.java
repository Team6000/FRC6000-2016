package org.usfirst.frc.team6000.robot.commands;

import org.usfirst.frc.team6000.robot.Robot;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveStraight extends Command {

    private StraightDriveParent parent;
    private AHRS gyro;
    
    private double targetAngle;
    
    public DriveStraight(StraightDriveParent o) {
        requires(Robot.driveTrain);
        parent = o;
        gyro = Robot.ahrs; // Change this if for whatever reason the gyro location changes
    }

    protected void initialize() {
        targetAngle = gyro.getYaw();
    }

    protected void execute() {
        double angle = gyro.getYaw(); // TODO: Check if degrees or radians
        double error = targetAngle - angle;
        
        error = error > 180 ? error - 360 : error; // Fix error to range of -180 to 180.
        // TODO: Check if this makes sense in the morning
        
        if(error < 0) // Should move left
        {
            Robot.driveTrain.rawDrive(-1 * error / 180, 1.0); // TODO: Tune values for snappy feedback
        } else if(error > 0) // Should move right
        {
            Robot.driveTrain.rawDrive(1.0, error / 180);
        }
    }

    protected boolean isFinished() {
        return parent.isDoneDriving();
    }

    protected void end() {
        Robot.driveTrain.stop();
    }

    protected void interrupted() {
    }
    
    public interface StraightDriveParent {
        public boolean isDoneDriving();
    }
}
