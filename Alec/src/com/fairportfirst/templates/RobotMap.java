package com.fairportfirst.templates;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * round.
 */
public class RobotMap {
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static final int leftMotor = 1;
    // public static final int rightMotor = 2;
    
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static final int rangefinderPort = 1;
    // public static final int rangefinderModule = 1;
    
    public static final String CAMERA_ADDRESS = "10.5.78.12";
    
    public static final int SHOOTER_JAGUAR_CHANNEL = 1;
    public static final int SHOOTER_JAGUAR_MODULE = 1;
    
    public static final int DRIVE_LEFT_JAGUAR_ID = 13;
    public static final int DRIVE_RIGHT_JAGUAR_ID = 12;
    
    public static final int COMPRESSOR_SWITCH_CHANNEL = 1;
    public static final int COMPRESSOR_RELAY_CHANNEL = 2;
    
    public static final int CATHODE_RELAY_CHANNEL = 1;
    public static final int joystickChannel = 1;
    
    public static final int POTENTIOMETER_CHANNEL = 1;
}
