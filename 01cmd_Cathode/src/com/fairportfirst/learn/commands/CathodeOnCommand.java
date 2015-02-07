
package com.fairportfirst.learn.commands;

import com.fairportfirst.learn.subsystems.CathodeSubsystem;

/**
 *
 * @author bradmiller
 */
public class CathodeOnCommand extends CommandBase {

    private CathodeSubsystem cathodeSys;
    
    public CathodeOnCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        cathodeSys = CathodeSubsystem.getInstance();
        requires(cathodeSys);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        cathodeSys.turnOn();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
