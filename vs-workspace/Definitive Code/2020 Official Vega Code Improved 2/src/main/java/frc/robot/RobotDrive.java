/////////////////////////////////////////////////////////////////////
// File: RobotDrive.java
/////////////////////////////////////////////////////////////////////
//
// Purpose: Used for drive-related things for the robot.
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

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.drive.MecanumDrive;

class RobotDrive {

        // Used for being able to access Robot.java stuff in here.
        Robot robot = new Robot();

        // Creating the drive motors.
        CANSparkMax frontLeftDriveMotor = new CANSparkMax(robot.varsAndConsts.FRONT_LEFT_DRIVE_MOTOR_ID,
                        MotorType.kBrushless);
        CANSparkMax frontRightDriveMotor = new CANSparkMax(robot.varsAndConsts.FRONT_RIGHT_DRIVE_MOTOR_ID,
                        MotorType.kBrushless);
        CANSparkMax backLeftDriveMotor = new CANSparkMax(robot.varsAndConsts.BACK_LEFT_DRIVE_MOTOR_ID,
                        MotorType.kBrushless);
        CANSparkMax backRightDriveMotor = new CANSparkMax(robot.varsAndConsts.BACK_RIGHT_DRIVE_MOTOR_ID,
                        MotorType.kBrushless);

        // Creating an object of the MecanumDrive class.
        // This links the 4 drive motors together.
        MecanumDrive mecanumDrive = new MecanumDrive(frontLeftDriveMotor, backLeftDriveMotor, frontRightDriveMotor,
                        backRightDriveMotor);

        // Constructor.
        RobotDrive() {

                // Removes pointless, annoying warnings/errors from the RioLog.
                mecanumDrive.setSafetyEnabled(false);
        }

}