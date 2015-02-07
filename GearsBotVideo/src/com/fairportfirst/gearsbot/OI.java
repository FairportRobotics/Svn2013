
package com.fairportfirst.gearsbot;

import com.fairportfirst.gearsbot.commands.CloseClaw;
import com.fairportfirst.gearsbot.commands.DriveToDistance;
import com.fairportfirst.gearsbot.commands.OpenClaw;
import com.fairportfirst.gearsbot.commands.SetElevatorSetpoint;
import com.fairportfirst.gearsbot.commands.SetWristSetpoint;
import com.fairportfirst.gearsbot.commands.SodaDelivery;
import com.fairportfirst.gearsbot.subsystems.Elevator;
import com.fairportfirst.gearsbot.subsystems.Wrist;
import com.sun.squawk.platform.posix.natives.Socket;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
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

    
    // NOTE: Use Logitech Extreme 3D Pro Joystick for buttons
    // Based on: http://www.youtube.com/watch?v=PpDX0CAcUNc
    Joystick stick = new Joystick(1);
    Button b1 = new JoystickButton(stick, 1);
    Button b2 = new JoystickButton(stick, 2);

    // Based on: http://www.youtube.com/watch?v=j2Xz8bRRcF0&feature=plcp
    Button b3 = new JoystickButton(stick, 3);
    Button b4 = new JoystickButton(stick, 4);
    Button b5 = new JoystickButton(stick, 5);
    Button b6 = new JoystickButton(stick, 6);

    // Based on: http://www.youtube.com/watch?v=j2Xz8bRRcF0&feature=plcp
    Button b7 = new JoystickButton(stick, 7);
    Button b8 = new JoystickButton(stick, 8);

    // based on: http://www.youtube.com/watch?v=5rJLi-QOflc
    Button b10 = new JoystickButton(stick, 10);
    
    public OI() {
        
        // Based on: http://www.youtube.com/watch?v=PpDX0CAcUNc
        b1.whenPressed(new OpenClaw());
        b2.whenPressed(new CloseClaw());
        
        // Based on: http://www.youtube.com/watch?v=j2Xz8bRRcF0&feature=plcp
        b3.whenPressed(new SetWristSetpoint(Wrist.PICKUP));
        b4.whenPressed(new SetWristSetpoint(Wrist.STOW));
        b5.whenPressed(new SetElevatorSetpoint(Elevator.BOTTOM));
        b6.whenPressed(new SetElevatorSetpoint(Elevator.STOW));

        // Based on: http://www.youtube.com/watch?v=j2Xz8bRRcF0&feature=plcp
        b7.whenPressed(new DriveToDistance(.1));
        b8.whenPressed(new DriveToDistance(.2));
        
        // based on: http://www.youtube.com/watch?v=5rJLi-QOflc
        // Runs entire teleop sequence
        b10.whenPressed(new SodaDelivery());

        
        
    }

   // based on: http://www.youtube.com/watch?v=BZ9l4DAaUAI&feature=plcp
    public double getLeftSpeed() {
        return stick.getY();
    }
        
    public double getRightSpeed() {
        return stick.getY();
    }
        
}

