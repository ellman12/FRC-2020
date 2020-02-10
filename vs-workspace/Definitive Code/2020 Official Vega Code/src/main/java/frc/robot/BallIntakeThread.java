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

    String name;

    Thread ballIntakeThread;

    Runtime runtime = Runtime.getRuntime();

    BallIntakeThread(String threadName) {

        name = threadName;

        ballIntakeThread = new Thread(ballIntakeThread, threadName);
        ballIntakeThread.start();

    }

    public void run() {

        while (ballIntakeThread.isAlive() == true) {

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