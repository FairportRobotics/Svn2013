/*
 * Title: Target Custom
 * Purpose: to serve as a modified version of the pre-made Target class.
 */

package com.fairportfirst.frc2011.vision;

import edu.wpi.first.wpilibj.image.BinaryImage;
import edu.wpi.first.wpilibj.image.ColorImage;
import edu.wpi.first.wpilibj.image.NIVisionException;
import edu.wpi.first.wpilibj.image.ParticleAnalysisReport;

/**
 * This class serves as a modified version of the pre-built Target class from the Tracker Demo Java example.
 * @author Team578, modified by Patrick Cipolla year 2011
 */
public class TargetCustom {

    /**
     * Various double variables are instantiated.
     */
    private static final double FRC_PARTICLE_TO_IMAGE_PERCENT = .0001;
    private static final double FRC_SIZE_FACTOR = 3.0;
    private static final double FRC_MAX_IMAGE_SEPARATION = 20.0;
    private static final double FRC_ALIGNMENT_SCALE = 3.0;

    /**
     * A ParticleAnalysisReport object is created.
     */
    final ParticleAnalysisReport firstParticle;

    //
    /**
     * Sets the class' ParticleAnalysisProject to the one sent to this class.
     * @param firstParticle The ParticleAnalysisReport object that the firstParticle object is set to.
     */
    TargetCustom(ParticleAnalysisReport firstParticle)
    {
        this.firstParticle = firstParticle;
    }

    /**
     * A sub-class within the TargetCustom class. This governs the Threshold aspect used to find targeted objects in an image.
     */
    public static class Threshold
    {
        //Various int variables that are used to represent the various characteristics
        //regarding the threshold of a targeted object.
        /**
         * The various attributes of the Threshold are created here. They are all int variables
         */
        int plane1Low;
        int plane1High;
        int plane2Low;
        int plane2High;
        int plane3Low;
        int plane3High;

        /**
         * A constructor that sets all six int attributes to imported values.
         * @param plane1Low The lower bound for the threshold's hue.
         * @param plane1High The upper bound for the threshold's hue.
         * @param plane2Low The lower bound for the threshold's saturation.
         * @param plane2High The upper bound for the threshold's saturation.
         * @param plane3Low The lower bound for the threshold's luminance.
         * @param plane3High The upper bound for the threshold's luminance.
         */
        public Threshold(int plane1Low, int plane1High,
                int plane2Low, int plane2High,
                int plane3Low, int plane3High) {
            this.plane1Low = plane1Low;
            this.plane1High = plane1High;
            this.plane2Low = plane2Low;
            this.plane2High = plane2High;
            this.plane3Low = plane3Low;
            this.plane3High = plane3High;
        }
    }

    /**
     * A method used in order to find the first targeted object based on the Threshold found within 'image'.
     * @param image A ColorImage object representing the current image produced by the AxisCamera.
     * @param firstThreshold A Threshold object representing the threshold that is used to find a target in the image.
     * @return A TargetCustom object that contains the first targeted object found in 'image'. If there are no targeted objects in 'image' the method returns null.
     * @throws NIVisionException
     */
    public static TargetCustom getTargetCustom(ColorImage image,
            Threshold firstThreshold) throws NIVisionException{
        BinaryImage firstColor = image.thresholdHSL(
                firstThreshold.plane1Low, firstThreshold.plane1High,
                firstThreshold.plane2Low, firstThreshold.plane2High,
                firstThreshold.plane3Low, firstThreshold.plane3High);

        ParticleAnalysisReport[] firstColorHits = firstColor.getOrderedParticleAnalysisReports(3);
        firstColor.free();

        for (int i = 0; i < firstColorHits.length; i++) {
            ParticleAnalysisReport firstTrackReport = firstColorHits[i];
            if (firstTrackReport.particleToImagePercent < FRC_PARTICLE_TO_IMAGE_PERCENT) {
                break;
            }                        
                //return the relevant track report
                return new TargetCustom(firstTrackReport);            
            }

        return null;
    }

    /**
     * A method that gets the position of the current targeted object on the x-axis.
     * @return A double of the current position on the x-axis of a targeted object.
     */
    public double getXPosition()
    {
        return (firstParticle.center_mass_x_normalized * firstParticle.particleToImagePercent) /
                getSize();
    }

    /**
     * A method that gets the position of the current targeted object on the y-axis.
     * @return A double of the current position on the y-axis of a targeted object.
     */
    public double getYPosition()
    {
        return (firstParticle.center_mass_y_normalized * firstParticle.particleToImagePercent) /
                getSize();
    }

    /**
     * A method that gets the size of a targeted object as it compares to the image as a whole.
     * @return A double of the size of a targeted object in terms of being compared.
     */
    public double getSize()
    {
        return firstParticle.particleToImagePercent;
    }

    /**
     * A method that creates a String object that represents the information of this class at current times.
     * @return A print statement used by the class to print its outputs.
     */
    public String toString()
    {
        return "Target at ( " + getXPosition() + " , " + getYPosition() + " ) of size " + getSize();
    }

}