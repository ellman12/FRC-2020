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
/////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////
package frc.robot;

class BallIntakeThread {

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

        }

    }
}