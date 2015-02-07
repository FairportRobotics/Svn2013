package com.fairportfirst.frc2013.templates.commands;

import com.fairportfirst.frc2013.templates.OI;

/**
 * Toggles the shift state; high changes to low, low changes to high
 * High is high speed but little Hourse Power.
 * Low is low speed but High Hourse Power.  
 * @author tyler
 */
public class ShiftCheckerCommand extends CommandBase {
    public ShiftCheckerCommand() {
        requires(driveSubsystem);
    }
    
    protected void initialize() {
        //  Sets a high or slow shifter to the opposite.
        if (driveSubsystem.IsHighSpeed()) {
            new ShiftLowSpeedCommand().start();
        } else {
            new ShiftHighSpeedCommand().start();
        }
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
