package com.fairportfirst.frc2011.structures;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.can.CANNotInitializedException;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import com.fairportfirst.frc2011.drive.RoboDrive;

/**
 * Tells what the TowerOfPower should do (arm and claw)
 * @author Brennan
 */
public class TowerOfPower
{
    final int encoderClicks = 3;
    final int outerPegAdd = 9;
    final int innerPegAdd = 10;
    //should be 5 inches above peg
    final int OUTSIDE_PEG_BOTTOM_ROW = (30 + outerPegAdd)/encoderClicks;//up 9
    final int INSIDE_PEG_BOTTOM_ROW = (38 + innerPegAdd)/encoderClicks;//up 10
    final int OUTSIDE_PEG_MIDDLE_ROW = (66 + outerPegAdd)/encoderClicks;//up 9
    final int INSIDE_PEG_MIDDLE_ROW = (74 + innerPegAdd)/encoderClicks;//up 10
    final int OUTSIDE_PEG_TOP_ROW = (102 + outerPegAdd)/encoderClicks;// up 9
    final int INSIDE_PEG_TOP_ROW = (110 + innerPegAdd)/encoderClicks;//up 10
    private double encoderPosition;
    RoboDrive robo;
    public Joystick towerStick;
    private Joystick otherStick;
    private Jaguar towerJaguar;
    private Victor towerVictor;
    
    private boolean isMovingTower = false;
            //set the0 values above depending on the encoder
    private boolean manual = false;

