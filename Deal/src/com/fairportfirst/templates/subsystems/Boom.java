/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fairportfirst.templates.subsystems;

import com.fairportfirst.templates.RobotMap;
import com.fairportfirst.templates.commands.SetBoomSetpoint;
import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author deal
 * 
 * Based on: http://www.youtube.com/watch?v=j2Xz8bRRcF0&feature=plcp
 * 
 */
public class Boom extends PIDSubsystem {

    private static final double Kp = -2;
    private static final double Ki = 0.0;
    private static final double Kd = 0.0;
    
    public static final double EXTENDED = 5.0;
    public static final double RETRACTED = 0.0;

    private static double retractedPos = 0.0;
    private static double extendedPos = 0.0;

    SpeedController motor;
    AnalogChannel pot = new AnalogChannel(RobotMap.POTENTIOMETER_CHANNEL);
            
    // Initialize your subsystem here
    public Boom() {
        super("Boom", Kp, Ki, Kd);
        try {
            motor = new CANJaguar(RobotMap.WINCH_JAGUAR);
            // Use these to get going:
            // setSetpoint() -  Sets where the PID controller should move the system
            //                  to
            // enable() - Enables the PID controller.
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
        
        setInputRange(RETRACTED, EXTENDED);
        
        setSetpoint(RETRACTED);
        
        
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
        return pot.getAverageVoltage();
    }
    
    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
        motor.set(output);
    }
    
    public void setRetractedPos() {
        new SetBoomSetpoint(RETRACTED).start();
    }

    public void setExtendedPos() {
        new SetBoomSetpoint(EXTENDED).start();
    }

    public void extend() {
        //motor.set(1);
        
        this.setSetpoint(1);
    }

    public void retract() {
        //motor.set(-1);
        
        this.setSetpoint(-1);
    }

    public void doNothing() {
        //motor.set(0);
        
        this.setSetpoint(0);
    }
}
