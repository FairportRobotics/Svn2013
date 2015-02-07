package com.fairportfirst.frc2013.templates.commands;

import com.fairportfirst.frc2013.templates.subsystems.ShooterSubsystem;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * @author alec
 */
public class ShootSequenceCommand extends CommandGroup {
    /**
     * Activates the shooter wheels, and waits for them to get to speed
     * Then, pushes a disc into the shooter, and retracts the feeder
     * This shoots the disc, and the feeder is retracted.
     */
    public ShootSequenceCommand() {
        
        // Turns on the shooter untill up to speed
        addSequential(new ShooterOnCommand());
        // Wait for one second.
        addSequential(new WaitCommand(2.0));
        // Extends the feeder for 0.4 seconds.
        addSequential(new ShooterExtendFeederCommand(),ShooterSubsystem.getInstance().FEED_TIME);
        // Retracts the feeder.
        addSequential(new ShooterRetractFeederCommand());
        // Turns the shooter off.
        addSequential(new ShooterOffCommand());
    }
}
