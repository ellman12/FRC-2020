package frc.robot;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.TimedRobot;

public class Robot extends TimedRobot {

  // Gyroscopes for drive and tilt.
  public static ADXRS450_Gyro driveGyro;
  public static ADXRS450_Gyro tiltGyro;

  // Ports for the two gyros.
  private static final SPI.Port DRIVE_GYRO_PORT = SPI.Port.kOnboardCS0;
  private static final SPI.Port TILT_GYRO_PORT = SPI.Port.kOnboardCS1;

  @Override
  public void robotInit() {

    // drive system gyro
    driveGyro = new ADXRS450_Gyro(DRIVE_GYRO_PORT);
    tiltGyro = new ADXRS450_Gyro(TILT_GYRO_PORT);

    // Initialize the gyros, calibrate, and reset to zero degrees.
    driveGyro.calibrate();
    driveGyro.reset();
    tiltGyro.calibrate();
    tiltGyro.reset();

  }

  @Override
  public void robotPeriodic() {

    System.out.println("Drive Gyro: \t" + driveGyro.getAngle() + "\t\t" + "Tilt Gyro: \t" + tiltGyro.getAngle());

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
}