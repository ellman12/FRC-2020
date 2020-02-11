/////////////////////////////////////////////////////////////////////
// File: DriveThread.java
/////////////////////////////////////////////////////////////////////
//
// Purpose: Defines the thread class responsible for robot movement
// forward, backwards, left, and right during autonomous.
// All autonomous movements and commands are created in
// Autonomous.java, and are called in here.
// Having them in a separate file declutters this already-long file.
//
// Authors: Elliott DuCharme, Larry Basegio, and Noah Stigeler.
//
// Environment: Microsoft VSCode Java
//
// Remarks:
//
/////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////
package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.drive.MecanumDrive;

// Creating the class that implements the Runnable interface.
class DriveThread implements Runnable {

    // Name of the Thread.
    String threadName;

    // Creating instance of the Thread class by
    // creating a thread (reserving memory for this object).
    Thread driveThread;

    // Creating an instance of the Variables class.
    Variables variables;

    // Getting a reference to the Runtime class.
    // We use this stuff for garbage collection.
    // According to page 461 chapter 11 of Java: The Complete Reference 9th edition
    // by Herbert Schildt, you can't instantiate a Runtime object.
    // But, you can get a reference to it. Using this, you can control
    // the state and behavior of the Java Virtual Machine.
    // Lots of cool functions in this section: totalMemory(), freeMemory(), etc.
    // Worth a look.
    Runtime runtime = Runtime.getRuntime();

    // Creating the drive motors.
    CANSparkMax frontLeftDriveMotor, backLeftDriveMotor, frontRightDriveMotor, backRightDriveMotor;

    // Creating the MecanumDrive constructor, which links all 4 motors together.
    MecanumDrive mecanumDrive = new MecanumDrive(frontLeftDriveMotor, backLeftDriveMotor, frontRightDriveMotor,
            backRightDriveMotor);

    // Magic number for the deadband for the analog triggers.
    // If the analog triggers are below this value, they will
    // not cause the robot to strafe. This prevents accidental strafing.
    final double PS4_ANALOG_TRIGGER_DEADBAND = 0.10;

    // Double variables for the values of the PS4 Controller axes.
    double PS4LeftXAxis;
    double PS4LeftYAxis;
    double PS4LeftAnalogTrigger;
    double PS4RightAnalogTrigger;

    // Magic numbers for Motor IDs.
    final int FRONT_LEFT_SPARK_ID = 1;
    final int BACK_LEFT_SPARK_ID = 2;
    final int FRONT_RIGHT_SPARK_ID = 3;
    final int BACK_RIGHT_SPARK_ID = 4;

    // Creating the PS4 Controller, and giving it the ID of 0
    // Controllers in the Driver Station start at 0, and go to up to 5.
    // If you somehow have 5 controllers, you're insane.
    Joystick PS4 = new Joystick(0);

    // DriveThread constructor.
    // The name of the Thread is passed in as an argument.
    DriveThread(String name) {

        // Assigning the name of the Thread to the argument.
        threadName = name;

        // Constructing the motors, giving them their IDs, and making them brushless.
        frontLeftDriveMotor = new CANSparkMax(FRONT_LEFT_SPARK_ID, MotorType.kBrushless);
        backLeftDriveMotor = new CANSparkMax(BACK_LEFT_SPARK_ID, MotorType.kBrushless);
        frontRightDriveMotor = new CANSparkMax(FRONT_RIGHT_SPARK_ID, MotorType.kBrushless);
        backRightDriveMotor = new CANSparkMax(BACK_RIGHT_SPARK_ID, MotorType.kBrushless);

        // Set the drive motors to coast mode to help prevent tipping.
        frontLeftDriveMotor.setIdleMode(IdleMode.kCoast);
        frontRightDriveMotor.setIdleMode(IdleMode.kCoast);
        backLeftDriveMotor.setIdleMode(IdleMode.kCoast);
        backRightDriveMotor.setIdleMode(IdleMode.kCoast);

        // Creating a new instance of the Variables class.
        variables = new Variables();

        // Actually creating the Thread.
        driveThread = new Thread(this, threadName);
        driveThread.start(); // Start the Thread.
    }

    // Function that actually runs stuff.
    public void run() {

        // While the Thread is alive.
        while (driveThread.isAlive() == true) {

            if (DriverStation.getInstance().isAutonomous()) {
                // TODO Auto functions called here.
            } else {
                // Have teleop stuff here.
                // Getting the values of the PS4 Controller's axes.
                PS4LeftXAxis = PS4.getRawAxis(variables.PS4_L_X_AXIS_ID);
                PS4LeftYAxis = PS4.getRawAxis(-variables.PS4_L_Y_AXIS_ID);
                PS4LeftAnalogTrigger = PS4.getRawAxis(variables.PS4_L_ANALOG_TRIG_ID);
                PS4RightAnalogTrigger = PS4.getRawAxis(variables.PS4_R_ANALOG_TRIG_ID);

                // Controlling the Mecanum Drive with the Joystick axes.
                mecanumDrive.driveCartesian(PS4.getY(), PS4.getX(), PS4.getZ());

                // If the analog triggers are pressed down sufficiently, strafe in the
                // corresponding direction.
                if (PS4LeftAnalogTrigger >= PS4_ANALOG_TRIGGER_DEADBAND) {
                    strafeLeft();
                }

                if (PS4_ANALOG_TRIGGER_DEADBAND >= PS4_ANALOG_TRIGGER_DEADBAND) {
                    strafeRight();
                }

            }

            try {
                driveThread.join();
            } catch (InterruptedException e) {
                System.out.println(threadName + " Interrupted.");
            }

            // Print out when the Thread is exiting, and force
            // garbage collection (freeing of memory resources) (.gc()).
            System.out.println(threadName + " Exiting");
            runtime.gc();

        }

    }

    /////////////////////////////////////////////////////////////////////
    // Function: strafeLeft()
    /////////////////////////////////////////////////////////////////////
    //
    // Purpose: Function called when the left analog trigger is pressed
    // down. Causes the robot to strafe left.
    //
    // Arguments: None
    //
    // Returns: void
    //
    // Remarks:
    // In order to strafe left...
    // the frontLeftDriveMotor has to spin backwards...
    // the backLeftDriveMotor has to spin forwards...
    // the frontRightMotor has to spin forwards...
    // and the backRightDriveMotor has to spin backwards.
    //
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    public void strafeLeft() {
        frontLeftDriveMotor.set(-PS4LeftAnalogTrigger);
        backLeftDriveMotor.set(PS4LeftAnalogTrigger);
        frontRightDriveMotor.set(PS4LeftAnalogTrigger);
        backRightDriveMotor.set(-PS4LeftAnalogTrigger);
    }

    /////////////////////////////////////////////////////////////////////
    // Function: strafeRight()
    /////////////////////////////////////////////////////////////////////
    //
    // Purpose: Function called when the right analog trigger is pressed
    // down. Causes the robot to strafe right.
    //
    // Arguments: None
    //
    // Returns: void
    //
    // Remarks:
    // In order to strafe right...
    // the frontLeftDriveMotor has to spin forwards...
    // the backLeftDriveMotor has to spin backwards...
    // the frontRightMotor has to spin backwards...
    // and the backRightDriveMotor has to spin forwards.
    //
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    public void strafeRight() {
        frontLeftDriveMotor.set(PS4LeftAnalogTrigger);
        backLeftDriveMotor.set(-PS4LeftAnalogTrigger);
        frontRightDriveMotor.set(-PS4LeftAnalogTrigger);
        backRightDriveMotor.set(PS4LeftAnalogTrigger);
    }

}