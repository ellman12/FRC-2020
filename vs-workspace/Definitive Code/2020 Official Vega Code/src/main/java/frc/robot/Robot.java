package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;

public class Robot extends TimedRobot {

  // distances expressed in inches; velocity in ft/sec
  static double v = 25.0; // Ball velocity.
  static double x0 = 0; // Initial x value.
  static double y0 = 24; // Launch location (initial y value).
  static double y = 98.25; // Target of y (height of inner goal of the tower (in inches)).

  // Calling the Thread classes in Robot.java.
  // Creating an instance of DriveThread.
  DriveThread driveThread;

  // Creating an instance of BallShootThread.
  BallShootThread ballShootThread;

  // Creating an instance of WormDriveThread.
  WormDriveThread wormDriveThread;

  // Create instance of the Sensors class.
  Sensors sensors = new Sensors();

  // Creating an instance of the Variables class.
  Variables variables = new Variables();

  // Creating an instance of the ComputeTrajectory class.
  ComputeTrajectory ComputeTrajectory = new ComputeTrajectory(x0, x, y0, y, v);

  @Override
  public void robotInit() {

    // Calling the DriveThread, and telling it to get ready to/start running.
    // Calling these Threads once in robotInit() should help prevent them from
    // being called a gazillion times in autoPeriodic/teleopPeriodic().
    driveThread = new DriveThread("DriveThread");

    // Calling the BallShootThread, and telling it to get ready to/start running.
    ballShootThread = new BallShootThread("BallShootThread");

    // Calling the WormDriveThread, and telling it to get ready to/start running.
    wormDriveThread = new WormDriveThread("WormDriveThread");

    // Setting Thread priorities.
    driveThread.driveThread.setPriority(variables.MAX_THREAD_PRIORITY); // Thread priority of 10 (max).
    ballShootThread.ballShootThread.setPriority(variables.MAX_THREAD_PRIORITY); // Thread priority of 10 (max).
    wormDriveThread.wormDriveThread.setPriority(variables.MAX_THREAD_PRIORITY); // Thread priority of 10 (max).
  }

  @Override
  public void robotPeriodic() {

    // Calling the readSensors() function.
    // It is constantly being called in robotPeriodic(), and thus all of the sensors
    // in the Sensors class are constantly being read.
    // This helps reduce redundancy, because the same sensors don't need to be read
    // multiple times, the variable they're stored in can just be accessed whenever
    // necessary.
    sensors.readSensors();
  }

  @Override
  public void autonomousInit() {
  }

  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopPeriodic() {
  }

  @Override
  public void testPeriodic() {
  }

}