/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fairportfirst.learn.commands;

import com.fairportfirst.learn.subsystems.CathodeSubsystem;

/**
 *
 * @author team578
 */
public class CathodeOffCommand extends CommandBase {

    private CathodeSubsystem cathodeSys;
    
    public CathodeOffCommand() {
        cathodeSys = CathodeSubsystem.getInstance();
        requires(cathodeSys);
    }
    
    protected void initialize() {
    }

    protected void execute() {
        cathodeSys.turnOff();
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}
