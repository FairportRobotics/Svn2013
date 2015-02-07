package com.fairportfirst.frc2011.sensors;

import edu.wpi.first.wpilibj.Accelerometer;
import edu.wpi.first.wpilibj.ADXL345_I2C;

/**
 * A class to provide scalar velocity and position based on the acceleration from the provided accelerometer.  The values provided are based on a right Riemann sum approximation.
 * @author Patrick
 */

public class AccelerometerInterface
{
//    Accelerometer accel;
    ADXL345_I2C accel;
    private double velocity;
    private double position;
    private double lastTime;

    private final double FPS = 32.2;

    /*
     * A constructor.  The input should be the channel of an accelerometer.
     */
    public AccelerometerInterface(int accelerometerChannel, ADXL345_I2C.DataFormat_Range range)
    {
        accel = new ADXL345_I2C(accelerometerChannel, range);
//        accel = new Accelerometer(accelerometerChannel);
        lastTime = System.currentTimeMillis();
        velocity = 0;
        position = 0;
    }
    /**
     * Displays the velocity of the robot
     * @return - The formula for determining the velocity
     */
    public double getVelocity()
    {
        return velocity * FPS;
    }
    /**
     * Displays the position of the robot
     * @return - The formula for determining where the robot is
     */
    public double getPosition()
    {
        return position * FPS;
    }
    /**
     * Gets the acceleration of the robot
     * @param axis - The direction it's accelerating
     * @return - Returns the acceleration of the axis
     */
    public double getAcceleration(ADXL345_I2C.Axes axis)
    {
        return accel.getAcceleration(axis);
    }
    /**
     * Checks to see if the robot is hitting an object
     * @return - True if it hits an object
     */
    public boolean hitSide()
    {
        if(accel.getAcceleration(ADXL345_I2C.Axes.kX)>0)
            return true;
        else
            return false;
    }

    /**
     * Performs calculus to get the position
     * @param axis - Asks which axis you want to find the position of
     */
    public void run(ADXL345_I2C.Axes axis)
    {
        double timeDif = System.currentTimeMillis() - lastTime;
        velocity += (accel.getAcceleration(axis) * timeDif);
        position += (velocity * timeDif);

        lastTime = System.currentTimeMillis();
    }
}
