/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fairportfirst.templates.commands;

import com.fairportfirst.templates.subsystems.CathodeSubsystem;

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
