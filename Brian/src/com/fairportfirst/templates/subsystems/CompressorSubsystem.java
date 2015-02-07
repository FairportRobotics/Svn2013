/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fairportfirst.templates.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.fairportfirst.templates.RobotMap;
import com.fairportfirst.templates.commands.CompressorStartCommand;

/**
 *
 * @author Brian
 */
public class CompressorSubsystem extends Subsystem{
    
    private static CompressorSubsystem instance;
    private Compressor compressor;
    
    public CompressorSubsystem(){
        compressor = new Compressor(RobotMap.COMPRESSOR_SWITCH_CHANNEL, RobotMap.COMPRESSOR_REALAY_CHANNEL);
        System.out.println("WE HAVE FINALY >>>>>>>>>>>>>>>>>>>>>>>>>>.");
    }

    protected void initDefaultCommand() {
        setDefaultCommand(new CompressorStartCommand());
    }
    
    public void start(){
        compressor.start();
    }
    
    public boolean isEnabled(){
        return true;
    }
    
    public static CompressorSubsystem getInstance(){
        
        if(instance == null){
            instance = new CompressorSubsystem();
        }
        return instance;
    }

    
}
