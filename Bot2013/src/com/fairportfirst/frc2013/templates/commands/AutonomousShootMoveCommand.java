package com.fairportfirst.frc2013.templates.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * @author Alec
 */
public class AutonomousShootMoveCommand extends CommandGroup {
    
    public AutonomousShootMoveCommand() {
        //Shoots the frisbee
        addSequential(new AutonomousShootCommand());
        //Drive backward for 1.3 seconds
        addSequential(new DriveBackwardCommand(), 1.3);
    }
}
