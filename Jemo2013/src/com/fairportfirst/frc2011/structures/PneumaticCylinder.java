package com.fairportfirst.frc2011.structures;
import edu.wpi.first.wpilibj.Solenoid;
/**
 * Tells the cylinders in general how to act
 * @author Charles
 */
public class PneumaticCylinder {
    private Solenoid fire;
    private Solenoid retract;

    public PneumaticCylinder(int fireChannel, int releaseChannel, boolean fireState)
    {
        fire = new Solenoid(fireChannel);
        retract = new Solenoid(releaseChannel);
        fire.set(fireState);
        retract.set(!fireState);
    }
    /**
     * Extends the pneumatic cylinder
     * @param state - If state is true, extend
     */
    public void setFire(boolean state)
    {
        fire.set(state);
    }
    /**
     * Retracts the pneumatic cylinder
     * @param state - If the state is true, retract
     */
    public void setRetract(boolean state)
    {
        retract.set(state);
    }
    /**
     * Tells whether or not it is firing
     * @return - True if it is firing
     */
    public boolean getFireState()
    {
        return fire.get();
    }
    /**
     * Tells whether or not it is retracting
     * @return - True is it is retracting
     */
    public boolean getRetractState()
    {
        return retract.get();
    }
}
