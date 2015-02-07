package com.fairportfirst.frc2013.templates.subsystems;

import com.fairportfirst.frc2013.templates.RobotMap;
import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 * @author Tyler
 */
public class WinchSubsystem extends PIDSubsystem {
    private AnalogChannel pot;
    private CANJaguar winchFront;
    private CANJaguar winchBack;
    private static WinchSubsystem instance = null;
    
    private static final double MIN_POINT = 1;//Fully retracted
    private static final double MAX_POINT = 5;//Fully extended
    
    /**
     *  Instantiates the winch subsystem.
     */
    public WinchSubsystem() {
        super("Winch",2.3,0,0);//Sets PID values
        
        pot = new AnalogChannel(RobotMap.POTENTIOMETER_CHANNEL);
        
        //Maximum and Minimum values the winch can be.
        setInputRange(MIN_POINT, MAX_POINT); 
        
        setSetpoint(MIN_POINT);//By default retracted.
        
//        try {
//            winchFront = new CANJaguar(RobotMap.WINCH_JAGUAR_FRONT_ID);
//            winchBack = new CANJaguar(RobotMap.WINCH_JAGUAR_BACK_ID);
//        } catch (CANTimeoutException ex) {
//            ex.printStackTrace();
//        }
    }
    
    protected void initDefaultCommand() {
        
    }
    
    /**
     * Gets extended value.
     * @return The max extended value.
     */
    public static double getExtendedVal(){
        return MAX_POINT;
    }
    
    /**
     * Gets retracted value.
     * @return The minimum extended value.
     */
    public static double getRetractedVal(){
        return MIN_POINT;
    }
    
    /**
     * Enabled the winch.
     */
    public void enableWinch(){
        enable();//Turn on the PIDSubsystem.
    }
    
    /**
     * Gets the average voltage.
     * @return The average voltage/
     */
    protected double returnPIDInput() {
        return pot.getAverageVoltage();
    }

    /**
     * Sets the winch speed.
     * @param d Winch speed.
     */
    protected void usePIDOutput(double d) {
//        try {
//            winchFront.setX(d);
//            winchBack.setX(-d);
//        } catch (CANTimeoutException ex) {
//            ex.printStackTrace();
//        }
    }
    
    /**
     * Checks to see if the winch is at the setpoint.
     * @return boolean
     */
    public boolean atSetPoint(){
        return Math.abs(getPosition() - getSetpoint())<.1;
    }
    
    /**
     * Gets the winch subsystem instance.
     * @return The winch subsystem instance.
     */
    public static WinchSubsystem getInstance() {
        if(instance == null) {
            instance = new WinchSubsystem();
        }
        return instance;
    }
}
