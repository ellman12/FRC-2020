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
// 
// TODO Actually finish this Thread, once us programmers know more
// about the robot and how it's going to work.
//
/////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////
package frc.robot;

class ClimbThread implements Runnable {

    ClimbThread(String threadName) {

    }

    public void run() {

         while the thread is alive {

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
}