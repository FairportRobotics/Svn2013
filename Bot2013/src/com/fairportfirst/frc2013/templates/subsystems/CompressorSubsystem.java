package com.fairportfirst.frc2013.templates.subsystems;

import com.fairportfirst.frc2013.templates.RobotMap;
import com.fairportfirst.frc2013.templates.commands.CompressorStartCommand;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * @author Tyler
 */
public class CompressorSubsystem extends Subsystem{
    
    private static CompressorSubsystem instance;
    private Compressor compressor;
    private int timeRun = 0;
    
    /**
     * Maps the compressor.
     */
    public CompressorSubsystem(){
        compressor = new Compressor(RobotMap.COMPRESSOR_SWITCH_CHANNEL, RobotMap.COMPRESSOR_RELAY_CHANNEL);
    }

    /**
     * Sets the compressor start command.
     */
    protected void initDefaultCommand() {
        setDefaultCommand(new CompressorStartCommand());
    }
    
    /**
     * Starts the compressor subsystem.
     */
    public void start(){
        if(!compressor.enabled()) {
            timeRun++;
        }
        SmartDashboard.putNumber("CompressorStart: ",timeRun);
        compressor.start();
    }
    
    /**
     * Gets the instance of the compressor subsystem.
     * @return The compressor subsystem.
     */
    public static CompressorSubsystem getInstance(){   
        if(instance == null){
            instance = new CompressorSubsystem();
        }
        return instance;
    } 
}
