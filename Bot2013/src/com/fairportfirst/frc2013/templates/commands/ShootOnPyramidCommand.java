/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fairportfirst.frc2013.templates.commands;

/**
 *
 * @author User
 */
public class ShootOnPyramidCommand extends CommandBase {
    
    public ShootOnPyramidCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(shooterSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        shooterSubsystem.setShootSetPointFront(shooterSubsystem.PYRAMID_SHOOT_RPM);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        shooterSubsystem.spinWheelToSpeed();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return shooterSubsystem.isUpToShootSpeed();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
