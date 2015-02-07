package com.fairportfirst.frc2013.templates;

/**
 *
 * @author Brian
 */
public class ClimbMode {
    
    private int value;
    
    public static ClimbMode AUTOMATIC = new ClimbMode(1);
    public static ClimbMode MANUAL = new ClimbMode(2);
            
            
    private ClimbMode(int value){
        this.value = value;
    }
}
