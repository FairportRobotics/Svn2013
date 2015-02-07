package com.fairportfirst.frc2013.templates.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 * @author User
 */
public class AutonomousShootCommand extends CommandGroup {
    
    public AutonomousShootCommand() {
        // Runs the shooter command. Sponsered by Captain Obvious.®
//        addSequential(new ShootSequenceCommand());
//        addSequential(new SpinUpShooterCommand());
        addSequential(new WaitCommand(CommandBase.getWaitBeforeShootTime()));
        addSequential(new ShooterThreePointPositionCommand());
        addSequential(new ShooterOnCommand());
        addSequential(new WaitCommand(5.2));
        for(int q = 0; q < 3; q++) {
            addSequential(new ShooterLoadCommand());
            addSequential(new WaitCommand(1.0));
        }
        addSequential(new ShooterOffCommand());
        addSequential(new ShooterFlatPositionCommand());
    }
}
