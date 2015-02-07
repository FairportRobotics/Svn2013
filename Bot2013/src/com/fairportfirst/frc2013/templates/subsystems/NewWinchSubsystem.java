package com.fairportfirst.frc2013.templates.subsystems;

import com.fairportfirst.frc2013.templates.RobotMap;
import com.fairportfirst.frc2013.templates.commands.WinchStopCommand;
import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 * @author User
 */
public class NewWinchSubsystem extends Subsystem {
 
    private static NewWinchSubsystem instance = null;
    private CANJaguar winchFront;
    private CANJaguar winchBack;
    
    public NewWinchSubsystem(){
//          try {
////            winchFront = new CANJaguar(RobotMap.WINCH_JAGUAR_FRONT_ID);
////            winchBack = new CANJaguar(RobotMap.WINCH_JAGUAR_BACK_ID);
//        } catch (CANTimeoutException ex) {
//            ex.printStackTrace();
//        }
    }

    public void initDefaultCommand() {
        setDefaultCommand(new WinchStopCommand());
    }
    
    public void stopWinch(){
//        try {
//            winchFront.setX(0.0);
//            winchBack.setX(0.0);
//        } catch (CANTimeoutException ex) {
//            ex.printStackTrace();
//        }
    }
    
    public void setWinchSpeed(double winchSpeed){
//        try {
//            winchFront.setX(winchSpeed);
//            winchBack.setX(-winchSpeed);
//        } catch (CANTimeoutException ex) {
//            ex.printStackTrace();
//        }
    }
    
    public static NewWinchSubsystem getInstance(){
        if(instance==null){
            instance=new NewWinchSubsystem();
        }
        return instance;
    }
}
