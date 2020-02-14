/////////////////////////////////////////////////////////////////////
// File: Autonomous.java
/////////////////////////////////////////////////////////////////////
//
// Purpose: Houses functions used in Autonomous.
// This helps keep DriveThread less cluttered.
//
// Authors: Elliott DuCharme and Noah Stigeler.
//
// Environment: Microsoft VSCode Java
//
// Remarks: Created on 2/08/2020.
//
/////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////
package frc.robot;

class Autonomous {

    // Creating an instance of the Robot class in here.
    Robot robot = new Robot();

    // Autonomous constructor.
    Autonomous() {

    }

    /////////////////////////////////////////////////////////////////////
    // Function: autoFunctions()
    /////////////////////////////////////////////////////////////////////
    //
    // Purpose:
    //
    // Arguments: None
    //
    // Returns: void
    //
    // Remarks:
    //
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    public void autoFunctions() {

        // Switch statement for where on the field we are starting:
        // (right side of the field, left, or middle).
        switch (robot.positionChoice) {
        case "Left Position":

            // Choice for which robots are going first.
            switch (robot.orderChoice) {

            case "Going First":

                break;

            case "Going Second":

                break;

            // Going last is the default value.
            default:

                break;

            }

            break;

        case "Right Position":

            // Choice for which robots are going first.
            switch (robot.orderChoice) {

            case "Going First":

                break;

            case "Going Second":

                break;

            // Going last is the default value.
            default:

                break;

            }

            break;

        // Middle position is the default value.
        default:

            // Choice for which robots are going first.
            switch (robot.orderChoice) {

            case "Going First":

                break;

            case "Going Second":

                break;

            // Going last is the default value.
            default:

                break;

            }

        }

    }
}