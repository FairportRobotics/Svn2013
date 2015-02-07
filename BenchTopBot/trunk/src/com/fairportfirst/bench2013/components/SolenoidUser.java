/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.fairportfirst.bench2013.components;

import edu.wpi.first.wpilibj.Solenoid;
/**
 *
 * @author alec
 */
public class SolenoidUser {

    //Note: wire these through a solenoid breakout. Without this it will not work
    //The hardware component says phenmatics bumper. This component uses TWO pins
    //Not 3

    private Solenoid mrSolenoid;
    public SolenoidUser(int channel)
    {
        mrSolenoid = new Solenoid(channel);
    }
    public void setBoolean(boolean setThis) //sets the state of the solenoid
    {
        mrSolenoid.set(setThis);
    }
    public boolean get() //gets the state of the solenoid
    {
        return mrSolenoid.get();
    }
}