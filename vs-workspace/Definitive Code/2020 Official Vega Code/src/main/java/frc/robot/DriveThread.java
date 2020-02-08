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

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.drive.MecanumDrive;

// Creating the class that implements the Runnable interface.
class DriveThread implements Runnable {

    // Creating instance of the Thread class by
    // creating a thread (reserving memory for this object).
    Thread driveThread;

    // Getting a reference to the Runtime class.
    // We use this stuff for garbage collection.
    // According to page 461 chapter 11 of Java: The Complete Reference 9th edition
    // by Herbert Schildt, you can't instantiate a Runtime object.
    // But, you can get a reference to it. Using this, you can control
    // the state and behavior of the Java Virtual Machine.
    // Lots of cool functions in this section: totalMemory(), freeMemory(), etc.
    // Worth a look.
    Runtime runtime = Runtime.getRuntime();

    // Creating the drive motors.
    CANSparkMax frontLeftMotor, backLeftMotor, frontRightMotor, backRightMotor;

    // Creating the MecanumDrive constructor, which links all 4 motors together.
    MecanumDrive mecanumDrive = new MecanumDrive(frontLeftMotor, backLeftMotor, frontRightMotor, backRightMotor);

    // Magic number for the deadband for the analog triggers.
    // If the analog triggers are below this value, they will
    // not cause the robot to strafe. This prevents accidental strafing.
    final double PS4_ANALOG_TRIGGER_DEADBAND = 0.15;

    // Magic numbers for Motor IDs.
    final int FRONT_LEFT_SPARK_ID = 1;
    final int BACK_LEFT_SPARK_ID = 2;
    final int FRONT_RIGHT_SPARK_ID = 3;
    final int BACK_RIGHT_SPARK_ID = 4;

    // ID for PS4 Controller.
    final int PS4_ID = 1;

    // Creaing the Controller.
    Joystick PS4 = new Joystick(PS4_ID);

    // DriveThread constructor.
    // String threadName is what is inputted when the Thread is created in the code.
    // Normally, you would have like "DriveThread" or something.
    DriveThread(String threadName) {

        // Constructing the motors, giving them their IDs, and making them brushless.
    frontLeftMotor = new CANSparkMax(FRONT_LEFT_SPARK_ID, MotorType.kBrushless);
    backLeftMotor = new CANSparkMax(BACK_LEFT_SPARK_ID, MotorType.kBrushless);
    frontRightMotor = new CANSparkMax(FRONT_RIGHT_SPARK_ID, MotorType.kBrushless);
    backRightMotor = new CANSparkMax(BACK_RIGHT_SPARK_ID, MotorType.kBrushless);

        driveThread = new Thread(this, threadName); // Actually creating the Thread.
        driveThread.start(); // Start the thread.

    public void run() {

        while (driveThread.isAlive() == true) {

            // Set the flag active so that any joystick
            // manipulations are disabled while this
            // thread is active. Note that delays within
            // this thread will not affect the main()
            // program.
            Robot.driveThreadActive = true;

            // The various member functions would be called here.
            // For example:
            // driveFwd(5.0); // move the robot forward 5.0 feet

            try {
                Robot.auto_drive.t.join();
            } catch (InterruptedException e) {
                System.out.println(name + "Interrupted.");
            }

            System.out.println(name + "Exiting Drive Thread");
            r.gc(); // force garbage collection (freeing of memory resources)

            // Reset flag
            Robot.driveThreadActive = false;
        }

        // Should get a false indication
        System.out.println("Thread Status = " + Robot.auto_drive.t.isAlive());

    }

}