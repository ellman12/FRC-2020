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

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.MecanumDrive;

public class Robot extends TimedRobot {

  // Used for running auto code once.
  boolean autoOnce = true;

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

  CANEncoder frontLeftDriveEnc;

  // Magic numbers.
  final double DRIVE_FWD_AND_BWD_SPEED = 0.5; // How fast the drive motors spin in driveFwd() and driveBwd().
  final double ENCODER_CALIBRATION_DRIVEFWDBWD = 1.43; // Encoder calibration for the driveFwd/Bwd auto functions.

  final double STRAFE_SPEED = 0.5; // Magic number for the speed for how fast the robot strafes.
  final double ENCODER_CALIBRATION_STRAFE = 1.15; // Encoder calibration for the strafeLeft/Right auto functions.

  @Override
  public void robotInit() {

    // Creating the drive motors, and assigning their IDs.
    frontLeftDriveMotor = new CANSparkMax(1, MotorType.kBrushless);
    frontRightDriveMotor = new CANSparkMax(2, MotorType.kBrushless);
    backLeftDriveMotor = new CANSparkMax(3, MotorType.kBrushless);
    backRightDriveMotor = new CANSparkMax(4, MotorType.kBrushless);

    // Adding the drive motors to the Mecanum Drive.
    mecanumDrive = new MecanumDrive(frontLeftDriveMotor, backLeftDriveMotor, frontRightDriveMotor, backRightDriveMotor);

    // Assigning the PS4 controller the ID of 0.
    PS4 = new Joystick(0);

    driveGyro = new ADXRS450_Gyro();

    // Assigning the encoder to its motor.
    frontLeftDriveEnc = new CANEncoder(frontLeftDriveMotor);

    // Putting the drive motors in coast mode.
    // frontLeftDriveMotor.setIdleMode(IdleMode.kCoast);
    // frontRightDriveMotor.setIdleMode(IdleMode.kCoast);
    // backLeftDriveMotor.setIdleMode(IdleMode.kCoast);
    // backRightDriveMotor.setIdleMode(IdleMode.kCoast);

    // Putting the drive in brake mode.
    frontLeftDriveMotor.setIdleMode(IdleMode.kBrake);
    frontRightDriveMotor.setIdleMode(IdleMode.kBrake);
    backLeftDriveMotor.setIdleMode(IdleMode.kBrake);
    backRightDriveMotor.setIdleMode(IdleMode.kBrake);

    // Turning off pointless warnings and the like.
    mecanumDrive.setSafetyEnabled(false);

    // Resetting the encoder.
    frontLeftDriveEnc.setPosition(0);

  }

  @Override
  public void robotPeriodic() {
  }

  @Override
  public void autonomousInit() {
  }

  @Override
  public void autonomousPeriodic() {

    // Run auto stuff only once.
    if (autoOnce == true) {
      driveBwd(5);
      Timer.delay(0.5);
      strafeRightAuto(4);
      Timer.delay(0.5);
      strafeLeftAuto(8);
      Timer.delay(0.5);
      strafeRightAuto(4);
      Timer.delay(0.5);
      driveFwd(5);
      autoOnce = false;
    }
  }

