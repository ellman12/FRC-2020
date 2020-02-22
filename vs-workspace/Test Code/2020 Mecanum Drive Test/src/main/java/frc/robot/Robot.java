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

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends TimedRobot {

  static String gameData;

  // Creating the drive motors.
  CANSparkMax frontLeftMotor, frontRightMotor, backLeftMotor, backRightMotor;

  // WPI_TalonFX fLShooter, bLshooter, frshooter, brshooter;

  // CANSparkMax rworm, lworm;

  // WPI_TalonFX intake;

  // Creating the Mecanum Drive.
  MecanumDrive mecanumDrive;

  // Creating the PS4 controller.
  Joystick PS4;

  // SpeedControllerGroup wormDrive;

  double PS4_L_Y;

  final double PS4_TRIGGER_DEADBAND_POSITIVE = 0.2;
  final double PS4_TRIGGER_DEADBAND_NEGATIVE = -0.2;

  @Override
  public void robotInit() {

    // Dummy IDs for right now!
    // frontLeftMotor = new (1);
    // frontRightMotor = new WPI_TalonFX(3);
    // backLeftMotor = new WPI_TalonFX(2);
    // backRightMotor = new WPI_TalonFX(4);

    frontLeftMotor = new CANSparkMax(1, MotorType.kBrushless);
    frontRightMotor = new CANSparkMax(3, MotorType.kBrushless);
    backLeftMotor = new CANSparkMax(2, MotorType.kBrushless);
    backRightMotor = new CANSparkMax(4, MotorType.kBrushless);

    // fLShooter = new WPI_TalonFX(5);
    // bLshooter = new WPI_TalonFX(7);
    // frshooter = new WPI_TalonFX(6);
    // brshooter = new WPI_TalonFX(8);

    // rworm = new CANSparkMax(9, MotorType.kBrushless);
    // lworm = new CANSparkMax(10, MotorType.kBrushless);

    // intake = new WPI_TalonFX(11);

    // backLeftMotor.setInverted(false);
    // frontLeftMotor.setInverted(false);

    // backRightMotor.setInverted(true);
    // frontRightMotor.setInverted(true);

    // Adding the drive motors to the Mecanum Drive.
    mecanumDrive = new MecanumDrive(frontLeftMotor, backLeftMotor, frontRightMotor, backRightMotor);

    // Assigning the PS4 controller the ID of 0.
    PS4 = new Joystick(0);

    // fLShooter.setNeutralMode(NeutralMode.Brake);
    // bLshooter.setNeutralMode(NeutralMode.Brake);
    // frshooter.setNeutralMode(NeutralMode.Brake);
    // brshooter.setNeutralMode(NeutralMode.Brake);

    // rworm.setIdleMode(IdleMode.kBrake);
    // lworm.setIdleMode(IdleMode.kBrake);

    // rworm.setInverted(true);

    // wormDrive = new SpeedControllerGroup(rworm, lworm);

    // backRightMotor.setInverted(true);
    // frontRightMotor.setInverted(true);

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

    frontLeftMotor.set(0.2);
    frontRightMotor.set(0.2);
    backLeftMotor.set(0.2);
    backRightMotor.set(0.2);

    // mecanumDrive.driveCartesian(0.2, 0.2, 0.2);

  }

  @Override
  public void teleopPeriodic() {

    // PS4_L_Y = PS4.getY();

    // if (PS4.getRawButton(9) == true) {
    // fLShooter.set(1.0);
    // frshooter.set(-1.0);
    // } else {
    // fLShooter.set(0);
    // frshooter.set(0);
    // }

    // if (PS4.getRawButton(1) == true) {
    // fLShooter.set(0.3);
    // frshooter.set(-0.3);
    // brshooter.set(-0.3);
    // bLshooter.set(0.3);
    // wormDrive.set(0.3);
    // wormDrive.set(0.30);

    // } else if (PS4.getRawButton(2) == true) {

    // // wormDrive.set(-0.3);
    // wormDrive.set(-0.30);

    // } else {
    // fLShooter.set(0.0);
    // frshooter.set(0.0);
    // brshooter.set(0.0);
    // bLshooter.set(0.0);
    // wormDrive.set(0.0);
    // }

    // if (PS4.getRawButton(3) == true) {
    // intake.set(-0.3);
    // } else {
    // intake.set(0);
    // }

    // if (PS4.getRawButton(5) == true) {
    // bLshooter.set(0.1);
    // brshooter.set(-0.1);
    // } else {
    // bLshooter.set(0.0);
    // brshooter.set(0.0);
    // }

    // if (PS4_L_Y < 0.2 || PS4_L_Y > -0.2) {
    // PS4_L_Y = 0;
    // }

    // Long-ass if statement that acts as a deadband for the drive.
    if (((PS4.getX() > PS4_TRIGGER_DEADBAND_POSITIVE) || (PS4.getY() > PS4_TRIGGER_DEADBAND_POSITIVE)
        || (PS4.getZ() > PS4_TRIGGER_DEADBAND_POSITIVE))
        || ((PS4.getX() < PS4_TRIGGER_DEADBAND_NEGATIVE) || (PS4.getY() < PS4_TRIGGER_DEADBAND_NEGATIVE)
            || (PS4.getZ() < PS4_TRIGGER_DEADBAND_NEGATIVE))) {
      // mecanumDrive.driveCartesian(-PS4_L_Y, PS4.getZ(), PS4.getX());
      // The arguments for this function won't match up with the actual joystick axes
      // for some reason.
      // Depending on the robot, you might have to experiment with these.
      // Z = Right joystick X axis; Y = Left joystick Y axis; X = left joystick X
      // axis.
      // In this case, ySpeed is the strafeing stuff, xSpeed is for driving
      // forward/backward, and zRotation is for turning left/right.
      mecanumDrive.driveCartesian(PS4.getZ(), -PS4.getY(), PS4.getX());

      // mecanumDrive.drivePolar(magnitude, angle, zRotation);
    }

    if (PS4.getRawButton(1)) {
      // TODO.
      mecanumDrive.driveCartesian(PS4.getZ(), -PS4.getRawAxis(5), 0);
    } else {
      if (((PS4.getX() > PS4_TRIGGER_DEADBAND_POSITIVE) || (PS4.getY() > PS4_TRIGGER_DEADBAND_POSITIVE)
          || (PS4.getZ() > PS4_TRIGGER_DEADBAND_POSITIVE))
          || ((PS4.getX() < PS4_TRIGGER_DEADBAND_NEGATIVE) || (PS4.getY() < PS4_TRIGGER_DEADBAND_NEGATIVE)
              || (PS4.getZ() < PS4_TRIGGER_DEADBAND_NEGATIVE))) {
        // mecanumDrive.driveCartesian(-PS4_L_Y, PS4.getZ(), PS4.getX());
        // The arguments for this function won't match up with the actual joystick axes
        // for some reason.
        // Depending on the robot, you might have to experiment with these.
        // Z = Right joystick X axis; Y = Left joystick Y axis; X = left joystick X
        // axis.
        // In this case, ySpeed is the strafeing stuff, xSpeed is for driving
        // forward/backward, and zRotation is for turning left/right.
        mecanumDrive.driveCartesian(PS4.getZ(), -PS4.getY(), PS4.getX());
      } else {
        mecanumDrive.driveCartesian(0, 0, 0);
      }

    }

    // TODO polar stuff.
    // IDK which one we should use; not much documentation on each.
    // mecanumDrive.driveCartesian(ySpeed, xSpeed, zRotation, gyroAngle);
    // mecanumDrive.drivePolar(magnitude, angle, zRotation);

  }

  @Override
  public void testPeriodic() {
  }

  // public String getGameData() {

  // gameData = DriverStation.getInstance().getGameSpecificMessage();

  // gameData = gameData.substring(0, 2);

  // SmartDashboard.putString("Colors", gameData);

  // return (gameData);
  // }

}