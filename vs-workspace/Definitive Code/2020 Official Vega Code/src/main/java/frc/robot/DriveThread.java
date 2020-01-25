/////////////////////////////////////////////////////////////////////
//  File:  DriveThread.java
/////////////////////////////////////////////////////////////////////
//
//Purpose:  Defines the thread class responsible for robot movement forward
//          and backward, left and right turns during autonomous
//          operation. All autonomous movements would be included
//          in the t.run() function.
// 
//          Also used for controlling the robot with a joystick.
//
//Programmers: Elliott DuCharme
//
//Revisions:
//
//Remarks: 
//
/////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////
package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.drive.MecanumDrive;

class DriveThread implements Runnable {

    // Variables used for the Thread.
    String name;
    Thread t;
    Runtime r = Runtime.getRuntime();

    // Initial position of encoder.
    public double initPosition;

    // Initial position of driveGyro.
    public double init_Angle;

    // Linking other classes together.
    Constants Constants;
    Delay Delay;

    // Creating the motors for the drive.
    CANSparkMax frontLeftMotor, frontRightMotor, backLeftMotor, backRightMotor;

    // Encoder for frontLeftMotor.
    CANEncoder left_enc;

    // Creatingn the Mecanum Drive.
    MecanumDrive mecanumDrive;

    // Creating the PS4 controller.
    Joystick PS4;

    // Joystick axes.
    double PS4LeftXAxis, PS4LeftYAxis, PS4RightXAxis, PS4RightYAxis, PS4LeftAnalogTrigger, PS4RightAnalogTrigger;

    // Constructor
    DriveThread(String threadname) {

        Constants = new Constants();
        Delay = new Delay();

        frontLeftMotor = new CANSparkMax(1, MotorType.kBrushless);
        frontRightMotor = new CANSparkMax(2, MotorType.kBrushless);
        backLeftMotor = new CANSparkMax(3, MotorType.kBrushless);
        backRightMotor = new CANSparkMax(4, MotorType.kBrushless);

        left_enc = new CANEncoder(frontLeftMotor);

        // Adding the drive motors to the Mecanum Drive.
        mecanumDrive = new MecanumDrive(frontLeftMotor, backLeftMotor, frontRightMotor, backLeftMotor);

        // Assigning the PS4 controller the ID of 1.
        PS4 = new Joystick(Constants.PS4_PORT_NUMBER);

        // We want to establish an initial encoder reading. This will enable reseting
        // encoder position to zero when we start moving. We use absolute values to
        // make the subsequent subtraction more easily interpreted.
        // Robot.left_enc.setPosition(0.0);
        // initPosition = Robot.left_enc.getPosition(); // should be zero
        // init_Angle = Robot.driveGyro.getAngle();

        // The initial position should be zero.
        System.out.println("Left Encoder Initial Position = " + initPosition);
        System.out.println("Initial Heading = " + init_Angle);

        name = threadname;
        t = new Thread(this, name);
        System.out.println("New thread: " + t);

        t.start(); // Start the thread
    }

    public void run() {
        // While the drive thread is alive and running.
        while (Robot.driveThread.t.isAlive() == true) {

            // Set the flag active so that any joystick
            // manipulations are disabled while this
            // thread is active. Note that delays within
            // this thread will not affect the main()
            // program.
            Robot.drive_thread_active = true;

            // The various member functions would be called here.
            // For example:

            // driveFwd(5.0);

            // turnRight_Arcade(90);

            // Wait for the thread to complete.
            try {
                Robot.driveThread.t.join();
            } catch (InterruptedException e) {
                System.out.println(name + "Interrupted.");
            }

            // Print out when the thread is exiting.
            System.out.println(name + "Exiting Drive Thread");
            r.gc(); // force garbage collection (freeing of memory resources)

            // Reset flag.
            Robot.drive_thread_active = false;
        }

        // Should get a false indication
        System.out.println("Thread Status = " + Robot.driveThread.t.isAlive());

    }

}

/////////////////////////////////////////////////////////////////////
// Function: public int driveFwd(double distance)
/////////////////////////////////////////////////////////////////////
//
// Purpose: Drives the robot forward the distance in feet specified
// by the argument.
//
// Arguments: double distance, The distance to be traveled in feet.
//
// Returns: A double representing overshoot/undershoot of the movement
// in inches.
//
// Remarks:
//
/////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////
// public double driveFwd(double distance) {
// int loop_count = 0;

// double counts;
// double initial_position;
// double current_position;
// double target;

// double fraction; // Helps the robot slow down.
// double heading; // Direction the robot is moving.

// double error; // The difference between where we end up and where we want to
// be.

// // Determine where we are pointing - we want to maintain this heading during
// // this forward movement.
// // heading = Robot.driveGyro.getAngle();
// // Robot.left_enc.setPosition(0.0);

// // Read encoder #1, get the initial encoder position and assign it to
// // the current position. Calculate the number of encoder counts
// // necessary to reach the final destination (target).
// // initial_position = Robot.left_enc.getPosition(); // should be zero

// System.out.println("initPos = " + initial_position);

// current_position = initial_position;
// // counts = calcCounts_SAE(distance);
// target = initial_position + counts;

// // fraction starts out as equal to 1.0 and decreases as we approach the
// target.
// // fraction is counts remaining divided by total counts.
// fraction = Math.abs((target - current_position) / (target -
// initial_position));

// System.out
// .println("initial_position = " + initial_position + " target = " + target + "
// fraction = " + fraction);

// // We attempt a bit of proportional control as we approach the target. We
// want
// // to slow down so that we don't overshoot. These fractions appear to work.
// // We drive at high speed for 80% of the distance and then slow. On carpet
// this
// // seemed to work very well for distance of 10 feet.
// // We want braking enabled.
// // We also need to put a timer within the while() loop to provide an escape
// in
// // the event that the system gets lost during autonomous requiring a restart
// of
// // the
// // program.

// while (current_position < target) {

// if (fraction > Constants.BRAKE_FRACTION) {
// moveFwd(Constants.START_SPEED, heading);
// } else {
// moveFwd(Constants.BRAKE_SPEED, heading);
// }

// Timer.delay(0.01);
// System.out.println(
// "current_position = " + current_position + " target = " + target + " fraction
// = " + fraction);

// // We don't want to stay longer than we have to. Assuming
// // that the 10 msec is reasonably accurate we limit the
// // move to 5 seconds for starters.
// loop_count++;
// if ((loop_count % Constants.ENC_CONSOLE_UPDATE) == 0) {
// // Provide periodic status
// System.out.println(
// "current_position = " + current_position + " target = " + target + " fraction
// = " + fraction);
// }
// if (loop_count == Constants.ENC_LOOP_ESCAPE) {
// break; // escape clause
// }

// // current_position = Robot.left_enc.getPosition();
// fraction = Math.abs((target - current_position) / (target -
// initial_position));
// }

// // stop movement, compute error (overshoot or undershoot)
// diff_drive.arcadeDrive(0, 0);
// current_position = left_enc.getPosition();
// error = calcDistance_SAE(current_position - target);
// System.out.println("error = " + error + " inches");

// return (error);
// }

// }