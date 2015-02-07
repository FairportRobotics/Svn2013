
package com.fairportfirst.frc2014.subsystems;

import com.fairportfirst.frc2014.DriveMode;
import com.fairportfirst.frc2014.RobotMap;
import com.fairportfirst.frc2014.RobotTemplate;
import com.fairportfirst.frc2014.commands.TankDriveCommand;
import com.fairportfirst.frc2014.commands.ThreeAxisDriveCommand;
import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveSubsystem extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    static DriveSubsystem instance;
    
    CANJaguar frontLeft;
    CANJaguar frontRight;
    CANJaguar backLeft;
    CANJaguar backRight;
    
    public DriveSubsystem(){
        
        try{
            frontLeft = new CANJaguar(RobotMap.FRONT_LEFT_JAGUAR_ID);
            frontRight = new CANJaguar(RobotMap.FRONT_RIGHT_JAGUAR_ID);
            
            backLeft = new CANJaguar(RobotMap.BACK_LEFT_JAGUAR_ID);
            backRight = new CANJaguar(RobotMap.BACK_RIGHT_JAGUAR_ID);
        }catch(CANTimeoutException ex){
            ex.printStackTrace();
        }
    }
    
    public void driveTank(double left, double right){
        try {
            frontLeft.setX(left);
            frontRight.setX(right);
            
            backLeft.setX(left);
            backRight.setX(right);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
        
        
    }

    
    public void driveThreeAxis(double frontBack, double leftRight){
        try {            
            frontLeft.setX(frontBack * -leftRight);
            frontRight.setX(frontBack * leftRight);
            
            backLeft.setX(frontBack * -leftRight);
            backRight.setX(frontBack * leftRight);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
        
    }
    
    public void spin(double speed){
        try {
            frontLeft.setX(speed);
            frontRight.setX(-speed);
            
            backLeft.setX(speed);
            backRight.setX(-speed);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
        
        
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
        
        if(RobotTemplate.getDriveMode() == DriveMode.tankDrive){
            setDefaultCommand(new TankDriveCommand());
        }else{
            setDefaultCommand(new ThreeAxisDriveCommand());
        }
        
    }
    
    public static DriveSubsystem getInstance(){
        if(instance == null){
            instance = new DriveSubsystem();
        }
        
        return instance;
    }
}

