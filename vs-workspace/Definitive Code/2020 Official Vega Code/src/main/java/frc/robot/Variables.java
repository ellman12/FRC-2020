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
    // Magic numbers for PS4 Controller IDs.
    final int PS4_L_X_AXIS_ID = 0;
    final int PS4_L_Y_AXIS_ID = 1;
    final int PS4_R_X_AXIS_ID = 2;
    final int PS4_R_Y_AXIS_ID = 3;
    final int PS4_L_ANALOG_TRIG_ID = 4;
    final int PS4_R_ANALOG_TRIG_ID = 5;

    ///////////////////// CONSTANTS ////////////////////
    final int MIN_THREAD_PRIORITY = 1;
    final int NORM_THREAD_PRIORITY = 5;
    final int MAX_THREAD_PRIORITY = 10;

    // Constructor.
    Variables() {

    }

}