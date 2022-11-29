package org.firstinspires.ftc.teamcode.util;


import com.acmerobotics.dashboard.config.Config;

@Config
public class CONFIG {
    //drivetrain wheels :OOO
    public static String FRONT_LEFT = "fl"; //ch 0
    public static String FRONT_RIGHT = "fr"; //ch 1
    public static String BACK_LEFT = "bl"; //ch 3
    public static String BACK_RIGHT = "br"; //ch 2

    //webcam
    public static String webcam = "Webcam 1";

    //subsystems
    public static String clawServo = "claw"; //ch 0

    public static String liftMotor = "lift"; //

    public static String clawLift = "clawLift"; //

    public static String clawUp = "clawUp"; //ch 3, servo to lift the claw up

    public static String liftMotorRight = "r_lift"; //exp 0
    public static String liftMotorLeft = "l_lift";  //exp 1

    public static String O_C = "fl"; //center odo, ch 0
    public static String O_L = "fr"; //right odo, ch 1
    public static String O_R = "bl"; //left odo, ch 3
}
