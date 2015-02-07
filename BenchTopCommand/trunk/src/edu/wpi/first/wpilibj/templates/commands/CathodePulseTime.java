/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 * @author Alec
 */
public class CathodePulseTime extends CommandGroup {
    
    public CathodePulseTime() {
        addSequential(new CathodeOffTimeCommand());
        System.out.println("Cathod timer on!");
        addSequential(new CathodeOnTimeCommand());
        System.out.println("Cathid timer off!");         
    }
    
    protected void execute() {
        
    }
    
    
}
