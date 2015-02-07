/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.fairportfirst.bench2013.components;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Timer;

/**
 *
 * @author team578
 */
public class RelayUser
{
    /*HardWare Specs: Spike PWM plugged into digital sidecar relay section with
    black closest to the edge of the board, the pwm plugged into a spike with
    the black cable next to the 'b'*/

    private Relay mrRelay;//Sets up the variable
    private double timerStart = Timer.getFPGATimestamp();
    private double normalizedInput = 0;

    //Channel on the digital sidecar that this relay is plugged into
    public RelayUser(int channel)
    {
        mrRelay = new Relay(channel,1,Relay.Direction.kReverse);
    }

    /**
     *
     * @param joy a FIRST joystick
     * This method does what you want it to do in this case we flucuiate the
     * relay to pulsate based on the magnitude of the joystick
     */
    public void useWithJoyStick(Joystick joy)
    {
        System.out.println("trigger: " + joy.getTrigger());
        //It is getting the trigger status correctly
        if (joy.getTrigger())
        {
            normalizedInput = (joy.getY() + 1) / 2;
            System.out.println("normalizedInput: " + normalizedInput);
            //The input is reversed
            //We have a problem with the code turning the light on
            
            //If the delta time elapsed is greater than the min bound of time(seconds)
            //times the magnitude of the joystick [ 0,1.0]
            if(Timer.getFPGATimestamp() - timerStart>=1*normalizedInput)
            {
                //Turns the relay on
                mrRelay.set(Relay.Value.kOn);
                mrRelay.setDirection(Relay.Direction.kForward);

                //If the delta time is greater than the max bound of time
                if(Timer.getFPGATimestamp() - timerStart>=2*(normalizedInput - 1))
                {
                    //turns off the relay
                    mrRelay.set(Relay.Value.kOff);
                    mrRelay.setDirection(Relay.Direction.kReverse);
                    timerStart = Timer.getFPGATimestamp();//resets the time start
                }
            }
        }
    }
}
