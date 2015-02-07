package com.fairportfirst.frc2013.templates.commands;

/**
 * Shifts the drive to low-speed, high-torque mode
 * @author tyler
 */
public class ShiftLowSpeedCommand extends CommandBase {
    // Shifts to low speed.
    public ShiftLowSpeedCommand() {
        requires(driveSubsystem);
        setTimeout(0.01);
    }

    protected void initialize() {
        // Shifts to low speed.
        driveSubsystem.shiftLowSpeed();
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
