package com.fairportfirst.frc2013.templates;

import com.fairportfirst.frc2013.templates.commands.AutonomousShootCommand;
import com.fairportfirst.frc2013.templates.commands.AutonomousShootMoveCommand;
import com.fairportfirst.frc2013.templates.commands.CommandBase;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class RobotTemplate extends IterativeRobot {

    Command autonomousCommand;
//    Command compressorStart;
    
    SendableChooser autoChooser;
    SendableChooser climbModeChooser;
    SendableChooser driveModeChooser;
    SendableChooser debugModeChooser;
    
    private double matchTime = 0;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        // instantiate the command used for the autonomous period
        
        //Chooser for debug mode
        debugModeChooser = new SendableChooser();
        debugModeChooser.addDefault("Normal Mode", DebugMode.NORMAL);
        debugModeChooser.addObject("Debug Mode", DebugMode.DEBUG);
        SmartDashboard.putData("Debug Mode Chooser", debugModeChooser);
        
        // Chooser for autonomous modes.
        autoChooser = new SendableChooser();
        autoChooser.addDefault("Shoot", new AutonomousShootCommand());
        autoChooser.addObject("Shoot then Move", new AutonomousShootMoveCommand());
        SmartDashboard.putData("Autonomous Mode Chooser", autoChooser);
        
        // Chooser for climb modes.
        climbModeChooser = new SendableChooser();
        climbModeChooser.addDefault("Automatic climb", ClimbMode.AUTOMATIC);
        climbModeChooser.addObject("Manual climb", ClimbMode.MANUAL);
        SmartDashboard.putData("Climb Mode chooser", climbModeChooser);
        
        // Chooser for driver modes.
        driveModeChooser = new SendableChooser();
        driveModeChooser.addDefault("Linear Drive",DriveMode.LINEAR);
        driveModeChooser.addObject("Ramped Drive",DriveMode.RAMPED);
        driveModeChooser.addObject("Square Drive",DriveMode.SQUARE);
        driveModeChooser.addObject("Demo Drive Mode", DriveMode.DEMO);
        SmartDashboard.putData("Drive Mode", driveModeChooser);
        
        // Initialize all subsystems
        CommandBase.init();
        //
//        compressorStart = new CompressorStartCommand();
    }
    
    /**
     * Called once before autonomous.
     */
    public void autonomousInit() {
        CommandBase.initAutonomuseVals();
        autonomousCommand = (Command)autoChooser.getSelected();
        autonomousCommand.start();
//        compressorStart.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    /**
     * Called once before teleop is initialized.
     */
    public void teleopInit() {
        if(autonomousCommand!=null) {
            autonomousCommand.cancel();
        }
//        compressorStart.start();
        //DEBUG MODE ALWAYS FIRST...
        CommandBase.oi.setDebugMode((DebugMode)debugModeChooser.getSelected());
        CommandBase.oi.setClimbMode((ClimbMode)climbModeChooser.getSelected());
        CommandBase.setDriveMode((DriveMode)driveModeChooser.getSelected());
        CommandBase.initPreferenceVals();
        //Make sure it says we are not aligned 
        SmartDashboard.putBoolean("Aligned with Pyramid", false);
        matchTime = Timer.getFPGATimestamp()-15.0;//Subtract 15 seconds for 
        //autonomus so we are 15 seconds ahead
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        SmartDashboard.putString("Match Time: ", convertTimeToDMS());
        CommandBase.displayValues();  
    }
    
    /**
     * Converts cRIo time to something usable. cRio uses 1.5 system were 1 is 
     * minutes and 0.5 represents 30 seconds.
     * @return a String that represents time in the minutes:seconds format.
     */
    public String convertTimeToDMS() {
        double time = Timer.getFPGATimestamp() - matchTime;
        double doubleMin = time/60.0;
        int minutes = (int) (doubleMin);
        int seconds = (int) ((doubleMin-minutes)*60);
        return minutes + ":" + seconds;
    }
}
