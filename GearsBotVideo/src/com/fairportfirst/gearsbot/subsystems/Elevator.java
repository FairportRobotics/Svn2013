/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fairportfirst.gearsbot.subsystems;

import com.fairportfirst.gearsbot.RobotMap;
import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 * @author deal
 * 
 * Based on: http://www.youtube.com/watch?v=j2Xz8bRRcF0&feature=plcp
 * 
 */
public class Elevator extends PIDSubsystem {

    private static final double Kp = 6;
    private static final double Ki = 0.0;
    private static final double Kd = 0.0;

    public final static double BOTTOM = 4.6;
    public final static double STOW = 1.45;
    public final static double TABLE_HEIGHT = 1.58;
            
    SpeedController motor = new Jaguar(RobotMap.elevatorMotor);
    AnalogChannel pot = new AnalogChannel(RobotMap.elevatorPot);    
    
    // Initialize your subsystem here
    public Elevator() {
        super("Elevator", Kp, Ki, Kd);

        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
        setSetpoint(STOW);
        enable();
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
        return pot.getVoltage();
    }
    
    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
        motor.set(output);
    }
}
