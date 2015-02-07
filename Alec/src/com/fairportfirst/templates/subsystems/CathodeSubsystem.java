/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fairportfirst.templates.subsystems;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;
import com.fairportfirst.templates.RobotMap;
import com.fairportfirst.templates.commands.CathodeOffCommand;
import com.fairportfirst.templates.commands.CathodeOffPotentiCommand;
import com.fairportfirst.templates.commands.CathodeOnPotentiCommand;
import com.fairportfirst.templates.commands.CathodePotentiCommand;

/**
 *
 * @author Brian
 */
public class CathodeSubsystem extends Subsystem{

    private static CathodeSubsystem instance;
    private Relay relay;
    
    public CathodeSubsystem() {
        relay = new Relay(RobotMap.CATHODE_RELAY_CHANNEL);
    }
    
    protected void initDefaultCommand() {
        setDefaultCommand(new CathodeOffCommand());
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
        relay.setDirection(Relay.Direction.kForward);
    }
    
    public void off(){
        relay.set(Relay.Value.kOff);
              relay.setDirection(Relay.Direction.kReverse);
    }
    
}
