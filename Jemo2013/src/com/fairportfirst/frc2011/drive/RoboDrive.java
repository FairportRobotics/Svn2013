package com.fairportfirst.frc2011.drive;

import com.fairportfirst.frc2011.vision.CameraMax;
import com.fairportfirst.frc2011.sensors.*;
import com.fairportfirst.frc2011.structures.*;
import edu.wpi.first.wpilibj.AnalogModule;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.camera.AxisCamera;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Dashboard;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalModule;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.ADXL345_I2C;
import edu.wpi.first.wpilibj.Victor;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SimpleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 * @author Charles
 */
public class RoboDrive extends IterativeRobot {
    //make 578 made class pointers
    public Drive drive;
//    public PathDrive pathDrive;
//    public Pneumatics pneumatics;
//    public MiniBotDeplyment bumbleBotDeploy;
//    public TheClaw claw;
//    public TheArm arm;
//    public TowerOfPower tower;
//    public CameraMax cMax;
    public AccelerometerInterface accel;

    //make FIRST made class pointers
    DriverStationLCD dslcd;
    public Timer timer;
    public double startTime;
    //make hardware class pointers
    public Joystick twistStick;
    public Joystick towerStick;

//    AxisCamera camera;
//    AxisCamera ourCamera;

    private Victor leftFrontJag;
    private Victor leftBackJag;
    private Victor rightFrontJag;
    private Victor rightBackJag;

//    private DigitalInput lightSensor1;
//    private DigitalInput lightSensor2;
//    private DigitalInput lightSensor3;
//    private DigitalInput upperLimit;
//    private DigitalInput lowerLimit;
//  Dashboard data
    private int solenoid;
    private boolean clawState;
    private boolean armState;
    private boolean lightSensor;
    private boolean filling;
    private double expiration;
    private boolean deploying;
    private boolean compressorIsFull;
    private boolean minibotArmed;
    private double shotsLeft;

    public final int rightJoystickYAxisNumber = 2;
    public final int leftJoystickYAxisNumber = 5;


    public RoboDrive() {
        WatchWhaleWhaleWatch.getInstance().setEnabled(true);
        dslcd = DriverStationLCD.getInstance();
        dslcd.println(DriverStationLCD.Line.kMain6, 1, "instantiating Bot2011");
    }

 //  private AxisCamera ourCamera;
    /**
     * 
     */
    public void robotInit() {
        //hardware init
//        lightSensor1= new DigitalInput(1);
//        lightSensor2= new DigitalInput(2);
//        lightSensor3= new DigitalInput(3);
//        upperLimit = new DigitalInput(5);
//        lowerLimit = new DigitalInput(7);
//        accel = new AccelerometerInterface(1, ADXL345_I2C.DataFormat_Range.k2G);

        twistStick = new Joystick(1);
        towerStick = new Joystick(2);

//        try {//CANbus init and drive init
            leftFrontJag = new Victor(2);
            leftBackJag = new Victor(5);
            rightFrontJag = new Victor(3);
            rightBackJag = new Victor(4);

            drive = new Drive(leftFrontJag, leftBackJag, rightFrontJag, rightBackJag);
//        } catch (CANTimeoutException ex) {
//            ex.printStackTrace();
//        }

        timer = new Timer();
        //578 made class instaciations
//        pneumatics = new Pneumatics(4, 1, timer);
//        pathDrive = new PathDrive(lightSensor1, lightSensor2, lightSensor3, drive);
//        bumbleBotDeploy = new MiniBotDeplyment(5, 6, pneumatics, getTime(), towerStick);
//        claw = new TheClaw(2, 1, pneumatics, towerStick);
//        arm = new TheArm(3, 4, pneumatics, towerStick);
//        tower = new TowerOfPower(5,7,6, towerStick);
//        tower = new TowerOfPower(6, towerStick, twistStick, lowerLimit, upperLimit);
//        cMax = new CameraMax();
    }
    /**
     * Tells the robot to fill the tanks when autonomous starts
     */
    public void autonomousInit() {
        WatchWhaleWhaleWatch.getInstance().setEnabled(true);
//        pneumatics.fillTanks();
//        claw.openClaw();
//        arm.moveArm(false);
//        arm.moveArm(true);
//        if(timer!=null) {
            timer.reset();
            timer.start();

        startTime = timer.getFPGATimestamp();
//        }
    }
    /**
     * When autonomous ends, start Teleop
     */
    public void teleopInit()
    {
        WatchWhaleWhaleWatch.getInstance().setEnabled(true);
        WatchWhaleWhaleWatch.getInstance().feed();
        
//        pneumatics.fillTanks();
        WatchWhaleWhaleWatch.getInstance().feed();

//        if(timer!=null)
//        {
            timer.reset();
            timer.start();
//        }
        startTime = timer.getFPGATimestamp();
        WatchWhaleWhaleWatch.getInstance().feed();
        


    }
    /**
     * Tells the robot to stop after a certain period of time
     */
    public void disabledInit()
    {
        if (timer != null)
        {
            timer.stop();
        }
    }

