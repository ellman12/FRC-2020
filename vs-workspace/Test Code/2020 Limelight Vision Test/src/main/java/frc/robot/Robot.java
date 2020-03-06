/////////////////////////////////////////////////////////////////////
// File: Robot.java
/////////////////////////////////////////////////////////////////////
//
// Purpose: Probably the only file for this program. This program is
// for testing Vision and also our new Limelight for the 2020 season.
//
// Authors: Elliott DuCharme.
//
// Environment: Microsoft VSCode Java
//
// Remarks: Created on 2/29/2020.
//
// For future reference:
// https://docs.limelightvision.io/en/latest/getting_started.html#basic-programming
//
/////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////
package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.MecanumDrive;

public class Robot extends TimedRobot {

  // Creating the drive motors.
  CANSparkMax frontLeftDriveMotor = new CANSparkMax(1, MotorType.kBrushless);
  CANSparkMax frontRightDriveMotor = new CANSparkMax(2, MotorType.kBrushless);
  CANSparkMax backLeftDriveMotor = new CANSparkMax(3, MotorType.kBrushless);
  CANSparkMax backRightDriveMotor = new CANSparkMax(4, MotorType.kBrushless);

  // Creating an object of the MecanumDrive class.
  // This links the 4 drive motors together.
  MecanumDrive mecanumDrive = new MecanumDrive(frontLeftDriveMotor, backLeftDriveMotor, frontRightDriveMotor,
      backRightDriveMotor);

  @Override
  public void robotInit() {

    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");

    // Whether the limelight has any valid targets (0 or 1)
    NetworkTableEntry tv = table.getEntry("tv");

    // Horizontal Offset From Crosshair To Target (-27 degrees to 27 degrees)
    NetworkTableEntry tx = table.getEntry("tx");

    // Vertical Offset From Crosshair To Target (-20.5 degrees to 20.5 degrees)
    NetworkTableEntry ty = table.getEntry("ty");

    // Target Area (0% of image to 100% of image)
    NetworkTableEntry ta = table.getEntry("ta");
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

  }

  @Override
  public void testPeriodic() {
  }
}