/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fairportfirst.templates.subsystems;

import com.fairportfirst.templates.RobotMap;
import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author User
 */
public class PotentiometerSubsystem extends PIDSubsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    private AnalogChannel pot;
    private CANJaguar winch;
    private static PotentiometerSubsystem instance = null;
    
    private static final double MIN_POINT = 1;
    private static final double MAX_POINT = 5;
    private static final double SET_POINT = 3;
    
    /**
     *
     */
    public PotentiometerSubsystem() {
        super(2.3,0,0);//I hope this works...
        
        pot = new AnalogChannel(RobotMap.POTENTIOMETER_CHANNEL);
        
        setInputRange(MIN_POINT, MAX_POINT);
        
        setSetpoint(SET_POINT);
        
        try {
            winch = new CANJaguar(13);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }

    
    protected void initDefaultCommand() {
    }
    
    public void enableWinch(){
        enable();
    }
    
    protected double returnPIDInput() {
        SmartDashboard.putNumber("potentiometer", pot.getVoltage());
        return pot.getAverageVoltage();
    }

    protected void usePIDOutput(double d) {
        try {
            winch.setX(d);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }
    
    public boolean atSetPoint(){
        return Math.abs(getPosition() - getSetpoint())<.1;
    }
    
    public static PotentiometerSubsystem getInstance() {
        if(instance == null) {
            instance = new PotentiometerSubsystem();
        }
        return instance;
    }
}