  /**
   * This function is called once each time the robot enters autonomous mode.
   */
    /**
     * During the autonomous period, the robot will follow the lines on the ground
     */
    public void autonomousPeriodic()
    {
        dslcd.println(DriverStationLCD.Line.kMain6, 1, "autonomous()");
        WatchWhaleWhaleWatch.getInstance().feed();
//        arm.moveArm(true);
//        pneumatics.cycle();
        WatchWhaleWhaleWatch.getInstance().feed();

        if(getTime()>1.1) //&& getTime()<6)//gets to middle in about 6 sec
        {
//            tower.raiseToTop();
//            if(tower.getUpperSwitch())
//                System.out.println("tower at top at "+getTime());
//            else
//                System.out.println("tower riase" + getTime());
        }

        if(getTime()<0.5)
            drive.strafeForward(.6);
        else if(getTime()<0.8)
            drive.strafeBackward(.2);
//        else if(getTime()<14.5)
//            pathDrive.followStraightLine(65);
        else
            drive.strafeForward(-.6);

        if(getTime()>13)
        {
//            claw.closeClaw();
            System.out.println("claw close" + getTime());
        }
        WatchWhaleWhaleWatch.getInstance().feed();
        
        updateDashboard();

//        dslcd.println(DriverStationLCD.Line.kUser2, 1, "lightSensor 1: " + lightSensor1.get());
//        dslcd.println(DriverStationLCD .Line.kUser3, 1, "lightSensor 2: " + lightSensor2.get());
//        dslcd.println(DriverStationLCD.Line.kUser4, 1, "lightSensor 3: " + lightSensor3.get());
        dslcd.updateLCD();
    }
    /**
     * Tells the robot that autonomous doesn't go on forever
     */
    public void autonomousContinuous()
    {
//        pathDrive.isEnd();
    }

