package com.fairportfirst.frc2013.templates.commands;

/**
 * Turns off the shooter wheels
 * @author Brian
 */
public class ShooterOffCommand extends CommandBase {
    // Turns the shooter off.
    public ShooterOffCommand() {
        requires(shooterSubsystem);
    }

    protected void initialize() {
        // Turns off the shooter.
        shooterSubsystem.turnShooterWheelsOff();
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
