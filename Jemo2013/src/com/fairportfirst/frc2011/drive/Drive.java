package com.fairportfirst.frc2011.drive;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.RobotDrive;

/**
 * This class includes methods to be used in order to make
 * calls that drive the robot's Victors and motors.
 * @author Charles
 */
public class Drive
{

    /**
     * Various objects are made here.
     */
//    private RobotDrive robotDrive;
    Victor frontLeftMotor;
    Victor backLeftMotor;
    Victor frontRightMotor;
    Victor backRightMotor;
    static final int kFrontLeft_val = 0;
    static final int kFrontRight_val = 1;
    static final int kRearLeft_val = 2;
    static final int kRearRight_val = 3;

    /**
     * Constructor that sets the four Victor objects.
     * @param newfrontLeftMotor Represents the front left Victor.
     * @param newbackLeftMotor Represents the back left Victor.
     * @param newfrontRightMotor Represents the front right Victor.
     * @param newbackRightMotor Represents the back right Victor.
     */
    public Drive(Victor newfrontLeftMotor, Victor newbackLeftMotor, Victor newfrontRightMotor, Victor newbackRightMotor)
    {
        frontLeftMotor =newfrontLeftMotor;
        backLeftMotor = newbackLeftMotor;
        frontRightMotor = newfrontRightMotor;
        backRightMotor = newbackRightMotor;
//        robotDrive = new RobotDrive(frontLeftMotor, backLeftMotor, frontRightMotor, backRightMotor);

    }

    /**
     * This method allows you to manually control the robot.
     * @param x The speed that the robot should drive in the X direction. [-1.0..1.0]
     * @param y The speed that the robot should drive in the Y direction.
     * @param rotation The rate of rotation for the robot that is completely independent of
     * the translation. [-1.0..1.0]
     */
    public void manualControl(double x, double y, double rotation)
    {
        //For turning and strafing
        int polarity = Math.abs(rotation)>0.09?1:-1;
        double xIn = x;
        double yIn = y;
        // Negate y for the joystick.
        yIn = -yIn;
        // Compenstate for gyro angle.
        double rotated[] = rotateVector(xIn, yIn, 0);
        xIn = rotated[0];
        yIn = rotated[1];

        double wheelSpeeds[] = new double[4];
        wheelSpeeds[kFrontLeft_val] = xIn + yIn + rotation;
        wheelSpeeds[kFrontRight_val] = (-xIn + yIn + rotation)*polarity;
        wheelSpeeds[kRearLeft_val] = -xIn + yIn + rotation;
        wheelSpeeds[kRearRight_val] = (xIn + yIn + rotation)*polarity;

        normalize(wheelSpeeds);

//        byte syncGroup = (byte)0x80;

        frontLeftMotor.set(wheelSpeeds[kFrontLeft_val]);
        frontRightMotor.set(wheelSpeeds[kFrontRight_val]);
        backLeftMotor.set(wheelSpeeds[kRearLeft_val]);
        backRightMotor.set(wheelSpeeds[kRearRight_val]);
        System.out.println("x: " + xIn + " y: " + yIn + " rot: " + rotation + " pol: " + polarity);
        System.out.println("lFront: " + frontLeftMotor.get());
        System.out.println("rFront: " + frontRightMotor.get());
        System.out.println("lBack: " + backLeftMotor.get());
        System.out.println("rBack: " + backRightMotor.get());
//        if (m_isCANInitialized) {
//            try {
//                CANJaguar.updateSyncGroup(syncGroup);
//            } catch (CANNotInitializedException e) {
//                m_isCANInitialized = false;
//            } catch (CANTimeoutException e) {}
//        }
//        robotDrive.mecanumDrive_Cartesian(x, y, rotation, 0);

    }

    /**
     * This method makes the robot strafe in a leftward direction.
     * @param magnitude The value sent to the Victor to determine its speed.
     */
    public void strafeLeft(double magnitude)
    {
//        robotDrive.mecanumDrive_Polar(magnitude, 270, 0);
    }

    /**
     * This method makes the robot strafe in a rightward direction.
     * @param magnitude The value sent to the Victor to determine its speed.
     */
    public void strafeRight(double magnitude)
    {
//        robotDrive.mecanumDrive_Polar(magnitude, 90, 0);
    }

    /**
     * This method makes the robot strafe in a forward direction.
     * @param magnitude The value sent to the Victor to determine its speed.
     */
    public void strafeForward(double magnitude)
    {
//        robotDrive.mecanumDrive_Polar(magnitude, 0, 0);
    }

    /**
     * This method makes the robot strafe in a backward direction.
     * @param magnitude The value sent to the Victor to determine its speed.
     */
    public void strafeBackward(double magnitude)
    {
//        robotDrive.mecanumDrive_Polar(magnitude, 180, 0);
    }

    /**
     * This method makes the robot strafe towards a direction based on an angle.
     * @param magnitude The value sent to the Victor to determine its speed.
     * @param direction The angle of direction at which the robot strafes on.
     */
    public void strafeOnAngle(double magnitude, double direction)
    {
//        robotDrive.mecanumDrive_Polar(magnitude, direction, 0);
    }

    public void strafePolar(double magnitude, double direction, double rotation)
    {
//        robotDrive.mecanumDrive_Polar(magnitude, direction, rotation);
    }
    /**
     * This method sets the two front Victors to a value determined by 'speed'.
     * @param speed The speed set to the front Victors.
     */
    public void rev(double speed)
    {
        frontLeftMotor.set(speed);
        frontRightMotor.set(speed);
    }
    //HElper method for new polarity
    protected static double[] rotateVector(double x, double y, double angle)
    {
        double cosA = Math.cos(angle * (3.14159 / 180.0));
        double sinA = Math.sin(angle * (3.14159 / 180.0));
        double out[] = new double[2];
        out[0] = x * cosA - y * sinA;
        out[1] = x * sinA + y * cosA;
        return out;
    }
    protected static void normalize(double wheelSpeeds[])
    {
        double maxMagnitude = Math.abs(wheelSpeeds[0]);
        int i;
        for (i=1; i<4; i++)
        {
            double temp = Math.abs(wheelSpeeds[i]);
            if (maxMagnitude < temp) maxMagnitude = temp;
        }
        if (maxMagnitude > 1.0)
        {
            for (i=0; i<4; i++)
            {
                wheelSpeeds[i] = wheelSpeeds[i] / maxMagnitude;
            }
        }
    }
}
