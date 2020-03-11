/////////////////////////////////////////////////////////////////////
// File: BallShootThread.java
/////////////////////////////////////////////////////////////////////
//
// Purpose: Thread used for controlling the mechanisms for shooting
// lemons (power cells).
//
// Authors: Elliot DuCharme and Larry Basegio.
//
// Environment: Microsoft VSCode Java.
//
// Remarks: Created on 2/27/2020.
//
/////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////
package frc.robot;

class BallShootThread extends Constants implements Runnable {

    // Creating instance of the Thread class by
    // creating a thread (reserving memory for this object).
    Thread ballShootThread;

    // Getting a reference to the Runtime class.
    // We use this stuff for garbage collection.
    Runtime runtime = Runtime.getRuntime();

    // BallShootThread constructor.
    // The name of the Thread is passed in as an argument.
    BallShootThread(String name) {

        // Name of the Thread.
        String threadName = name;

        // Actually creating the Thread.
        ballShootThread = new Thread(ballShootThread, threadName);
        ballShootThread.start(); // Start the Thread.
    }

    // Function that actually runs stuff.
    public void run() {

        // While the Thread is alive, do stuff.
        while (ballShootThread.isAlive() == true) {

        }

    }
}