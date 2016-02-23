package org.usfirst.frc.team6000.robot.commands;

import org.usfirst.frc.team6000.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

/**
 *
 */
public class Aim extends Command {

	private static final double HALF_SCREEN = 224.0;
	private static final double SPEED = 0.1;
	private static final double CENTERED_TOLERANCE_PIXELS = 10.0;

	private NetworkTable table;
	private double[] defaultValue = new double[0];

	public Aim() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}
	

	// Called just before this Command runs the first time
	protected void initialize() {
		table = NetworkTable.getTable("GRIP/myContoursReport");	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {

		// if not centered, rotate until we are centered
		while (getXAngleOff() > CENTERED_TOLERANCE_PIXELS) {
			double centerX = getCenterX();
			System.out.println("centerX: " + centerX);
			if (centerX < HALF_SCREEN) {
				rotateLeft(SPEED);
			} else {
				rotateRight(SPEED);
			}
		}

		// now that we are centered, find distance to target
		// TODO: FIX THIS
		double distance = findDistance(getCenterY());
		System.out.println("distance: " + distance);
		
		// given distance, find angle to aim up
		double yAngle = findYAngleGivenDistance(distance);
		System.out.println("yAngle: " + yAngle);
	}
	
	private double getValueForLargestArea(double[] areas, double[] values ){
		int largestContourPosition = 0;
		for (int x = 0; x < areas.length; x++) {
			if (areas[x] > areas[largestContourPosition]) {
				largestContourPosition = x;
			}	
		}
		return values[largestContourPosition];
	}

	private double getXAngleOff() {
		return Math.abs(HALF_SCREEN - getCenterX());
	}

	private double getCenterX() {
		double[] areas = table.getNumberArray("area", defaultValue);
		double[] values = table.getNumberArray("centerX", defaultValue);		
		double centerX = getValueForLargestArea(areas, values);
		return centerX;
	}

	private double getCenterY() {
		double[] areas = table.getNumberArray("area", defaultValue);
		double[] values = table.getNumberArray("centerY", defaultValue);		
		double centerY = getValueForLargestArea(areas, values);
		return centerY;
	}

	private void rotateLeft(double speed) {
		//TODO: Use correct command to rotate
		RobotMap.leftMotor.set(-speed);
		RobotMap.rightMotor.set(speed);
	}

	private void rotateRight(double speed) {
		//TODO: Use correct command to rotate
		RobotMap.leftMotor.set(speed);
		RobotMap.rightMotor.set(-speed);
	}

	double findYAngleGivenDistance(double distance) {
		// Formula based on collecting data from webcam and plotting graph
		return Math.toDegrees(Math.atan(95 / distance));
	}

	double findDistance(double centerY) {
		// TODO: Fix this
		// Formula based on collecting data from webcam and plotting graph
		return 213.5 + ((56 - 213) / (1 + Math.pow(centerY / 298, 2.9)));
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
