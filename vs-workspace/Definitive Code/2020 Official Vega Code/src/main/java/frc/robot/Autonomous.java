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

import edu.wpi.first.wpilibj.Timer;

class Autonomous {

    // Creating an instance of the Robot class in here.
    Robot robot = new Robot();

    // Creating an instance of the Sensors class in here.
    Sensors sensors = new Sensors();

    // Variables used for ComputeTrajectory.java.
    // Distances expressed in inches; velocity in ft/sec.
    // Velocity and these other values might need to be adjusted.
    static double v = 25.0; // Ball velocity.
    static double x0 = 0; // Initial x value.
    static double y0 = 24; // Launch location (initial y value).
    static double y = 98.25; // Target of y (height of inner goal of the tower (in inches)).

    // Creating an instance of the ComputeTrajectory class.
    ComputeTrajectory computeTrajectory = new ComputeTrajectory(x0, sensors.proximitySensorDistance, y0, y, v);

    // Magic numbers for how long to wait for the other robots to go (in seconds).
    final int GOING_SECOND_DELAY = 5;
    final int GOING_LAST_DELAY = 8;

    // Autonomous constructor.
    Autonomous() {

    }

    /////////////////////////////////////////////////////////////////////
    // Function: autoFunctions()
    /////////////////////////////////////////////////////////////////////
    //
    // Purpose: The function that calls other functions to make the robot
    // do what the driver picks it to do in SmartDashboard.
    //
    // Arguments: None
    //
    // Returns: void
    //
    // Remarks: Created on 2/14/2020.
    //
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    public void autoFunctions() {

        // Switch statement for where on the field we are starting:
        // (right side of the field, left, or middle (default starting position)).
        switch (robot.positionChoice) {
        case "Left Position":

            orderChoiceAuto(robot.orderChoice);
            // Move to the goal somehow.
            // Fire.
            ballShootAuto(robot.goalChoice);

            break;

        case "Right Position":

            orderChoiceAuto(robot.orderChoice);
            // Move to the goal somehow.
            // Fire.
            ballShootAuto(robot.goalChoice);

            break;

        // Middle position is the default value.
        default:

            // move over to the goal somehow
            ballShootAuto(robot.goalChoice);
            // Fire.
            ballShootAuto(robot.goalChoice);

        }

    }

    /////////////////////////////////////////////////////////////////////
    // Function: ballShootAuto(...)
    /////////////////////////////////////////////////////////////////////
    //
    // Purpose: Used for telling the ball shooter where to go in Autonomous.
    //
    // Arguments: String target: "High Goal" or "Low Goal":
    // If we're going for the high or low goal for autonomous.
    // The value is passed in from the SmartDashboard.
    //
    // Returns: void
    //
    // Remarks: Created on 2/14/2020.
    //
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    public void ballShootAuto(String target) {

        // If our target is the high goal, adjust the angle of the shooter to the
        // required angle.
        if (target == "High Goal") {

            computeTrajectory.adjustAngleOfShooter();

            // If our target is the low goal, move the ball shooter to there.
        } else if (target == "Low Goal") {

            // Low Goal stuff.

        } else {
            ; // Do nothing.
        }

    }

    /////////////////////////////////////////////////////////////////////
    // Function: orderChoiceAuto(...)
    /////////////////////////////////////////////////////////////////////
    //
    // Purpose: Used in Auto for telling our robot when to move, and how
    // long to wait, if applicable.
    //
    // Arguments: String orderChoice, which is either "Going First", or
    // "Going Second" ("Going Last" is the default value in SmartDashboard).
    //
    // Returns: void
    //
    // Remarks: Created on 2/14/2020.
    //
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    public void orderChoiceAuto(String orderChoice) {

        switch (orderChoice) {

        case "Going First":
            // No delay, because we're going first.
            break;

        case "Going Second":
            // Wait this many seconds.
            Timer.delay(GOING_SECOND_DELAY);
            break;

        // Our default value is "Going Last".
        default:
            // Wait this many seconds.
            Timer.delay(GOING_LAST_DELAY);
        }

    }

}