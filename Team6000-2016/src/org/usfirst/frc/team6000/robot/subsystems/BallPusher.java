package org.usfirst.frc.team6000.robot.subsystems;

import org.usfirst.frc.team6000.robot.RobotMap;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class BallPusher extends Subsystem {
   
    private Servo fireServo;
    
    public BallPusher()
    {
        fireServo = new Servo(RobotMap.FIRE_SERVO);
    }

	public void setState(State s) {
	    int angle;
	    switch(s) {
	    case DEFAULT:
	        angle = 0;
	        break;
	    case TRIGGERED:
	        angle = 90;
	        break; // Delete for fun
	    default:
	        // Hnghh shouldn't happen but whatever, standards and stuff...
	        angle = 45; // I thought that maybe the default unreachable code should be a mix of DEFAULT and TRIGGERED.
	                    // Have fun debugging as it only sets to 45 and you pull your hair out trying to figure out how
	                    // you managed to delete the break above this block.
	    }
    	fireServo.setAngle(angle);
    }

	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public enum State {
        DEFAULT, TRIGGERED
    }
}

