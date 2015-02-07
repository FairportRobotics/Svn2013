package com.fairportfirst.frc2013.templates.commands;

/**
 * 
 * @author User
 */
public class DriveBackwardCommand extends CommandBase {
    // Speed the robot drives at in autonomus.
    private final double DRIVE_SPEED  = -0.6;
    
    public DriveBackwardCommand() {
        requires(driveSubsystem);
    }

    protected void initialize() {
    }
    
    protected void execute() {
        // Drive the robot at the specified speed. 
        driveSubsystem.drive(DRIVE_SPEED, DRIVE_SPEED);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
