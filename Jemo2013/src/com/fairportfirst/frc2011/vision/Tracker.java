/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package com.fairportfirst.frc2011.vision;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.camera.AxisCameraException;
import edu.wpi.first.wpilibj.image.ColorImage;
import edu.wpi.first.wpilibj.image.NIVisionException;

/**
 * An interface of sorts between the TargetCustom class and the CameraMax class.
 * @author dtjones, modified by Patrick Cipolla team 578 year 2011
 */
public class Tracker {

    /**
     * A Tracker object and a Threshold object from the TargetCustom class are instantiated.
     */
    static Tracker instance = null;
    TargetCustom.Threshold firstColor = new TargetCustom.Threshold(215, 245, 42, 255, 7, 255);
    //AxisCamera camera = AxisCamera.getInstance();

    /**
     * If the Tracker object 'instance' has no instance then this method assigns one to it.
     * @return The current instance of 'instance' is returned.
     */
    public static Tracker getInstance() {
        if (instance == null) {
            instance = new Tracker();
        }
        return instance;
    }

    /**
     * This method creates a threshold instance for this class of the targeted image. Threshold is used in order to differ a targeted object from the rest of an image.
     * @param lowerHue The lower bound for the threshold's hue.
     * @param upperHue The upper bound for the threshold's hue.
     * @param lowerSaturation The lower bound for the threshold's saturation.
     * @param upperSaturation The upper bound for the threshold's saturation.
     * @param lowerLuminance The lower bound for the threshold's luminance.
     * @param upperLuminance The upper bound for the threshold's luminance.
     */
    public void setFirstColorThresholds(int lowerHue, int upperHue,
            int lowerSaturation, int upperSaturation,
            int lowerLuminance, int upperLuminance) {
        firstColor = new TargetCustom.Threshold(lowerHue, upperHue,
                lowerSaturation, upperSaturation,
                lowerLuminance, upperLuminance);
    }

    //Pre:  Brings in a ColorImage object; it is a representation of image on the camera.
    //Post: Returns a TargetCustom object representing the targeted objects found in the image.
    /**
     * This method find a target depicted by the threshold in a image captured by the AxisCamera.
     * @param img The image most recently captured by the camera.
     * @return A TargetCustom object found within the image is returned.
     * @throws NIVisionException
     * @throws AxisCameraException
     */
    public TargetCustom getTargetCustom(ColorImage img) throws NIVisionException, AxisCameraException {
        while (true) {
            ColorImage image = img;
            try {
                return TargetCustom.getTargetCustom(image, firstColor);
            } catch (Exception e) {
                e.printStackTrace();
            //the finally clause happens no matter what ends the loop (http://www.javaworld.com/javaworld/jw-02-1997/jw-02-hood.html)
            } finally {
                if (image != null) {
                    image.free();
                }
                image = null;
            }
            //Timer.delay(.001); //? - may need to change
        }
    }
}
