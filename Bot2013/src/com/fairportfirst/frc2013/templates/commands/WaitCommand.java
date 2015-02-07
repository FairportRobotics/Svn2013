package com.fairportfirst.frc2013.templates.commands;

/**
 *
 * @author alec
 */
public class WaitCommand extends CommandBase {
    
    /**
     * This command terminates after the given time. This was for command groups
     * whose individual commands terminates at once. So the timing system would 
     * not work. Thus this command which terminates after the given time. 
     * @param t time for this command to exist for 
     */
    public WaitCommand(double t) {
        setTimeout(t);
    }

    protected void initialize() {
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return isTimedOut();
    }

    protected void end() {
        
    }

    protected void interrupted() {
        
    }
}
