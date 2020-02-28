/////////////////////////////////////////////////////////////////////
// File: DriveThread.java
/////////////////////////////////////////////////////////////////////
//
// Purpose: This Thread is responsible for the joystick controls for
// the robot in teleop.
//
// Authors: Elliott DuCharme and Larry Basegio.
//
// Environment: Microsoft VSCode Java
//
// Remarks: Created on 2/27/2020.
//
/////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////
package frc.robot;

// Creating the class that implements the Runnable interface.
class DriveThread implements Runnable {

    // Used for being able to access Robot.java stuff in here.
    Robot robot = new Robot();

    // Creating an instance of the Thread class, named driveThread.
    Thread driveThread;

    // Used for the Mecanum drive deadband.
    final double PS4_TRIGGER_DEADBAND_POSITIVE = 0.2;
    final double PS4_TRIGGER_DEADBAND_NEGATIVE = -0.2;

    // Constructor.
    DriveThread(String name) {

        // Name of the Thread.
        String threadName = name;

        // Getting a reference to the Runtime class.
        // We use this stuff for garbage collection.
        // According to page 461 chapter 11 of Java: The Complete Reference 9th edition
        // by Herbert Schildt, you can't instantiate a Runtime object.
        // But, you can get a reference to it. Using this, you can control
        // the state and behavior of the Java Virtual Machine.
        // Lots of cool functions in this section: totalMemory(), freeMemory(), etc.
        // Worth a look.
        Runtime runtime = Runtime.getRuntime();

        // Actually creating the Thread.
        driveThread = new Thread(this, threadName);
        driveThread.start(); // Start the Thread.
    }

    // The stuff that is actually run while the Thread is running.
    public void run() {

        // While the Thread is alive, do stuff.
        while (driveThread.isAlive() == true) {

            // Long if statement that acts as a deadband for the drive.
            // Basically, if the X, Y, OR Z axis values are greater than 0.2, OR -0.2, run
            // the Mecanum drive. Else, don't run the Mecanum drive stuff.

            // The arguments for this function won't match up with the actual joystick axes
            // for some reason.
            // Depending on the robot, you might have to experiment with these.
            // Z = Right joystick X axis (changed to the analog triggers using
            // getZAxisTriggers()); Y = Left joystick Y axis; X = left joystick X
            // axis. In this case, ySpeed is the strafing stuff, xSpeed is for driving
            // forward/backward, and zRotation is for turning left/right.
            if (((robot.PS4.getX() > PS4_TRIGGER_DEADBAND_POSITIVE)
                    || (robot.PS4.getY() > PS4_TRIGGER_DEADBAND_POSITIVE)
                    || (getZAxisTriggers() > PS4_TRIGGER_DEADBAND_POSITIVE))
                    || ((robot.PS4.getX() < PS4_TRIGGER_DEADBAND_NEGATIVE)
                            || (robot.PS4.getY() < PS4_TRIGGER_DEADBAND_NEGATIVE)
                            || (getZAxisTriggers() < PS4_TRIGGER_DEADBAND_NEGATIVE))) {
                robot.robotDrive.mecanumDrive.driveCartesian(-robot.PS4.getY(), getZAxisTriggers(), robot.PS4.getX());
            } else {
                // Don't run the drive motors.
                robot.robotDrive.mecanumDrive.driveCartesian(0, 0, 0);
            }

        }

    }

    /////////////////////////////////////////////////////////////////////
    // Function: getZAxisTriggers()
    /////////////////////////////////////////////////////////////////////
    //
    // Purpose: Gets the value for the "Z" axis using the 2 analog triggers.
    //
    // Arguments: none
    //
    // Returns: double zAxis: the value from -1 to 1, which is used for
    // controlling the speed and direction for strafing in teleop.
    //
    // Remarks: Created on 2/22/2020.
    //
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    public double getZAxisTriggers() {

        // This variable is used for setting the Z axis for strafing.
        double zAxis;

        // These variables are for getting the values for the left and right
        // analog triggers, respectively.
        double leftAnalogTrigger;
        double rightAnalogTrigger;

        // Axes 2 and 3 are the left and right analog triggers, respectively.
        // You have to add 1 because the triggers start at -1 and go to 1.
        // Adding 1 makes them start at 0 when not being pressed.
        leftAnalogTrigger = robot.PS4.getRawAxis(3) + 1;
        rightAnalogTrigger = robot.PS4.getRawAxis(4) + 1;

        // Do the math for getting the value for strafing.
        // Example 1: if the driver presses the right one down, that value will be 1 - 0
        // = 100% speed (1).
        // Example 2: if the driver presses the left one down, that value will be 0 - 1
        // ; -100% speed (-1).
        zAxis = rightAnalogTrigger - leftAnalogTrigger;

        // Return the value, to be used elsewhere.
        return zAxis;
    }

}