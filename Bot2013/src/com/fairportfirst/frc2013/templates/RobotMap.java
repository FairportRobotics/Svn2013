package com.fairportfirst.frc2013.templates;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // SHOOTER CHANNELS
    public static final int SHOOTER_FRONT_JAGUAR_ID = 12;
    public static final int SHOOTER_REAR_JAGUAR_ID = 14;
    public static final int FEEDER_SOLENOID_CHANNEL = 7;
    public static final int SHOOTER_ELEVATION_SOLENOID_CHANNEL = 1;
    
    // DRIVE JAGUAR ID'S
    public static final int DRIVE_LEFT_FRONT_JAGUAR_ID = 3;
    public static final int DRIVE_RIGHT_BACK_JAGUAR_ID = 11;
    public static final int DRIVE_LEFT_BACK_JAGUAR_ID = 2;
    public static final int DRIVE_RIGHT_FRONT_JAGUAR_ID = 5;    
    public static final int SHIFTER_HIGH_SPEED_SOLENOID_CHANNEL = 3;
    public static final int SHIFTER_HIGH_TORQUE_SOLENOID_CHANNEL = 4;
    
    
    // COMPRESSOR CHANNELS
    public static final int COMPRESSOR_SWITCH_CHANNEL = 1;
    public static final int COMPRESSOR_RELAY_CHANNEL = 1;
    
    // INPUT CHANNELS
    public static final int LEFT_JOYSTICK_CHANNEL = 1;
    public static final int RIGHT_JOYSTICK_CHANNEL = 2;
    public static final int JOYSTICK_SHIFTER_BUTTON = 4;
    public static final int JOYSTICK_CLIMER_AUTO_BUTTON = 8;
    public static final int JOYSTICK_CLIMBER_GRAB_BUTTON = 9;
    public static final int JOYSTICK_CLIMBER_RETRACT_BUTTON = 10;
    public static final int JOYSTICK_FEED_SHOOTER_BUTTON = 1;
    public static final int JOYSTICK_TOGGLE_SHOOTER_WHEELS_BUTTON = 3;

    //Operter buttons
    public static final int OPERATOR_JOYSTICK_CHANNEL = 3;
    public static final int OPERATOR_SHOOTER_DOWN_BUTTON = 1;
    public static final int OPERATOR_SHOOTER_THREE_POINT_BUTTON = 2;
    public static final int OPERATOR_WINCH_EXTEND_BUTTON = 8;
    public static final int OPERATOR_WINCH_RETRACT_BUTTON = 6;
    public static final int OPERATOR_PYRAMID_ALIGN_BUTTON = 10;
    
    // CLIMBING CHANNELS
    public static final int BOOM_EXTEND_SOLENOID_CHANNEL = 5;
    public static final int BOOM_RETRACT_SOLENOID_CHANNEL = 6;
    public static final int CLIMBING_ARM_ANGLE_SOLENOID_CHANNEL = 4;
    public static final int WINCH_JAGUAR_FRONT_ID = 4;
    public static final int WINCH_JAGUAR_BACK_ID = 10;
    public static final int POTENTIOMETER_CHANNEL = 1;
    public static final int RETRACT_LIMIT_SWITCH_CHANNEL = 2;
    
    // CAMERA IP ADDRESS
    public static final String CAMERA_ADDRESS = "10.5.78.11";
    
    
    // Debug mode button binds
    
        //Shooter
        public static final int DEBUG_FEEDER_TOGGLE_BUTTON = 1;
        public static final int DEBUG_FRONT_SHOOTER_BUTTON = 2;
        public static final int DEBUG_BACK_SHOOTER_BUTTON = 3;
        public static final int DEBUG_LIFTER_BOTTOM_BUTTON = 4;
        public static final int DEBUG_LIFTER_UPPER_BUTTON = 5;
        
        //Drive
        public static final int DEBUG_SHIFTER_TOGGLE_BUTTON = 6;
        
        //Winch
        public static final int DEBUG_WINCH_TOGGLE_BUTTON = 7;
    
    
}
