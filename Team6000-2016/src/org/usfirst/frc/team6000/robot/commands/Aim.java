package org.usfirst.frc.team6000.robot.commands;

import org.usfirst.frc.team6000.robot.OI;
import org.usfirst.frc.team6000.robot.Robot;
import org.usfirst.frc.team6000.robot.RobotMap;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Aim extends Command {

	private static final double HALF_SCREEN = 224.0;
	private static final double SPEED = 0.15;
	private static final double CENTERED_TOLERANCE_PIXELS = 10.0;
	// private static final double NO_VALUE = Double.NEGATIVE_INFINITY;
	private final static int CALIBRATION_DEGREES = 5;
	private final static int CENTER_SCREEN_PIXELS = 224;
	private final static String MY_CONTOURS_REPORT_TABLE_NAME = "GRIP/myContoursReport";
	private final static long SLEEP_MILLIS = 1000;

	private static final int NO_VALUE = Integer.MIN_VALUE;
	private static final List<String> TABLE_NAME_KEYS = Arrays.asList("area", "centerX", "centerY");
	private static final Double[] DEFAULT_VALUE = new Double[0];
	private static final double PIXEL_TOLERANCE = 3;
	public static boolean breakOutOfLoop = false;

	public volatile boolean hasButtonBeenClicked = false;
	public volatile boolean areaHasNotIncreasedDramitically;
	public static ArrayList<Double> centerYShootingHistory = new ArrayList();
	public static ArrayList<Integer> shooterAngleShootingHistory = new ArrayList();

	ExecutorService threadPool = Executors.newCachedThreadPool();

	private NetworkTable table;
	private int sizesAreSameCount = 0;
	private double[] defaultValue = new double[0];
	private boolean isFinished = false;

	public Aim() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.driveTrain);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		table = NetworkTable.getTable("GRIP/myContoursReport");

	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
      rotateToTargetInFixedIncrements();
	}

	public static void setBreakOutOfLoopToTrue(){
		breakOutOfLoop = true;
	}
	
	
	private void sleep() {
		sleep(SLEEP_MILLIS);
	}

	private void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
		}
	}


	private Integer getCenterYOfGoal(Map<String, Double[]> map) {
		Double[] areaArray = map.get("area");
		Double[] centerYArray = map.get("centerY");
		int position = findLargestContourPosition(areaArray);
		if (position == NO_VALUE) {
			return NO_VALUE;
		}
		return centerYArray[position].intValue();
	}

	private Double getAreaOfGoal(Map<String, Double[]> map) {
		Double[] areaArray = map.get("area");
		int position = findLargestContourPosition(areaArray);
		if (position == NO_VALUE) {
			return (double) NO_VALUE;
		}
		return areaArray[position];
	}

	public Map<String, Double[]> getTableValueMap() {
		Map<String, Double[]> map = new HashMap<>();
		for (String key : TABLE_NAME_KEYS) {
			map.put(key, table.getNumberArray(key, DEFAULT_VALUE));
		}

		Double[] areaArray = map.get("area");
		Double[] centerXArray = map.get("centerX");
		if (areaArray.length == centerXArray.length) {
			sizesAreSameCount++;
		} else {
			System.out.println("Successive calls to NetworkTable returned different size arrays." + "  areaArray: "
					+ areaArray.length + "  centerXArray: " + centerXArray.length + ".  Number of times NOT a problem: "
					+ sizesAreSameCount);
			map = getTableValueMap();
		}

		return map;
	}

	public void onAimUsingRotatingContinuslyButtonClicked() {
		threadPool.submit(new Runnable() {
			public void run() {
				rotateToTargetInFixedIncrements();
			}
		});
	}

	private void determineIfAreaHasNotIncreased() {
		// Get initial value of oldArea
		Double oldArea = (double) NO_VALUE;
		Map<String, Double[]> map = getTableValueMap();
		Double[] areaArray = map.get("area");
		int position = findLargestContourPosition(areaArray);
		if (position != NO_VALUE) {
			oldArea = areaArray[position];
		}

		Double newArea;
		while (true) {
			// Get new area'
			map = getTableValueMap();
			areaArray = map.get("area");
			position = findLargestContourPosition(areaArray);
			if (position == NO_VALUE) {
				newArea = (double) NO_VALUE;
			} else {
				newArea = areaArray[position];
			}

			// Compare new area
			areaHasNotIncreasedDramitically = areaHasNotChangedDramatically(oldArea, newArea);

			// Get ready to do it again
			oldArea = newArea;
			sleep(10);
		}
	}

	private boolean areaHasNotChangedDramatically(double oldArea, double newArea) {
		if (oldArea == NO_VALUE || newArea == NO_VALUE) {
			return false;
		}
		if (Math.abs(oldArea - newArea) < 100) {
			return true;
		} else {
			System.out.println("area jumped dramatically");
			return false;
		}
	}

	private void rotateLeft(int calibrationDegrees) {
		Scheduler.getInstance().add(new RobotAngle(-calibrationDegrees, 0.5));
	}

	private void rotateRight(int calibrationDegrees) {
		Scheduler.getInstance().add(new RobotAngle(calibrationDegrees, 0.5));
	}

	private Integer getCenterXOfGoal(Map<String, Double[]> map) {
		Double[] areaArray = map.get("area");
		Double[] centerXArray = map.get("centerX");
		int position = findLargestContourPosition(areaArray);
		if (position == NO_VALUE) {
			return NO_VALUE;
		}
		return centerXArray[position].intValue();
	}

	public void TESTreadValues(String tableName) throws InterruptedException {
		while (true) {
			Map<String, Double[]> map = getTableValueMap();
			Double[] areaArray = map.get("area");
			Double[] centerXArray = map.get("centerX");
			int largestContourPosition = findLargestContourPosition(areaArray);
			if (largestContourPosition == NO_VALUE) {
				System.out.println("Array is empty");
			} else {
				System.out.println(centerXArray[largestContourPosition]);
			}
			sleep();
		}
	}

	private int findLargestContourPosition(Double[] areaArray) {
		if (areaArray.length == 0) {
			return NO_VALUE;
		}
		int largestContourPosition = 0;
		double largestContour = areaArray[0];
		for (int x = 1; x < areaArray.length; x++) {
			if (areaArray[x] > largestContour) {
				largestContourPosition = x;
				largestContour = areaArray[x];
			}
		}
		return largestContourPosition;
	}

	public void rotateToTargetInFixedIncrements() {
		System.out.println("started rotating to target");
		Map<String, Double[]> map = getTableValueMap();
		double centerX = getCenterXOfGoal(map);
		SmartDashboard.putNumber("centerX", centerX);
		double oldArea = getAreaOfGoal(map);
		SmartDashboard.putNumber("oldArea", oldArea);

		while (centerX == NO_VALUE) {
			sleep(10);
			map = getTableValueMap();
			centerX = getCenterXOfGoal(map);
			oldArea = getAreaOfGoal(map);
		}
		double newArea;
		boolean finished = false;
		long startTime = 0;
		while (!finished && !breakOutOfLoop) {
			boolean inCenter = Math.abs(centerX - CENTER_SCREEN_PIXELS) < PIXEL_TOLERANCE;
			if (inCenter) {
				if (startTime == 0) {
					startTime = System.currentTimeMillis();
				} else if (System.currentTimeMillis() - startTime > 500) {
					finished = true;
				}
			} else {
				startTime = 0;
				newArea = getAreaOfGoal(map);
				boolean notChanged = areaHasNotChangedDramatically(oldArea, newArea);
				// System.out.println("areaHasNotChangedDramatically: " +
				// notChanged);
				if (notChanged) {
					if (centerX < CENTER_SCREEN_PIXELS) {
						rotateLeft(1);
					}
					if (centerX > CENTER_SCREEN_PIXELS) {
						rotateRight(1);
					}
				}
				map = getTableValueMap();
				centerX = getCenterXOfGoal(map);
				oldArea = newArea;
			}
			System.out.println(centerX);
			sleep(100);
		}
		if (breakOutOfLoop == false) {	
			System.out.println("STOP, AT TARGET");
			//TODO check with Seiji if right
			RobotMap.leftMotor.set(0.0);
			RobotMap.rightMotor.set(0.0);
			shootUsingYValue();
		} else {
			breakOutOfLoop = false;
		}
	}

	public void shootUsingYValue() {
		Map<String, Double[]> map = getTableValueMap();
		double centerY = getCenterYOfGoal(map);
		SmartDashboard.putNumber("centerY", centerY);
		int shooterAngle = getShooterAngleFromCenterY(centerY);
		//centerYShootingHistory.add(centerY);
		//shooterAngleShootingHistory.add(shooterAngle);
		rotateShooterAngle(shooterAngle);
		//TODO check with Seiji if right
		// TODO Scheduler.getInstance().add(new Fire(90));
	}

	private int getShooterAngleFromCenterY(double centerY) {
		// TODO Auto-generated method stub
		return 0;
	}

	private void rotateShooterAngle(int shooterAngle) {
		//TODO check if this is right with Seiji
		Scheduler.getInstance().add(new ShooterAngle(shooterAngle, 1));
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
