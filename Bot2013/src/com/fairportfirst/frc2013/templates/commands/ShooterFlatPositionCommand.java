package com.fairportfirst.frc2013.templates.commands;

/**
 * @author Tyler
 */
public class ShooterFlatPositionCommand extends CommandBase {
    // Lowers the shooter.
    public ShooterFlatPositionCommand() {
        requires(shooterSubsystem);
    }

    protected void initialize() {
        // Sets the elevation to off.
        shooterSubsystem.setElevatorLowerOff();
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
