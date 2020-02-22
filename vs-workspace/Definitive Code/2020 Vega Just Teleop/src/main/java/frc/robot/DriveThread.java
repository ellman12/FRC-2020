/////////////////////////////////////////////////////////////////////
// File: DriveThread.java
/////////////////////////////////////////////////////////////////////
//
// Purpose: Defines the thread class responsible for robot movement
// forward, backwards, left, and right during autonomous.
// All autonomous movements and commands are created in
// Autonomous.java, and are called in here.
// Having them in a separate file declutters this already-long file.
//
// Authors: Elliott DuCharme, Larry Basegio, and Noah Stigeler.
//
// Environment: Microsoft VSCode Java
//
// Remarks:
//
/////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////
package frc.robot;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.drive.MecanumDrive;

// Creating the class that implements the Runnable interface.
class DriveThread implements Runnable {

    String threadName;

    Thread driveThread;

    // Creating the drive motors.
    CANSparkMax frontLeftDriveMotor, backLeftDriveMotor, frontRightDriveMotor, backRightDriveMotor;

    // Creating the Mecanum Drive.
    MecanumDrive mecanumDrive;

    // Creating the PS4 controller.
    Joystick PS4;

    // Creating the drive gyro.
    ADXRS450_Gyro driveGyro;

    // Magic numbers for the deadbands for the PS4 joystick axes.
    final double PS4_TRIGGER_DEADBAND_POSITIVE = 0.2;
    final double PS4_TRIGGER_DEADBAND_NEGATIVE = -0.2;

    // Creating the encoder for the front left drive motor.
    CANEncoder frontLeftDriveEnc;

    // Getting a reference to the Runtime class.
    // We use this stuff for garbage collection.
    // According to page 461 chapter 11 of Java: The Complete Reference 9th edition
    // by Herbert Schildt, you can't instantiate a Runtime object.
    // But, you can get a reference to it. Using this, you can control
    // the state and behavior of the Java Virtual Machine.
    // Lots of cool functions in this section: totalMemory(), freeMemory(), etc.
    // Worth a look.
    Runtime runtime = Runtime.getRuntime();

    DriveThread(String name) {

        // Assigning the name of the Thread to the argument.
        threadName = name;

        frontLeftDriveMotor = new CANSparkMax(1, MotorType.kBrushless);
        frontRightDriveMotor = new CANSparkMax(2, MotorType.kBrushless);
        backLeftDriveMotor = new CANSparkMax(3, MotorType.kBrushless);
        backRightDriveMotor = new CANSparkMax(4, MotorType.kBrushless);

        // Adding the drive motors to the Mecanum Drive.
        mecanumDrive = new MecanumDrive(frontLeftDriveMotor, backLeftDriveMotor, frontRightDriveMotor,
                backRightDriveMotor);

        // Assigning the PS4 controller the ID of 0.
        PS4 = new Joystick(0);

        // Creating the gyro.
        driveGyro = new ADXRS450_Gyro();

        // Creating the CAN Encoder, assigning it to the frontLeftDriveMotor, and
        // setting it to 0 (resetting it).
        frontLeftDriveEnc = new CANEncoder(frontLeftDriveMotor);
        frontLeftDriveEnc.setPosition(0);

        mecanumDrive.setSafetyEnabled(false);

        // Actually creating the Thread.
        driveThread = new Thread(this, threadName);
        driveThread.start(); // Start the Thread.

    }

