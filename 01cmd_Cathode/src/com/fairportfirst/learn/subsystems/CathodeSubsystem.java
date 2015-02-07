
package com.fairportfirst.learn.subsystems;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;
import com.fairportfirst.learn.RobotMap;
import com.fairportfirst.learn.commands.CathodeOffCommand;
import com.fairportfirst.learn.commands.CathodeOnCommand;

/**
 *
 */
public class CathodeSubsystem extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    private static CathodeSubsystem instance = null;
    private Relay cathodeRelay;

    public CathodeSubsystem() {
        cathodeRelay = new Relay(RobotMap.digitalIOModuleChannel,RobotMap.relayChannel);
    }
    
    public static CathodeSubsystem getInstance() {
        if(instance == null) {
            instance = new CathodeSubsystem();
        }
        return instance;
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new CathodeOffCommand());
    }
    
    public void turnOn() {
        cathodeRelay.set(Relay.Value.kOn);
        cathodeRelay.setDirection(Relay.Direction.kForward);
    }
    
    public void turnOff() {
        cathodeRelay.set(Relay.Value.kOff);
        cathodeRelay.setDirection(Relay.Direction.kReverse);
    }
}

