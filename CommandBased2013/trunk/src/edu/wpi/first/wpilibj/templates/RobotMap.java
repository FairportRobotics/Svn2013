package edu.wpi.first.wpilibj.templates;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
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
    
    public static final int SHOOTER_JAGUAR_CHANNEL = 1;
    public static final int SHOOTER_JAGUAR_MODULE = 1;
    
    public static final int DRIVE_JAGUAR_ID = 13;
    
    public static final int COMPRESSOR_SWITCH_CHANNEL = 1;
    public static final int COMPRESSOR_REALAY_CHANNEL = 1;
    
    public static final int CATHODE_RELAY_CHANNEL = 1;
    public static final int joystickChannel = 1;
}
