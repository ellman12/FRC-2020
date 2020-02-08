package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;

public class Robot extends TimedRobot {

  // Creating instance of DriveThread.
  DriveThread driveThread;

  // Create instance of the Sensors class.
  Sensors sensors = new Sensors();

  @Override
  public void robotInit() {

    // Calling the DriveThread, and telling it to do its thing.
    // Calling it once in robotInit() should help prevent it from
    // being called a gazillion times in autoPeriodic/teleopPeriodic().
    driveThread = new DriveThread("driveThread");

  }

  @Override
  public void robotPeriodic() {
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