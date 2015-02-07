package com.fairportfirst.frc2013.templates.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Turns on the shooter wheels, to fire the loaded disc
 * @author Brian & Yaro
 */
public class ShooterOnCommand extends CommandBase {
    // Turns the shooter on.
    public ShooterOnCommand() {
        requires(shooterSubsystem);
    }

    protected void initialize() {
        // Turns the shooter on.
        shooterSubsystem.setSpeed(shooterSubsystem.getWantedShootSpeedFront(),shooterSubsystem.getWantedShootSpeedRear());          
    }

    protected void execute() {
//        shooterSubsystem.spinWheelToSpeed();
    }

    protected boolean isFinished() {
        return true;//shooterSubsystem.isUpToShootSpeed();
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
