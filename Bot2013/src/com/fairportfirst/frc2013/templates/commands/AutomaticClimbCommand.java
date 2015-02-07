package com.fairportfirst.frc2013.templates.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * @author Alec
 */
public class AutomaticClimbCommand extends CommandGroup {
    public AutomaticClimbCommand() {
        
        /**
         * Start the sequence of events for climbing.
         */
        addSequential(new ShooterThreePointPositionCommand());
        
        addSequential(new GrabRungCommand());
        addSequential(new ClimbCommand());
        
        addSequential(new ShootOnPyramidCommand());
        
        addSequential(new GrabRungCommand());
        addSequential(new ClimbCommand());
        
        addSequential(new GrabRungCommand());
        addSequential(new ClimbCommand());
    }
}
