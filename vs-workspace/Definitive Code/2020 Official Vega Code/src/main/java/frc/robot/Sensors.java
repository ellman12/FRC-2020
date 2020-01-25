/////////////////////////////////////////////////////////////////////
//  File:  Sensors.java
/////////////////////////////////////////////////////////////////////
//
//  Purpose:  Reads all the sensors.  This is not a thread but an
//            implementation intended to be called within 
//            robotPeriodic().  When implemented with robotPeriodic()
//            It was not easy to stop it.  Implication is that
//            robotPeriodic() is a thread of sorts.
//
//  Programmers: Larry Basegio and Elliott DuCharme.
//
//  Environment: Microsoft VSCode Java
//
//  Inception Date:
//
//  Revisions:
//
/////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////

package frc.robot;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;

class Sensors {

    double drive_position; // Drive encoder value.
    double drive_angle; // driveGyro angle.
    double proxDistance; // Proximity sensor value in inches.

    // Creating the gyro for the drive.
    ADXRS450_Gyro driveGyro;

    // Linking other classes together.
    public static Autonomous autoFunctions;
    public static ColorClass colorClass;
    public static Constants constants;
    public static Delay delay;
    public static DriveThread driveThread;
    public static ProximitySensor proximitySensor;
    public static Sensors sensors;

    // Constructor
    Sensors() {
        drive_position = 0;
        drive_angle = 0;
        proxDistance = 0;

        autoFunctions = new Autonomous();
        colorClass = new ColorClass();
        constants = new Constants();
        delay = new Delay();
        proximitySensor = new ProximitySensor();
        sensors = new Sensors();

        // Assign the driveGyro the port number of 0.
        driveGyro = new ADXRS450_Gyro(constants.DRIVE_GYRO_PORT);
    }

    // Entry point for the thread. This is where any desired
    // action needs to be implemented. It will run in "parallel"
    // or "time share" with other active threads. The prototype
    // of this function is fixed. It cannot be overidden with
    // a version that passes in variables. This makes it necessary
    // to create static variables within Robot.java that can be
    // seen within this function.

    // Read sensors on the robot.
    int readSensors() {

        // Get the position associated with the left drive.
        drive_position = driveThread.left_enc.getPosition();

        // Gyro associated with robot direction control
        drive_angle = driveGyro.getAngle();

        // Get the distance in inches that the prox sensor reads.
        proxDistance = proximitySensor.getDistance();

        // Read the color sensor value.
        colorClass.getColorString();

        return (0);
    }

}