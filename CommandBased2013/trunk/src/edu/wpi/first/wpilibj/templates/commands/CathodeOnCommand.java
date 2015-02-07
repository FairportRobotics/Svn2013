/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.templates.subsystems.CathodeSubsystem;

/**
 *
 * @author Brian
 */
public class CathodeOnCommand extends CommandBase{
    
    CathodeSubsystem cathodeSubsystem;

    protected void initialize() {
        cathodeSubsystem = CathodeSubsystem.getInstance();
        requires(cathodeSubsystem);
    }

    protected void execute() {
        cathodeSubsystem.on();
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}
