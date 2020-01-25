/////////////////////////////////////////////////////////////////////
// File: Constants.java
/////////////////////////////////////////////////////////////////////
//
// Purpose: Used for housing miscellaneous magic numbers.
//
// Authors: Elliott DuCharme
//
// Environment: Microsoft VSCode Java
//
// Remarks: Any number that has some random value can be considered
// a magic number.
//
/////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////
package frc.robot;

import edu.wpi.first.wpilibj.SPI;

class Constants {

    // The variables for each class go here; organized alphabetically.

    /////////////////////////// START OF MAGIC NUMBERS ///////////////////////////

    ///////////////////////////// Autonomous.java /////////////////////////////

    ///////////////////////////// ColorSensor.java /////////////////////////////

    // Minimum values for color measurement.
    // If below these, can't read color values properly.
    final double MIN_IR_VALUE = 10;
    final int MIN_PROX_VALUE = 50;

    ///////////////////////////// DriveThread.java /////////////////////////////

    // Dummy IDs for right now!
    final int FRONT_LEFT_MOTOR_ID = 1;
    final int FRONT_RIGHT_MOTOR_ID = 2;
    final int BACK_LEFT_MOTOR_ID = 3;
    final int BACK_RIGHT_MOTOR_ID = 4;

    // Gear reduction and the diameter of the wheel in inches.
    final double DRIVE_REDUCTION = 12.75;
    final double WHEEL_DIAMETER = 6.0;

    // Fixed parameters for conversion of distance to encoder counts
    final double INCHES_PER_FOOT = 12.0;
    final double CM_PER_METER = 100.0;

    /*
     * Encoders are now on the motors (NEOS). Output from the encoder in this case
     * is 1.0. A gear reduction of 16:1 implies 16.0 per revolution of the output
     * shaft. The precision of this is 42 counts per motor shaft revolution times 16
     * of the gear reduction = 1/672. An eight inch wheel diameter implies a
     * distance traveled of PI*8.0 = 25.17 inches. Distance resolution of the
     * encoder/gearbox comination is 25.17/672 = 0.037 inches/count. Should be good
     * enough. However the output when reading the encoder function is 1.0 for each
     * revolution of the motor, or 16 for one revolution of the output shaft. This
     * implies that the inch to output conversion is 25.17/16.0 or 1.572 inches per
     * unit output
     */
    final double ENCODER_RESOLUTION = 0.676; // counts per inch.
    final double INCHES_PER_COUNT = 1.479; // inches per count.

    // Fixed parameters for driveFwd(...)/driveBwd(...)
    final double START_SPEED = 0.6;
    final double BRAKE_SPEED = 0.3;
    final double BRAKE_FRACTION = 0.25;

    // Fixed parameters for console updates and while() loop escapes
    final int ENC_CONSOLE_UPDATE = 20;
    final int ENC_LOOP_ESCAPE = 250;
    final int GYRO_CONSOLE_UPDATE = 20;
    final int GYRO_LOOP_ESCAPE = 200;

    // Fixed parameters for gyro operation. Specified here to facilitate
    // changes without confusion in the various functions using these
    // variables.
    final double ROT_SPEED = 0.5; // Starting rotation speed for turning
    // As we approach the target we reduce the speed by this factor
    final double ROT_ATTEN = 1.5;
    // proximity (in degrees) to target angle stage 1 rotation attenuation rate
    final double ANGL_PROX_1 = 25.0;
    // proximity (in degrees) to target angle stage 2 rotation attenuation rate
    final double ANGL_PROX_2 = 5.0;

    ///////////////////////////// Proximity.java /////////////////////////////

    // Resolution of the proximity sensor.
    final double SENSOR_RES = 1024;

    // Millimeters per inch.
    final double MM_PER_INCH = 25.4;

    ///////////////////////////// Robot.java /////////////////////////////

    final int PS4_PORT_NUMBER = 1;

    // Magic numebrs for PS4 controller axes.
    final int LEFT_X_AXIS_PORT = 0;
    final int LEFT_Y_AXIS_PORT = 1;
    final int RIGHT_X_AXIS_PORT = 2;
    final int RIGHTT_Y_AXIS_PORT = 5;

    // Magic numbers for button ID's.
    final int X_BUTTON = 1;
    final int CIRCLE_BUTTON = 2;
    final int SQUARE_BUTTON = 3;
    final int TRIANGLE_BUTTON = 4;
    final int L1_BUTTON = 5;
    final int R1_BUTTON = 6;
    final int SHARE_BUTTON = 7;
    final int OPTIONS_BUTTON = 8;
    final int LEFT_STICK_BUTTON = 9;
    final int RIGHT_STICK_BUTTON = 10;

    ///////////////////////////// Sensors.java /////////////////////////////

    // Used to recude how much the sensor values are printed out.
    private final int SENSOR_INTERVAL = 10;

    // Ports for the two gyros.
    final SPI.Port DRIVE_GYRO_PORT = SPI.Port.kOnboardCS1;
    final SPI.Port TILT_GYRO_PORT = SPI.Port.kOnboardCS1;

    ///////////////////////////// END OF MAGIC NUMBERS /////////////////////////////

    // Class constructor.
    Constants() {

    }

}