/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fairportfirst.templates.subsystems;

import com.fairportfirst.templates.RobotMap;
import edu.wpi.first.wpilibj.camera.AxisCamera;
import edu.wpi.first.wpilibj.camera.AxisCameraException;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.image.BinaryImage;
import edu.wpi.first.wpilibj.image.ColorImage;
import edu.wpi.first.wpilibj.image.CriteriaCollection;
import edu.wpi.first.wpilibj.image.Image;
import edu.wpi.first.wpilibj.image.LinearAverages;
import edu.wpi.first.wpilibj.image.NIVision;
import edu.wpi.first.wpilibj.image.NIVisionException;
import edu.wpi.first.wpilibj.image.ParticleAnalysisReport;
import java.util.Vector;

/**
 *
 * @author alec 
 */
public class CameraSubsystem extends Subsystem {

    private AxisCamera camera;
    private CriteriaCollection cc;
    private Scores scores[];
    private final int HUE_LOW = 60;
    private final int HUE_HIGH = 100;
    private final int SATURATION_LOW = 90;
    private final int SATURATION_HIGH = 255;
    private final int VALUE_LOW = 20;
    private final int VALUE_HIGH = 255;
    private final int NOT_A_TARGET = -1;
    private final int MIDDLE_TARGET = 0;
    private final int HIGH_TARGET = 1;
    private final int PYRAMID_PRIMARY_TARGET = -2;
    private final int PYRAMID_SECONDARY_TARGET = -3;
    private final int MAX_SHOOT_DISTANCE = 400;
    private final int SECONDARY_PYRAMID_TARGET_WIDTH = 4;
    private final double SECONDARY_PYRAMID_TARGET_HEIGHT = 1.5;
    private final double PRIMARY_PYRAMID_TARGET_WIDTH = 1.5;
    private final int PRIMARY_PYRAMID_TARGET_HEIGHT = 20;
    
    private final int XMAXSIZE = 24;
    private final int XMINSIZE = 24;
    private final int YMAXSIZE = 24;
    private final int YMINSIZE = 48;
    private final double xMax[] = {1, 1, 1, 1, .5, .5, .5, .5, .5, .5, .5, .5, .5, .5, .5, .5, .5, .5, .5, .5, 1, 1, 1, 1};
    private final double xMin[] = {.4, .6, .1, .1, .1, .1, .1, .1, .1, .1, .1, .1, .1, .1, .1, .1, .1, .1, .1, .1, .1, .1, 0.6, 0};
    private final double yMax[] = {1, 1, 1, 1, .5, .5, .5, .5, .5, .5, .5, .5, .5, .5, .5, .5, .5, .5, .5, .5, 1, 1, 1, 1};
    private final double yMin[] = {.4, .6, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .05, .6, 0};
    private final int RECTANGULARITY_LIMIT = 60;
    private final int ASPECT_RATIO_LIMIT = 75;
    private final int X_EDGE_LIMIT = 40;
    private final int Y_EDGE_LIMIT = 60;
    private final int X_IMAGE_RES = 320;          //X Image resolution in pixels, should be 160, 320 or 640
    private final double VIEW_ANGLE = 48;       //Axis M1011 camera
    
    public CameraSubsystem() {
        camera = AxisCamera.getInstance(RobotMap.CAMERA_ADDRESS);
        cc = new CriteriaCollection();      // create the criteria for the particle filter
        cc.addCriteria(NIVision.MeasurementType.IMAQ_MT_AREA, 500, 65535, false);
    }
    
    public void initDefaultCommand() {
        //setDefaultCommand(new MySpecialCommand());
    }
    
