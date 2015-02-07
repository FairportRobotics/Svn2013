package com.fairportfirst.frc2013.templates.commands;

import com.fairportfirst.frc2013.templates.DebugMode;
import com.fairportfirst.frc2013.templates.DriveMode;
import com.fairportfirst.frc2013.templates.OI;
import com.fairportfirst.frc2013.templates.subsystems.CameraSubsystem;
import com.fairportfirst.frc2013.templates.subsystems.ClimberSubsystem;
import com.fairportfirst.frc2013.templates.subsystems.CompressorSubsystem;
import com.fairportfirst.frc2013.templates.subsystems.DriveSubsystem;
import com.fairportfirst.frc2013.templates.subsystems.NewWinchSubsystem;
import com.fairportfirst.frc2013.templates.subsystems.ShooterSubsystem;
import com.fairportfirst.frc2013.templates.subsystems.WinchSubsystem;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The base for all commands. All atomic commands should subclass CommandBase.
 * CommandBase stores creates and stores each control system. To access a
 * subsystem elsewhere in your code in your code use CommandBase.exampleSubsystem
 * @author FIRST
 */
public abstract class CommandBase extends Command {

    public static OI oi;
    protected static Preferences prefs = Preferences.getInstance();
    // Create a single static instance of all of your subsystems
    public static ShooterSubsystem shooterSubsystem = ShooterSubsystem.getInstance();
    public static CompressorSubsystem compressorSubsystem = CompressorSubsystem.getInstance();
    public static DriveSubsystem driveSubsystem = DriveSubsystem.getInstance();
    public static CameraSubsystem cameraSubsystem = CameraSubsystem.getInstance();
    public static ClimberSubsystem climberSubsystem = ClimberSubsystem.getInstance();
    public static WinchSubsystem winchSubsystem = WinchSubsystem.getInstance();
    public static NewWinchSubsystem newWinchSubsystem = NewWinchSubsystem.getInstance();
   
    private static int waitBeforeShootTime = 0;
    
    public final int LINEAR_DRIVE = 0;
    public final int RAMPED_DRIVE = 1;
    public final int SQUARE_DRIVE = 2;
    private static DriveMode driveMode = DriveMode.LINEAR;
    private DebugMode debugMode = DebugMode.NORMAL;
    
    public static void init() { 
        oi = new OI();
        SmartDashboard.putData(shooterSubsystem);
//        prefs;
    }

    public CommandBase(String name) {
        super(name);
    }

    public CommandBase() {
        super();
    }
    
    /**
     * Sets the user inputed values for autonomous
     */
    public static void initAutonomuseVals() {
        waitBeforeShootTime = prefs.getInt("waitBeforeShootTime", 0);
    }
    
    /**
     * Sets the user inputed values for teleop
     */
    public static void initPreferenceVals(){
        shooterSubsystem.setShootSpeedFront(prefs.getInt("shootSpeedFront", 70)/100.0);
        shooterSubsystem.setShootSpeedBack(prefs.getInt("shootSpeedRear", 70)/100.0);
        driveSubsystem.setWinchExtendSpeed(prefs.getInt("winchExtendSpeed", 35)/100.0);
        driveSubsystem.setWinchRetractSpeed(prefs.getInt("winchRetractSpeed", 35)/100.0);
    }
    
    /**
     * Gets the current debug mode.
     * 
     * @return Integer value for Debug mode
     */
    public DebugMode getDebugMode(){
        return debugMode;
    }
    
    /**
     * Set the drive mode
     * @param mode Integer value to set debug mode.
     */
    public void setDebugMode(DebugMode mode){
        debugMode = mode;
    }
    
    /**
     * Gets the current drive mode.
     * @return Integer value for drive ID.
     */
    public static DriveMode getDriveMode(){
        return driveMode;
    }
    
    /**
     * Set the drive mode.
     * @param mode Integer value to set the drive mode.
     */
    public static void setDriveMode(DriveMode mode){
        driveMode = mode;
    }
    
    /**
     * 
     * @return Time in seconds before the shooter shoots in autonomous  
     */
    public static int getWaitBeforeShootTime() {
        return waitBeforeShootTime;
    }
    
    /**
     * Displays values to the smartdashboard.
     */
    public static void displayValues() {
        SmartDashboard.putDouble("Shoot Front speed", shooterSubsystem.getShootSpeedFrontJag());
        SmartDashboard.putDouble("Shoot Rear speed", shooterSubsystem.getShootSpeedRearJag());
    }
}