    public void run() {

        // While the Thread is alive.
        while (driveThread.isAlive() == true) {

            // if (DriverStation.getInstance().isAutonomous()) {
            // strafeLeftAuto(5);
            // } else {
            // // Long if statement that acts as a deadband for the drive.
            // if (((PS4.getX() > PS4_TRIGGER_DEADBAND_POSITIVE) || (PS4.getY() >
            // PS4_TRIGGER_DEADBAND_POSITIVE)
            // || (getZAxis() > PS4_TRIGGER_DEADBAND_POSITIVE))
            // || ((PS4.getX() < PS4_TRIGGER_DEADBAND_NEGATIVE) || (PS4.getY() <
            // PS4_TRIGGER_DEADBAND_NEGATIVE)
            // || (getZAxis() < PS4_TRIGGER_DEADBAND_NEGATIVE))) {
            // mecanumDrive.driveCartesian(-PS4.getY(), getZAxis(), PS4.getX());
            // } else {
            // mecanumDrive.driveCartesian(0, 0, 0);
            // }

            // }

        }

        // Thread class provides the join() method which allows one thread to wait until
        // another thread completes its execution.
        // Basically, if t is a Thread object whose thread is currently executing, then
        // t.join() will make sure that t is terminated before the next instruction is
        // executed by the program.
        try {
            driveThread.join();
        } catch (InterruptedException e) {
            System.out.println(threadName + " Interrupted.");
        }

        // Print out when the Thread is exiting, and force
        // garbage collection (freeing of memory resources) (.gc()).
        System.out.println(threadName + " Exiting");
        runtime.gc();

    }

    /////////////////////////////////////////////////////////////////////
    // Function: getZAxis()
    /////////////////////////////////////////////////////////////////////
    //
    // Purpose: Gets the value for the "Z" axis using the 2 analog triggers.
    //
    // Arguments: none
    //
    // Returns: double zAxis: the value.
    //
    // Remarks: Created on 2/22/2020.
    //
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    public double getZAxis() {

        // This variable is used for setting the Z axis for strafing.
        double zAxis;

        // These variables are for getting the values for the left and right
        // analog triggers, respectively.
        double leftAnalogTrigger;
        double rightAnalogTrigger;

        // Axes 2 and 3 are the left and right analog triggers, respectively.
        // You have to add 1 because the triggers start at -1 and go to 1.
        // Adding 1 makes them start at 0 when not being pressed.
        leftAnalogTrigger = PS4.getRawAxis(3) + 1;
        rightAnalogTrigger = PS4.getRawAxis(4) + 1;

        // Do the math for getting the value for strafing.
        // Example 1: if the driver presses the right one down, that value will be 1 - 0
        // = 100% speed (1).
        // Example 2: if the driver presses the left one down, that value will be 0 - 1
        // ; -100% speed (-1).
        zAxis = rightAnalogTrigger - leftAnalogTrigger;

        // Return the value, to be used elsewhere.
        return zAxis;
    }

    /////////////////////////////////////////////////////////////////////
    // Function: strafeLeftAuto()
    /////////////////////////////////////////////////////////////////////
    //
    // Purpose: Used for strafing left the specified number of inches
    // in autonomous.
    //
    // Arguments: double feet: the feet we want the robot to strafe.
    //
    // Returns: void
    //
    // Remarks: Created on 2/22/2020.
    //
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    // public void strafeLeftAuto(double feet) {

    // // Speed for how fast the robot strafes.
    // final double strafeSpeed = 0.5;

    // // Initialize the encoder to 0 (reset it).
    // frontLeftDriveEnc.setPosition(0);

    // // Convert feet to inches.
    // double inches = feet * 12.0;

    // // The counter.
    // double currentCounts = 0;

    // // 1.15 is a magic number for the calibration for the encoders.
    // // This should give us how many counts.
    // double encoderCounts = inches / 1.15;

    // while (currentCounts < encoderCounts) {

    // // Strafe left.
    // frontLeftDriveMotor.set(-strafeSpeed);
    // backLeftDriveMotor.set(strafeSpeed);
    // frontRightDriveMotor.set(strafeSpeed);
    // backRightDriveMotor.set(-strafeSpeed);

    // // Delay for 20 ms.
    // Timer.delay(0.02);

    // // Read the encoder, and get our current counts.
    // currentCounts = Math.abs(frontLeftDriveEnc.getPosition());

    // }

}