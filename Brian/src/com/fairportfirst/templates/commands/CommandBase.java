package com.fairportfirst.templates.commands;

import com.fairportfirst.templates.subsystems.ShooterSubsystem;
import com.fairportfirst.templates.subsystems.CompressorSubsystem;
import com.fairportfirst.templates.subsystems.DriveSubsystem;
import com.fairportfirst.templates.subsystems.SmartDashboardSubsystem;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.fairportfirst.templates.OI;

/**
 * The base for all commands. All atomic commands should subclass CommandBase.
 * CommandBase stores creates and stores each control system. To access a
 * subsystem elsewhere in your code in your code use CommandBase.exampleSubsystem
 * @author Author
 */
public abstract class CommandBase extends Command {

    public static OI oi;
    // Create a single static instance of all of your subsystems
    public static ShooterSubsystem shooterSubsystem = ShooterSubsystem.getInstance();
    public static SmartDashboardSubsystem smartDashboardSubsystem = SmartDashboardSubsystem.getInstance();
    public static CompressorSubsystem compressorSubsystem = CompressorSubsystem.getInstance();
    public static DriveSubsystem driveSubsystem = DriveSubsystem.getInstance();
    
    public static void init() {
        // This MUST be here. If the OI creates Commands (which it very likely
        // will), constructing it during the construction of CommandBase (from
        // which commands extend), subsystems are not guaranteed to be
        // yet. Thus, their requires() statements may grab null pointers. Bad
        // news. Don't move it.
        oi = new OI();

        // Show what command your subsystem is running on the SmartDashboard
        SmartDashboard.putData("Shooter Command",  new ShooterSpeedCommand());
        SmartDashboard.putData("Compressor Command", new CompressorStartCommand());
    }

    public CommandBase(String name) {
        super(name);
    }

    public CommandBase() {
        super();
    }
}
