/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fairportfirst.templates.subsystems;

import com.fairportfirst.templates.RobotMap;
import com.fairportfirst.templates.commands.DriveManualCommand;
import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Solenoid;

/**
 *
 * @author team578
 */
public class DriveSubsystem extends Subsystem{
    
    private CANJaguar leftMotorControllBack;
    private CANJaguar leftMotorControllFront;
    private CANJaguar rightMotorControllFront;
    private CANJaguar rightMotorControllBack;
    
    private boolean isHighSpeed;
    private Solenoid shifterSolenoid;
    
    private boolean disabledForClimb = false;
    private static DriveSubsystem instance;
    
    public DriveSubsystem(){
        
        SmartDashboard.putBoolean("Optimized Drive", false);
        
        try{
            leftMotorControllFront = new CANJaguar(RobotMap.DRIVE_LEFT_FRONT_JAGUAR_ID);
            rightMotorControllFront = new CANJaguar(RobotMap.DRIVE_RIGHT_FRONT_JAGUAR_ID);
            rightMotorControllBack = new CANJaguar(RobotMap.DRIVE_RIGHT_BACK_JAGUAR_ID);
            leftMotorControllBack = new CANJaguar(RobotMap.DRIVE_LEFT_BACK_JAGUAR_ID);
        }catch(Exception e){
            e.printStackTrace();
        }
        shifterSolenoid = new Solenoid(RobotMap.SHIFTER_SOLENOID_CHANNEL);
        shiftHighSpeed();
    }
    
    public void drive(double leftSpeed, double rightSpeed){
        if(!disabledForClimb) {
            try {
                leftMotorControllFront.setX(leftSpeed);
                leftMotorControllBack.setX(-leftSpeed);
                rightMotorControllFront.setX(-rightSpeed);
                rightMotorControllBack.setX(rightSpeed);
            } catch (CANTimeoutException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public double getLeftSpeed(){
        try {
            return leftMotorControllFront.getX();
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
        return 0.0;
    }
    
    public double getRightSpeed(){
        try {
            return rightMotorControllFront.getX();
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
        return 0.0;
    }
    
    public void shiftHighSpeed(){
        if(!disabledForClimb) {
            isHighSpeed = true;
            shifterSolenoid.set(true);
            SmartDashboard.putNumber("Shifter", isHighSpeed?1:-1);
        }
    }
    
    public void shiftLowSpeed(){
        if(!disabledForClimb) {
            isHighSpeed = false;
            shifterSolenoid.set(false);
            SmartDashboard.putNumber("Shifter", isHighSpeed?1:-1);
        }
    }
    
    public boolean IsHighSpeed(){
        return isHighSpeed;
    }
    
    public boolean getDriveDisabledForClimb() {
        return disabledForClimb;
    }
    
    public void disableDriveForClimb() {
        disabledForClimb = true;
    }
    
    public void enableDriveForClimb() {
        disabledForClimb = false;
    }
    
    protected void initDefaultCommand() {
        setDefaultCommand(new DriveManualCommand());
    }
    
    public static DriveSubsystem getInstance(){
        if(instance == null){
            instance = new DriveSubsystem();
        }
        
        return instance;
    }
}
