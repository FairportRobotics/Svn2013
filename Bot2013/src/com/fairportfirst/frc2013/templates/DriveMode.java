package com.fairportfirst.frc2013.templates;

/**
 *
 * @author Brian
 */
public class DriveMode {
    
    public final int value;
    
    public static final DriveMode LINEAR = new DriveMode(1);
    public static final DriveMode RAMPED = new DriveMode(2);
    public static final DriveMode SQUARE = new DriveMode(3);
    public static final DriveMode DEMO = new DriveMode(4);
    
   private DriveMode(int value) {
       this.value  = value;
   }
}
