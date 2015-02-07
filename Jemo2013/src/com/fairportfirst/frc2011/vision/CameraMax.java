package com.fairportfirst.frc2011.vision;

import edu.wpi.first.wpilibj.camera.AxisCamera;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.camera.AxisCamera;
import edu.wpi.first.wpilibj.camera.AxisCameraException;
import edu.wpi.first.wpilibj.image.BinaryImage;
import edu.wpi.first.wpilibj.image.ColorImage;
import edu.wpi.first.wpilibj.image.NIVisionException;


/**
 * This serves as the main class that uses all of the other camera related classes.
 * @author Team578, Patrick Cipolla
 */
public class CameraMax{

    /**
     * Makes two objects and a double variable.
     */
    private ColorImage cameraImage;
    private final double centerX = 160.0; //width = 320
    private TargetCustom target;

    /**
     * A constructor that sets 'target' and 'cameraImage' to 'null'.
     */
    public CameraMax()
    {
        target = null;
        cameraImage = null;
    }

    /**
     * This method sets the 'cameraImage' object to 'img', the current image obtained by the AxisCamera.
     * @param img A ColorImage object that represents the current image captured by the AxisCamera.
     * @return A boolean that returns true if the 'img' has an image. False if 'img' has no image.
     */
    public boolean setImage(ColorImage img)
    {
        if(img == null)
        {
            return false;
        }
        else
        {
            cameraImage = img;
            return true;
        }
    }

    /**
     * This method finds the position of a targeted object relative to the center of the image on the x-axis.
     * @return A double representing the number of pixels between the target and the center of the image on the x-axis (320 pixels wide). If 160, no target has been found.
     */
    public double getTarget()
    {
        Tracker tracker = Tracker.getInstance();
        Timer timer = new Timer();

        if (AxisCamera.getInstance().freshImage()) {
                System.out.println("Fresh image");
            }

            try {
                target = tracker.getTargetCustom(cameraImage);
            } catch (AxisCameraException e) {
            } catch (NIVisionException e) {
            }

            if (target != null) {
                double centerRelative = (target.getXPosition() - centerX);//centerX?
                System.out.println(target + " in " + timer.get() + " seconds");
                timer.reset();
                Timer.delay(.001);
                return centerRelative;
            } else {
                System.out.println("No target found for " + timer.get() + " seconds");
                timer.reset();
                Timer.delay(.001);
                return centerX;
            }
            
    }
    //target size determines the alloted threshold.
}
