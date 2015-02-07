/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fairportfirst.frc2013.templates.commands;

import com.fairportfirst.frc2013.templates.subsystems.ShooterSubsystem;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 * @author User
 */
public class ShootSequenceForPyramid extends CommandGroup {
    
    /**
     * Fires 3 discs via the ShootOnPyramidCommand.
     */
    public ShootSequenceForPyramid() {
        
        for(int shootTimes=0; shootTimes<3;shootTimes++){
            //Turn on the shooter, wait until up to speed.
            addSequential(new ShootOnPyramidCommand());
            // Retracts the feeder.
            addSequential(new ShooterRetractFeederCommand(),ShooterSubsystem.getInstance().FEED_TIME);
            // Extends the feeder for 0.4 seconds.
            addSequential(new ShooterExtendFeederCommand());
        }
        // Turns the shooter off.
        addSequential(new ShooterOffCommand());
    }
}
