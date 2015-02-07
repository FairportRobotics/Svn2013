/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fairportfirst.templates.subsystems;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.fairportfirst.templates.RobotMap;

/**
 *
 * @author Stefen
 */
public class ShooterSubsystem extends Subsystem
{
    private Jaguar mrJaguar;    
    private static ShooterSubsystem instance;
    
    /**
     * 
     * Construct new Shooter subsystem
     * 
     * Holds shooter subsystem. 
     */
    
    public ShooterSubsystem()
    {
        mrJaguar = new Jaguar(RobotMap.SHOOTER_JAGUAR_MODULE,RobotMap.SHOOTER_JAGUAR_CHANNEL);
        LiveWindow.addActuator("Shooter", "Shoot Speed", mrJaguar);
    }

    protected void initDefaultCommand() {
        
    }
    
    /**
     * Set jaguar speed.
     * 
     * @param speed Speed for jaguar.
     */
    public void setSpeed(double speed)
    {
        mrJaguar.set(speed);
    }
    
    /**
     * Get current speed of jaguar.
     * 
     * @return Returns current speed of jaguar.
     */
    public double getSpeed()
    {
        return mrJaguar.getSpeed();
    }
    
    /**
     * 
     * Get instance of Shooter subsystem. This also null checks.
     * 
     * @return Returns instance of ShooterSubsystem.
     */
    public static ShooterSubsystem getInstance()
    {
        if(instance == null)
        {
            instance = new ShooterSubsystem();
        }
        
        return instance;
    }
}
