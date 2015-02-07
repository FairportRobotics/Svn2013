
package com.fairportfirst.frc2013.templates;

import com.fairportfirst.frc2013.templates.commands.AutomaticClimbCommand;
import com.fairportfirst.frc2013.templates.commands.CameraAlignCommand;
import com.fairportfirst.frc2013.templates.commands.ClimbCommand;
import com.fairportfirst.frc2013.templates.commands.FeedShooterAtSpeedCommand;
import com.fairportfirst.frc2013.templates.commands.GrabRungCommand;
import com.fairportfirst.frc2013.templates.commands.ShiftCheckerCommand;
import com.fairportfirst.frc2013.templates.commands.ShootSequenceCommand;
import com.fairportfirst.frc2013.templates.commands.ShootSequenceForPyramid;
import com.fairportfirst.frc2013.templates.commands.ShooterFlatPositionCommand;
import com.fairportfirst.frc2013.templates.commands.ShooterThreePointPositionCommand;
import com.fairportfirst.frc2013.templates.commands.ToggleFeederCommand;
import com.fairportfirst.frc2013.templates.commands.ToggleLiftCommand;
import com.fairportfirst.frc2013.templates.commands.ToggleShooterBackCommand;
import com.fairportfirst.frc2013.templates.commands.ToggleShooterFrontCommand;
import com.fairportfirst.frc2013.templates.commands.ToggleSpinUpCommand;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    
    
    
    //Binds for NORMAL drive mode.
    static Joystick leftStick = new Joystick(RobotMap.LEFT_JOYSTICK_CHANNEL);
    static Joystick rightStick = new Joystick(RobotMap.RIGHT_JOYSTICK_CHANNEL);
    static Joystick operatorStick = new Joystick(RobotMap.OPERATOR_JOYSTICK_CHANNEL);
    static Button shifterButton;
    static Button climberAutoButton;
    static Button climberGrabButton;
    static Button climberRetractButton;
    static Button shootForPyramidButton;
    static Button shootButton;
    static Button toggleShooterWheelButton;
    static Button shooterDownButton;
    static Button shooterThreePointButton;
    static Button winchRetractButton;
    static Button winchExtendButton;
    static Button pyramidAlignButton;
    
    //Binds for DEBUG drive mode.
    static Button feederToggleButton;
    static Button shooterFrontWheelButton;
    static Button shooterBackWheelButton;
    static Button lifterButton;
    static Button shifterToggleButton;
    
    //Winch
    static Button winchToggleButton = new JoystickButton(rightStick, RobotMap.DEBUG_WINCH_TOGGLE_BUTTON);

    static final double INTERCEPT = 0.2;
    static final double CURVATURE = 0.2;
    static final double DEAD_ZONE = 0.1;
    /**
     * Instantiate button input class.
     * Links button to command.
     */
    public OI() {
        
    }
    
    public void assignButtons(){
        shifterButton.whenPressed(new ShiftCheckerCommand());
        shootButton.whenPressed(new ShootSequenceCommand());
        //shootButton.whenPressed(new FeedShooterAtSpeedCommand());
        toggleShooterWheelButton.whenPressed(new ToggleSpinUpCommand());
        winchRetractButton.whileHeld(new ClimbCommand());
        winchExtendButton.whileHeld(new GrabRungCommand());
        pyramidAlignButton.whenPressed(new CameraAlignCommand());
        
        shooterDownButton.whenPressed(new ShooterFlatPositionCommand());
        shooterThreePointButton.whenPressed(new ShooterThreePointPositionCommand());
    }
    
    public void setDebugMode(DebugMode debugMode){
        if(debugMode == DebugMode.DEBUG){
            shifterToggleButton = new JoystickButton(rightStick, RobotMap.DEBUG_SHIFTER_TOGGLE_BUTTON);
            shifterToggleButton.whenPressed(new ShiftCheckerCommand()); 
            lifterButton = new JoystickButton(rightStick, RobotMap.DEBUG_LIFTER_UPPER_BUTTON);
            lifterButton.whenPressed(new ToggleLiftCommand());
            shooterBackWheelButton = new JoystickButton(rightStick, RobotMap.DEBUG_BACK_SHOOTER_BUTTON);
            shooterBackWheelButton.whenPressed(new ToggleShooterBackCommand());
            shooterFrontWheelButton = new JoystickButton(rightStick, RobotMap.DEBUG_FRONT_SHOOTER_BUTTON);
            shooterFrontWheelButton.whenPressed(new ToggleShooterFrontCommand());
            feederToggleButton = new JoystickButton(leftStick, RobotMap.DEBUG_FEEDER_TOGGLE_BUTTON);
            feederToggleButton.whenPressed(new ToggleFeederCommand());
        }else{
            shooterDownButton = new JoystickButton(operatorStick, RobotMap.OPERATOR_SHOOTER_DOWN_BUTTON);
            shooterThreePointButton = new JoystickButton(operatorStick, RobotMap.OPERATOR_SHOOTER_THREE_POINT_BUTTON);
            shootButton = new JoystickButton(rightStick, RobotMap.JOYSTICK_FEED_SHOOTER_BUTTON);
            toggleShooterWheelButton = new JoystickButton(operatorStick,RobotMap.JOYSTICK_TOGGLE_SHOOTER_WHEELS_BUTTON);
            climberRetractButton = new JoystickButton(operatorStick, RobotMap.JOYSTICK_CLIMBER_RETRACT_BUTTON);
            climberGrabButton = new JoystickButton(operatorStick, RobotMap.JOYSTICK_CLIMBER_GRAB_BUTTON);
            climberAutoButton = new JoystickButton(operatorStick, RobotMap.JOYSTICK_CLIMER_AUTO_BUTTON);
            shifterButton = new JoystickButton(rightStick, RobotMap.JOYSTICK_SHIFTER_BUTTON);
            operatorStick = new Joystick(RobotMap.OPERATOR_JOYSTICK_CHANNEL);
            rightStick = new Joystick(RobotMap.RIGHT_JOYSTICK_CHANNEL);
            leftStick  = new Joystick(RobotMap.LEFT_JOYSTICK_CHANNEL);
            winchRetractButton = new JoystickButton(operatorStick, RobotMap.OPERATOR_WINCH_RETRACT_BUTTON);
            winchExtendButton = new JoystickButton(operatorStick, RobotMap.OPERATOR_WINCH_EXTEND_BUTTON);
            pyramidAlignButton = new JoystickButton(operatorStick,RobotMap.OPERATOR_PYRAMID_ALIGN_BUTTON);
            System.out.println(">>>>Done...");
            assignButtons();
        }
    }
    
    /**
     * Tells the OI how to handle climb input.
     * @param climbMode Represents climb mode.
     */
    public void setClimbMode(ClimbMode climbMode) {
        if(climbMode == ClimbMode.AUTOMATIC) {
            climberAutoButton.whenPressed(new AutomaticClimbCommand());
        } else if(climbMode == ClimbMode.MANUAL) {
            climberGrabButton.whenPressed(new GrabRungCommand());
            climberRetractButton.whenPressed(new ClimbCommand());
            shootForPyramidButton.whenPressed(new ShootSequenceForPyramid());
        }
    }
    
    /**
     * @return The left stick's Y value.
     */
    public static double getLeftDrive() {
        return leftStick.getY();        
    }
    
    /**
     * @return The right stick's Y value.
     */
    public static double getRightDrive() {
        return rightStick.getY();
    }
    
    /**
     * @return Squares left joystick value for more sensitivity.
     */
    public static double getLeftSimboticsDrive(){
        return leftStick.getY() * leftStick.getY();
    }
    
    /**
     *@return Squares the right joystick value for more sensitivity.
     */
    public static double getRightSimboticsDrive(){
        return rightStick.getY() * rightStick.getY();
    }
    
    /**
     * Optimized drive for left stick.
     * 
     * http://www.chiefdelphi.com/media/papers/2421 
     * 
     * More info ^^
     * @return Curves the output for sensitivity and range.
     */
    public static double getLeftOptimizedDrive(){
        
        double output = 0.0;
        double input = leftStick.getY();
        
        if(input < DEAD_ZONE && input > -DEAD_ZONE){
            return output;
        }else{
            if(input > 0){
                output = (INTERCEPT + (1-INTERCEPT)*(CURVATURE* 
                        input * input * input + (1-CURVATURE)*input));
            }else{
                output = (-INTERCEPT + (1-INTERCEPT)*(CURVATURE* 
                        input * input * input + (1-CURVATURE)*input));
            }
            return output;
        }
    }
    
    /**
     * Optimized drive for right stick.
     * 
     * http://www.chiefdelphi.com/media/papers/2421 
     * 
     * More info 
     * @return Curves the output for sensitivity and range.
     */
    public static double getRightOptimizedDrive(){
        
        double output = 0.0;
        double input = rightStick.getY();
        
        if(input < DEAD_ZONE && input > -DEAD_ZONE){ // Is it in the dead zone?
            return output;
        }else{
            if(input > 0){
                output = (INTERCEPT + (1-INTERCEPT)*(CURVATURE* 
                        input * input * input + (1-CURVATURE)*input));
            }else{
                output = (-INTERCEPT + (1-INTERCEPT)*(CURVATURE* 
                        input * input * input + (1-CURVATURE)*input));
            }
            return output;
        }
        
    }
    
    public static double getRightDemoModeDrive(){
        return getRightDrive()/2;
    }
    
    public static double getLeftDemoModeDrive(){
        return getLeftDrive()/2;
    }
}
