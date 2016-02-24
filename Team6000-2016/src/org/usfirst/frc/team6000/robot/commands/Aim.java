package org.usfirst.frc.team6000.robot.commands;

import org.usfirst.frc.team6000.robot.OI;
import org.usfirst.frc.team6000.robot.Robot;
import org.usfirst.frc.team6000.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

/**
 *
 */
public class Aim extends Command {

	private static final double HALF_SCREEN = 224.0;
	private static final double SPEED = 0.15;
	private static final double CENTERED_TOLERANCE_PIXELS = 10.0;
	private static final double NO_VALUE = Double.NEGATIVE_INFINITY;

	private NetworkTable table;
	private double[] defaultValue = new double[0];
    private boolean isFinished = false;
	
	public Aim() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.DriveTrain);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		table = NetworkTable.getTable("GRIP/myContoursReport");
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
 
		// if not centered, rotate until we are centered
		double xAngleOff = getXAngleOff();
		while (xAngleOff > CENTERED_TOLERANCE_PIXELS) {
			if (xAngleOff == NO_VALUE){
				return;
			}
		    double centerX = getCenterX();
			System.out.println("centerX: " + centerX);
			if (centerX < HALF_SCREEN) {
				rotateLeft(SPEED);
			} else {
				rotateRight(SPEED);
			}
			if (OI.leftStick.getTrigger(null)) {
				break;
			}
			xAngleOff = getXAngleOff();
		}

		// now that we are centered, find distance to target
		double distance = findDistance(getCenterY());
		if (distance == NO_VALUE){
			return;
		}
		System.out.println("distance: " + distance);

		// given distance, find angle to aim up
//		double yAngle = findYAngleGivenDistance(distance);
//		System.out.println("yAngle: " + yAngle);
//		System.out.println("centerY: " + getCenterY());
//
//		ShooterAngle shooterAngle = new ShooterAngle(yAngle, 35);
//		Scheduler.getInstance().add(shooterAngle);
		
	}

	private double getValueForLargestArea(double[] areas, double[] values) {
		if (areas.length == 0 || values.length == 0 || (areas.length != values.length)) {
			return NO_VALUE;
		}
		int largestContourPosition = 0;
		for (int x = 0; x < areas.length; x++) {
			if (areas[x] > areas[largestContourPosition]) {
				largestContourPosition = x;
			}
		}
		return values[largestContourPosition];
	}

	private double getXAngleOff() {
		double centerX = getCenterX();
		if (centerX == NO_VALUE){
			return NO_VALUE;
		}
		return Math.abs(HALF_SCREEN - centerX);
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
		// TODO: Use correct command to rotate
		Robot.DriveTrain.rotate(-speed);
	}

	private void rotateRight(double speed) {
		// TODO: Use correct command to rotate
		Robot.DriveTrain.rotate(speed);
	}

	double findYAngleGivenDistance(double distance) {
		// Formula based on collecting data from webcam and plotting graph
		return 63.13 + (46095730 - 63.13)/(1 + Math.pow((distance - 31)/0.10167, 2.3777));
	}

	double findDistance(double centerY) {
		// TODO: Fix this
		// Formula based on collecting data from webcam and plotting graph
		return 20434640 + ((86.2 - 20434640) / (1 + Math.pow(centerY / 887537.7, 1.444)));
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return isFinished;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
