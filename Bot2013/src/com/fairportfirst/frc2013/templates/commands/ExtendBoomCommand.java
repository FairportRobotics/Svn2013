package com.fairportfirst.frc2013.templates.commands;

/**
 *
 * @author Tyler
 */
public class ExtendBoomCommand extends CommandBase {
    
    
    public ExtendBoomCommand() {
        requires(climberSubsystem);
    }

    protected void initialize() {
        // Extends the boom
        climberSubsystem.ExtendBoom();
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
