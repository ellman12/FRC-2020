/////////////////////////////////////////////////////////////////////
// File: VarsAndConsts.java
/////////////////////////////////////////////////////////////////////
//
// Purpose: Houses miscellaneous variables and constants that don't
// belong elsewhere.
//
// Authors: Elliott DuCharme
//
// Environment: Microsoft VSCode Java
//
// Remarks: Created on 2/25/2020.
//
/////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////
package frc.robot;

class VarsAndConsts {

    ///////////////////// VARIABLES ////////////////////

    ///////////////////// CONSTANTS ////////////////////
    // Magic numbers for DriveThread Motor IDs.
    final static int FRONT_LEFT_DRIVE_MOTOR_ID = 1;
    final static int FRONT_RIGHT_DRIVE_MOTOR_ID = 2;
    final static int BACK_LEFT_DRIVE_MOTOR_ID = 3;
    final static int BACK_RIGHT_DRIVE_MOTOR_ID = 4;

    // Magic numbers for BallShootThread Motor IDs.
    final static int FRONT_LEFT_SHOOTER_MOTOR_ID = 5;
    final static int FRONT_RIGHT_SHOOTER_MOTOR_ID = 6;
    final static int BACK_LEFT_SHOOTER_MOTOR_ID = 7;
    final static int BACK_RIGHT_SHOOTER_MOTOR_ID = 8;

    // TODO Worm drive motors, intake, etc.

    // Magic numbers for PS4 Controller Axes IDs.
    final static int PS4_L_X_AXIS_ID = 0;
    final static int PS4_L_Y_AXIS_ID = 1;
    final static int PS4_R_X_AXIS_ID = 2;
    final static int PS4_R_Y_AXIS_ID = 3;
    final static int PS4_L_ANALOG_TRIG_ID = 4;
    final static int PS4_R_ANALOG_TRIG_ID = 5;

    // Magic numbers used for the PS4 Controller button ID's.
    final static int PS4_SQUARE_BUTTON = 1;
    final static int PS4_X_BUTTON = 2;
    final static int PS4_CIRCLE_BUTTON = 3;
    final static int PS4_TRIANGLE_BUTTON = 4;
    final static int PS4_LEFT_BUMPER = 5;
    final static int PS4_RIGHT_BUMPER = 6;
    // ID's 7 and 8 are not included because they're
    // "button" ID's for the Analog Triggers, which is
    // what the Driver Station sees for some reason.
    final static int PS4_SHARE_BUTTON = 9;
    final static int PS4_OPTIONS_BUTTON = 10;
    final static int PS4_LEFT_STICK_IN = 11;
    final static int PS4_RIGHT_STICK_IN = 12;
    final static int PS4_PS4_LOGO_BUTTON = 13;
    final static int PS4_TOUCHPAD = 14;

}