package com.fairportfirst.frc2013.templates;

/**
 *
 * @author tyler
 */
public class DebugMode {
    
    private int value;
    
    public static DebugMode NORMAL = new DebugMode(1);
    public static DebugMode DEBUG = new DebugMode(2);
    
    public DebugMode(int value){
        this.value = value;
    }
}
