/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fairportfirst.gearsbot.subsystems;

import com.fairportfirst.gearsbot.RobotMap;
import com.fairportfirst.gearsbot.commands.ClawDoNothing;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;


/**
 *
 * @author deal
 */
public class Claw extends Subsystem {
    // Declare local variables
    SpeedController motor = new Jaguar(RobotMap.clawMotor);
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
        setDefaultCommand(new ClawDoNothing());
    }
    
    public void open() {
        motor.set(1);
    }

    public void close() {
        motor.set(-1);
    }

    public void doNothing() {
        motor.set(0);
    }
}
