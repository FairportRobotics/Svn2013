package com.fairportfirst.frc2013.templates.commands;

/**
 * @author tyler
 */
public class RetractBoomCommand extends CommandBase {
    public RetractBoomCommand() {
        requires(climberSubsystem);
    }
    
    protected void initialize() {
        // Retracts the boom.
        climberSubsystem.RetractBoom();
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
