package com.fairportfirst.templates.commands;

import com.fairportfirst.templates.subsystems.ShooterSubsystem;
import com.fairportfirst.templates.subsystems.SmartDashboardSubsystem;

/**
 * Command that sets the shooter speed.
 * @author !bradmiller
 */
public class ShooterSpeedCommand extends CommandBase {

    ShooterSubsystem shooterSubsystem;
    /**
     * the subsystem that controls the shooter
     */
    SmartDashboardSubsystem smartDashboardSubsystem;
    
    /**
     * Constructs ShooterSpeedCommand
     * 
     */
    public ShooterSpeedCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        
        // Declares shootSubsystem
        shooterSubsystem = ShooterSubsystem.getInstance();
        requires(shooterSubsystem);
        
        //Declares smartDashBoard
        smartDashboardSubsystem = SmartDashboardSubsystem.getInstance();
        requires(smartDashboardSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
       
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