    /**Gets the best target based on rectangularity, distance, and target score.
     * 
     * @return the index of the target with the best values as specified above.
     */
    public int getBestTargetIndex() {
        int currentBestTarget = -1;
        double bestScore = -1;
        for(int q = 0; q < scores.length; q++) {
            if(scores[q].targetPoint > NOT_A_TARGET) {
                if(currentBestTarget == -1) {
                    currentBestTarget = q;
                } else if(bestScore < getTargetBestScoreValue(q)) {
                    currentBestTarget = q;
                } else if(bestScore == getTargetBestScoreValue(q)) {
                    if(scores[currentBestTarget].distance>scores[q].distance || scores[currentBestTarget].targetPoint>scores[q].targetPoint || scores[currentBestTarget].rectangularity>scores[q].rectangularity) {
                        currentBestTarget = q;
                    }
                }
                bestScore = getTargetBestScoreValue(currentBestTarget);
            }
        }
        return currentBestTarget;
    }
    
    /**Current algorithm is rectangularity/(MAX_SHOOT_DISTANCE - distance) * score
     * 
     * @param index of the target you want to be scored
     * @return the score represented by the algorithm above
     */
    public double getTargetBestScoreValue(int index) {
        if(scores[index].targetPoint > NOT_A_TARGET) {
            return -1;
        }
        return scores[index].rectangularity/(MAX_SHOOT_DISTANCE-scores[index].distance)*(scores[index].targetPoint+2);
    }
    
    /**Gets the target with the index specified
     * 
     * @param index the index of the target you want
     * @return the target that index matches above
     */
    public Scores getTarget(int index) {
        return scores[index];
    }
    
