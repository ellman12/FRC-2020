package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.hal.sim.DriverStationSim;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.MecanumDrive;

public class Robot extends TimedRobot {

  // Creating instance of DriveThread.
  DriveThread driveThread;

  @Override
  public void robotInit() {

    // Calling the DriveThread, and telling it to do its thing.
    // Calling it once in robotInit() should help prevent it from
    // being called a gazillion times in autoPeriodic/teleopPeriodic().
    driveThread = new DriveThread("driveThread");

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

  /////////////////////////////////////////////////////////////////////
  // Function: strafeLeft()
  /////////////////////////////////////////////////////////////////////
  //
  // Purpose: Function called when the left analog trigger is pressed
  // down. Causes the robot to strafe left.
  //
  // Arguments: None
  //
  // Returns: void
  //
  // Remarks:
  // In order to strafe left...
  // the frontLeftMotor has to spin backwards...
  // the backLeftMotor has to spin forwards...
  // the frontRightMotor has to spin forwards...
  // and the backRightMotor has to spin backwards.
  //
  /////////////////////////////////////////////////////////////////////
  /////////////////////////////////////////////////////////////////////
  public void strafeLeft() {
    frontLeftMotor.set(-PS4LeftAnalogTrigger);
    backLeftMotor.set(PS4LeftAnalogTrigger);
    frontRightMotor.set(PS4LeftAnalogTrigger);
    backRightMotor.set(-PS4LeftAnalogTrigger);
  }

  /////////////////////////////////////////////////////////////////////
  // Function: strafeRight()
  /////////////////////////////////////////////////////////////////////
  //
  // Purpose: Function called when the right analog trigger is pressed
  // down. Causes the robot to strafe right.
  //
  // Arguments: None
  //
  // Returns: void
  //
  // Remarks:
  // In order to strafe right...
  // the frontLeftMotor has to spin forwards...
  // the backLeftMotor has to spin backwards...
  // the frontRightMotor has to spin backwards...
  // and the backRightMotor has to spin forwards.
  //
  /////////////////////////////////////////////////////////////////////
  /////////////////////////////////////////////////////////////////////
  public void strafeRight() {
    frontLeftMotor.set(PS4LeftAnalogTrigger);
    backLeftMotor.set(-PS4LeftAnalogTrigger);
    frontRightMotor.set(-PS4LeftAnalogTrigger);
    backRightMotor.set(PS4LeftAnalogTrigger);
  }

}