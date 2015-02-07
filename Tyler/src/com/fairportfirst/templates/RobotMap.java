package com.fairportfirst.templates;

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
    
    
    //CHANGE ALL THESE VALUES RIGHT NOW.
    public static final int DRIVE_LEFT_FRONT_JAGUAR_ID = 12;
    public static final int DRIVE_RIGHT_BACK_JAGUAR_ID = 10;
    public static final int DRIVE_LEFT_BACK_JAGUAR_ID = 2;
    public static final int DRIVE_RIGHT_FRONT_JAGUAR_ID = 11;
    
    public static final int COMPRESSOR_SWITCH_CHANNEL = 1;
    public static final int COMPRESSOR_REALAY_CHANNEL = 1;
    
    public static final int LEFT_JOYSTICK_CHANNEL = 1;
    public static final int RIGHT_JOYSTICK_CHANNEL = 2;
    
    public static final int OPERATOR_JOYSTICK_CHANNEL = 3;
    
    public static final int JOYSTICK_SHIFTER_BUTTON = 2;
    
    public static final int SHIFTER_SOLENOID_CHANNEL = 1;
    
    public static final int WINCH_FRONT_JAGUAR_ID = 4;//We have to set this.
    public static final int WINCH_REAR_JAGUAR_ID = 5;//We have to set this.
    
    public static final int BOOM_EXTEND_SOLENOID_CHANNEL = 2;//We have to set this.
    public static final int BOOM_RETRACT_SOLENOID_CHANNEL = 3;//We have to set this.
    
    public static final int CLIMBING_ARM_ANGLE_SOLENOID_CHANNEL = 4;//We have to set this.
    
    public static final int WINCH_JAGUAR = 13;
    public static final int POTENTIOMETER_CHANNEL = 1;
    
    public static final String CAMERA_ADDRESS = "10.5.78.11";
    
    public static final int FEEDER_SOLENOID_CHANNEL = 2;
}
