package com.fairportfirst.frc2013.templates.subsystems;

import com.fairportfirst.frc2013.templates.RobotMap;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * @author tyler
 */
public class ClimberSubsystem extends Subsystem {
    private static ClimberSubsystem instance;
    private DigitalInput retractLimitSwitch;
    
    private Solenoid boomExtendSolenoid;
    private Solenoid boomRetractSolenoid;
    private boolean hasRetracted = false;
    private boolean hasExtended = false;
    
    /**
     * Maps the boom solenoids.
     */
    public ClimberSubsystem() {
        retractLimitSwitch = new DigitalInput(RobotMap.RETRACT_LIMIT_SWITCH_CHANNEL);
        boomExtendSolenoid = new Solenoid(RobotMap.BOOM_EXTEND_SOLENOID_CHANNEL);
        boomRetractSolenoid = new Solenoid(RobotMap.BOOM_RETRACT_SOLENOID_CHANNEL);
    }
    
    public void initDefaultCommand() {
    }
    
    /**
     * Extends the boom.
     */
    public void ExtendBoom(){
        if(!hasExtended) {
            boomExtendSolenoid.set(false);
            boomRetractSolenoid.set(true);
            hasExtended = true;
            hasRetracted = false;
        }
    }
    
    /**
     * Retracts the boom.
     */
    public void RetractBoom(){
        if(!hasRetracted) {
            boomExtendSolenoid.set(true);
            boomRetractSolenoid.set(false);
            hasRetracted = true;
            hasExtended = false;
        }
    }
    
    public boolean getRetractLimitSwitchHit() {
        SmartDashboard.putBoolean("Limit Switch", retractLimitSwitch.get());
        return retractLimitSwitch.get();
    }
    
    /**
     * Gets the instance of the climber subsystem.
     * @return The climber subsystem.
     */
    public static ClimberSubsystem getInstance(){
        if(instance == null){
            instance = new ClimberSubsystem();
        }
        return instance;
    }
}
