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

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.SPI;

class Sensors {

    ADXRS450_Gyro driveGyro = new ADXRS450_Gyro(SPI.Port.kOnboardCS0);

    Sensors() {

    }

}