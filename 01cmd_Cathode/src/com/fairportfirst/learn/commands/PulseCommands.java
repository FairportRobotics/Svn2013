/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fairportfirst.learn.commands;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.command.CommandGroup;
import com.fairportfirst.learn.OI;
/**
 *
 * @author team578
 */
public class PulseCommands extends CommandGroup {
    
    public PulseCommands() {
        double timeSpeed = 0.1;//OI.getJoystick().getMagnitude();
        System.out.println(timeSpeed);
        addSequential(new CathodeOnCommand(), timeSpeed);
        addSequential(new CathodeOffCommand(), timeSpeed);
        
    }
    
}
