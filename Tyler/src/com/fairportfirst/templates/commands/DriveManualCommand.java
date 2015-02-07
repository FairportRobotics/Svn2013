/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fairportfirst.templates.commands;

import com.fairportfirst.templates.OI;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author team578
 */
public class DriveManualCommand extends CommandBase {
    
    public DriveManualCommand() {
        requires(driveSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        
        if(SmartDashboard.getBoolean("Optimized Drive")){
            driveSubsystem.drive(OI.getLeftSimboticsDrive(), OI.getRightSimboticsDrive());
        }else{
            driveSubsystem.drive(OI.getLeftDrive(), OI.getRightDrive());
        }
        
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