    private DigitalInput upperLimitSwitch;
    private DigitalInput lowerLimitSwitch;
    private DigitalInput pegSwitch;
    private Encoder towerHeight;

//    public TowerOfPower(int upperChannel, int lowerChannel, int pegSwitchChannel, int jaguarChannel, Joystick joy)
//    {
//        upperLimitSwitch = new DigitalInput(upperChannel);
//        lowerLimitSwitch = new DigitalInput(lowerChannel);
//        pegSwitch = new DigitalInput(pegSwitchChannel);
//        towerHeight = new Encoder(1,10);//this could generate errors
//        towerHeight.reset();
//        towerHeight.setDistancePerPulse(18.0 / 1000.0);
//        towerStick = joy;
////        try
////        {
//            towerJaguar = new Jaguar(jaguarChannel);
////        }
////        catch(CANTimeoutException e)
////        {
////
////        }
////        catch (CANNotInitializedException e)
////        {
////
////        }
//    }
    /**
     * Declares the Joystick and DigitalInput methods
     * @param jagNum - The number of the Jaguar
     * @param joy - What the towerStick equals
     * @param lowerChannel - The lowerLimitSwitch
     * @param upperChannel - The upperLimitSwitch
     */
    public TowerOfPower(int jagNum, Joystick tower, Joystick other, DigitalInput lowerChannel, DigitalInput upperChannel)
    {
        towerStick = tower;
        otherStick = other;
        lowerLimitSwitch = lowerChannel;
        upperLimitSwitch = upperChannel;
//        try
//        {
//            towerJaguar = new Jaguar(jagNum);
            towerVictor = new Victor(jagNum);
//        }
//        catch(CANTimeoutException e)
//        {
//
//        }
//        catch (CANNotInitializedException e)
//        {
//
//        }
    }
    /**
     * Tells the TowerOfPower to be controlled automatically
     */
    public void automaticControl()
    {//When the amount that the tower has moved is equal to the height of a peg, it stops moving.
        if(upperLimitSwitch.get()||lowerLimitSwitch.get())
        {
//            towerJaguar.set(0.0);
            towerVictor.set(0);
        }
        if(towerStick.getRawAxis(robo.leftJoystickYAxisNumber)>.6)
        {
            isMovingTower = true;
            if(isMovingTower == true)
            {
                if(isAtPeg())
                {
//                    towerJaguar.set(0);
                    towerVictor.set(0);
                    isMovingTower = false;
                }
                else
                {
//                    towerJaguar.set(1);
                    towerVictor.set(1);
                }
            }
        }
        else if(towerStick.getRawAxis(robo.leftJoystickYAxisNumber)<-0.6)
        {
            isMovingTower = true;
            if(isMovingTower == true)
            {
                if(isAtPeg())
                {
//                    towerJaguar.set(0);
                    towerVictor.set(0);
                    isMovingTower = false;
                }
                else
                {
//                    towerJaguar.set(-0.8);
                    towerVictor.set(-0.8);
                }
            }
        }
    }
    /**
     * Tells what peg the claw is at
     * @return - Top, Middle, or Bottom Row; Inside or Outside Peg
     */
    public boolean isAtPeg() {
        return (towerHeight.getDistance() == OUTSIDE_PEG_BOTTOM_ROW||
                towerHeight.getDistance() == OUTSIDE_PEG_MIDDLE_ROW||
                towerHeight.getDistance() == OUTSIDE_PEG_TOP_ROW) 
                && pegSwitch.get()
                ||
                (towerHeight.getDistance() == INSIDE_PEG_BOTTOM_ROW||
                towerHeight.getDistance() == INSIDE_PEG_MIDDLE_ROW||
                towerHeight.getDistance() == INSIDE_PEG_TOP_ROW)
                && !pegSwitch.get();
    }
    /**
     * Tells what the joystick axis is
     */
    public void manualJoystick()
    {
        //it sets the towerStick based on the limit switched and towerJaguar
        if(otherStick.getRawButton(8))
            manual= !manual;
        if(upperLimitSwitch.get()&&!manual)
        {
//            isMovingTower = false;
            if(towerStick.getX()>0)
            {
//               towerJaguar.set(getMagnitude(-towerStick.getRawAxis(4)));
               towerVictor.set(-towerStick.getX());
            }
           else
            {
//                towerJaguar.set(0);
                towerVictor.set(0);
            }
        }
        else if(!lowerLimitSwitch.get()&&!manual)
        {
//            isMovingTower = false;
            if(towerStick.getX()<0)
            {
//                towerJaguar.set(getMagnitude(-towerStick.getRawAxis(4)));
               towerVictor.set(-towerStick.getX());
            }
            else
            {
//                towerJaguar.set(0);
                towerVictor.set(0);
            }
        }
        else
        {
//            isMovingTower = false;
//            towerJaguar.set(getMagnitude(-towerStick.getRawAxis(4)));
            towerVictor.set(-towerStick.getX());
       }
    }
    /**
     * Tells whether the lower limit switch is hit
     * @return True if it hits the upper limit switch
     */
    public boolean getUpperSwitch()
    {
        return upperLimitSwitch.get();
    }
    /**
     * Tells whether the lower limit switch is hit
     * @return True if it hits the lower limit switch
     */
    public boolean getLowerSwitch()
    {
        return lowerLimitSwitch.get();
    }
    /**
     * Raises the tower of power until it hits the upper limit switch
     */
    public void raiseToTop()
    {
        if(!upperLimitSwitch.get())
        {
//            towerJaguar.set(1);
            towerVictor.set(1);
        }
        else
        {
            System.out.println("at top at ");//+robo.getTime());
//            towerJaguar.set(0);
            towerVictor.set(0);
        }
    }
    private double getMagnitude(double joyVal)
    {
       double mag;
       mag = joyVal;
       if(mag<=.005&&mag>=-.005)
           mag = 0;
       else
       {
           if(mag>1)
               mag = 1;
           else if(mag <-1)
               mag = -1;
        }
       return mag;
    }
//    private double rise(int input)
//    {
//        if(inStartPosition)
//        {
//            switch(input)
//            {
//                //chooses the peg that
//                //each peg is given a number starting at the top left which is one, and ending on the bottom right which is 9
//                /**
//                 * 1  2  3  --top row
//                 * 4  5  6  --middle row
//                 * 7  8  9  --bottom row
//                 */
//                case 1:
//                    return OUTSIDE_PEG_TOP_ROW;
//                case 2:
//                    return INSIDE_PEG_TOP_ROW;
//                case 3:
//                    return OUTSIDE_PEG_TOP_ROW;
//                case 4:
//                    return OUTSIDE_PEG_MIDDLE_ROW;
//                case 5:
//                    return INSIDE_PEG_MIDDLE_ROW;
//                case 6:
//                    return OUTSIDE_PEG_MIDDLE_ROW;
//                case 7:
//                    return OUTSIDE_PEG_BOTTOM_ROW;
//                case 8:
//                    return INSIDE_PEG_BOTTOM_ROW;
//                case 9:
//                    return OUTSIDE_PEG_BOTTOM_ROW;
//                default:
//                    //deal with erroneous numbers here
//            }
//        }
//        else
//        {
//            switch(input)
//                {
//                case 1:
//                        return OUTSIDE_PEG_TOP_ROW - towerHeight.getDistance();
//                    case 2:
//                        return INSIDE_PEG_TOP_ROW - towerHeight.getDistance();
//                    case 3:
//                        return OUTSIDE_PEG_TOP_ROW - towerHeight.getDistance();
//                    case 4:
//                        return OUTSIDE_PEG_MIDDLE_ROW - towerHeight.getDistance();
//                    case 5:
//                        return INSIDE_PEG_MIDDLE_ROW - towerHeight.getDistance();
//                    case 6:
//                        return OUTSIDE_PEG_MIDDLE_ROW - towerHeight.getDistance();
//                    case 7:
//                        return OUTSIDE_PEG_BOTTOM_ROW - towerHeight.getDistance();
//                    case 8:
//                        return INSIDE_PEG_BOTTOM_ROW - towerHeight.getDistance();
//                    case 9:
//                        return OUTSIDE_PEG_BOTTOM_ROW - towerHeight.getDistance();
//                    default:
//                        //deal with erroneous numbers here
//                }
//        }
//        return 0;
//    }
}
