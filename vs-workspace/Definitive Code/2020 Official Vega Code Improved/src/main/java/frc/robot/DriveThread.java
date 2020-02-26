/////////////////////////////////////////////////////////////////////
// File: DriveThread.java
/////////////////////////////////////////////////////////////////////
//
// Purpose:
//
// Authors: Elliott DuCharme and Larry Basegio.
//
// Environment: Microsoft VSCode Java
//
// Remarks:
//
/////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////
package frc.robot;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.drive.MecanumDrive;

// Creating the class that implements the Runnable interface.
class DriveThread implements Runnable {

    // Creating an instance of the Thread class.
    Thread driveThread;

    // Constructor.
    DriveThread(String name) {

        // Name of the Thread.
        String threadName = name;

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
        CANSparkMax frontLeftDriveMotor = new CANSparkMax(1, MotorType.kBrushless);
        CANSparkMax frontRightDriveMotor = new CANSparkMax(2, MotorType.kBrushless);
        CANSparkMax backLeftDriveMotor = new CANSparkMax(3, MotorType.kBrushless);
        CANSparkMax backRightDriveMotor = new CANSparkMax(4, MotorType.kBrushless);

        // Creating an object of the MecanumDrive class.
        MecanumDrive mecanumDrive = new MecanumDrive(frontLeftDriveMotor, backLeftDriveMotor, frontRightDriveMotor,
                backRightDriveMotor);

        // Creating the PS4 controller, with an ID of 0.
        Joystick PS4 = new Joystick(0);

        // Removes pointless, annoying warnings/errors from the RioLog.
        mecanumDrive.setSafetyEnabled(false);

        // Actually creating the Thread.
        driveThread = new Thread(this, threadName);
        driveThread.start(); // Start the Thread.
    }

    // The stuff that is actually run while the Thread is running.
    public void run() {

        // While the Thread is alive, do stuff.
        while (driveThread.isAlive() == true) {

        }

    }

}