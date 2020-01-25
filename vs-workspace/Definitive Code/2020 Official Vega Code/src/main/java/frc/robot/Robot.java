/////////////////////////////////////////////////////////////////////
// File: Robot.java
/////////////////////////////////////////////////////////////////////
//
// Purpose: The official robot code for the 2020 FRC robot Vega.
//
// Authors: Elliott DuCharme
//
// Environment: Microsoft VSCode Java
//
// Remarks:
//
/////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////
package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;

public class Robot extends TimedRobot {

  // Linking other classes together.
  public static Autonomous autoFunctions;
  public static ColorClass colorClass;
  public static Constants constants;
  public static Delay delay;
  public static DriveThread driveThread;
  public static ProximitySensor proximitySensor;
  public static Sensors sensors;

  // Boolean for if the drive thread is active or not.
  // Starts out as false.
  public static boolean drive_thread_active = false;

  // Used for running auto code only once.
  boolean auto_once = true;

  @Override
  public void robotInit() {

    autoFunctions = new Autonomous();
    colorClass = new ColorClass();
    constants = new Constants();
    delay = new Delay();
    proximitySensor = new ProximitySensor();
    sensors = new Sensors();

    sensors.driveGyro.calibrate();
    sensors.driveGyro.reset();
  }

  @Override
  public void robotPeriodic() {

    // Read the sensors constantly, no matter if the robot
    // is in Auto, Teleop, or whenever.
    sensors.readSensors();
  }

  @Override
  public void autonomousInit() {

  }

  @Override
  public void autonomousPeriodic() {

    // Need to prevent unintentional duplication of the drive thread
    // as this block during autonomous is called every 20msec. We
    // limit the creation of the thread to one time. It is
    // intended that a single thread handles all of the
    // autonomous operations. That said, we could create additional
    // threads for other functions inside this block.
    if (auto_once == true) {

      driveThread = new DriveThread("autonomous operations");

      auto_once = false;
    }

  }

  @Override
  public void teleopPeriodic() {

    // Reading the values of the 4 analog stick positions.
    driveThread.PS4LeftXAxis = driveThread.PS4.getRawAxis(Constants.LEFT_X_AXIS_PORT);
    driveThread.PS4LeftYAxis = driveThread.PS4.getRawAxis(Constants.LEFT_Y_AXIS_PORT);
    driveThread.PS4RightXAxis = driveThread.PS4.getRawAxis(Constants.RIGHT_X_AXIS_PORT);
    driveThread.PS4RightYAxis = driveThread.PS4.getRawAxis(Constants.RIGHTT_Y_AXIS_PORT);

    // Drive the robot's Mecanum Drive with the PS4 controller.
    driveThread.mecanumDrive.driveCartesian(driveThread.PS4.getY(), driveThread.PS4.getX(), driveThread.PS4.getZ());

    // IDK about the Z axis.
    // Our 2nd year robot (Kova) could strafe using the 2 analog triggers. We should
    // do that?

    // IDK which one we should use; not much documentation on each.
    // mecanumDrive.driveCartesian(ySpeed, xSpeed, zRotation, gyroAngle);
    // mecanumDrive.drivePolar(magnitude, angle, zRotation);

  }

  @Override
  public void testPeriodic() {
  }
}