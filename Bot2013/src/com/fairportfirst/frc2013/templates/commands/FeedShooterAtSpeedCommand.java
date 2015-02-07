package com.fairportfirst.frc2013.templates.commands;

/**
 *
 * @author team578
 */
public class FeedShooterAtSpeedCommand extends CommandBase {
    
    public FeedShooterAtSpeedCommand() {
        requires(shooterSubsystem);
    }

    protected void initialize() {
        if(shooterSubsystem.isUpToShootSpeed()) {
            //Shoot a frisbee
            new ShooterLoadCommand().start();
        } else if(shooterSubsystem.getIsSpinningUp() &&(shooterSubsystem.isFrontEncoderDisabled() || shooterSubsystem.isRearEncoderDisabled())) {
            //Shoot a frisbee, one encoder is broken
            new ShooterLoadCommand().start();
        }
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
        
    }
}
