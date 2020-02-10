/////////////////////////////////////////////////////////////////////
// File: WormDriveThread.java
/////////////////////////////////////////////////////////////////////
//
// Purpose: Thread used for controlling the worm drive for raising and
// lowering the ball shooter, and also the climb.
//
// Authors: Elliot DuCharme and Noah Stigeler.
//
// Environment: Microsoft VSCode Java
//
// Remarks: Created on 2/08/2020 at 5:47 PM.
//
/////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////
package frc.robot;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

class WormDriveThread implements Runnable {

    // Name of the Thread.
    String threadName;

    // Creating instance of the Thread class by
    // creating a thread (reserving memory for this object).
    Thread wormDriveThread;

    // Creating an instance of the DriveThread class in here.
    DriveThread driveThread = new DriveThread("DriveThread");

    // Creating an instance of the Variables class in here.
    Variables variables = new Variables();

    // Getting a reference to the Runtime class.
    // We use this stuff for garbage collection.
    // According to page 461 chapter 11 of Java: The Complete Reference 9th edition
    // by Herbert Schildt, you can't instantiate a Runtime object.
    // But, you can get a reference to it. Using this, you can control
    // the state and behavior of the Java Virtual Machine.
    // Lots of cool functions in this section: totalMemory(), freeMemory(), etc.
    // Worth a look.
    Runtime runtime = Runtime.getRuntime();

    // Creating the Falcon 500 that controls the worm drive.
    WPI_TalonFX wormDriveFalcon;

    // Magic number for the ID for the worm drive Falcon 500.
    final int WORM_DRIVE_FALCON_ID = 9;

    // Magic number for controlling how fast we want the
    // worm drive Falcon 500 to spin.
    final double WORM_DRIVE_FALCON_SPEED = 0.5;

    // WormDriveThread constructor.
    // The name of the Thread is passed in as an argument.
    WormDriveThread(String name) {

        // Assigning the name of the Thread to the argument.
        threadName = name;

        // Creating the Worm Drive Falcon 500.
        wormDriveFalcon = new WPI_TalonFX(WORM_DRIVE_FALCON_ID);

        // Set the wormDriveFalcon in brake mode, which should help it keep the ball
        // shooter up where we want it.
        wormDriveFalcon.setNeutralMode(NeutralMode.Brake);

        // Creating the Worm Drive Thread.
        wormDriveThread = new Thread(wormDriveThread, threadName);
        wormDriveThread.start(); // Start the Thread.

    }

    // Function that actually runs stuff.
    public void run() {

        while (wormDriveThread.isAlive() == true) {

            // While this Thread is running, have this function ready to go.
            adjustShooterAngle(WORM_DRIVE_FALCON_SPEED);

            try {
                driveThread.join();
            } catch (InterruptedException e) {
                System.out.println(threadName + "Interrupted.");
            }

            // Print out when the Thread is exiting, and force garbage collection (freeing
            // of memory resources) (.gc()).
            System.out.println(threadName + "Exiting Drive Thread");
            runtime.gc();

        }
    }

    /////////////////////////////////////////////////////////////////////
    // Function: adjustShooterAngle()
    /////////////////////////////////////////////////////////////////////
    //
    // Purpose: Adjusts the angle of the ball shooter, by running the
    // worm drive motors either forwards or backwards.
    //
    // Arguments: double wormDriveSpeed
    //
    // Returns: void
    //
    // Remarks: Created on 2/08/2020 at 6:01 PM.
    //
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    public void adjustShooterAngle(double wormDriveSpeed) {

        // If the driver pushes the Square Button on the PS4 Controller,
        // set the worm drive Falcon to go backwards (lower it).
        if (driveThread.PS4.getRawButton(variables.PS4_SQUARE_BUTTON) == true) {

            wormDriveFalcon.set(-wormDriveSpeed);

            // If the driver pushes the Triangle Button on the PS4 Controller,
            // set the worm drive Falcon to go forwards (raise it up).
        } else if (driveThread.PS4.getRawButton(variables.PS4_TRIANGLE_BUTTON) == true) {

            wormDriveFalcon.set(wormDriveSpeed);
        }

        // If the driver is an idiot and is pressing BOTH the Square Button AND (&&) the
        // Triangle Button at the same time, OR (||) if the driver is pushing neither
        // button, set the motor speed to 0.
        else if (((driveThread.PS4.getRawButton(variables.PS4_SQUARE_BUTTON) == true)
                && (driveThread.PS4.getRawButton(variables.PS4_TRIANGLE_BUTTON) == true))
                || ((driveThread.PS4.getRawButton(variables.PS4_SQUARE_BUTTON) == false)
                        && (driveThread.PS4.getRawButton(variables.PS4_TRIANGLE_BUTTON) == false))) {

            wormDriveFalcon.set(0);
        }

        // Just in case.
        else {
            wormDriveFalcon.set(0);
        }

    }
}