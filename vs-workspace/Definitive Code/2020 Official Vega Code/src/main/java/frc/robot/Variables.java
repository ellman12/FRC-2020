/////////////////////////////////////////////////////////////////////
// File: Variables.java
/////////////////////////////////////////////////////////////////////
//
// Purpose: Houses miscellaneous variables and the like.
// Also houses miscellaneous constants.
// Variables and constants are typically housed here if they don't
// really belong anywhere else.
//
// Authors: Noah S. and Elliot D.
//
// Environment: Microsoft VSCode Java
//
// Remarks: Created on 2/08/2020 at 10:09 AM
//
/////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////
package frc.robot;

class Variables {

    ///////////////////// Variables ////////////////////

    ///////////////////// CONSTANTS ////////////////////
    // Magic numbers for PS4 Controller Axes IDs.
    final int PS4_L_X_AXIS_ID = 0;
    final int PS4_L_Y_AXIS_ID = 1;
    final int PS4_R_X_AXIS_ID = 2;
    final int PS4_R_Y_AXIS_ID = 3;
    final int PS4_L_ANALOG_TRIG_ID = 4;
    final int PS4_R_ANALOG_TRIG_ID = 5;

    // Magic numbers used for setting the priorities of Threads.
    final int MIN_THREAD_PRIORITY = 1;
    final int NORM_THREAD_PRIORITY = 5;
    final int MAX_THREAD_PRIORITY = 10;

    // Magic numbers used for the PS4 Controller button ID's.
    final int PS4_SQUARE_BUTTON = 1; // Used in WormDriveThread.java for lowering the worm drive.
    final int PS4_X_BUTTON = 2; // Used in BallShootThread.java for shooting balls.
    final int PS4_CIRCLE_BUTTON = 3; // TODO Used in BallIntakeThread.java for intaking balls.
    final int PS4_TRIANGLE_BUTTON = 4; // Used in WormDriveThread.java for raising the worm drive.
    final int PS4_LEFT_BUMPER = 5;
    final int PS4_RIGHT_BUMPER = 6;
    // ID's 7 and 8 are not included because they're
    // "button" ID's for the Analog Triggers.
    final int PS4_SHARE_BUTTON = 9;
    final int PS4_OPTIONS_BUTTON = 10;
    final int PS4_LEFT_STICK_IN = 11;
    final int PS4_RIGHT_STICK_IN = 12;
    final int PS4_PS4_LOGO_BUTTON = 13;
    final int PS4_TOUCHPAD = 14;

    // Constructor.
    Variables() {

    }

}