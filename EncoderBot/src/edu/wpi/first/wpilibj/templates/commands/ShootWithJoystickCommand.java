
package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author bradmiller
 */
public class ShootWithJoystickCommand extends CommandBase {

    public ShootWithJoystickCommand() {
        // Use requires() here to declare subsystem dependencies
         requires(shooterSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        SmartDashboard.putNumber("Stick", oi.getShootJoystickValue());
        shooterSubsystem.setSetpointRPM((int)(oi.getShootJoystickValue()*4000.0));
        shooterSubsystem.shoot();
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
