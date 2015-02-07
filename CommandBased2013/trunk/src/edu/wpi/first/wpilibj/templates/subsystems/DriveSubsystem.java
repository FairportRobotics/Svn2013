/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.templates.RobotMap;
import edu.wpi.first.wpilibj.templates.commands.DriveCommand;

/**
 *
 * @author team578
 */
public class DriveSubsystem extends Subsystem{
    
    private CANJaguar jagz;
    
    private static DriveSubsystem instance;
    
    public DriveSubsystem(){
        try{
            jagz = new CANJaguar(RobotMap.DRIVE_JAGUAR_ID);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void drive(double speed){
        try {
            jagz.setX(speed);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }
    
    public void smoothDrive(double increase){
        double setSpeed=0;
        String error = "";
        try {
            setSpeed = jagz.getX()+increase;
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
        if(setSpeed > 1.0){
            setSpeed=1;
        } else if(setSpeed < -1.0){
            setSpeed=-1;
        }
        error = setSpeed+"";
        try {
            jagz.setX(setSpeed);
        } catch (CANTimeoutException ex) {
            error = "error";
            ex.printStackTrace();
        }
        SmartDashboard.putString("drive", error);
    }
    
    protected void initDefaultCommand() {
        setDefaultCommand(new DriveCommand());
    }
    
    public static DriveSubsystem getInstance(){
        if(instance == null){
            instance = new DriveSubsystem();
        }
        
        return instance;
    }
    
}
