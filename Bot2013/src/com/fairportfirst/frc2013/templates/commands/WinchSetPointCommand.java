package com.fairportfirst.frc2013.templates.commands;

/**
 * @author tyler
 */
public class WinchSetPointCommand extends CommandBase {
    
    private double setPoint;
    
    // Sets the winch's setpoint.
    public WinchSetPointCommand(double setPoint) {
        requires(winchSubsystem);
        this.setPoint = setPoint;
    }

    protected void initialize() {
        // Sets the winch's setpoint.
        winchSubsystem.setSetpoint(setPoint);
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return winchSubsystem.atSetPoint();
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
