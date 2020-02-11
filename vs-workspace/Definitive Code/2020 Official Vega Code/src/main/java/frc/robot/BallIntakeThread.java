/////////////////////////////////////////////////////////////////////
// File: BallIntakeThread.java
/////////////////////////////////////////////////////////////////////
//
// Purpose: 
//
// Authors: Elliot DuCharme and Noah Stigeler.
//
// Environment: Microsoft VSCode Java
//
// Remarks: Created on 2/08/2020.
// This for the first argument wasn't working, even though in
// DriveThread that is how we do it...?
// 
// TODO Actually finish this Thread, once us programmers know more
// about the robot and how it's going to work.
//
/////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////
package frc.robot;

class BallIntakeThread implements Runnable {

    // Name of the Thread.
    String threadName;

    // Creating instance of the Thread class by
    // creating a thread (reserving memory for this object).
    Thread ballIntakeThread;

    // Getting a reference to the Runtime class.
    // We use this stuff for garbage collection.
    // According to page 461 chapter 11 of Java: The Complete Reference 9th edition
    // by Herbert Schildt, you can't instantiate a Runtime object.
    // But, you can get a reference to it. Using this, you can control
    // the state and behavior of the Java Virtual Machine.
    // Lots of cool functions in this section: totalMemory(), freeMemory(), etc.
    // Worth a look.
    Runtime runtime = Runtime.getRuntime();

    // TODO Some motors or something here, possibly a SpeedControllerGroup or 2...

    // BallIntakeThread constructor.
    // The name of the Thread is passed in as an argument.
    BallIntakeThread(String name) {

        // Assigning the name of the Thread to the argument.
        threadName = name;

        // Actually creating the Thread.
        ballIntakeThread = new Thread(ballIntakeThread, threadName);
        ballIntakeThread.start(); // Start the Thread.

    }

    // Function that actually runs stuff.
    public void run() {

        // While the Thread is alive, do stuff.
        while (ballIntakeThread.isAlive() == true) {

            // Thread class provides the join() method which allows one thread to wait until
            // another thread completes its execution.
            // Basically, if t is a Thread object whose thread is currently executing, then
            // t.join() will make sure that t is terminated before the next instruction is
            // executed by the program.
            try {
                ballIntakeThread.join();
            } catch (InterruptedException e) {
                System.out.println(threadName + "Interrupted.");
            }

            // Print out when the Thread is exiting, and force
            // garbage collection (freeing of memory resources) (.gc()).
            System.out.println(threadName + " Exiting");
            runtime.gc();

        }

    }
}