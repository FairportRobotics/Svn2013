package com.fairportfirst.frc2013.templates.commands;

/**
 *
 * @author Ebony "Enoby" Dark'ness Dementia Raven Way
 */
public class WinchExtendCommand extends CommandBase {
    
    public WinchExtendCommand() {
        requires(newWinchSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        newWinchSubsystem.setWinchSpeed(driveSubsystem.getWinchExtendSpeed());
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
