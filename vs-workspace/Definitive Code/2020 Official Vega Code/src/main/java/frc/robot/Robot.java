package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.MecanumDrive;

public class Robot extends TimedRobot {

  CANSparkMax frontLeftMotor, backLeftMotor, frontRightMotor, backRightMotor;

  SpeedControllerGroup leftDriveMotors = new SpeedControllerGroup(frontLeftMotor, backLeftMotor);
  SpeedControllerGroup rightDriveMotors = new SpeedControllerGroup(frontRightMotor, backRightMotor);

  MecanumDrive mecanumDrive = new MecanumDrive(frontLeftMotor, backLeftMotor, frontRightMotor, backRightMotor);

  double PS4LeftXAxis;
  double PS4LeftYAxis;
  double PS4LeftAnalogTrigger;
  double PS4RightAnalogTrigger;

  // Magic numbers for Motor IDs.
  final int FRONT_LEFT_SPARK_ID = 1;
  final int BACK_LEFT_SPARK_ID = 2;
  final int FRONT_RIGHT_SPARK_ID = 3;
  final int BACK_RIGHT_SPARK_ID = 4;

  // ID for PS4 Controller.
  final int PS4_ID = 1;

  // Magic numbers for PS4 Controller IDs.
  final int PS4_L_X_AXIS_ID = 0;
  final int PS4_L_Y_AXIS_ID = 1;
  final int PS4_R_X_AXIS_ID = 2;
  final int PS4_R_Y_AXIS_ID = 3;
  final int PS4_L_ANALOG_TRIG_ID = 4;
  final int PS4_R_ANALOG_TRIG_ID = 5;

  // Magic number for the deadband for the analog triggers.
  // If the analog triggers are below this value, they will
  // not cause the robot to strafe. This prevents accidental strafing.
  final double PS4_ANALOG_TRIGGER_DEADBAND = 0.15;

  Joystick PS4 = new Joystick(PS4_ID);

  @Override
  public void robotInit() {

    frontLeftMotor = new CANSparkMax(FRONT_LEFT_SPARK_ID, MotorType.kBrushless);
    backLeftMotor = new CANSparkMax(BACK_LEFT_SPARK_ID, MotorType.kBrushless);
    frontRightMotor = new CANSparkMax(FRONT_RIGHT_SPARK_ID, MotorType.kBrushless);
    backRightMotor = new CANSparkMax(BACK_RIGHT_SPARK_ID, MotorType.kBrushless);

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