/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fairportfirst.frc2013.templates.commands;

/**
 *
 * @author tyler
 */
public class ToggleShooterBackCommand extends CommandBase {
    
    public ToggleShooterBackCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(shooterSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        if(shooterSubsystem.isBackShooterOn()){
            shooterSubsystem.setShootSpeedBack(0.0);
        }else{
            shooterSubsystem.setShootSpeedBack(shooterSubsystem.getWantedShootSpeedRear());
        }
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
