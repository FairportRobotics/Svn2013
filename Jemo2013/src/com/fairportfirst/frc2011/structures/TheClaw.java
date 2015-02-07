package com.fairportfirst.frc2011.structures;

import com.fairportfirst.frc2011.ui.TowerStick;
import edu.wpi.first.wpilibj.Joystick;
/**
 * Tells the claw when and how to open/close
 * @author Stefen
 */
public class TheClaw
{
    private Pneumatics pneumatics;
    private PneumaticCylinder claw;
    private Joystick towerStick;
    public TheClaw(int clawSolenoidFireChannel, int clawSolenoidReleaseChannel, Pneumatics pneu, Joystick joy)
    {
        claw = new PneumaticCylinder(clawSolenoidFireChannel,clawSolenoidReleaseChannel,false);
        pneumatics =pneu;
        towerStick = joy;
    }
    public void openClaw()
    {
//        if(pneumatics.canExtend() && claw.getRetractState())
//        {
            pneumatics.extend(claw);
//        }
    }
    /**
     * Tells the claw to close if the claw is retracting
     */
    public void closeClaw()
    {
//        if(claw.getRetractState())
//        {
            pneumatics.retract(claw);
//        }
    }
    /**
     * Tells the claw to extend or retract depending on what button is pressed
     */
    public void clawControl() {
        if(towerStick.getRawButton(6))
        {
            openClaw();
        }
        else if(towerStick.getRawButton(4))
        {
            closeClaw();
        }
    }
    /**
     * Tells the Claw to open or not
     * @return - True if the claw is firing
     */
    public boolean isOpen() {
      return claw.getFireState();
    }
}
