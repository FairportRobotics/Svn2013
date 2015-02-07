/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.templates.OI;
/**
 *
 * @author team578
 */
public class PulseCommands extends CommandGroup {
    
    public PulseCommands() {
        double timeSpeed = 0.1;
        addSequential(new CathodeOnCommand(), timeSpeed);
        addSequential(new CathodeOffCommand(), timeSpeed);
    }
    
    protected void execute() {
        
    }
}
