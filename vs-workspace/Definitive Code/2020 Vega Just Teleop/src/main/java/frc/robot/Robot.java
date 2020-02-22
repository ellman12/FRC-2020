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

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends TimedRobot {

  // Creating the drive motors.
  CANSparkMax frontLeftDriveMotor, backLeftDriveMotor, frontRightDriveMotor, backRightDriveMotor;

  // Creating the Mecanum Drive.
  MecanumDrive mecanumDrive;

  // Creating the PS4 controller.
  Joystick PS4;

  // Creating the drive gyro.
  ADXRS450_Gyro driveGyro;

  // Magic numbers for the deadbands for the PS4 joystick axes.
  final double PS4_TRIGGER_DEADBAND_POSITIVE = 0.2;
  final double PS4_TRIGGER_DEADBAND_NEGATIVE = -0.2;

  @Override
  public void robotInit() {

    frontLeftDriveMotor = new CANSparkMax(1, MotorType.kBrushless);
    frontRightDriveMotor = new CANSparkMax(2, MotorType.kBrushless);
    backLeftDriveMotor = new CANSparkMax(3, MotorType.kBrushless);
    backRightDriveMotor = new CANSparkMax(4, MotorType.kBrushless);

    // Adding the drive motors to the Mecanum Drive.
    mecanumDrive = new MecanumDrive(frontLeftDriveMotor, backLeftDriveMotor, frontRightDriveMotor, backRightDriveMotor);

    // Assigning the PS4 controller the ID of 0.
    PS4 = new Joystick(0);

    driveGyro = new ADXRS450_Gyro();

    mecanumDrive.setSafetyEnabled(false);
    // mecanumDrive.setDeadband(0.3);

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

    // Long-ass if statement that acts as a deadband for the drive.
    if (((PS4.getX() > PS4_TRIGGER_DEADBAND_POSITIVE) || (PS4.getY() > PS4_TRIGGER_DEADBAND_POSITIVE)
        || (getZAxis() > PS4_TRIGGER_DEADBAND_POSITIVE))
        || ((PS4.getX() < PS4_TRIGGER_DEADBAND_NEGATIVE) || (PS4.getY() < PS4_TRIGGER_DEADBAND_NEGATIVE)
            || (getZAxis() < PS4_TRIGGER_DEADBAND_NEGATIVE))) {
      mecanumDrive.driveCartesian(-PS4.getY(), getZAxis(), PS4.getX());
    } else {
      mecanumDrive.driveCartesian(0, 0, 0);
    }

  }

  @Override
  public void testPeriodic() {
  }

  /////////////////////////////////////////////////////////////////////
  // Function: getZAxis()
  /////////////////////////////////////////////////////////////////////
  //
  // Purpose: Gets the value for the "Z" axis using the 2 analog triggers.
  //
  // Arguments: none
  //
  // Returns: double zAxis: the value.
  //
  // Remarks: Created on 2/22/2020.
  //
  /////////////////////////////////////////////////////////////////////
  /////////////////////////////////////////////////////////////////////
  public double getZAxis() {

    // This variable is used for setting the Z axis for strafing.
    double zAxis;

    // These variables are for getting the values for the left and right
    // analog triggers, respectively.
    double leftAnalogTrigger;
    double rightAnalogTrigger;

    // Axes 2 and 3 are the left and right analog triggers, respectively.
    // You have to add 1 because the triggers start at -1 and go to 1.
    // Adding 1 makes them start at 0 when not being pressed.
    leftAnalogTrigger = PS4.getRawAxis(3) + 1;
    rightAnalogTrigger = PS4.getRawAxis(4) + 1;

    // Do the math for getting the value for strafing.
    // Example 1: if the driver presses the right one down, that value will be 1 - 0
    // = 100% speed (1).
    // Example 2: if the driver presses the left one down, that value will be 0 - 1
    // ; -100% speed (-1).
    zAxis = rightAnalogTrigger - leftAnalogTrigger;

    // Return the value, to be used elsewhere.
    return zAxis;
  }

}