    /**
     * @return the index of the target that is least skewed
     */
    public int findMostDirectTarget() {
        if(scores.length==0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        int mostDirectIndex = 0;
        for(int q = 0; q < scores.length; q++) {
            if(scores[q].targetPoint > NOT_A_TARGET && scores[q].rectangularity < scores[mostDirectIndex].rectangularity) {
                mostDirectIndex = q;
            }
        }
        return mostDirectIndex;
    }
    
    /**Returns the highest scoring targets index that is also the closet to the 
     * camera.
     * 
     * @return the index that is the closest highest scoring target. 
     */
    public int findHighestScoringTarget() {
        if(scores.length==0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        int highestScoreIndex = 0;
        int[] targetCanidates = null;
        for(int q  = 0; q  < scores.length; q++) {
            if(scores[q].targetPoint > NOT_A_TARGET && scores[q].targetPoint >= scores[highestScoreIndex].targetPoint) {
                if(scores[q].targetPoint == scores[highestScoreIndex].targetPoint) {
                    if(targetCanidates == null) {
                        targetCanidates = new int[]{q};
                    } else {
                        targetCanidates = appendIntArray(targetCanidates,q);
                    }
                    highestScoreIndex = q;
                } else {
                    highestScoreIndex = q;
                }
            }
        }
       return findClosestTarget(targetCanidates);
    }
    
    /**Essentally is an array list for ints
     * 
     * @param ray the ray you want to add the var to
     * @param newVal the value you want to add to the array
     * @return an array the ray plus the newVal at the end
     */
    public int[] appendIntArray(int[] ray, int newVal) {
        int[] finalRay = new int[ray.length+1];
        for(int q = 0; q < ray.length; q++) {
            finalRay[q] = ray[q];
        }
        finalRay[ray.length] = newVal;
        return finalRay;
    }
    
     /**Essentally is an array list for doubles
     * 
     * @param ray the ray you want to add the var to
     * @param newVal the value you want to add to the array
     * @return an array the ray plus the newVal at the end
     */
    public double[] appendDoubleArray(double[] ray, double newVal) {
        if(ray == null) {
            return new double[]{newVal};
        }
        double[] finalRay = new double[ray.length+1];
        for(int q = 0; q < ray.length; q++) {
            finalRay[q] = ray[q];
        }
        finalRay[ray.length] = newVal;
        return finalRay;
    }
    
    /**This method finds the closest target, as an index, in the given set. 
     * 
     * @param targetCanidates the index spots of which targets you want to scan 
     * the closest target for.
     * @return the index of the closest target for the set.
     */
    public int findClosestTarget(int[] targetCanidates) {
        int closestDistanceIndex = 0;
        if(targetCanidates.length == 0 || scores.length == 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        for(int q = 0; q < targetCanidates.length; q++) {
            if(scores[targetCanidates[q]].targetPoint > NOT_A_TARGET && scores[targetCanidates[q]].distance < scores[closestDistanceIndex].distance) {
                closestDistanceIndex = targetCanidates[q];
            }
        }
        return closestDistanceIndex;
    }
    
    /**This method searches through the scores to find the one with the closest distance.
     * Efficency: O(n)
     * 
     * @return the index of the score closest to the robot 
     */
    public int findClosestTarget() {
        int closestDistanceIndex = 0;
        if(scores.length==0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        for(int q = 0; q < scores.length; q++) {
            if(scores[q].targetPoint > NOT_A_TARGET && scores[q].distance < scores[closestDistanceIndex].distance) {
                closestDistanceIndex = q;
            }
        }
        return closestDistanceIndex;
    }
    
    public double[] getListSkewData() {
        double[] skewData = new double[scores.length];
        for(int q = 0; q < scores.length; q++) {
            if(scores[q].targetPoint < 0) {
                skewData[q] = -1;
            } else {
                skewData[q] = scores[q].rectangularity;
            }
        }
        return skewData;
     }
    
    /**
     * This method scans the current image from the camera. It then runs the image
     * through an algorithm that picks out shapes in a specified color var. It 
     * stores the target, and data about the target, such as distance from the 
     * robot(in inches).
     */
    public void identifyTargets() {
        ColorImage image = null;
        try {
            image = camera.getImage();
        } catch (AxisCameraException ex) {
            ex.printStackTrace();
        } catch (NIVisionException ex) {
            ex.printStackTrace();
        }
        try {
            if(image!=null) {
                // keep only red objects
                BinaryImage thresholdImage = image.thresholdHSV(HUE_LOW, HUE_HIGH, SATURATION_LOW, SATURATION_HIGH, VALUE_LOW, VALUE_HIGH);
                // filter out small particles
                BinaryImage convexHullImage = thresholdImage.convexHull(false);
                // filter out small particles
                BinaryImage filteredImage = convexHullImage.particleFilter(cc);
                
                scores = new Scores[filteredImage.getNumberParticles()];
                for (int i = 0; i < scores.length; i++) {
                    ParticleAnalysisReport report = filteredImage.getParticleAnalysisReport(i);
                    scores[i] = new Scores();

                    scores[i].rectangularity = scoreRectangularity(report);
                    scores[i].aspectRatioOuter = scoreAspectRatio(filteredImage,report, i, true);
                    scores[i].aspectRatioInner = scoreAspectRatio(filteredImage, report, i, false);
                    scores[i].aspectRatioPrimary = scorePyramidAspectRatio(filteredImage,report, i, true);
                    scores[i].aspectRatioSecondary = scorePyramidAspectRatio(filteredImage, report, i, false);
                    scores[i].xEdge = scoreXEdge(thresholdImage, report);
                    scores[i].yEdge = scoreYEdge(thresholdImage, report);
                    
                    if(scoreCompare(scores[i], false)) {
                        scores[i].targetPoint = HIGH_TARGET;
                        scores[i].cenX = report.center_mass_x_normalized;
                        scores[i].cenY = report.center_mass_y_normalized;
                        scores[i].distance = computeDistance(thresholdImage, report, i, false);
                    } else if (scoreCompare(scores[i], true)) {
                        scores[i].targetPoint = MIDDLE_TARGET;
                        scores[i].cenX = report.center_mass_x_normalized;
                        scores[i].cenY = report.center_mass_y_normalized;
                        scores[i].distance = computeDistance(thresholdImage, report, i, true);
                    } else if (scorePyramidCompare(scores[i], false)) {
                        scores[i].targetPoint = PYRAMID_SECONDARY_TARGET;
                        scores[i].cenX = report.center_mass_x_normalized;
                        scores[i].cenY = report.center_mass_y_normalized;
                    } else if (scorePyramidCompare(scores[i], true)) {
                        scores[i].targetPoint = PYRAMID_PRIMARY_TARGET;
                        scores[i].cenX = report.center_mass_x_normalized;
                        scores[i].cenY = report.center_mass_y_normalized;
                    }else {
                        scores[i].targetPoint = NOT_A_TARGET;
                    }
                }
                filteredImage.free();
                convexHullImage.free();
                thresholdImage.free();
                image.free();
            }
        } catch (NIVisionException ex) {
            ex.printStackTrace();
        }
    }
    
    public double[] getMotorValuesForPyramidAlignment()
    {
        double[] motorValues = new double[]{0,0};
        Scores primary = null, secondary1 = null, secondary2 = null;
        
        for (int k = 0; k < scores.length; k++)
        {
            if (primary != null && secondary1 != null && secondary2 != null)
            {
                k = scores.length;
            } else if (scores[k].targetPoint == PYRAMID_PRIMARY_TARGET && primary == null)
            {
                primary = scores[k];
            } else if (scores[k].targetPoint == PYRAMID_SECONDARY_TARGET)
            {
                if (secondary1 == null)
                {
                    secondary1 = scores[k];
                }
                else
                {
                    secondary2 = scores[k];
                }
            }
        }
        
        if (primary != null && secondary1 != null && secondary2 != null)
        {
            double motorLeft = secondary1.cenX - secondary2.cenX;
            motorValues[0] = motorLeft;
            motorValues[1] = -motorLeft;
        }
        
        return motorValues;
    }
    
        /**
     * Computes the estimated distance to a target using the height of the particle in the image. For more information and graphics
     * showing the math behind this approach see the Vision Processing section of the ScreenStepsLive documentation.
     * 
     * @param image The image to use for measuring the particle estimated rectangle
     * @param report The Particle Analysis Report for the particle
     * @param outer True if the particle should be treated as an outer target, false to treat it as a center target
     * @return The estimated distance to the target in Inches.
     */
    double computeDistance (BinaryImage image, ParticleAnalysisReport report, int particleNumber, boolean outer) throws NIVisionException {
            double rectShort, height;
            int targetHeight;

            rectShort = NIVision.MeasureParticle(image.image, particleNumber, false, NIVision.MeasurementType.IMAQ_MT_EQUIVALENT_RECT_SHORT_SIDE);
            //using the smaller of the estimated rectangle short side and the bounding rectangle height results in better performance
            //on skewed rectangles
            height = Math.min(report.boundingRectHeight, rectShort);
            targetHeight = outer ? 29 : 21;

            return X_IMAGE_RES * targetHeight / (height * 12 * 2 * Math.tan(VIEW_ANGLE*Math.PI/(180*2)));
    }
    
    /**
     * Computes a score (0-100) comparing the aspect ratio to the ideal aspect ratio for the target. This method uses
     * the equivalent rectangle sides to determine aspect ratio as it performs better as the target gets skewed by moving
     * to the left or right. The equivalent rectangle is the rectangle with sides x and y where particle area= x*y
     * and particle perimeter= 2x+2y
     * 
     * @param image The image containing the particle to score, needed to perform additional measurements
     * @param report The Particle Analysis Report for the particle, used for the width, height, and particle number
     * @param outer	Indicates whether the particle aspect ratio should be compared to the ratio for the inner target or the outer
     * @return The aspect ratio score (0-100)
     */
    public double scoreAspectRatio(BinaryImage image, ParticleAnalysisReport report, int particleNumber, boolean outer) throws NIVisionException
    {
        double rectLong, rectShort, aspectRatio, idealAspectRatio;

        rectLong = NIVision.MeasureParticle(image.image, particleNumber, false, NIVision.MeasurementType.IMAQ_MT_EQUIVALENT_RECT_LONG_SIDE);
        rectShort = NIVision.MeasureParticle(image.image, particleNumber, false, NIVision.MeasurementType.IMAQ_MT_EQUIVALENT_RECT_SHORT_SIDE);
        idealAspectRatio = outer ? (62/29) : (62/20);	//Dimensions of goal opening + 4 inches on all 4 sides for reflective tape
	
        //Divide width by height to measure aspect ratio
        if(report.boundingRectWidth > report.boundingRectHeight){
            //particle is wider than it is tall, divide long by short
            aspectRatio = 100*(1-Math.abs((1-((rectLong/rectShort)/idealAspectRatio))));
        } else {
            //particle is taller than it is wide, divide short by long
                aspectRatio = 100*(1-Math.abs((1-((rectShort/rectLong)/idealAspectRatio))));
        }
	return (Math.max(0, Math.min(aspectRatio, 100.0)));		//force to be in range 0-100
    }
    
    public double scorePyramidAspectRatio(BinaryImage image, ParticleAnalysisReport report, int particleNumber, boolean primary) throws NIVisionException
    {
        double rectLong, rectShort, aspectRatio, idealAspectRatio;

        rectLong = NIVision.MeasureParticle(image.image, particleNumber, false, NIVision.MeasurementType.IMAQ_MT_EQUIVALENT_RECT_LONG_SIDE);
        rectShort = NIVision.MeasureParticle(image.image, particleNumber, false, NIVision.MeasurementType.IMAQ_MT_EQUIVALENT_RECT_SHORT_SIDE);
        idealAspectRatio = primary ? (PRIMARY_PYRAMID_TARGET_WIDTH/PRIMARY_PYRAMID_TARGET_HEIGHT) : (SECONDARY_PYRAMID_TARGET_WIDTH/SECONDARY_PYRAMID_TARGET_HEIGHT);	//Dimensions of goal opening + 4 inches on all 4 sides for reflective tape
	
        //Divide width by height to measure aspect ratio
        if(report.boundingRectWidth > report.boundingRectHeight){
            //particle is wider than it is tall, divide long by short
            aspectRatio = 100*(1-Math.abs((1-((rectLong/rectShort)/idealAspectRatio))));
        } else {
            //particle is taller than it is wide, divide short by long
                aspectRatio = 100*(1-Math.abs((1-((rectShort/rectLong)/idealAspectRatio))));
        }
	return (Math.max(0, Math.min(aspectRatio, 100.0)));		//force to be in range 0-100
    }
    
    /**
     * Compares scores to defined limits and returns true if the particle appears to be a target
     * 
     * @param scores The structure containing the scores to compare
     * @param outer True if the particle should be treated as an outer target, false to treat it as a center target
     * 
     * @return True if the particle meets all limits, false otherwise
     */
    boolean scoreCompare(Scores scores, boolean outer){
            boolean isTarget = true;

            isTarget &= scores.rectangularity > RECTANGULARITY_LIMIT;
            if(outer){
                    isTarget &= scores.aspectRatioOuter > ASPECT_RATIO_LIMIT;
            } else {
                    isTarget &= scores.aspectRatioInner > ASPECT_RATIO_LIMIT;
            }
            isTarget &= scores.xEdge > X_EDGE_LIMIT;
            isTarget &= scores.yEdge > Y_EDGE_LIMIT;

            return isTarget;
    }
    
    boolean scorePyramidCompare(Scores scores, boolean primary){
            boolean isTarget = true;

            isTarget &= scores.rectangularity > RECTANGULARITY_LIMIT;
            if(primary){
                    isTarget &= scores.aspectRatioPrimary > ASPECT_RATIO_LIMIT;
            } else {
                    isTarget &= scores.aspectRatioSecondary > ASPECT_RATIO_LIMIT;
            }
            isTarget &= scores.xEdge > X_EDGE_LIMIT;
            isTarget &= scores.yEdge > Y_EDGE_LIMIT;

            return isTarget;
    }
    
    /**
     * Computes a score (0-100) estimating how rectangular the particle is by comparing the area of the particle
     * to the area of the bounding box surrounding it. A perfect rectangle would cover the entire bounding box.
     * 
     * @param report The Particle Analysis Report for the particle to score
     * @return The rectangularity score (0-100)
     */
    double scoreRectangularity(ParticleAnalysisReport report){
            if(report.boundingRectWidth*report.boundingRectHeight !=0){
                    return 100*report.particleArea/(report.boundingRectWidth*report.boundingRectHeight);
            } else {
                    return 0;
            }	
    }
    
    /**
     * Computes a score based on the match between a template profile and the particle profile in the X direction. This method uses the
     * the column averages and the profile defined at the top of the sample to look for the solid vertical edges with
     * a hollow center.
     * 
     * @param image The image to use, should be the image before the convex hull is performed
     * @param report The Particle Analysis Report for the particle
     * 
     * @return The X Edge Score (0-100)
     */
    public double scoreXEdge(BinaryImage image, ParticleAnalysisReport report) throws NIVisionException
    {
        double total = 0;
        LinearAverages averages;
        
        NIVision.Rect rect = new NIVision.Rect(report.boundingRectTop, report.boundingRectLeft, report.boundingRectHeight, report.boundingRectWidth);
        averages = NIVision.getLinearAverages(image.image, LinearAverages.LinearAveragesMode.IMAQ_COLUMN_AVERAGES, rect);
        float columnAverages[] = averages.getColumnAverages();
        for(int i=0; i < (columnAverages.length); i++){
                if(xMin[(i*(XMINSIZE-1)/columnAverages.length)] < columnAverages[i] 
                   && columnAverages[i] < xMax[i*(XMAXSIZE-1)/columnAverages.length]){
                        total++;
                }
        }
        total = 100*total/(columnAverages.length);
        return total;
    }
    
    /**
	 * Computes a score based on the match between a template profile and the particle profile in the Y direction. This method uses the
	 * the row averages and the profile defined at the top of the sample to look for the solid horizontal edges with
	 * a hollow center
	 * 
	 * @param image The image to use, should be the image before the convex hull is performed
	 * @param report The Particle Analysis Report for the particle
	 * 
	 * @return The Y Edge score (0-100)
	 *
    */
    public double scoreYEdge(BinaryImage image, ParticleAnalysisReport report) throws NIVisionException
    {
        double total = 0;
        LinearAverages averages;
        
        NIVision.Rect rect = new NIVision.Rect(report.boundingRectTop, report.boundingRectLeft, report.boundingRectHeight, report.boundingRectWidth);
        averages = NIVision.getLinearAverages(image.image, LinearAverages.LinearAveragesMode.IMAQ_ROW_AVERAGES, rect);
        float rowAverages[] = averages.getRowAverages();
        for(int i=0; i < (rowAverages.length); i++){
                if(yMin[(i*(YMINSIZE-1)/rowAverages.length)] < rowAverages[i] 
                   && rowAverages[i] < yMax[i*(YMAXSIZE-1)/rowAverages.length]){
                        total++;
                }
        }
        total = 100*total/(rowAverages.length);
        return total;
    }
    
    /**
     * This class holds all the data of a target.
     */
    public class Scores {
        double rectangularity;
        double aspectRatioInner;
        double aspectRatioOuter;
        double aspectRatioPrimary;
        double aspectRatioSecondary;
        double xEdge;
        double yEdge;
        double distance;
        double cenX;//Normalized [ -1...1 ]
        double cenY;//Normalized [ -1...1 ]
        int targetPoint;//Tells if it is a target
    }
}
