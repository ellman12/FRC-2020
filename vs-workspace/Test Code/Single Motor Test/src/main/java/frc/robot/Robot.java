/////////////////////////////////////////////////////////////////////
// File: Robot.java
/////////////////////////////////////////////////////////////////////
//
// Purpose: Program used for testing motor speeds.
//
// Authors: Elliott DuCharme of FRC Team #5914.
//
// Environment: Microsoft VSCode Java
//
// Remarks: Made for 2020 competition; probably won't be useful for
// future seasons.
//
// SD stands for SmartDashboard.
//
/////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////
package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends TimedRobot {

  // Magic numbers for motor ports.
  final int TEST_SPARK_MAX1_PORT = 1;
  final int TEST_SPARK_MAX2_PORT = 3;
  final int TEST_FALCON_PORT = 2;

  String nullVarTest;

  // Creating the 2 Spark Maxes.
  CANSparkMax testSparkMax1 = new CANSparkMax(TEST_SPARK_MAX1_PORT, MotorType.kBrushless);
  CANSparkMax testSparkMax2 = new CANSparkMax(TEST_SPARK_MAX2_PORT, MotorType.kBrushless);

  // Creating the Falcon 500.
  WPI_TalonFX testFalcon = new WPI_TalonFX(TEST_FALCON_PORT);

  // Doubles used for controlling the motor speeds through SD.
  double SparkMax1Speed = 0;
  double SparkMax2Speed = 0;
  double Falcon500Speed = 0;

  // SD stuff.
  public static final String sparkMax1SpeedChoice = "sparkMax1SpeedChoice";
  public SendableChooser<String> sendChooserSpeedSparkMax1 = new SendableChooser<>();

  public static final String sparkMax2SpeedChoice = "sparkMax2SpeedChoice";
  public SendableChooser<String> sendChooserSpeedSparkMax2 = new SendableChooser<>();

  public static final String falconSpeedChoice = "falconSpeedChoice";
  public SendableChooser<String> sendChooserSpeedFalcon = new SendableChooser<>();

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

    // Allows controlling individual motors through SD.
    // The values are a percantage from -100% to 100%.
    SparkMax1Speed = SmartDashboard.getNumber(sparkMax1SpeedChoice, 100) / 100;
    SmartDashboard.putNumber(sparkMax1SpeedChoice, SparkMax1Speed * 100);

    SparkMax2Speed = SmartDashboard.getNumber(sparkMax2SpeedChoice, 100) / 100;
    SmartDashboard.putNumber(sparkMax2SpeedChoice, SparkMax2Speed * 100);

    Falcon500Speed = SmartDashboard.getNumber(falconSpeedChoice, 100) / 100;
    SmartDashboard.putNumber(falconSpeedChoice, Falcon500Speed * 100);

    testFalcon.set(Falcon500Speed);

    testSparkMax1.set(SparkMax1Speed);

    testSparkMax2.set(SparkMax2Speed);

  }

  @Override
  public void testPeriodic() {
  }
}