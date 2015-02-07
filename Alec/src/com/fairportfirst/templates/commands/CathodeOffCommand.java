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
public class CathodeOffCommand extends CommandBase{
    
    CathodeSubsystem cathodeSubsystem;

    protected void initialize() {
        cathodeSubsystem = CathodeSubsystem.getInstance();
        requires(cathodeSubsystem);
    }

    protected void execute() {
        cathodeSubsystem.off();
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}
