/////////////////////////////////////////////////////////////////////
// File: Robot.java
/////////////////////////////////////////////////////////////////////
//
// Purpose: This program is for testing miscellaneous stuff on the
// 2020 Robot. This code is terrible and definitely a perfect
// example of spaghetti code.
//
// Authors: Elliott DuCharme.
//
// Environment: Microsoft VSCode Java.
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

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.MecanumDrive;

public class Robot extends TimedRobot {

  // Creating the drive motors.
  // CANSparkMax frontLeftMotor, frontRightMotor, backLeftMotor, backRightMotor;

  WPI_TalonFX climbFalcon;

  WPI_TalonFX fLShooter, bLshooter, frshooter, brshooter;

  CANSparkMax rworm, lworm;

  WPI_TalonFX intake;

  // Creating the Mecanum Drive.
  // MecanumDrive mecanumDrive;

  // Creating the PS4 controller.
  Joystick PS4;

  SpeedControllerGroup wormDrive;

  // double PS4_L_Y;

  // final double PS4_TRIGGER_DEADBAND_POSITIVE = 0.2;
  // final double PS4_TRIGGER_DEADBAND_NEGATIVE = -0.2;

  @Override
  public void robotInit() {

    // frontLeftMotor = new CANSparkMax(1, MotorType.kBrushless);
    // frontRightMotor = new CANSparkMax(3, MotorType.kBrushless);
    // backLeftMotor = new CANSparkMax(2, MotorType.kBrushless);
    // backRightMotor = new CANSparkMax(4, MotorType.kBrushless);

    climbFalcon = new WPI_TalonFX(12);

    fLShooter = new WPI_TalonFX(5);
    bLshooter = new WPI_TalonFX(7);
    frshooter = new WPI_TalonFX(6);
    brshooter = new WPI_TalonFX(8);

    rworm = new CANSparkMax(9, MotorType.kBrushless);
    lworm = new CANSparkMax(10, MotorType.kBrushless);

    intake = new WPI_TalonFX(11);

    // backLeftMotor.setInverted(false);
    // frontLeftMotor.setInverted(false);

    // backRightMotor.setInverted(true);
    // frontRightMotor.setInverted(true);

    // Adding the drive motors to the Mecanum Drive.
    // mecanumDrive = new MecanumDrive(frontLeftMotor, backLeftMotor,
    // frontRightMotor, backRightMotor);

    // Assigning the PS4 controller the ID of 0.
    PS4 = new Joystick(0);

    fLShooter.setNeutralMode(NeutralMode.Brake);
    bLshooter.setNeutralMode(NeutralMode.Brake);
    frshooter.setNeutralMode(NeutralMode.Brake);
    brshooter.setNeutralMode(NeutralMode.Brake);

    rworm.setIdleMode(IdleMode.kBrake);
    lworm.setIdleMode(IdleMode.kBrake);

    // Might be necessary, might not be...? *Shrug*
    lworm.setInverted(true);
    rworm.setInverted(false);

    wormDrive = new SpeedControllerGroup(rworm, lworm);

    climbFalcon.setNeutralMode(NeutralMode.Brake);

    // backRightMotor.setInverted(true);
    // frontRightMotor.setInverted(true);

    // mecanumDrive.setSafetyEnabled(false);

  }

  @Override
  public void robotPeriodic() {

    // Has to be called constantly, for some reason...?
    NetworkTable networkTable = NetworkTableInstance.getDefault().getTable("limelight");
    networkTable.getEntry("camMode").setValue(0);
    networkTable.getEntry("ledMode").setValue(3);
    networkTable.getEntry("stream").setValue(0);
  }

  @Override
  public void autonomousInit() {
  }

  @Override
  public void autonomousPeriodic() {

  }

  @Override
  public void teleopPeriodic() {

    // if (PS4.getRawButton(1) == true) {
    // fLShooter.set(0.1);
    // frshooter.set(-0.1);
    // brshooter.set(-0.1);
    // bLshooter.set(0.1);
    // } else {
    // fLShooter.set(0);
    // frshooter.set(0);
    // brshooter.set(0);
    // bLshooter.set(0);
    // }

    climbFalcon.set(-PS4.getRawAxis(5));

    if (PS4.getRawButton(1)) {
      intake.set(-0.2);
    } else if (PS4.getRawButton(2)) {
      intake.set(0.2);
    } else {
      intake.set(0.0);
    }

    if (PS4.getRawButton(5)) { // left bumper
      // wormDrive.set(0.4); // down
      lworm.set(-0.1);
      rworm.set(0.15);
    } else if (PS4.getRawButton(6)) { // right bumper
      // wormDrive.set(-0.4); // up
      lworm.set(0.1);
      rworm.set(-0.15);
    } else {
      wormDrive.set(0);
    }

    // // Switch between camera modes for the cameras.
    // if (PS4.getRawButton(1)) {

    // // Sets the camera mode display thing to 0: side-by-side.
    // NetworkTableInstance.getDefault().getTable("limelight").getEntry("stream").setNumber(0);

    // // Sets the camera mode display thing to 1: PiP Main - The secondary camera
    // // stream is placed in the lower-right corner of the
    // // primary camera stream
    // //
    // NetworkTableInstance.getDefault().getTable("limelight").getEntry("stream").setNumber(0);

    // // Sets the camera mode display thing to 2: PiP Secondary - The primary
    // camera
    // // stream is placed in the lower-right corner of the secondary camera stream
    // //
    // NetworkTableInstance.getDefault().getTable("limelight").getEntry("stream").setNumber(0);
    // }

    // Long if statement that acts as a deadband for the drive.
    // if (((PS4.getX() > PS4_TRIGGER_DEADBAND_POSITIVE) || (PS4.getY() >
    // PS4_TRIGGER_DEADBAND_POSITIVE)
    // || (PS4.getZ() > PS4_TRIGGER_DEADBAND_POSITIVE))
    // || ((PS4.getX() < PS4_TRIGGER_DEADBAND_NEGATIVE) || (PS4.getY() <
    // PS4_TRIGGER_DEADBAND_NEGATIVE)
    // || (PS4.getZ() < PS4_TRIGGER_DEADBAND_NEGATIVE))) {

    // The arguments for this function won't match up with the actual joystick axes
    // for some reason.
    // Depending on the robot, you might have to experiment with these.
    // Z = Right joystick X axis; Y = Left joystick Y axis; X = left joystick X
    // axis.
    // In this case, ySpeed is the strafeing stuff, xSpeed is for driving
    // forward/backward, and zRotation is for turning left/right.
    // mecanumDrive.driveCartesian(PS4.getZ(), -PS4.getY(), PS4.getX());

  }

  @Override
  public void testPeriodic() {
  }

}