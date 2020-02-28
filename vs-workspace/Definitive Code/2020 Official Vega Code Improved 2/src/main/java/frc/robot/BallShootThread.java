/////////////////////////////////////////////////////////////////////
// File: BallShootThread.java
/////////////////////////////////////////////////////////////////////
//
// Purpose: Thread used for controlling the mechanisms for shooting
// lemons (power cells).
//
// Authors: Elliot DuCharme and Larry Basegio.
//
// Environment: Microsoft VSCode Java
//
// Remarks: Created on 2/27/2020.
//
/////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////
package frc.robot;

class BallShootThread implements Runnable {

    // Creating instance of the Thread class by
    // creating a thread (reserving memory for this object).
    Thread ballShootThread;

    // Creating an instance of the Robot class in here.
    // Used for accessing the other Thread classes,
    // which are created in that file.
    Robot robot = new Robot();

    // Getting a reference to the Runtime class.
    // We use this stuff for garbage collection.
    // According to page 461 chapter 11 of Java: The Complete Reference 9th edition
    // by Herbert Schildt, you can't instantiate a Runtime object.
    // But, you can get a reference to it. Using this, you can control
    // the state and behavior of the Java Virtual Machine.
    // Lots of cool functions in this section: totalMemory(), freeMemory(), etc.
    // Worth a look.
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