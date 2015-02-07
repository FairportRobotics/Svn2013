package com.fairportfirst.frc2013.templates.commands;

/**
 *
 * @author alec
 */
public class SpinUpShooterCommand extends CommandBase {
    
    /**
     * Spins the wheels up to speed. Should be PID but is not currently.
     */
    public SpinUpShooterCommand() {
        requires(shooterSubsystem);
    }

    protected void initialize() {
    }

    protected void execute() {
        shooterSubsystem.spinWheelToSpeed();
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
