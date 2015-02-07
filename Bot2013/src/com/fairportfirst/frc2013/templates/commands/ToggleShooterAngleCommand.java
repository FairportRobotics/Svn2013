package com.fairportfirst.frc2013.templates.commands;

/**
 *
 * @author User
 */
public class ToggleShooterAngleCommand extends CommandBase {
    
    private boolean isAngledLow = true;
    
    public ToggleShooterAngleCommand() {

    }

    protected void initialize() {
        if(isAngledLow){
            new ShooterThreePointPositionCommand().start();
        }else if(!isAngledLow){
            new ShooterFlatPositionCommand().start();
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
