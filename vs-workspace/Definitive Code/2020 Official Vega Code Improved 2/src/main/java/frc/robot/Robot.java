/////////////////////////////////////////////////////////////////////
// File: Robot.java
/////////////////////////////////////////////////////////////////////
//
// Purpose: The main file that links every other class and thread
// together, plus some other stuff.
//
// Authors: Elliott DuCharme and Larry Basegio.
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

  // Creating the PS4 controller, with an ID of 0.
  Joystick PS4 = new Joystick(0);

  // Creating instances of these classes, so everything is all linked together.
  // When Robot is extended in other classes, all of this stuff can be accessed
  // in those files, too.
  BallIntake ballIntake = new BallIntake();
  // TODO BallIntakeThread
  BallShooter ballShooter = new BallShooter();
  BallShootThread ballShootThread = new BallShootThread("ballShootThread");
  DriveThread driveThread = new DriveThread("driveThread");
  RobotDrive robotDrive = new RobotDrive();
  VarsAndConsts varsAndConsts = new VarsAndConsts();

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