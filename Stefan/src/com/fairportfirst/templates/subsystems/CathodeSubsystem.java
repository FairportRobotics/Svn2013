/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fairportfirst.templates.subsystems;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;
import com.fairportfirst.templates.RobotMap;

/**
 *
 * @author Brian
 */
public class CathodeSubsystem extends Subsystem{

    private static CathodeSubsystem instance;
    private Relay relay = new Relay(RobotMap.CATHODE_RELAY_CHANNEL);
    
    protected void initDefaultCommand() {
    }
    
    public static CathodeSubsystem getInstance(){
        if (instance == null)
        {
            instance = new CathodeSubsystem();
        }
        return instance;
    }
    
    public void on(){
        relay.set(Relay.Value.kOn);
    }
    
    public void off(){
        relay.set(Relay.Value.kOff);
    }
    
}
