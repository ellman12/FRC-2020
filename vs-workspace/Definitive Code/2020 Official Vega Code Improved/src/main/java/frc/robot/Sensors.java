/////////////////////////////////////////////////////////////////////
// File: Sensors.java
/////////////////////////////////////////////////////////////////////
//
// Purpose: Class used for housing and reading all the sensors at once.
// It makes a lot more sense to read all of them with one function,
// compared to sloppily reading them at random intervals.
// Plus we don't have to have long lines and stuff for reading a gyro
// or something: we just have it read the variable from this class, 
// which contains the current angle.
//
// Authors: Elliott DuCharme.
//
// Environment: Microsoft VSCode Java
//
// Remarks: Created on 2/25/2020.
//
/////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////
package frc.robot;

import com.revrobotics.CANEncoder;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.util.Color;

class Sensors {

    // Constructor, creating the sensors and variables
    // and stuff to be read in robotPeriodic().
    Sensors() {

        // Creating an instance of Robot.java in here.
        Robot robot = new Robot();

        // Stores the gyro values.
        double driveGyroAngle;

        // Proximity sensor distance value (in inches).
        double proximitySensorDistance;

        // Creating all of the necessary sensors.
        CANEncoder frontLeftDriveEncoder = new CANEncoder(robot.driveThread.driveThread.frontLeftDriveMotor);

    }
}