package com.fairportfirst.frc2013.templates.commands;

import com.fairportfirst.frc2013.templates.subsystems.ShooterSubsystem;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Loads a new disc into the shooter, by pushing the disc in, and retracting the feeder after.
 * @author Brian
 */
public class ShooterLoadCommand extends CommandGroup {
    
    /**
     * Extends the shooter feeder, waits the time in the shooterSubsystem 
     * seconds, and retracts the feeder.
     */
    public ShooterLoadCommand() {
        
        //push the disc into the shooter
        addSequential(new ShooterExtendFeederCommand(),ShooterSubsystem.getInstance().FEED_TIME);
        
        //wait for the feeder to push the disc in fully
        
        //pull the feeder back to make room for the next disc
        addSequential(new ShooterRetractFeederCommand());
        
        //Activate the PID again to increased 
        addSequential(new ShooterOnCommand());
    }
}
