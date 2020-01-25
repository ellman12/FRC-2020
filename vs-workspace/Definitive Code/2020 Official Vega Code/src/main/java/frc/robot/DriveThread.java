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

}
// NOT DONE!!!