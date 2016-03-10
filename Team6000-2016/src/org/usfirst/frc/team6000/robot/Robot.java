
package org.usfirst.frc.team6000.robot;

import org.usfirst.frc.team6000.robot.subsystems.BallPusher;
import org.usfirst.frc.team6000.robot.subsystems.DriveTrain;
import org.usfirst.frc.team6000.robot.subsystems.Shooter;
import org.usfirst.frc.team6000.robot.subsystems.ShooterArticulator;
import org.usfirst.frc.team6000.robot.subsystems.Intake;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	
	public static double P;
	public static double I;
	public static double D;
	
	public static final DriveTrain DriveTrain = new DriveTrain();
	public static final Shooter Shooter = new Shooter();
	public static final ShooterArticulator ShooterArticulator = new ShooterArticulator();
	public static final BallPusher BallPusher = new BallPusher();
	public static final Intake Intake = new Intake();
	public static OI oi;

    Command autonomousCommand;
    SendableChooser chooser;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
		oi = new OI();
		try {
			RobotMap.ahrs =  new AHRS(SPI.Port.kMXP);
		} catch (RuntimeException ex) {
			 DriverStation.reportError("Error instantiating navX-MXP:  " + ex.getMessage(), true);
		}
		
		
     }
	
	/**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
     */
    public void disabledInit(){

    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString code to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the chooser code above (like the commented example)
	 * or additional comparisons to the switch structure below with additional strings & commands.
	 */
    public void autonomousInit() {
      //  autonomousCommand = (Command) chooser.getSelected();
        
		/* String autoSelected = SmartDashboard.getString("Auto Selector", "Default");
		switch(autoSelected) {
		case "My Auto":
			autonomousCommand = new MyAutoCommand();
			break;
		case "Default Auto":
		default:
			autonomousCommand = new ExampleCommand();
			break;
		} */
    	
    	// schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
        
    }

    /**
     * This function is called periodically during autonomous
     */
 	//NetworkTable table;
 	
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        
//        table = NetworkTable.getTable("GRIP/myContoursReport");
//        double[] defaultValue = new double [0];
//        while (true) {
//        	double [] areas = table.getNumberArray("area", defaultValue);
//        	System.out.print("areas");
//        	for (double area : areas) {
//        		System.out.print(area + " ");
//        	}
//        	System.out.println();
//        	Timer.delay(1);
//        }
      
     }

    public void teleopInit() {
    	
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
        RobotMap.articulatorEncoder.reset();
        RobotMap.ahrs.reset();
        
        
       
        		}

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        SmartDashboard.putNumber("Encoder_Rate", RobotMap.articulatorEncoder.getRate());
        SmartDashboard.putNumber("Encoder_Angle", RobotMap.articulatorEncoder.getDistance());
        SmartDashboard.putNumber("LeftWheelSpeed", RobotMap.leftWheelEncoder.getRate());
        SmartDashboard.putNumber("RightWheelSpeed", RobotMap.rightWheelEncoder.getRate());
        
        
       if (RobotMap.ahrs != null) SmartDashboard.putNumber("Robot_Angle", RobotMap.ahrs.getYaw());
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
