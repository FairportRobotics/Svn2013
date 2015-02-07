package com.fairportfirst.frc2011.structures;

import edu.wpi.first.wpilibj.Joystick;
/**
 * Tells the arm how and when to extend/retract
 * @author Charles
 */
public class TheArm {

    private PneumaticCylinder arm;
    private Joystick towerStick;
    private Pneumatics pneumatics;
    public TheArm(int armSolenoidFireChannel, int armSolenoidReleaseChannel, Pneumatics pneu, Joystick joy)
    {
        arm = new PneumaticCylinder(armSolenoidFireChannel,armSolenoidReleaseChannel,true);
        pneumatics =pneu;
        towerStick = joy;
    }
    /**
     * Tells the robot to move the arm up if the arm is retracting
     * @param up - If the arm is retracting, go up; If the arm isn't going up, retract
     */
    public void moveArm(boolean up)
    {
        if(up && arm.getRetractState())
            pneumatics.extend(arm);
        else if(!up && arm.getFireState())
            pneumatics.retract(arm);
    }
    /**
     * Tells the arm to move up/down depending on what button is pressed
     */
    public void armControl()
    {
        if(towerStick.getRawButton(3))
            pneumatics.retract(arm);
        else if(towerStick.getRawButton(5))
            pneumatics.extend(arm);
    }
    /**
     * Tells the arm state
     * @return - True if it is fired; False if it is retracted
     */
    public boolean getArmState()
    {
        return arm.getFireState();
    }
}
