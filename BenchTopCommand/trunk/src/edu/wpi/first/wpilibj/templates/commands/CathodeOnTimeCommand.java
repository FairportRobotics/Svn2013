/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.templates.OI;
import edu.wpi.first.wpilibj.templates.subsystems.CathodeSubsystem;

/**
 *
 * @author User
 */
public class CathodeOnTimeCommand extends CommandBase {
    
    private CathodeSubsystem cathodeSys;
    
    public CathodeOnTimeCommand() {
        cathodeSys = CathodeSubsystem.getInstance();
        requires(cathodeSys);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        setTimeout(OI.getJoystick());
        cathodeSys.turnOn();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
        
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}