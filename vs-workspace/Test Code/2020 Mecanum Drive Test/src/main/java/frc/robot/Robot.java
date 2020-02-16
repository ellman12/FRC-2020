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

  WPI_TalonFX fLShooter, bLshooter, frshooter, brshooter;

  CANSparkMax rworm, lworm;

  WPI_TalonFX intake;

  // Creating the Mecanum Drive.
  MecanumDrive mecanumDrive;

  // Creating the PS4 controller.
  Joystick PS4;

  SpeedControllerGroup wormDrive;

  double PS4_Left_X;
  double PS4_Left_Y;
  double PS4_Left_Z;

  @Override
  public void robotInit() {

    // Dummy IDs for right now!
    // frontLeftMotor = new (1);
    // frontRightMotor = new WPI_TalonFX(3);
    // backLeftMotor = new WPI_TalonFX(2);
    // backRightMotor = new WPI_TalonFX(4);

    frontLeftMotor = new CANSparkMax(1, MotorType.kBrushless);
    frontRightMotor = new CANSparkMax(2, MotorType.kBrushless);
    backLeftMotor = new CANSparkMax(3, MotorType.kBrushless);
    backRightMotor = new CANSparkMax(4, MotorType.kBrushless);

    fLShooter = new WPI_TalonFX(5);
    bLshooter = new WPI_TalonFX(7);
    frshooter = new WPI_TalonFX(6);
    brshooter = new WPI_TalonFX(8);

    rworm = new CANSparkMax(9, MotorType.kBrushless);
    lworm = new CANSparkMax(10, MotorType.kBrushless);

    intake = new WPI_TalonFX(11);

    // Adding the drive motors to the Mecanum Drive.
    mecanumDrive = new MecanumDrive(frontLeftMotor, backLeftMotor, frontRightMotor, backRightMotor);

    // Assigning the PS4 controller the ID of 0.
    PS4 = new Joystick(0);

    fLShooter.setNeutralMode(NeutralMode.Brake);
    bLshooter.setNeutralMode(NeutralMode.Brake);
    frshooter.setNeutralMode(NeutralMode.Brake);
    brshooter.setNeutralMode(NeutralMode.Brake);

    rworm.setIdleMode(IdleMode.kBrake);
    lworm.setIdleMode(IdleMode.kBrake);

    rworm.setInverted(true);

    wormDrive = new SpeedControllerGroup(rworm, lworm);

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

    if (PS4.getRawButton(9) == true) {
      fLShooter.set(0.3);
      frshooter.set(-0.3);
    } else {
      fLShooter.set(0);
      frshooter.set(0);
    }

    if (PS4.getRawButton(1) == true) {
      // fLShooter.set(0.3);
      // frshooter.set(-0.3);
      // brshooter.set(-0.3);
      // bLshooter.set(0.3);
      // wormDrive.set(0.3);
      wormDrive.set(0.10);

    } else if (PS4.getRawButton(2) == true) {

      // wormDrive.set(-0.3);
      wormDrive.set(-0.10);

    } else {
      // fLShooter.set(0.0);
      // frshooter.set(0.0);
      // brshooter.set(0.0);
      // bLshooter.set(0.0);
      wormDrive.set(0.0);
    }

    if (PS4.getRawButton(3) == true) {
      intake.set(-0.3);
    } else {
      intake.set(0);
    }

    if (PS4.getRawButton(5) == true) {
      bLshooter.set(0.1);
      brshooter.set(-0.1);
    } else {
      bLshooter.set(0.0);
      brshooter.set(0.0);
    }

    // Drive the robot's Mecanum Drive with the PS4 controller.

    PS4_Left_X = PS4.getX();
    PS4_Left_Y = PS4.getY();
    PS4_Left_Z = PS4.getZ();

    if (((PS4.getX() > 0.2) && (PS4.getY() > 0.2) && (PS4.getZ() > 0.2))
        || ((PS4.getX() < -0.2) && (PS4.getY() < -0.2) && (PS4.getZ() < -0.2))) {
      mecanumDrive.driveCartesian(PS4.getZ(), PS4.getX(), PS4.getY(), 0);
    }

    // mecanumDrive.driveCartesian(PS4.getX(), PS4.getY(), PS4.getZ(), 0);

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