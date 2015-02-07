/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fairportfirst.gearsbot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 * @author deal
 * 
 * based on:  http://www.youtube.com/watch?v=5rJLi-QOflc
 * 
 */
public class SodaDelivery extends CommandGroup {
    
    
    public SodaDelivery() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
        addSequential(new PrepareToGrab());
        addSequential(new Grab());
        addSequential(new DriveToDistance(.11));
        addSequential(new PlaceSoda());
        addSequential(new DriveToDistance(.2));
        addSequential(new Stow());
    }
}
