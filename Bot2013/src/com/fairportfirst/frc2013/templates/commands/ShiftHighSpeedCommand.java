package com.fairportfirst.frc2013.templates.commands;

/**
 * Shifts the drive to high-speed, low-torque mode
 * @author tyler
 */
public class ShiftHighSpeedCommand extends CommandBase {
    // Shifts to high speed.
    public ShiftHighSpeedCommand() {
        requires(driveSubsystem);
        setTimeout(0.001);
    }

    protected void initialize() {
        //Shifts to high speed.
        driveSubsystem.shiftHighSpeed();
    }

    protected void execute() {
        
    }

    protected boolean isFinished() {
        return this.isTimedOut();
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
