package org.usfirst.frc.team6000.robot;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Victor;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // DriveTrain subsystem
    public static int DRIVE_LEFT = 0;
    public static int DRIVE_RIGHT = 1;
    public static int DRIVE_ENCODER_LEFT_A = 6;
    public static int DRIVE_ENCODER_LEFT_B = 7;
    public static int DRIVE_ENCODER_RIGHT_A = 8;
    public static int DRIVE_ENCODER_RIGHT_B = 9;
    
    // Shooter subsystem
    public static int SHOOTER_MOTOR_LEFT = 2;
    public static int SHOOTER_MOTOR_RIGHT = 3;
    public static int SHOOTER_ENCODER_LEFT_A = 2;
    public static int SHOOTER_ENCODER_LEFT_B = 3;
    public static int SHOOTER_ENCODER_RIGHT_A = 4;
    public static int SHOOTER_ENCODER_RIGHT_B = 5;
    
    // ShooterArticulator subsystem
    public static int SHOOTER_ANGLER = 4;
    public static int ARTICULATOR_ENCODER_A = 0;
    public static int ARTICULATOR_ENCODER_B = 1;
    public static int SHOOTER_ZERO_SWITCH = 0;
    
    // Intake subsystem
    public static int INTAKE_WHEELS = 6;
    public static int INTAKE_PISTON_LEFT_A = 1;
    public static int INTAKE_PISTON_LEFT_B = 2;
    public static int INTAKE_PISTON_RIGHT_A = 3;
    public static int INTAKE_PISTON_RIGHT_B = 4;
    
    // BallPusher subsystem
    public static int FIRE_SERVO = 5;
    
}
