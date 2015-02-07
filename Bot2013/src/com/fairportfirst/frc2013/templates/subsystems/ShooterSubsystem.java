package com.fairportfirst.frc2013.templates.subsystems;

import com.fairportfirst.frc2013.templates.DriveMode;
import com.fairportfirst.frc2013.templates.RobotMap;
import com.fairportfirst.frc2013.templates.commands.CommandBase;
import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * @author Stefen
 */
public class ShooterSubsystem extends Subsystem
{
    private CANJaguar shooterFrontJag;
    private CANJaguar shooterRearJag;
    private Solenoid shooterFeeder;
    private Solenoid shooterElevation;
    private static ShooterSubsystem instance;
    public final int MAX_RPM=4000;
    public final int THREE_POINT_SHOOT_RPM = 3500;
    public final int PYRAMID_SHOOT_RPM = 1000;
    private double shootSetPointFront = 0;
    private double shootSetPointRear = 0;
    public final double FEED_TIME = 0.1;
    private final double TOLERANCE_RPM_PERCENT = 0.1;
    private double shootSpeedFront = 0.7;
    private double shootSpeedRear = 0.7;
    
    /**
     * 
     * Construct new Shooter subsystem
     * 
     * Holds shooter subsystem. 
     */    
    public ShooterSubsystem() {
        try {
            shooterFrontJag = new CANJaguar(RobotMap.SHOOTER_FRONT_JAGUAR_ID);
            shooterFrontJag.configEncoderCodesPerRev(360);
            shooterFrontJag.setSpeedReference(CANJaguar.SpeedReference.kEncoder);
            
            shooterRearJag = new CANJaguar(RobotMap.SHOOTER_REAR_JAGUAR_ID);
            shooterRearJag.configEncoderCodesPerRev(360);
            shooterRearJag.setSpeedReference(CANJaguar.SpeedReference.kEncoder);            
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
        shooterFeeder = new Solenoid(RobotMap.FEEDER_SOLENOID_CHANNEL);
        shooterElevation = new Solenoid(RobotMap.SHOOTER_ELEVATION_SOLENOID_CHANNEL);
//        setFeederOff();
    }

    protected void initDefaultCommand() {
        
    }
    
    /**
     * Sets the shooter setpoint to the given value.
     * @param setPoint The setpoint value.
     */
    public void setShootSetPointFront(int setPoint){
        shootSetPointFront = setPoint;
    }
    
    public void setShootSetPointRear(int setPoint){
        shootSetPointRear = setPoint;
    }
    
    public void setShootSetPointBoth(int setPoint) {
        setShootSetPointFront(setPoint);
        setShootSetPointRear(setPoint);
    }
    
    /**
     * Sets the jaguar values to one or zero.
     */
    public void spinWheelToSpeed(){
        try {
            if(isFrontEncoderDisabled()) {
                shooterFrontJag.setX(shootSpeedFront);
            } else if(shooterFrontJag.getSpeed()<shootSetPointFront){
                shooterFrontJag.setX(1.0);
            }else{
                shooterFrontJag.setX(0.0);
            }
            
            if(isRearEncoderDisabled()) {
                shooterRearJag.setX(shootSpeedRear);
            } else if(shooterRearJag.getSpeed()<shootSetPointRear){
                shooterRearJag.setX(1.0);
            }else{
                shooterRearJag.setX(0.0);
            }
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }
    
    /**
     * Set jaguar speed.
     * 
     * @param speed Speed for jaguar.
     */
    public void setSpeed(double speedFront, double speedBack)
    {
        try {
            shooterFrontJag.setX(speedFront);
            shooterRearJag.setX(speedBack);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }
    
    public boolean getIsSpinningUp() {
        return isFrontShooterOn() && isBackShooterOn();
    }
    
    public boolean isBackShooterOn(){
        try {
            return shooterRearJag.getX()>0.0;
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
    public boolean isFrontShooterOn(){
        try {
            return shooterFrontJag.getX()>0.0;
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
    /**
     * 
     * Sets the state of the solenoid to true, to grab a disc for the shooter.
     */
    public void setFeederOn()
    {
        shooterFeeder.set(true);
    }
    
    /**
     * 
     * Sets the state of the solenoid to false, to retract the feeder and pull 
     * the Frisbee into the wheels.
     */
    public void setFeederOff()
    {
        shooterFeeder.set(false);
    }
    
    /**
     * 
     * Gets the state of the solenoid, representing extended or retracted
     * for the state of the feeder used for loading discs into the shooter
     * 
     * @return Returns the state of the Solenoid
     */
    public boolean getFeederState()
    {
        return shooterFeeder.get();
    }
        
    /**
     * Turns the upper elevator solenoid on.
     */
    public void setElevatorLowerOn()
    {
        shooterElevation.set(true);
    }

    /**
     * Turns the lower 
     */
    public void setElevatorLowerOff(){
        shooterElevation.set(false);
    }
   
    /**
     * Sets the solenoids raising the shooter into the position that can score 
     * three point shots.
     */
    public void shootPositionThreePoint() {
        setElevatorLowerOn();
    }
    
    public boolean getLowerElevationState(){
        return shooterElevation.get();
    }
    
    public double getWantedShootSpeedFront(){
        if(CommandBase.getDriveMode() == DriveMode.DEMO){
            return -0.40;
        }
        return shootSpeedFront;
    }
    
    public double getWantedShootSpeedRear(){
        if(CommandBase.getDriveMode() == DriveMode.DEMO){
            return -0.40;
        }
        return shootSpeedRear;
    }
    
    public void setShootSpeedFront(double speed){
        shootSpeedFront = speed;
    }
    
    public void setShootSpeedBack(double speed){
        shootSpeedRear = speed;
    }
    
    public double getShootSpeedFrontJag() {
        try {
            return shooterFrontJag.getSpeed();
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        } catch(NullPointerException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    
    public double getShootSpeedRearJag() {
        try {
            return shooterRearJag.getSpeed();
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        } catch(NullPointerException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    /**
     * Checks to see if the shooter is up to full speed
     * @return boolean
     */
    public boolean isUpToShootSpeed()
    {
//        return isWithinToleranceRPM(getShootSpeedFrontJag(), shootSetPointFront) && isWithinToleranceRPM(getShootSpeedRearJag(), shootSetPointRear);
        return true;
    }
    
    private boolean isWithinToleranceRPM(double rpm, double wantedRPM) {
        double percentError = Math.abs(wantedRPM - rpm)/wantedRPM;
        return percentError < TOLERANCE_RPM_PERCENT;
    }
    
    public boolean isFrontEncoderDisabled() {
        return getIsSpinningUp() && getShootSpeedFrontJag()==0;
    }
    
    public boolean isRearEncoderDisabled() {
        return getIsSpinningUp() && getShootSpeedRearJag()==0;
    }
    
    public void turnShooterWheelsOff() {
        setSpeed(0, 0);
    }
    
    /**
     * 
     * Get instance of Shooter subsystem. This also null checks.
     * 
     * @return Returns instance of ShooterSubsystem.
     */
    public static ShooterSubsystem getInstance()
    {
        if(instance == null)
        {
            instance = new ShooterSubsystem();
        }
        return instance;
    }
}