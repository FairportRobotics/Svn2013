/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fairportfirst.templates.subsystems;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.fairportfirst.templates.RobotMap;
import com.fairportfirst.templates.commands.DriveCommand;

/**
 *
 * @author team578
 */
public class DriveSubsystem extends Subsystem{
    
    private CANJaguar leftDriveMotorController;
    private CANJaguar rightDriveMotorController;
    
    private static DriveSubsystem instance;
    
    public DriveSubsystem(){
        try{
//            leftDriveMotorController = new CANJaguar(RobotMap.DRIVE_LEFT_JAGUAR_ID);
//            rightDriveMotorController = new CANJaguar (RobotMap.DRIVE_RIGHT_JAGUAR_ID);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void drive(double speedL,double speedR){
        try {
            leftDriveMotorController.setX(speedL);
            rightDriveMotorController.setX(speedR);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }
    
    protected void initDefaultCommand() {
//        setDefaultCommand(new DriveCommand());
    }
    
    public static DriveSubsystem getInstance(){
        if(instance == null){
            instance = new DriveSubsystem();
        }
        
        return instance;
    }
    
}
