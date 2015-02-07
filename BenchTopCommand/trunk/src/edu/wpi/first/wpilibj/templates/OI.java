
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.DigitalIOButton;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.templates.commands.CathodeOnCommand;
import edu.wpi.first.wpilibj.templates.commands.CathodePulseTime;
import edu.wpi.first.wpilibj.templates.commands.PulseCommands;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    
    private static double x = 0;
    private static boolean ascending = true;
    
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
    
    // Another type of button you can create is a DigitalIOButton, which is
    // a button or switch hooked up to the cypress module. These are useful if
    // you want to build a customized operator interface.
    // Button button = new DigitalIOButton(1);
    
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
    
       
    static Joystick stick  = new Joystick(RobotMap.joystickChannel);
    Button triggerButton = new JoystickButton(stick,RobotMap.stickTriggerButton);
    public OI() {
        triggerButton.whileActive(new CathodePulseTime());
    }
    
    public static double getJoystick() {
        double correctStick = Math.abs((1 - stick.getMagnitude()));
        
        if (x <= 5)
        {
            x++;
        }
        else
        {
            x = 0;
        }
        
        //x /= 10;
        System.out.println("X: " + x);
        
        //Divide magnitude by 10 to speed up interval.
//        double bothSticks = Math.abs(stick.getY() + stick.getX());
        return Math.abs(Math.cos(x));
    }
}

