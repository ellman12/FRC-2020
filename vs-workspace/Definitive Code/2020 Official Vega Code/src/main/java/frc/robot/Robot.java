package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;

public class Robot extends TimedRobot {

  // Creating instance of DriveThread.
  DriveThread driveThread;

  // Create instance of the Sensors class.
  Sensors sensors = new Sensors();

  // Creating an instance of the Variables class.
  Variables variables = new Variables();

  @Override
  public void robotInit() {

    // Calling the DriveThread, and telling it to get ready to/start running.
    // Calling it once in robotInit() should help prevent it from
    // being called a gazillion times in autoPeriodic/teleopPeriodic().
    driveThread = new DriveThread("driveThread");

    // Setting initial Thread priorities.
    driveThread.driveThread.setPriority(variables.MAX_THREAD_PRIORITY); // Thread priority of 10 (max).
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