package com.fairportfirst.frc2013.templates.commands;

/**
 *
 * @author Tyler
 */
public class CompressorStartCommand extends CommandBase{
    
    public CompressorStartCommand(){
        requires(compressorSubsystem);
    }
    
    protected void initialize() {
        // Start the compressor, it's self regulating so we only need to start it.
        compressorSubsystem.start();
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
     }
}
