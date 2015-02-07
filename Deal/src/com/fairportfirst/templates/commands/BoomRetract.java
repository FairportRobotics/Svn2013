/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fairportfirst.templates.commands;

/**
 *
 * @author deal
 */
public class BoomRetract extends CommandBase {
    
    public BoomRetract() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(boom);
        setTimeout(0.8);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        boom.setRetractedPos();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (boom.getPosition() - boom.getSetpoint()) < 0.08;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

        // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
