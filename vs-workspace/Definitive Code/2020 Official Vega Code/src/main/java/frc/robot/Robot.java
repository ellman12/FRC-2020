package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.MecanumDrive;

public class Robot extends TimedRobot {

  // Double variables for the PS4 Controller axes.
  double PS4LeftXAxis;
  double PS4LeftYAxis;
  double PS4LeftAnalogTrigger;
  double PS4RightAnalogTrigger;

  // Magic numbers for PS4 Controller IDs.
  final int PS4_L_X_AXIS_ID = 0;
  final int PS4_L_Y_AXIS_ID = 1;
  final int PS4_R_X_AXIS_ID = 2;
  final int PS4_R_Y_AXIS_ID = 3;
  final int PS4_L_ANALOG_TRIG_ID = 4;
  final int PS4_R_ANALOG_TRIG_ID = 5;

  @Override
  public void robotInit() {

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

    // Getting the values of the PS4 Controller's axes.
    PS4LeftXAxis = PS4.getRawAxis(PS4_L_X_AXIS_ID);
    PS4LeftYAxis = PS4.getRawAxis(PS4_L_Y_AXIS_ID);
    PS4LeftAnalogTrigger = PS4.getRawAxis(PS4_L_ANALOG_TRIG_ID);
    PS4RightAnalogTrigger = PS4.getRawAxis(PS4_R_ANALOG_TRIG_ID);

    // Controlling the Mecanum Drive with the Joystick axes.
    mecanumDrive.driveCartesian(PS4.getY(), PS4.getX(), PS4.getZ());

    // If the analog triggers are pressed down sufficiently, strafe in the
    // corresponding direction.
    if (PS4LeftAnalogTrigger >= PS4_ANALOG_TRIGGER_DEADBAND) {
      strafeLeft();
    }

    if (PS4_ANALOG_TRIGGER_DEADBAND >= PS4_ANALOG_TRIGGER_DEADBAND) {
      strafeRight();
    }

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