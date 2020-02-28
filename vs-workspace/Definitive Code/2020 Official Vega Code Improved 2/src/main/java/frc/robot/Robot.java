/////////////////////////////////////////////////////////////////////
// File: Robot.java
/////////////////////////////////////////////////////////////////////
//
// Purpose: 
//
// Authors: Elliott DuCharme.
//
// Environment: Microsoft VSCode Java
//
// Remarks: Created on 2/25/2020.
//
/////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////
package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;

public class Robot extends TimedRobot {

  // Used for running auto code once.
  boolean autoOnce = true;

  // Creating instances of these classes, so everything is all linked together.
  DriveThread driveThread = new DriveThread("driveThread");
  RobotDrive robotDrive = new RobotDrive();
  VarsAndConsts varsAndConsts = new VarsAndConsts();

  // Creating the PS4 controller, with an ID of 0.
  Joystick PS4 = new Joystick(0);

  @Override
  public void robotInit() {

  }

  @Override
  public void robotPeriodic() {
  }

  @Override
  public void autonomousInit() {

    // Put SmartDashboard stuff here...

  }

  @Override
  public void autonomousPeriodic() {

    // Run auto stuff only once.
    if (autoOnce == true) {
      // Auto code here...
      autoOnce = false;
    }

  }

  @Override
  public void teleopPeriodic() {

  }

  @Override
  public void testPeriodic() {
  }

}