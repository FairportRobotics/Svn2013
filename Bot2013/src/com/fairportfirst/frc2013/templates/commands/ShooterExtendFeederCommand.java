package com.fairportfirst.frc2013.templates.commands;

/**
 * Extends the shooter feeder, to load a disc into the shooter
 * @author Brian
 */
public class ShooterExtendFeederCommand extends CommandBase {
    // Extends feeder.
    public ShooterExtendFeederCommand() {
        requires(shooterSubsystem);
    }

    protected void initialize() {
        // Turns feeder on.
        shooterSubsystem.setFeederOn();
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
