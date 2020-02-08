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
// Authors: Noah Stigeler and Elliott DuCharme.
//
// Environment: Microsoft VSCode Java
//
// Remarks:
//
/////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////
package frc.robot;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.util.Color;

class Sensors {

    // Creating the 2 gyros.
    ADXRS450_Gyro driveGyro = new ADXRS450_Gyro(SPI.Port.kOnboardCS0);
    ADXRS450_Gyro wormDriveGyro = new ADXRS450_Gyro(SPI.Port.kOnboardCS1);

    // Creating the proximity sensor, with a channel of 0.
    static AnalogInput proximitySensor = new AnalogInput(0);

    // Magic numbers for proximity sensor.
    // Resolution of the proximity sensor.
    private final static double PROX_SENSOR_RES = 1024;

    // Millimeters per inch; used for proximity sensor.
    private final static double MM_PER_INCH = 25.4;

    // Voltage that the proximity sensor measures.
    // We use this, and convert it to inches.
    private static double measuredVoltage = 0.0;

    // Creating the color sensor.
    private ColorSensorV3 colorSensor = new ColorSensorV3(I2C.Port.kOnboard);

    // Magic numbers.
    // Minimum values for color measurement.
    // If below these, can't read color values properly.
    private final double MIN_COLOR_SENSOR_IR_VALUE = 10;
    private final int MIN_COLOR_SENSOR_PROX_VALUE = 50;

    // Creating an instance of the ColorMatch class.
    // This is used for matching colors.
    ColorMatch colorMatcher = new ColorMatch();

    // Assigning these RGB values to variables.
    private final Color blueColor = ColorMatch.makeColor(0.143, 0.427, 0.429);
    private final Color greenColor = ColorMatch.makeColor(0.197, 0.561, 0.240);
    private final Color redColor = ColorMatch.makeColor(0.561, 0.232, 0.114);
    private final Color yellowColor = ColorMatch.makeColor(0.361, 0.524, 0.113);

    // Sensors class constructor.
    Sensors() {

        // Adding the colors to look for.
        colorMatcher.addColorMatch(blueColor);
        colorMatcher.addColorMatch(greenColor);
        colorMatcher.addColorMatch(redColor);
        colorMatcher.addColorMatch(yellowColor);
    }

    /////////////////////////////////////////////////////////////////////
    // Function: getDistance()
    /////////////////////////////////////////////////////////////////////
    //
    // Purpose: Used for the proximitySensor.
    // Uses the voltage from proximitySensor.getVoltage(), plus
    // some fancy math stuff, to measure the distance to an object.
    //
    // Arguments: void.
    //
    // Returns: Distance to an object in inches.
    //
    // Remarks: The sensor can be a little unreliable at times, and the
    // texture/material of the object can affect how reliably it measures.
    //
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    public double getDistance() {
        double distance;
        measuredVoltage = proximitySensor.getVoltage();
        distance = (measuredVoltage * PROX_SENSOR_RES) / MM_PER_INCH;

        return distance;
    }

}