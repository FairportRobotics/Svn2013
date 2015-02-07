package com.fairportfirst.frc2013.templates.commands;

/**
 * Retracts the shooter feeder, to clear the shooter for the next disc.
 * @author Brian
 */
public class ShooterRetractFeederCommand extends CommandBase {
    // Retracts the feeder.
    public ShooterRetractFeederCommand() {
        requires(shooterSubsystem);
        setTimeout(0.01);
    }

    protected void initialize() {
        // Retracts the feeder.
        shooterSubsystem.setFeederOff();
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return isTimedOut();
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
