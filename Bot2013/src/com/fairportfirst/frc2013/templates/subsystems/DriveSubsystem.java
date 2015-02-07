package com.fairportfirst.frc2013.templates.subsystems;

import com.fairportfirst.frc2013.templates.RobotMap;
import com.fairportfirst.frc2013.templates.commands.DriveManualCommand;
import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * @author team578
 */
public class DriveSubsystem extends Subsystem{
    
    private CANJaguar leftMotorControllBack;
    private CANJaguar leftMotorControllFront;
    private CANJaguar rightMotorControllFront;
    private CANJaguar rightMotorControllBack;
    
    private boolean isHighSpeed;
    private Solenoid shifterHighSpeedSolenoid;    
    private Solenoid shifterHighTorqueSolenoid;
    private double winchExtendSpeed = 0.0;
    private double winchRetractSpeed = 0.0;
    
    private boolean disabledForClimb = false;
    private static DriveSubsystem instance;
    
    /**
     * Instantiates a new DriveSubsystem
     * Default shift speed is high(Speed)
     */    
    public DriveSubsystem(){
        
        SmartDashboard.putBoolean("Optimized Drive", false);
        SmartDashboard.putData(this);
        
        try{
            leftMotorControllFront = new CANJaguar(RobotMap.DRIVE_LEFT_FRONT_JAGUAR_ID);
            rightMotorControllFront = new CANJaguar(RobotMap.DRIVE_RIGHT_FRONT_JAGUAR_ID);
            rightMotorControllBack = new CANJaguar(RobotMap.DRIVE_RIGHT_BACK_JAGUAR_ID);
            leftMotorControllBack = new CANJaguar(RobotMap.DRIVE_LEFT_BACK_JAGUAR_ID);
        }catch(Exception e){
            e.printStackTrace();
        }
        shifterHighSpeedSolenoid = new Solenoid(RobotMap.SHIFTER_HIGH_SPEED_SOLENOID_CHANNEL);
        shifterHighTorqueSolenoid = new Solenoid(RobotMap.SHIFTER_HIGH_TORQUE_SOLENOID_CHANNEL);
        shiftHighSpeed();
    }
    
    /**
     * Set the speed of bot.
     * @param leftSpeed Speed to set left side of bot.
     * @param rightSpeed Speed to set right side of bot.
     */
    public void drive(double leftSpeed, double rightSpeed){
        if(!disabledForClimb) {
            try {
                leftMotorControllFront.setX(-leftSpeed);
                leftMotorControllBack.setX(-leftSpeed);
                rightMotorControllFront.setX(rightSpeed);
                rightMotorControllBack.setX(rightSpeed);
            } catch (CANTimeoutException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    /**
     * Get current speed of left jaguars.
     * @return double
     */
    public double getLeftSpeed(){
        try {
            return leftMotorControllFront.getX();
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
        return 0.0;
    }
    
    /**
     * Get current speed of right jaguars.
     * @return double
     */
    public double getRightSpeed(){
        
        try {
            return rightMotorControllFront.getX();
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
        return 0.0;
    }
    
    /**
     * Shift bot into high speed but low torque.
     */
    public void shiftHighSpeed(){
        if(!disabledForClimb) {
            isHighSpeed = true;
            shifterHighSpeedSolenoid.set(true);
            shifterHighTorqueSolenoid.set(false);
            SmartDashboard.putNumber("Shifter", isHighSpeed?1:-1);
        }
    }
    
    /**
     * Shift bot into low speed but high torque.
     */
    public void shiftLowSpeed(){
        if(!disabledForClimb) {
            isHighSpeed = false;
            shifterHighSpeedSolenoid.set(false);
            shifterHighTorqueSolenoid.set(true);
            SmartDashboard.putNumber("Shifter", isHighSpeed?1:-1);
        }
    }
    
    /**
     * Is the robot in high speed?
     * @return boolean Is high speed?
     */
    public boolean IsHighSpeed(){
        return isHighSpeed;
    }
    
    /**
     * Sets the drive manual command.
     */
    protected void initDefaultCommand() {
        setDefaultCommand(new DriveManualCommand());
    }
    
    public void setWinchExtendSpeed(double speed) {
        winchExtendSpeed = speed;
    }
    
    public void setWinchRetractSpeed(double speed) {
        winchRetractSpeed = speed;
    }
    
    public double getWinchExtendSpeed() {
        return winchExtendSpeed;
    }
    
    public double getWinchRetractSpeed() {
        return winchRetractSpeed;
    }
    
    /**
     * Gets the instance of the drive subsystem.
     * @return The drive subsystem.
     */
    public static DriveSubsystem getInstance(){
        if(instance == null){
            instance = new DriveSubsystem();
        } 
        return instance;
    }
}
