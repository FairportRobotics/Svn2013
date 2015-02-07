package com.fairportfirst.frc2013.templates.commands;

/**
 *
 * @author team578
 */
public class ShooterThreePointPositionCommand extends CommandBase {
    
    public ShooterThreePointPositionCommand() {
        requires(shooterSubsystem);
    }

    protected void initialize() {
        shooterSubsystem.shootPositionThreePoint();
        shooterSubsystem.setShootSetPointBoth(shooterSubsystem.THREE_POINT_SHOOT_RPM);
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
