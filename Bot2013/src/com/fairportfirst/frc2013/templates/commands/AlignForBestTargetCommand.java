package com.fairportfirst.frc2013.templates.commands;

/**
 * @author alec
 */
public class AlignForBestTargetCommand extends CommandBase {
    
    /**
     * This class uses the Camera and the drive subsystems.
     */
    public AlignForBestTargetCommand() {
        requires(cameraSubsystem);
        requires(driveSubsystem);
    }

    protected void initialize() {
        
    }

    /**
     * Runs continously. Gets the best possible target(as index) and rotates the 
     * robot toward the target.
     */
    protected void execute() {
        cameraSubsystem.identifyTargets();
        int index = cameraSubsystem.getBestTargetIndex();
        if(index!=-1) {
            double[] motorValue  = cameraSubsystem.getMotorValueForTarget(index);
            driveSubsystem.drive(motorValue[0], motorValue[0]);
        }
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
        
    }
}
