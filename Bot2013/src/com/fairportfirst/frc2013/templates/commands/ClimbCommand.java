package com.fairportfirst.frc2013.templates.commands;

import com.fairportfirst.frc2013.templates.subsystems.WinchSubsystem;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * @author Alec
 */
public class ClimbCommand extends CommandGroup {
    
    public ClimbCommand() {
        // Sets the winch setpoint.
        addParallel(new WinchExtendCommand());
        // Retracts the climber.
        addParallel(new RetractBoomCommand());
    }
}
