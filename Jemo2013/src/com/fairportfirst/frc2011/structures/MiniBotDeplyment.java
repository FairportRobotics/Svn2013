package com.fairportfirst.frc2011.structures;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;

/**
 * Shows what the status of the minibot deployment is on the dashboard
 * @author Alec
 */
public class MiniBotDeplyment 
{
    private Pneumatics pneumatics;
    private PneumaticCylinder miniPiston;
    private int loopInt = 0;
    private double time;
    private Joystick towerStick;
    private boolean hasPunched;
    private boolean isReady;
    private double deployTime;
    public MiniBotDeplyment(int solenoidFireChannel,int solenoidReleaseChannel,Pneumatics pneu, double startTimer, Joystick joy)
    {         
        time= startTimer;
        pneumatics =pneu;
        miniPiston = new PneumaticCylinder( solenoidFireChannel, solenoidReleaseChannel, false);
        towerStick = joy;
        isReady = false;
        hasPunched = false;
    }
    /**
     * Tells whether the minibot is ready to deploy or not
     * @return - True if minibot is ready
     */
    public boolean isReady()
    {
        return isReady;
    }
    /**
     * Automatically deploys the minibot after teleop if it hasn't already been deployed
     */
    public void autoDeployMinibot(double newTime)
    {
        time = newTime;
        if(towerStick.getRawButton(11))
            isReady=true;
        if(towerStick.getRawButton(12))
            isReady=false;
        if(time>110&&isReady&&!hasPunched)
//        if(time>20&&isReady&&!hasPunched)
        {
            pneumatics.extend(miniPiston);
            hasPunched = true;
            deployTime= time;
        }

//        if(hasPunched)
//            loopInt ++;

//        if(time>deployTime+2.9&&time<deployTime+3.1&&isReady&&hasPunched)
//        {
//             pneumatics.retract(miniPiston);
//        }
    }
    /**
     * Allows the minibot to be manually deployed
     */
    public void manualDeployMinibot()
    {
        if(towerStick.getRawButton(10))
        {
            pneumatics.extend(miniPiston);
            
        }

        if(towerStick.getRawButton(9))
        {
            pneumatics.retract(miniPiston);
        }
    }
    /**
     * Tells if the minibot is deploying or not
     * @return - True if minibot is deploying
     */
    public boolean isDepoying()
    {
        return miniPiston.getFireState();
    }
}