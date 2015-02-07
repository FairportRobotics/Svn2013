package com.fairportfirst.frc2013.templates.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * @author alec
 */
public class CameraAlignCommand extends CommandBase {
    
    private boolean isAligned = false;
    private final double PERCENT_TOLERANCE = 0.05;
    
    /**
    * This class uses the Camera and the drive subsystems.
    */
    public CameraAlignCommand() {
        requires(cameraSubsystem);
        requires(driveSubsystem);
    }

    protected void initialize() {
        
    }
    
    /**
     * Scans all the targets, finds the pyramid, and rotates the robot so it ends
     * up in the middle of the vertex.
     */
    protected void execute() {
        cameraSubsystem.identifyTargets();
        double[] motorValues = cameraSubsystem.getMotorValuesForPyramidAlignment();
        driveSubsystem.shiftLowSpeed();
        driveSubsystem.drive(motorValues[0], motorValues[1]);
        isAligned = isWithinTolerance(motorValues[0]) && isWithinTolerance(motorValues[1]);
        SmartDashboard.putBoolean("Aligned with Pyramid", isAligned);
    }

    protected boolean isFinished() {
        return isAligned;
    }
    
    private boolean isWithinTolerance(double turnSpeed) {
        return Math.abs(turnSpeed) < PERCENT_TOLERANCE;
    }

    protected void end() {
        
    }

    protected void interrupted() {
        
    }
}