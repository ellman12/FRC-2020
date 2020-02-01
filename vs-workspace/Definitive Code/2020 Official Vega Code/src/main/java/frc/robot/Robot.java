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

  Joystick PS4 = new Joystick(1);

  @Override
  public void robotInit() {

    frontLeft = new CANSparkMax(1, MotorType.kBrushless);
    backLeft = new CANSparkMax(2, MotorType.kBrushless);
    frontRight = new CANSparkMax(3, MotorType.kBrushless);
    backRight = new CANSparkMax(4, MotorType.kBrushless);

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