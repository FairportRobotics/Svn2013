package com.fairportfirst.frc2013.templates.commands;

import com.fairportfirst.frc2013.templates.subsystems.ClimberSubsystem;
import com.fairportfirst.frc2013.templates.subsystems.WinchSubsystem;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * @author Alec & Stefen
 */
public class GrabRungCommand extends CommandGroup {
    public GrabRungCommand() {
        //Checks to make sure we have not hit the limit switch
        if(ClimberSubsystem.getInstance().getRetractLimitSwitchHit()) {
            // Extends the winch setpoint to the extended value.
            addParallel(new WinchRetractCommand());
            // Extends the boom.
            addParallel(new ExtendBoomCommand());
        }
    }
}
