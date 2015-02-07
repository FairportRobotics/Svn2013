package com.fairportfirst.frc2011.sensors;

import edu.wpi.first.wpilibj.DigitalInput;
import com.fairportfirst.frc2011.drive.*;

/**
 * This class includes methods that utilize the implications of the light sensors.
 * @author Patrick
 */
public class PathDrive
{//note:middle sensors see line on start, left+right do not.

    /**
     * Various variables are created here.
     */
    private int endCounter = 0;
    private boolean atEnd;

    /**
     * Various objects are created here.
     */
    private DigitalInput leftSensor, middleSensor, rightSensor;
    private Drive pathDrive;
    RoboDrive robo;

    /**
     * Constructor that sets the channels for the DigitalInput objects
     * representing the light sensors. This also sets the Drive object.
     * @param leftChannel Represents the left light sensor.
     * @param middleChannel Represents the middle light sensor.
     * @param rightChannel Represents the right light sensor.
     * @param drive Represents the drive system.
     */
    public PathDrive(DigitalInput leftChannel, DigitalInput middleChannel, DigitalInput rightChannel, Drive drive) {
        leftSensor = leftChannel;
        middleSensor = middleChannel;
        rightSensor = rightChannel;
        pathDrive = drive;
        atEnd=false;
    }

    /**
     * isEnd checks to see if there's a t, which should trigger all 3 sensors.
     */
    public void isEnd()
    {
        if(leftSensor.get() && rightSensor.get())//&& middleSensor.get()
            atEnd=true;
//        return true;
            System.out.println("End: "+atEnd + leftSensor.get()+rightSensor.get());//+" at "+robo.getTime());
        // IsItEnd checks to see if there's a t, which should trigger all 3 sensors
    }
    public boolean atEnd()
    {
        return atEnd;
    }

    /**
     * This method is used to figure out whether or not the robot is headed in a straight forward direction.
     * @return A boolean that tells whether or not the robot is headed in a forward direction.
     */
    private boolean isY()
    {
        if(!leftSensor.get() && middleSensor.get() && !rightSensor.get())
            return true;
        else
            return false;
        // IsItY checks to see if the outer 2 sensors see the line, but not the 
        // middle one
    }

//    public boolean followStraightLine(int strafeAngle)
//    {
//        if(!isEnd()) {
//            if (leftSensor.get() && !middleSensor.get() && rightSensor.get()) {
//                pathDrive.strafeForward(0.6);
//                return true;
//            } //        now program what happens if the left sensor sees a line or if the right sensor sees a line.
//            //        if all three see a line, then the robot should stop.
//            else if ((!leftSensor.get() && middleSensor.get() && rightSensor.get())||(!leftSensor.get() && !middleSensor.get() && rightSensor.get())) {
//                pathDrive.strafeOnAngle(0.6, strafeAngle);
//                return true;
//            }
//            else if ((leftSensor.get() && middleSensor.get() && !rightSensor.get())||(leftSensor.get() && !middleSensor.get() && !rightSensor.get())) {
//                pathDrive.strafeOnAngle(0.6, -strafeAngle);
//                return true;
//            }
//            else
//                return false;
//        }
//        else
//            return true;
//
//    }

    /**
     * This method is for utilizing the various methods in this class so that the robot will be able to successfully turn and/or adjust itself so that it moves in a straight line according to the light sensor's statuses.
     * @param strafeAngle Represents the angle that that the robot needs to turn on to correctly follow one of the lines splitting away in the Y.
     * @return A boolean that returns 'true' if the robot is still moving and 'false' if the robot has stopped moving.
     */
    public boolean followStraightLine(int strafeAngle)
    {
//        if((!(!leftSensor.get() && !rightSensor.get()))||!atEnd) {\
            if(atEnd)
            {
                pathDrive.strafeBackward(0);
                System.out.println("teststop");
                return false;
            }
            else if(!leftSensor.get() && !rightSensor.get()) {
                pathDrive.strafeForward(0.21);//slow down??
//                System.out.println("testForward");
                return true;
            } //        now program what happens if the left sensor sees a line or if the right sensor sees a line.
            //        if all three see a line, then the robot should stop.
            else if (leftSensor.get() && !rightSensor.get()) {
//                pathDrive.strafeOnAngle(0.4, -strafeAngle);
                pathDrive.strafePolar(0.4, 0, -.1);
                System.out.println("testleft");// at "+robo.getTime());
                return true;
            }
            else if (!leftSensor.get() && rightSensor.get()) {
//                pathDrive.strafeOnAngle(0.4, strafeAngle);
                pathDrive.strafePolar(0.4, 0, .1);
                System.out.println("testright");// at "+robo.getTime());
                return true;
            }
            else if(leftSensor.get() && rightSensor.get())
                isEnd(  );
//            else
//            {
//                System.out.println("testnone");
//                return false;
//            }
//        }
//        else if(!atEnd)
//        {
//            atEnd=true;
            System.out.println("fail");
            return false;
//        }
//        else
//        {
            
//        }

    }

    /**
     * This method makes the robot follows a straight line until it reaches a Y, then it strafe left or right depending on the input.
     * @param leftOrRight Its value is used to determine which direction the robot shall turn on a Y: 0 means left, 1 means right
     * @return A boolean returning whether or not the robot will be moving on the Y split lines.
     */
    public boolean followYLine(int leftOrRight)
    {
        if(!isY()&&endCounter==0)
        {
            return followStraightLine(45);
        }
        else if(isY())
        {
            endCounter++;
            if(leftOrRight == 0)
               // 0 means go left, 1 means go right
            {
                pathDrive.strafeOnAngle(0.6, 45);
                return true;
            }
            else
            {
                pathDrive.strafeOnAngle(0.6, -45);
                return true;
            }
        }
        else if(endCounter > 0)
            return followStraightLine(60);
        return false;
//        if(endCounter == 2)
//            return true;
//        else
//        {
//            return false;
//        }
            
    }
            
}
