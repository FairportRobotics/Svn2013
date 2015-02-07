
package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.templates.RobotMap;
import edu.wpi.first.wpilibj.templates.commands.ShootWithJoystickCommand;

/**
 *
 */
public class ShooterSubsystem extends PIDSubsystem {

    private CANJaguar shooterWheelOne;
    private CANJaguar shooterWheelTwo;
    private int setPointRPM = 0;
    
    
    private static final double P = 1.0;
    private static final double I = 0.0;
    private static final double D = 0.0;

    
    public ShooterSubsystem() {
        super("Shooter",P,I,D);
        
        try {
            shooterWheelOne = new CANJaguar(RobotMap.SHOOTER_JAG_ONE_ID);
            shooterWheelTwo = new CANJaguar(RobotMap.SHOOTER_JAG_TWO_ID);
            
            shooterWheelTwo.configEncoderCodesPerRev(360);
            shooterWheelTwo.setSpeedReference(CANJaguar.SpeedReference.kEncoder);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
        setInputRange(0, 4000.0);
//        setAbsoluteTolerance(0.00000001);
//        enable();
        disable();
        LiveWindow.addActuator("Shooter", "PID", getPIDController());
        LiveWindow.addSensor("Shooter", "ENC", shooterWheelTwo);
    }
    
    public void initDefaultCommand() {
        setDefaultCommand(new ShootWithJoystickCommand());
    }

    protected double returnPIDInput() {
        try {
            System.out.println("Shoot: " + shooterWheelTwo.getSpeed());
            return shooterWheelTwo.getSpeed();
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
        return 0.0;
    }
    
//    private double Normalize

    public void shoot() {
        SmartDashboard.putNumber("Wanted: ", setPointRPM);
        try {
            SmartDashboard.putNumber("Current", shooterWheelTwo.getSpeed());
            if(shooterWheelTwo.getSpeed()<setPointRPM) {
                shooterWheelOne.setX(1.0);
                shooterWheelTwo.setX(1.0);
            } else {
                shooterWheelOne.setX(0.0);
                shooterWheelTwo.setX(0.0);
            }
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }
    
    public void setSetpointRPM(int rpm) {
        setPointRPM = rpm;
    }
//    
    protected void usePIDOutput(double d) {
//        System.out.println("Output: " + d);
//        try {
//            shooterWheelTwo.setX(d);
//            shooterWheelOne.setX(d);
//        } catch (CANTimeoutException ex) {
//            ex.printStackTrace();
//        }
    }
}

