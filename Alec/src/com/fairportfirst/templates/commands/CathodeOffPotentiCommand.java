/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fairportfirst.templates.commands;

import com.fairportfirst.templates.OI;
import com.fairportfirst.templates.commands.CommandBase;

/**
 *
 * @author User
 */
public class CathodeOffPotentiCommand extends CommandBase {
    
    
    public CathodeOffPotentiCommand() {
        requires(cathodeSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        setTimeout(OI.getPotentiValue());
        cathodeSubsystem.on();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
        
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
