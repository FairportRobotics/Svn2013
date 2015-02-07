/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fairportfirst.gearsbot.subsystems;

import com.fairportfirst.gearsbot.RobotMap;
import com.fairportfirst.gearsbot.commands.DriveWithJoysticks;
import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 * @author deal
 */
public class DriveTrain extends PIDSubsystem {

    private static final double Kp = 3;
    private static final double Ki = 0.2;
    private static final double Kd = 0.0;

    private RobotDrive drive = new RobotDrive(RobotMap.leftMotor, RobotMap.rightMotor);
    private AnalogChannel rangeFinder = new AnalogChannel(RobotMap.rangeFinder);
            
    // Initialize your subsystem here
    public DriveTrain() {
        super("DriveTrain", Kp, Ki, Kd);

        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
        setDefaultCommand(new DriveWithJoysticks());
    }
    
    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
        return rangeFinder.getVoltage();
    }
    
    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
        tankDrive(output, output);
    }
    
    public void tankDrive(double left, double right) {
        drive.tankDrive(left, right);
    }
}
