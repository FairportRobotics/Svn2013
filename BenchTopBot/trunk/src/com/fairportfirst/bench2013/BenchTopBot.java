/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.fairportfirst.bench2013;


import com.fairportfirst.bench2013.components.CompressorUser;
import com.fairportfirst.bench2013.components.CylinderUser;
import com.fairportfirst.bench2013.components.RelayUser;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.smartdashboard.SendableGyro;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class BenchTopBot extends IterativeRobot
{
    Joystick mrJoystick;
    double mrGyroAngle = 0;//this make a ne variable that stores gyro angle
//    int pulsCount = 0;
    //RelayUser relayUsr = new RelayUser(1);
    CompressorUser compressor;
    CylinderUser mrCylinder;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit()
    {
        compressor = new CompressorUser(1,1);
	mrJoystick = new Joystick(1);
        mrCylinder = new CylinderUser(2,3,2);
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic()
    {

    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic()
    {
        mrCylinder.joyStickOperate(mrJoystick);
        //relayUsr.useWithJoyStick(mrJoystick);
//        mrGyroAngle = (10000*mrGyro.getAngle());
//        if(pulsCount>20*Math.abs(mrGyroAngle))
//	{
//            mrRelay.set(Relay.Value.kOn);// turn on light
//            mrRelay.setDirection(Relay.Direction.kForward);
//            if(pulsCount>25*Math.abs(mrGyroAngle))
//            {
//                pulsCount = 0;//set pulse count = 0
//            }
//	}
//	else
//	{
//            mrRelay.set(Relay.Value.kOff);
//            mrRelay.setDirection(Relay.Direction.kReverse);
//	}
//        System.out.println("reg = : " + mrJoystick.getY());
//        pulsCount++;
//        System.out.println("Abs of gyro * 20: " + (20*Math.abs(mrGyroAngle)));
//        System.out.println("GYRO ANGLE: " + mrGyroAngle + " p: " + pulsCount);
    }

}
