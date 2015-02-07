
package com.fairportfirst.templates;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    /* CREATING BUTTONS
    One type of button is a joystick button which is any button on a joystick.
    You create one by telling it which joystick it's on and which button
    number it is.
    Joystick stick = new Joystick(port);
    Button button = new JoystickButton(stick, buttonNumber);
    
    Another type of button you can create is a DigitalIOButton, which is
    a button or switch hooked up to the cypress module. These are useful if
    you want to build a customized operator interface.
    Button button = new DigitalIOButton(1);
    
    There are a few additional built in buttons you can use. Additionally,
    subclassing Button you can create custom triggers and bind those to
    commands the same as any other Button.
    
    TRIGGERING COMMANDS WITH BUTTONS
    Once you have a button, it's trivial to bind it to a button in one of
    three ways:
    
    Start the command when the button is pressed and let it run the command
    until it is finished as determined by it's isFinished method.
    button.whenPressed(new ExampleCommand());
    
    Run the command while the button is being held down and interrupt it once
    the button is released.
    button.whileHeld(new ExampleCommand());
    
    Start the command when the button is released  and let it run the command
    until it is finished as determined by it's isFinished method.
    button.whenReleased(new ExampleCommand());*/
    
    static Joystick leftStick  = new Joystick(RobotMap.LEFT_JOYSTICK_CHANNEL);
    static Joystick rightStick = new Joystick(RobotMap.RIGHT_JOYSTICK_CHANNEL);
    static Joystick operatorStick = new Joystick(RobotMap.OPERATOR_JOYSTICK_CHANNEL);
    static Button shifterButton = new JoystickButton(rightStick, RobotMap.JOYSTICK_SHIFTER_BUTTON);
    
    static double intercept = 0.2;
    static double curvature = 0.2;
    static double deadZone = 0.1;
    
    public void setClimbMode(String climbMode) {
        if(climbMode.equals("automatic")) {
            //TODO: Instantiate button to auto climb here
        } else if(climbMode.equals("manuel")) {
            //TODO: 
        }
    }
    
    public static double getLeftDrive() {
        return leftStick.getY();        
    }
    public static double getRightDrive() {
        return rightStick.getY();
    }
    
    public static double getRightSimboticsDrive(){
        return rightStick.getY() * rightStick.getY();
    }
    
    public static double getLeftSimboticsDrive(){
        return leftStick.getY() * leftStick.getY();
    }
    
    /*
     * Optimized drive for right stick.
     * 
     * http://www.chiefdelphi.com/media/papers/2421 
     * 
     * More info ^^
     * 
     */
    
    public static double getRightOptimizedDrive(){
        
        double output = 0.0;
        double input = rightStick.getY();
        
        if(input < deadZone && input > -deadZone){ // Is it in the dead zone?
            return output;
        }else{
            if(input > 0){
                output = (intercept + (1-intercept)*(curvature* 
                        input * input * input + (1-curvature)*input));
            }else{
                output = (-intercept + (1-intercept)*(curvature* 
                        input * input * input + (1-curvature)*input));
            }
            return output;
        }
        
    }
    
    /*
     * Optimized drive for left stick.
     * 
     * http://www.chiefdelphi.com/media/papers/2421 
     * 
     * More info ^^
     * 
     */
    
    public static double getLeftOptimizedDrive(){
        
        double output = 0.0;
        double input = leftStick.getY();
        
        if(input < deadZone && input > -deadZone){
            return output;
        }else{
            if(input > 0){
                output = (intercept + (1-intercept)*(curvature* 
                        input * input * input + (1-curvature)*input));
            }else{
                output = (-intercept + (1-intercept)*(curvature* 
                        input * input * input + (1-curvature)*input));
            }
            return output;
        }
    }
}
