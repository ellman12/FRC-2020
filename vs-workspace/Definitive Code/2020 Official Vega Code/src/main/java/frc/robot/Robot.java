package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class Robot extends TimedRobot {

  CANSparkMax frontLeft, backLeft, frontRight, backRight;

  SpeedControllerGroup leftDriveMotors = new SpeedControllerGroup(frontLeft, backLeft);
  SpeedControllerGroup rightDriveMotors = new SpeedControllerGroup(frontRight, backRight);

  DifferentialDrive diff_drive = new DifferentialDrive(leftDriveMotors, rightDriveMotors);

  double PS4LeftXAxis;
  double PS4LeftYAxis;

  final int FRONT_LEFT_SPARK_ID = 1;
  final int BACK_LEFT_SPARK_ID = 2;
  final int FRONT_RIGHT_SPARK_ID = 3;
  final int BACK_RIGHT_SPARK_ID = 4;

  final int PS4_ID = 1;

  Joystick PS4 = new Joystick(PS4_ID);

  @Override
  public void robotInit() {

    frontLeft = new CANSparkMax(FRONT_LEFT_SPARK_ID, MotorType.kBrushless);
    backLeft = new CANSparkMax(BACK_LEFT_SPARK_ID, MotorType.kBrushless);
    frontRight = new CANSparkMax(FRONT_RIGHT_SPARK_ID, MotorType.kBrushless);
    backRight = new CANSparkMax(BACK_RIGHT_SPARK_ID, MotorType.kBrushless);

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

    PS4LeftXAxis = PS4.getRawAxis(0);
    PS4LeftYAxis = PS4.getRawAxis(1);

    diff_drive.arcadeDrive(-PS4LeftYAxis, PS4LeftXAxis);

  }

  @Override
  public void testPeriodic() {
  }
}