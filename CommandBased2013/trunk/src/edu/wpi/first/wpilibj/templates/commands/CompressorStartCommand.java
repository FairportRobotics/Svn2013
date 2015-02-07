/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.templates.subsystems.CompressorSubsystem;
import edu.wpi.first.wpilibj.templates.subsystems.SmartDashboardSubsystem;

/**
 *
 * @author Brian
 */
public class CompressorStartCommand extends CommandBase{
    
    CompressorSubsystem compressorSubsystem;
    SmartDashboardSubsystem smartDashboardSubsystem;

    public CompressorStartCommand(){
         compressorSubsystem = CompressorSubsystem.getInstance();
        requires(compressorSubsystem);
        
        smartDashboardSubsystem = SmartDashboardSubsystem.getInstance();
        requires(smartDashboardSubsystem);
    }
    
    protected void initialize() {
    }

    protected void execute() {
        compressorSubsystem.start();
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}
