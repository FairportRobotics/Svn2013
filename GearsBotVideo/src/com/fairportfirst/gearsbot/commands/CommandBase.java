package com.fairportfirst.gearsbot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.fairportfirst.gearsbot.OI;
import com.fairportfirst.gearsbot.subsystems.Claw;
import com.fairportfirst.gearsbot.subsystems.DriveTrain;
import com.fairportfirst.gearsbot.subsystems.Elevator;
import com.fairportfirst.gearsbot.subsystems.Wrist;

/**
 * The base for all commands. All atomic commands should subclass CommandBase.
 * CommandBase stores creates and stores each control system. To access a
 * subsystem elsewhere in your code in your code use CommandBase.exampleSubsystem
 * @author Author
 */
public abstract class CommandBase extends Command {

    public static OI oi;
    // Create a single static instance of all of your subsystems
    
    // Based on: http://www.youtube.com/watch?v=PpDX0CAcUNc
    public static Claw claw = new Claw();
    
    // Based on: http://www.youtube.com/watch?v=j2Xz8bRRcF0&feature=plcp
    public static Wrist wrist = new Wrist();
    public static Elevator elevator = new Elevator();
    
    // based on: http://www.youtube.com/watch?v=BZ9l4DAaUAI&feature=plcp
    public static DriveTrain drivetrain = new DriveTrain();    

    public static void init() {
        // This MUST be here. If the OI creates Commands (which it very likely
        // will), constructing it during the construction of CommandBase (from
        // which commands extend), subsystems are not guaranteed to be
        // yet. Thus, their requires() statements may grab null pointers. Bad
        // news. Don't move it.
        oi = new OI();

        // Show what command your subsystem is running on the SmartDashboard
        SmartDashboard.putData(claw);
        SmartDashboard.putData(wrist);
        SmartDashboard.putData(elevator);
    }

    public CommandBase(String name) {
        super(name);
    }

    public CommandBase() {
        super();
    }
}
