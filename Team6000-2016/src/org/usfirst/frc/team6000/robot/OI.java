package org.usfirst.frc.team6000.robot;

import edu.wpi.first.wpilibj.Joystick;


import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team6000.robot.commands.Aim;
import org.usfirst.frc.team6000.robot.commands.ArticulateShooter;
import org.usfirst.frc.team6000.robot.commands.BallIntake;
import org.usfirst.frc.team6000.robot.commands.BreakOutOfAimLoop;
import org.usfirst.frc.team6000.robot.commands.DriveWithJoysticks;
import org.usfirst.frc.team6000.robot.commands.Fire;
import org.usfirst.frc.team6000.robot.commands.FireSequence;
import org.usfirst.frc.team6000.robot.commands.IntakeAngle;
import org.usfirst.frc.team6000.robot.commands.RobotAngle;
import org.usfirst.frc.team6000.robot.commands.SetShooterSpeed;
import org.usfirst.frc.team6000.robot.commands.Shoot;
import org.usfirst.frc.team6000.robot.commands.ShooterAngle;
import org.usfirst.frc.team6000.robot.commands.ZeroShooter;
import org.usfirst.frc.team6000.robot.subsystems.DriveTrain;
import org.usfirst.frc.team6000.robot.subsystems.Shooter;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
	public static Joystick leftStick = new Joystick(0);
	public static Joystick rightStick = new Joystick(1);
	public static Joystick shooterPad = new Joystick(2);
	
	Button button5 = new JoystickButton(leftStick, 5);
	Button button3 = new JoystickButton(leftStick, 3);
	
	Button button4 = new JoystickButton(leftStick,4);
	Button button6 = new JoystickButton(leftStick, 6);
	
	Button button7 = new JoystickButton(leftStick,7);
	Button button8 = new JoystickButton(leftStick, 8);
	
	Button button12 = new JoystickButton(leftStick,12);
	
	Button button9 = new JoystickButton(leftStick,9);
	Button button1 = new JoystickButton(leftStick, 1);
    
	Button shootButton0 = new JoystickButton(shooterPad, 0);
	Button shootButton1 = new JoystickButton(shooterPad, 1);
	Button shootButton2 = new JoystickButton(shooterPad, 2);
	Button shootButton3 = new JoystickButton(shooterPad, 3);
	Button shootButton4 = new JoystickButton(shooterPad, 4);
	Button shootButton5 = new JoystickButton(shooterPad, 7);
	
	Button intakeDown = new JoystickButton(shooterPad, 5);
	Button intakeUp = new JoystickButton(shooterPad, 6);
	
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
	public OI(){
		
//shootButton4.whenPressed(new BallIntake(-0.75, shooterPad, 4));
//shootButton5.whenPressed(new BallIntake(0.5, shooterPad, 5));
		shootButton2.whileHeld(new SetShooterSpeed(-90.0));
		shootButton1.whenPressed(new FireSequence());
		shootButton3.whenPressed(new ZeroShooter());
		
//		button4.whenPressed(new ArticulateShooter(0.25, leftStick, 4));
//		button6.whenPressed(new ArticulateShooter(-0.25, leftStick, 6));
		
		
		
		shootButton4.whenPressed(new ShooterAngle(55, shooterPad, 4));
		
		button12.whenPressed(new RobotAngle(85, leftStick, 12));
		
//		button9.whenPressed(new Aim());
		button1.whenPressed(new BreakOutOfAimLoop());
		
		intakeUp.whenPressed(new IntakeAngle(false));
		intakeDown.whenPressed(new BallIntake(-.75, shooterPad, 5));
		
	}

    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
}

