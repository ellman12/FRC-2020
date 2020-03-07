/////////////////////////////////////////////////////////////////////
// File: Vision.java
/////////////////////////////////////////////////////////////////////
//
// Purpose: Used for vision stuff for the Limelight.
//
// Authors: Elliott DuCharme.
//
// Environment: Microsoft VSCode Java.
//
// Remarks: Created on 3/05/2020.
//
/////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////
package frc.robot;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

// Extends Auto which also extends Robot.
class Vision extends Autonomous {

    // NetworkTable for the Limelight.
    private NetworkTable networkTable = NetworkTableInstance.getDefault().getTable("limelight");

    // Constants for the LED mode of the Limelight.
    // These control how the LED's work on it.
    // Blink, not-blinking, etc.
    final int CURR_PIPELINE_LED_MODE = 0; // Use the LED Mode set in the current pipeline.
    final int LED_MODE_FORCE_OFF = 1; // Force the LED's off.
    final int LED_MODE_FORCE_BLINK = 2; // Force the LED's to blink.
    final int LED_MODE_FORCE_ON = 3; // Force the LED's on.

    // Constants for the camera mode of the Limelight.
    // If the camera is processing vision or not.
    final int CAM_MODE_VISION_PROCESSOR = 0; // Processing vision.
    final int CAM_MODE_DRIVER_CAMERA = 1; // Like a normal camera (increases exposure, disables vision processing).

    // Constants for the camera stream.
    // These control how the Limelight displays the camera stream in Shuffleboard.
    final int STREAM_MODE_STANDARD = 0; // Side-by-side; works very well.
    // The secondary camera stream is placed in the lower-right corner of the
    // primary camera stream.
    final int STREAM_MODE_PIP_MAIN = 1;
    // The primary camera stream is placed in the lower-right corner of the
    // secondary camera stream.
    final int STREAM_MODE_PIP_SECONDARY = 2;

    // Constructor.
    Vision() {

        // TODO Set these to our desired initial values.

    }

    /////////////////////////////////////////////////////////////////////
    // Function: setLEDMode()
    /////////////////////////////////////////////////////////////////////
    //
    // Purpose: Sets how the LED's on the Limelight will work.
    // Throws an exception if the argument is not between 0 and 3.
    //
    // Arguments: int ledMode: 0 through 3. Check top of file for what
    // they represent.
    //
    // Returns: void
    //
    // Remarks: Created on 3/07/2020.
    //
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    public void setLEDMode(int ledMode) {

        // If out of range, throw an error. The error is definitely not
        // necessary, but it's a good programming practice.
        if (ledMode > 3 || ledMode < 0) {

            throw new IllegalArgumentException("ledMode cannot be greater than 3 and less than 0.");

        } else if (ledMode <= 3 && ledMode >= 0) {

            // Else if the passed-in value is in the range, set the value.
            networkTable.getEntry("ledMode").setValue(ledMode);
        }

    }

    /////////////////////////////////////////////////////////////////////
    // Function: getLEDMode()
    /////////////////////////////////////////////////////////////////////
    //
    // Purpose: Gets the current LED mode on the Limelight.
    //
    // Arguments: none
    //
    // Returns: NetworkTableEntry: the value of the LED mode.
    //
    // Remarks: Created on 3/07/2020.
    //
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    public NetworkTableEntry getLEDMode() {
        NetworkTableEntry ledMode = networkTable.getEntry("ledMode");
        return ledMode;
    }

    /////////////////////////////////////////////////////////////////////
    // Function: setCamMode()
    /////////////////////////////////////////////////////////////////////
    //
    // Purpose: Sets the Limelight operation mode: Vision processor or
    // Driver Camera (increases exposure, disables vision processing).
    // Throws an error (exception) if the argument is not 0 or 1.
    //
    // Arguments: int camMode. Must be either 0 or 1.
    // Check top of the file for what they are.
    //
    // Returns: void
    //
    // Remarks: Created on 3/07/2020.
    //
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    public void setCamMode(int camMode) {

        // If out of range, throw an exception.
        // Else if in range, set the value.
        if (camMode != 0 || camMode != 1) {

            throw new IllegalArgumentException("camMode can only be 0 or 1.");

        } else if (camMode == 0 && camMode == 1) {

            networkTable.getEntry("camMode").setValue(camMode);
        }

    }

    /////////////////////////////////////////////////////////////////////
    // Function: getCamMode()
    /////////////////////////////////////////////////////////////////////
    //
    // Purpose: Gets the camera mode on the Limelight.
    //
    // Arguments: none
    //
    // Returns: NetworkTableEntry: the value of the LED mode.
    //
    // Remarks: Created on 3/07/2020.
    //
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    public NetworkTableEntry getCamMode() {
        NetworkTableEntry camMode = networkTable.getEntry("camMode");
        return camMode;
    }

    /////////////////////////////////////////////////////////////////////
    // Function: setStreamMode()
    /////////////////////////////////////////////////////////////////////
    //
    // Purpose: Sets the way the Limelight displays the 2 camera streams
    // in Shuffleboard.
    //
    // Arguments: int streamMode. Must be between 0 and 2.
    // Check top of file for what they represent.
    // Throws an error (exception) if the argument is not between 0 and 2.
    //
    // Returns: void
    //
    // Remarks: Created on 3/07/2020.
    //
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    public void setStreamMode(int streamMode) {

        // If out of range, throw an error. The error is definitely not
        // necessary, but it's a good programming practice.
        if (streamMode > 2 || streamMode < 0) {

            throw new IllegalArgumentException("streamMode must be between 0 and 2.");

        } else if (streamMode <= 2 && streamMode >= 0) {

            // Else if the passed-in value is in the range, set the value.
            networkTable.getEntry("streamMode").setValue(streamMode);
        }
    }

    /////////////////////////////////////////////////////////////////////
    // Function: getStreamMode()
    /////////////////////////////////////////////////////////////////////
    //
    // Purpose: Gets the stream mode of the Limelight.
    //
    // Arguments: none
    //
    // Returns: NetworkTableEntry: the value of the LED mode.
    //
    // Remarks: Created on 3/07/2020.
    //
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    public NetworkTableEntry getStreamMode() {
        NetworkTableEntry streamMode = networkTable.getEntry("streamMode");
        return streamMode;
    }

}