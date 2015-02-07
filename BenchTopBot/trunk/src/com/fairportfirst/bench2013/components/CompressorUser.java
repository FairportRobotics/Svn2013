/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.fairportfirst.bench2013.components;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.SensorBase;
import edu.wpi.first.wpilibj.parsing.IDevice;

/**
 *
 * @author Alec
 */
public class CompressorUser {
    private Compressor mrCompressor;

    /**Constructor
     *
     * @param compresserSwitchChannel channel that the safety switch is on
     * @param compressorRelayChannel channel that the relay is on, this controls
     * the compressor on and off
     */
    public CompressorUser(int compresserSwitchChannel, int compressorRelayChannel) {
        mrCompressor = new Compressor(compresserSwitchChannel, compressorRelayChannel);
        mrCompressor.start();//Compressor runs it OWN System so you just need to
            //start it. It is self regulating
    }
}

