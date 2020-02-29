/////////////////////////////////////////////////////////////////////
// File: BallIntakeThread.java
/////////////////////////////////////////////////////////////////////
//
// Purpose: Thread used for running Ball Intake stuff.
//
// Authors: Elliot DuCharme.
//
// Environment: Microsoft VSCode Java
//
// Remarks: Created on 2/08/2020.
// This for the first argument wasn't working, even though in
// DriveThread that is how we do it...?
//
/////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////
package frc.robot;

class BallIntakeThread extends BallIntake implements Runnable {

    // Creating instance of the Thread class by
    // creating a thread (reserving memory for this object).
    Thread ballIntakeThread;

    // Getting a reference to the Runtime class.
    Runtime runtime = Runtime.getRuntime();

    // BallIntakeThread constructor.
    // The name of the Thread is passed in as an argument.
    BallIntakeThread(String name) {

        // Name of the Thread.
        String threadName = name;

        // Actually creating the Thread.
        ballIntakeThread = new Thread(ballIntakeThread, threadName);
        ballIntakeThread.start(); // Start the Thread.
    }

    // Function that actually runs stuff.
    public void run() {

        // While the Thread is alive, do stuff.
        while (ballIntakeThread.isAlive() == true) {

            // If the driver pushes the right bumper button on the PS4 Controller,
            // run the intake motors forward (inwards).
            if (driveThread.PS4.getRawButton(constants.PS4_RIGHT_BUMPER)) {
                intakeBalls(0.65, 0.5);

                // Else if the driver pushes the left bumper button on the PS4 Controller,
                // run the intake motors backwards (outward).
            } else if (driveThread.PS4.getRawButton(constants.PS4_LEFT_BUMPER)) {
                intakeBalls(-0.65, -0.5);
            } else {
                // Else, set the motors to 0 (don't run them).
                intakeBalls(0, 0);
            }

        }
    }

}