  @Override
  public void teleopPeriodic() {

    // Long if statement that acts as a deadband for the drive.
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

  /////////////////////////////////////////////////////////////////////
  // Function: strafeLeftAuto(...)
  /////////////////////////////////////////////////////////////////////
  //
  // Purpose: Used for strafing left in autonomous.
  //
  // Arguments: double feet.
  // This value is later converted to inches, to get inches per count.
  //
  // Returns: void
  //
  // Remarks: Created on 2/22/2020.
  //
  /////////////////////////////////////////////////////////////////////
  /////////////////////////////////////////////////////////////////////
  public void strafeLeftAuto(double feet) {

    // Initialize the encoder to 0 (reset it).
    frontLeftDriveEnc.setPosition(0);

    // Convert feet to inches.
    double inches = feet * 12.0;

    // Our current encoder count reading.
    double currentCounts = 0;

    // This should give us how many counts.
    double encoderCounts = inches / ENCODER_CALIBRATION_STRAFE;

    while (currentCounts < encoderCounts) {

      // Strafe left.
      frontLeftDriveMotor.set(-STRAFE_SPEED);
      backLeftDriveMotor.set(-STRAFE_SPEED);
      frontRightDriveMotor.set(STRAFE_SPEED);
      backRightDriveMotor.set(STRAFE_SPEED);

      // Delay for 20 ms.
      Timer.delay(0.02);

      // Read the encoder, and get our current counts.
      currentCounts = Math.abs(frontLeftDriveEnc.getPosition());

      // System.out.println("currentCounts: " + currentCounts + "\tencoderCounts: " +
      // encoderCounts);

    }

    // Stop the motors.
    frontLeftDriveMotor.set(0);
    backLeftDriveMotor.set(0);
    frontRightDriveMotor.set(0);
    backRightDriveMotor.set(0);

  }

  /////////////////////////////////////////////////////////////////////
  // Function: strafeRightAuto(...)
  /////////////////////////////////////////////////////////////////////
  //
  // Purpose: Used for strafing right in autonomous.
  //
  // Arguments: double feet.
  // This value is later converted to inches, to get inches per count.
  //
  // Returns: void
  //
  // Remarks: Created on 2/22/2020.
  //
  /////////////////////////////////////////////////////////////////////
  /////////////////////////////////////////////////////////////////////
  public void strafeRightAuto(double feet) {

    // Initialize the encoder to 0 (reset it).
    frontLeftDriveEnc.setPosition(0);

    // Convert feet to inches.
    double inches = feet * 12.0;

    // Our current encoder count reading.
    double currentCounts = 0;

    // This should give us how many counts.
    double encoderCounts = inches / ENCODER_CALIBRATION_STRAFE;

    while (currentCounts < encoderCounts) {

      // Strafe right.
      frontLeftDriveMotor.set(STRAFE_SPEED);
      backLeftDriveMotor.set(STRAFE_SPEED);
      frontRightDriveMotor.set(-STRAFE_SPEED);
      backRightDriveMotor.set(-STRAFE_SPEED);

      // Delay for 20 ms.
      Timer.delay(0.02);

      // Read the encoder, and get our current counts.
      currentCounts = Math.abs(frontLeftDriveEnc.getPosition());

      // System.out.println("currentCounts: " + currentCounts + "\tencoderCounts: " +
      // encoderCounts);

    }

    // Stop the motors.
    frontLeftDriveMotor.set(0);
    backLeftDriveMotor.set(0);
    frontRightDriveMotor.set(0);
    backRightDriveMotor.set(0);

  }

  /////////////////////////////////////////////////////////////////////
  // Function: driveFwd(...)
  /////////////////////////////////////////////////////////////////////
  //
  // Purpose: Used for driving forward in autonomous.
  //
  // Arguments: double feet.
  // This value is later converted to inches, to get inches per count.
  //
  // Returns: void
  //
  // Remarks: Created on 2/22/2020.
  //
  /////////////////////////////////////////////////////////////////////
  /////////////////////////////////////////////////////////////////////
  public void driveFwd(double feet) {

    // TODO acceleration/deceleration.

    // Initialize the encoder to 0 (reset it).
    frontLeftDriveEnc.setPosition(0);

    // Convert feet to inches.
    double inches = feet * 12.0;

    // Our current encoder count reading.
    double currentCounts = 0;

    // This should give us how many counts.
    double encoderCounts = inches / ENCODER_CALIBRATION_DRIVEFWDBWD;

    while (currentCounts < encoderCounts) {

      // Drive forward.
      frontLeftDriveMotor.set(DRIVE_FWD_AND_BWD_SPEED);
      backLeftDriveMotor.set(-DRIVE_FWD_AND_BWD_SPEED);
      frontRightDriveMotor.set(DRIVE_FWD_AND_BWD_SPEED);
      backRightDriveMotor.set(-DRIVE_FWD_AND_BWD_SPEED);

      // Delay for 20 ms.
      Timer.delay(0.02);

      // Read the encoder, and get our current counts.
      currentCounts = Math.abs(frontLeftDriveEnc.getPosition());

      // System.out.println("currentCounts: " + currentCounts + "\tencoderCounts: " +
      // encoderCounts);

    }

    // Stop the motors.
    // mecanumDrive.stopMotor();
    frontLeftDriveMotor.set(0);
    backLeftDriveMotor.set(0);
    frontRightDriveMotor.set(0);
    backRightDriveMotor.set(0);

  }

  /////////////////////////////////////////////////////////////////////
  // Function:
  /////////////////////////////////////////////////////////////////////
  //
  // Purpose: Used for driving backward in autonomous.
  //
  // Arguments: double feet
  // This value is later converted to inches, to get inches per count.
  //
  // Returns: void
  //
  // Remarks: Created on 2/22/2020.
  //
  /////////////////////////////////////////////////////////////////////
  /////////////////////////////////////////////////////////////////////
  public void driveBwd(double feet) {

    // TODO acceleration/deceleration.

    // Initialize the encoder to 0 (reset it).
    frontLeftDriveEnc.setPosition(0);

    // Convert feet to inches.
    double inches = feet * 12.0;

    // Our current encoder count reading.
    double currentCounts = 0;

    // This should give us how many counts.
    double encoderCounts = inches / ENCODER_CALIBRATION_DRIVEFWDBWD;

    while (currentCounts < encoderCounts) {

      // Drive forward.
      frontLeftDriveMotor.set(-DRIVE_FWD_AND_BWD_SPEED);
      backLeftDriveMotor.set(DRIVE_FWD_AND_BWD_SPEED);
      frontRightDriveMotor.set(-DRIVE_FWD_AND_BWD_SPEED);
      backRightDriveMotor.set(DRIVE_FWD_AND_BWD_SPEED);

      // Delay for 20 ms.
      Timer.delay(0.02);

      // Read the encoder, and get our current counts.
      currentCounts = Math.abs(frontLeftDriveEnc.getPosition());

      // System.out.println("currentCounts: " + currentCounts + "\tencoderCounts: " +
      // encoderCounts);

    }

    // Stop the motors.
    // mecanumDrive.stopMotor();
    frontLeftDriveMotor.set(0);
    backLeftDriveMotor.set(0);
    frontRightDriveMotor.set(0);
    backRightDriveMotor.set(0);

  }

}