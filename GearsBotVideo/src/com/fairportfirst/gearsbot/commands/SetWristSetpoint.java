/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fairportfirst.gearsbot.commands;

/**
 *
 * @author deal
 * 
 * Based on: http://www.youtube.com/watch?v=j2Xz8bRRcF0&feature=plcp
 * 
 */
public class SetWristSetpoint extends CommandBase {
    
    private double setpoint;
    
    public SetWristSetpoint(double setpoint) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(wrist);
        this.setpoint = setpoint;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        wrist.setSetpoint(setpoint);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()

    protected boolean isFinished() {
    // wrist movement should stop when wrist is close (.08) to setpoint
        return Math.abs(wrist.getPosition() - setpoint) < .08;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
