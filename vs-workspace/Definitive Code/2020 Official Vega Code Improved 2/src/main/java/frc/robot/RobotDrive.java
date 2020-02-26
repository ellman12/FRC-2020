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

class RobotDrive {

        // Constructor.
        RobotDrive() {

                // Creating the drive motors.
                CANSparkMax frontLeftDriveMotor = new CANSparkMax(VarsAndConsts.FRONT_LEFT_DRIVE_MOTOR_ID,
                                MotorType.kBrushless);
                CANSparkMax frontRightDriveMotor = new CANSparkMax(VarsAndConsts.FRONT_RIGHT_DRIVE_MOTOR_ID,
                                MotorType.kBrushless);
                CANSparkMax backLeftDriveMotor = new CANSparkMax(VarsAndConsts.BACK_LEFT_DRIVE_MOTOR_ID,
                                MotorType.kBrushless);
                CANSparkMax backRightDriveMotor = new CANSparkMax(VarsAndConsts.BACK_RIGHT_DRIVE_MOTOR_ID,
                                MotorType.kBrushless);

        }
}