  /**
   * This function is called once each time the robot enters operator control.
   */
    /**
     * What to display during TeleOp
     */
    public void teleopPeriodic() {
        dslcd.println(DriverStationLCD.Line.kMain6, 1, "operatorControl()");
        
        double magnitude;
        double xDir;
        double yDir;
        double direction;
        double rotation;
        WatchWhaleWhaleWatch.getInstance().feed();


        //Camera code
//        camera = AxisCamera.getInstance();//*****************************************************
////        trackerCam = new CameraMax(camera);
//        camera.writeRotation(AxisCamera.RotationT.k180);
//        camera.writeResolution(AxisCamera.ResolutionT.k320x240);//*******************************
//        camera.writeBrightness(50);//************************************************************
        
//        ColorImage temp = null;
//        if(camera.freshImage())
//        {
//            try {
//                temp = camera.getImage();
//            } catch (AxisCameraException ex) {
//                ex.printStackTrace();
//            } catch (NIVisionException ex) {
//                ex.printStackTrace();
//            }
//            cMax.setImage(temp);
//        }
//        dslcd.println(DriverStationLCD.Line.kMain6, 1, "Target Place: " + cMax.getTarget());
        WatchWhaleWhaleWatch.getInstance().feed();
        updateDashboard();
        System.out.println(getTime());

        //structues code
//        pneumatics.cycle();
//        tower.manualJoystick();
//        claw.clawControl();
//        arm.armControl();
//        bumbleBotDeploy.manualDeployMinibot();
//        bumbleBotDeploy.autoDeployMinibot(getTime());
        WatchWhaleWhaleWatch.getInstance().feed();
        //drive code
        magnitude = twistStick.getMagnitude();
//        if(magnitude<=.005&&magnitude>=-.005)
//            magnitude = 0;
//        else
//        {
//            if(magnitude>1)
//                magnitude = 1;
//            else if(magnitude <-1)
//                magnitude = -1;
//            else
//                magnitude = twistStick.getMagnitude();
//        }
        xDir = twistStick.getX();
        yDir = twistStick.getY();
        direction = twistStick.getDirectionDegrees();
        rotation = twistStick.getTwist();
        WatchWhaleWhaleWatch.getInstance().feed();

        solenoid = 42;
//        clawState = claw.isOpen();//twistStick.getRawButton(7);
//        armState = arm.getArmState();//twistStick.getRawButton(8);
        lightSensor = false;

//        filling = pneumatics.getCompressorState();//twistStick.getRawButton(9);
        expiration = 1.38;
//        deploying = bumbleBotDeploy.isDepoying();
//        compressorIsFull = pneumatics.isFull();
//        minibotArmed = bumbleBotDeploy.isReady();
//        shotsLeft = (double) pneumatics.shotsLeft();
//        expiration = bumbleBotDeploy.isDepoying();//twistStick.getRawButton(12);

        drive.manualControl(getMagnitude(xDir), getMagnitude(yDir), getMagnitude(rotation));
//        if(magnitude>0)
//            drive.manualControl(magnitude-.1, direction, rotation);
//        else
//            drive.manualControl(magnitude+.1, direction, rotation);
        //driverstation output
        WatchWhaleWhaleWatch.getInstance().feed();
//        dslcd.println(DriverStationLCD.Line.kMain6, 1, "RobotTemplate: " + ourCamera.freshImage());
//        dslcd.println(DriverStationLCD.Line.kUser4, 1, "have image: " + ourCamera.freshImage());
//        dslcd.println(DriverStationLCD.Line.kUser2, 1, "lightSensor 1: " + lightSensor1.get());
//        dslcd.println(DriverStationLCD.Line.kUser3, 1, "lightSensor 2: " + lightSensor2.get());
//        dslcd.println(DriverStationLCD.Line.kUser4, 1, "lightSensor 3: " + lightSensor3.get());
//        dslcd.println(DriverStationLCD.Line.kUser5, 1, "upperLimit: " + upperLimit.get());
//        dslcd.println(DriverStationLCD.Line.kUser6, 1, "lowerLimit: " + lowerLimit.get());
//        dslcd.println(DriverStationLCD.Line.kUser6, 1, "throttle: " + twistStick.getThrottle());
//        dslcd.println(DriverStationLCD.Line.kUser4, 1, "magnitude: " + magnitude);
//        dslcd.println(DriverStationLCD.Line.kUser5, 1, "magnitude: " + magnitude);
//        dslcd.println(DriverStationLCD.Line.kUser6, 1, "rotation: " + rotation);
//        dslcd.println(DriverStationLCD.Line.kUser3, 1, "throttle " + twistStick.getThrottle());
        dslcd.updateLCD();
//        dslcd.println(DriverStationLCD.Line.kUser3, 1, "LightSensorInput " + RoboDrive.get());
        WatchWhaleWhaleWatch.getInstance().feed();
    }
    /**
     * Tells the dashboard when and how to update variables and data
     */
   void updateDashboard()
    {
        Dashboard lowDashData = DriverStation.getInstance().getDashboardPackerLow();
        DriverStationLCD.getInstance().updateLCD();  //Update Camera Feed

        WatchWhaleWhaleWatch.getInstance().feed();
        lowDashData.addCluster();
        {
            lowDashData.addCluster();
            {     //analog modules
                lowDashData.addCluster();
                {
                    for (int i = 1; i <= 8; i++) {
                        lowDashData.addFloat((float) AnalogModule.getInstance(1).getAverageVoltage(i));
                    }
                }
                lowDashData.finalizeCluster();
                lowDashData.addCluster();
                {
                    for (int i = 1; i <= 8; i++) {
                        lowDashData.addFloat((float) AnalogModule.getInstance(2).getAverageVoltage(i));
                    }
                }
                lowDashData.finalizeCluster();
            }
            lowDashData.finalizeCluster();

            lowDashData.addCluster();
            { //digital modules
                lowDashData.addCluster();
                {
                    lowDashData.addCluster();
                    {
                        int module = 1;//4
                        lowDashData.addByte(DigitalModule.getInstance(module).getRelayForward());
                        lowDashData.addByte(DigitalModule.getInstance(module).getRelayForward());
                        lowDashData.addShort(DigitalModule.getInstance(module).getAllDIO());
                        lowDashData.addShort(DigitalModule.getInstance(module).getDIODirection());
                        lowDashData.addCluster();
                        {
                            for (int i = 1; i <= 10; i++) {
                                lowDashData.addByte((byte) DigitalModule.getInstance(module).getPWM(i));
                            }
                        }
                        lowDashData.finalizeCluster();
                    }
                    lowDashData.finalizeCluster();
                }
                lowDashData.finalizeCluster();

                lowDashData.addCluster();
                {
                    lowDashData.addCluster();
                    {
                        int module = 1;//6
                        lowDashData.addByte(DigitalModule.getInstance(module).getRelayForward());
                        lowDashData.addByte(DigitalModule.getInstance(module).getRelayReverse());
                        lowDashData.addShort(DigitalModule.getInstance(module).getAllDIO());
                        lowDashData.addShort(DigitalModule.getInstance(module).getDIODirection());
                        lowDashData.addCluster();
                        {
                            for (int i = 1; i <= 10; i++) {
                                lowDashData.addByte((byte) DigitalModule.getInstance(module).getPWM(i));
                            }
                        }
                        lowDashData.finalizeCluster();
                    }
                    lowDashData.finalizeCluster();
                }
                lowDashData.finalizeCluster();

            }
            lowDashData.finalizeCluster();

//            lowDashData.addByte(Solenoid.getAll());
            // Cluster for PWM??
            lowDashData.addCluster();
            {
//                lowDashData.addBoolean(true);  //Punches left
            }
            lowDashData.finalizeCluster();

            //Cluster for
            lowDashData.addCluster();
            {
//                lowDashData.addBoolean(claw.isOpen());  //Claw state
 
            }
            lowDashData.finalizeCluster();
            //lowDashData.addString("Hi"); // Don't really need

            lowDashData.addBoolean(false); //Solenoid
            lowDashData.addBoolean(clawState);  //Claw State

            lowDashData.addBoolean(!armState);  //Arm state


            lowDashData.addBoolean(filling);  //Filling // this one works DO NOT CHANGE!!!
            lowDashData.addDouble(WatchWhaleWhaleWatch.getInstance().getExpiration());  //expiration  // moved down one
            lowDashData.addBoolean(deploying);  // deploying
            lowDashData.addBoolean(lightSensor); // Light Sensor
            lowDashData.addBoolean(compressorIsFull);  //Compressor is Full
            lowDashData.addBoolean(minibotArmed); // Minibot Armed
            lowDashData.addDouble(shotsLeft); // Shots Left

        lowDashData.finalizeCluster();

        lowDashData.commit();
    }




    }
   /**
    * Gets the robot's magnitude
    * @param joyVal - The magnitude
    * @return - True if Magnitude is less than 0.2
    */
   public double getMagnitude(double joyVal)
    {
       double mag;
       mag = joyVal;
       if(mag<=.005&&mag>=-.005)
           mag = 0;
       else
       {
           if(mag>1)
               mag = 1;
           else if(mag <-1)
               mag = -1;
           else
               mag = joyVal;
        }
       if(twistStick.getThrottle()>.2)
            mag=mag/2;
       return mag;
    }

   /**
    *
    *
    * @return
    */
    /**
     * Gets the time
     * @return - True if there is no time left
     */
   public double getTime()
    {
       if(timer!=null)
            return timer.getFPGATimestamp()-startTime;
       return 0;
   }
}
