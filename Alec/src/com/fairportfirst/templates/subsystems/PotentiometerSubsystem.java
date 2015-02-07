/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fairportfirst.templates.subsystems;

import com.fairportfirst.templates.RobotMap;
import com.fairportfirst.templates.commands.CathodePotentiCommand;
import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author User
 */
public class PotentiometerSubsystem extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    private AnalogChannel potenti;
    private static PotentiometerSubsystem instance = null;
    
    public PotentiometerSubsystem() {
        potenti = new AnalogChannel(RobotMap.POTENTIOMETER_CHANNEL);
        SmartDashboard.putData("Cathode", new CathodePotentiCommand());
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
//        setDefaultCommand(new CathodePotentiCommand());
    }
    
    public double getValue() {
        SmartDashboard.putNumber("potentiometer", potenti.getVoltage());
        return potenti.getVoltage();
    }
    
    public static PotentiometerSubsystem getInstance() {
        if(instance == null) {
            instance = new PotentiometerSubsystem();
        }
        return instance;
    }
}
