package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends TimedRobot {

  final int TEST_FALCON_PORT = 1;
  final int TEST_SPARK_MAX_PORT = 2;

  WPI_TalonFX testFalcon = new WPI_TalonFX(TEST_FALCON_PORT);

  CANSparkMax testSparkMax = new CANSparkMax(TEST_SPARK_MAX_PORT, MotorType.kBrushless);

  double Falcon500Speed;

  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();

  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);
  }

  @Override
  public void robotPeriodic() {
  }

  @Override
  public void autonomousInit() {

    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);

  }

  @Override
  public void autonomousPeriodic() {

    switch (m_autoSelected) {
    case kCustomAuto:
      // Put custom auto code here
      break;
    case kDefaultAuto:
    default:
      // Put default auto code here
      break;
    }

  }

  @Override
  public void teleopPeriodic() {

    SmartDashboard.getNumber("Falcon 500 SPeed", Falcon500Speed);
    SmartDashboard.putNumber("Falcon 500 Speed", Falcon500Speed);

  }

  @Override
  public void testPeriodic() {
  }
}