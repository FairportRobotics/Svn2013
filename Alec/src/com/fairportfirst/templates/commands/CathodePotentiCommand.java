/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fairportfirst.templates.commands;

import com.fairportfirst.templates.subsystems.CathodeSubsystem;
import com.fairportfirst.templates.subsystems.PotentiometerSubsystem;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author User
 */
public class CathodePotentiCommand extends CommandGroup {
//    PotentiometerSubsystem pot;
    public CathodePotentiCommand() {
        addSequential(new CathodeOnPotentiCommand());
        addSequential(new CathodeOffPotentiCommand());
    }
    
    public void execute() {
//        
//        
    }
}
