package org.usfirst.frc.team6000.robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Victor;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    public static Victor leftMotor = new Victor(0);
    public static Victor rightMotor = new Victor(1);
    
    public static Victor leftShooterMotor = new Victor(2);
    public static Victor rightShooterMotor = new Victor(3);
    public static Encoder leftShooterEncoder = new Encoder(2, 3, false, Encoder.EncodingType.k2X);
    public static Encoder rightShooterEncoder = new Encoder(4, 5, true, Encoder.EncodingType.k2X);
    
    
    public static Victor shooterAngler = new Victor(4);
    public static Servo fireServo = new Servo(5);
    public static Encoder articulatorEncoder = new Encoder(0, 1, false, Encoder.EncodingType.k4X);
    
    
    
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    // public static int rangefinderModule = 1;
    
}
