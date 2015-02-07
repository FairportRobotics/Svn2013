package com.fairportfirst.frc2013.templates.commands;

/**
 *
 * @author alec
 */
public class ToggleSpinUpCommand extends CommandBase {
    
    /**
     * If the shooter is on turn it off, otherwise turn it on.
     */
    public ToggleSpinUpCommand() {
        requires(shooterSubsystem);
    }

    protected void initialize() {
        if(shooterSubsystem.getIsSpinningUp()) {
            //Need to turn off the shooter
            shooterSubsystem.turnShooterWheelsOff();
        } else {
            //Need to turn on the shooter
            new ShooterOnCommand().start();
//            new SpinUpShooterCommand().start();
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
