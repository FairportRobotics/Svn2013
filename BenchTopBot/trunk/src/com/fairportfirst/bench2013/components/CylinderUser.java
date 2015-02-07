/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.fairportfirst.bench2013.components;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;

/**
 *
 * @author alec
 */
public class CylinderUser {
    private SolenoidUser extender;
    private SolenoidUser retractor;
    private DigitalInput limitSwitch;

    /**
     *
     * @param extendChannel - analog channel for the extending solenoid
     * @param retractChannel - analog channel for the retracting solenoid
     */
    public CylinderUser(int extendChannel, int retractChannel) {
        extender = new SolenoidUser(extendChannel);
        retractor = new SolenoidUser(retractChannel);
    }

    public CylinderUser(int extendChannel, int retractChannel, int switchChannel ) {
        this(extendChannel,retractChannel);
        limitSwitch = new DigitalInput(switchChannel);
    }

    /**
     * extends the current cylinder
     */
    public void extend() {
        extender.setBoolean(true);
        retractor.setBoolean(false);
    }

    /**
     * retracts the current cylinder
     */
    public void retract() {
        extender.setBoolean(false);
        retractor.setBoolean(true);
    }

    /**
     *
     * @param on - sets the extender solenoid to on and retract to the opposite
     *      value of on
     */
    public void setCylinder(boolean on) {
        extender.setBoolean(on);
        retractor.setBoolean(!on);
    }

    /**
     *
     * @param joystick - lets a joystick control this cylinder where the trigger
     * press controls extends of the cylinder
     */
    public void joyStickOperate(Joystick joystick) {
        if(limitSwitch.get()) {
            setCylinder(joystick.getTrigger());
        } else {
            extender.setBoolean(false);
            retractor.setBoolean(joystick.getTrigger());
            System.out.println("Should not go!");
        }
        System.out.println("lmt " + limitSwitch.get());
    }
}
