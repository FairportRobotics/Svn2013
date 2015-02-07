package com.fairportfirst.frc2011.structures;

import edu.wpi.first.wpilibj.*;
import com.fairportfirst.frc2011.drive.*;

/**
 * Tells the pistons when to fill and it's behavior
 * @author Steven
 */
public class Pneumatics {
    private Compressor compressor;
    private boolean isFilling;
    private int extenstionsLeft;
    private final int INITEXTENDS = 6;
    private Timer timer;
    private RoboDrive robo;
    public Pneumatics(int pressureSwitchChannel, int compressorRelayChannel, Timer time) {// the constructor
        compressor = new Compressor(pressureSwitchChannel, compressorRelayChannel);
        isFilling = compressor.enabled();
        extenstionsLeft=INITEXTENDS;
        timer =time;
//        isFilling = false;
    }
    /**
     * Tells whether or not the compressor is full
     * @return - True if the compressor is full
     */
    public boolean isFull() {//checks to see if the compressor is full
        System.out.println(">>>HEY: " + compressor.getPressureSwitchValue());
        return (compressor.getPressureSwitchValue());
    }
    /**
     * Tells whether or not the arm can extend or not
     * @return - True if the arm can still extend
     */
    public boolean canExtend() {
        return extenstionsLeft>0;
    }
    /**
     * Tells the compressor to start filling
     */
    public void fillTanks() {
        WatchWhaleWhaleWatch.getInstance().feed();
        compressor.start();
        WatchWhaleWhaleWatch.getInstance().feed();
        isFilling = true;
    }
    /**
     * Extend if the cylinder is firing
     * @param cyl - Tells the cylinder to extend
     */
    public void extend(PneumaticCylinder cyl) {
        cyl.setRetract(false);
        cyl.setFire(true);
        extenstionsLeft--;
    }
    // added this method to pull the piston back
    /**
     * Tells the robot to retract the cylinder
     * @param cyl - Retracts the cylinder
     */
    public void retract(PneumaticCylinder cyl) {
        cyl.setRetract(true);
        cyl.setFire(false);
    }
    /**
     * Tells the robot not to extend or retract the cylinders if there are no more extensions left
     * @param cyl - Tells the robot not to extend or retract the cylinders
     */
    public void switchState(PneumaticCylinder cyl) {
        cyl.setFire(!cyl.getFireState());
        cyl.setRetract(!cyl.getRetractState());
        if(cyl.getFireState())
            extenstionsLeft--;
    }
    /**
     * Tells compressor to run or not to run, depending on whether it's full
     */
    public void cycle() {
            if (isFilling && isFull()) {
                compressor.stop();
                isFilling = false;
                extenstionsLeft = INITEXTENDS;
            }
            if((!isFull())&& !isFilling && extenstionsLeft <4)
            {
                compressor.start();
                isFilling = true;
            }
    }
    /**
     * Tells the compressor state
     * @return - True if the compressor is filling
     */
    public boolean getCompressorState()
    {
        return isFilling;
    }
    /**
     * Tells how many shots are left
     * @return - Returns an integer
     */
    public int shotsLeft()
    {
        return extenstionsLeft;
    }
}
