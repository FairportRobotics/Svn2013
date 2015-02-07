/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.fairportfirst.bench2013.components;

import edu.wpi.first.wpilibj.smartdashboard.SendableGyro;

/**
 *
 * @author alec
 */
public class GyroUser {
    private SendableGyro mrGyro;

    /**
     *
     * @param channel - the analog channel that the gyro goes into
     */
    public GyroUser(int channel) {
        mrGyro = new SendableGyro(channel);
    }

    /**
     *
     * @param sensitivity - how many volts are in one degree/second
     */
    public void setSensitivity(double sensitivity) {
        mrGyro.setSensitivity(sensitivity);
    }

    /**
     *
     * @return the current angle of the gyro
     */
    public double getAngle() {
        return mrGyro.getAngle();
    }
}
