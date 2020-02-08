/////////////////////////////////////////////////////////////////////
// File: BallShootThread.java
/////////////////////////////////////////////////////////////////////
//
// Purpose: Thread used for controlling the mechanisms for shooting
// lemons (power cells).
//
// Authors: Elliot DuCharme and Noah Stigeler.
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

class BallShootThread {

    // Name of the Thread.
    String threadName;

    // Creating instance of the Thread class by
    // creating a thread (reserving memory for this object).
    Thread ballShooThread;

    // Getting a reference to the Runtime class.
    // We use this stuff for garbage collection.
    // According to page 461 chapter 11 of Java: The Complete Reference 9th edition
    // by Herbert Schildt, you can't instantiate a Runtime object.
    // But, you can get a reference to it. Using this, you can control
    // the state and behavior of the Java Virtual Machine.
    // Lots of cool functions in this section: totalMemory(), freeMemory(), etc.
    // Worth a look.
    Runtime runtime = Runtime.getRuntime();

    // DriveThread constructor.
    // String threadName is what is inputted when the Thread is created in the code.
    // Normally, you would have like "DriveThread" or something.
    BallShootThread(String name) {

        threadName = name;

        ballShooThread = new Thread(ballShooThread, threadName);
        ballShooThread.start();

    }

    // Function that actually runs stuff.
    public void run() {

        // While the Thread is alive, do stuff.
        while (ballShooThread.isAlive() == true) {

        }

    }
}