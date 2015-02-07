/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fairportfirst.templates.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 * @author Stefen
 */

public class SmartDashboardSubsystem extends Subsystem
{
    private static SmartDashboardSubsystem instance;
    
    public SmartDashboardSubsystem()
    {
        
    }
    
    protected void initDefaultCommand() {

    }
    
    /**
     * 
     * @return Returns instance of SmartDashboard subsystem.
     */
    public static SmartDashboardSubsystem getInstance()
    {
        if(instance == null)
        {
            instance = new SmartDashboardSubsystem();
        }
        return instance;
    }
}
