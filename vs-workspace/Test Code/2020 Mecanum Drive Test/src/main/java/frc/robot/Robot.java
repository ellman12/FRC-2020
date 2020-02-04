/////////////////////////////////////////////////////////////////////
// File: Robot.java
/////////////////////////////////////////////////////////////////////
//
// Purpose: This program is for testing Mecanum Drive for the 2020 FRC game.
//
// Authors: Elliott DuCharme of FRC Team #5914
//
// Environment: Microsoft VSCode Java
//
// Remarks: 
//
/////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////
package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends TimedRobot {

  static String gameData;

  // Creating the drive motors.
  WPI_TalonFX frontLeftMotor, frontRightMotor, backLeftMotor, backRightMotor;

  // Creatingn the Mecanum Drive.
  MecanumDrive mecanumDrive;

  // Creating the PS4 controller.
  Joystick PS4;

  @Override
  public void robotInit() {

    // Dummy IDs for right now!
    frontLeftMotor = new WPI_TalonFX(1);
    frontRightMotor = new WPI_TalonFX(2);
    backLeftMotor = new WPI_TalonFX(3);
    backRightMotor = new WPI_TalonFX(4);

    // Adding the drive motors to the Mecanum Drive.
    mecanumDrive = new MecanumDrive(frontLeftMotor, backLeftMotor, frontRightMotor, backLeftMotor);

    // Assigning the PS4 controller the ID of 0.
    PS4 = new Joystick(0);
  }

  @Override
  public void robotPeriodic() {
  }

  @Override
  public void autonomousInit() {
  }

  @Override
  public void autonomousPeriodic() {

  }

  @Override
  public void teleopPeriodic() {

    // Drive the robot's Mecanum Drive with the PS4 controller.
    mecanumDrive.driveCartesian(PS4.getY(), PS4.getX(), PS4.getZ());

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

  public String getGameData() {

    gameData = DriverStation.getInstance().getGameSpecificMessage();

    gameData = gameData.substring(0, 2);

    SmartDashboard.putString("Colors", gameData);

    return (gameData);
  }

}