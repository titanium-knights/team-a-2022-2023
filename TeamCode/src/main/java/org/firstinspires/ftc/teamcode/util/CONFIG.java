package org.firstinspires.ftc.teamcode.util;


import com.acmerobotics.dashboard.config.Config;

@Config
public class CONFIG {
    //drivetrain wheels :OOO
    public static String FRONT_LEFT = "fl"; //ch 3
    public static String FRONT_RIGHT = "fr"; //ch 2
    public static String BACK_LEFT = "bl"; //ch 0
    public static String BACK_RIGHT = "br"; //ch 1

    //webcam
    public static String webcam = "Webcam 1";

    //subsystems
    public static String clawServo = "claw"; //ch 0

    public static String liftMotor = "lift"; //

    public static String clawLift = "clawLift"; //

    public static String clawUp = "clawUp"; //ch 3, servo to lift the claw up

    public static String liftMotorRight = "r_lift"; //exp 0
    public static String liftMotorLeft = "l_lift";  //exp 1

    public static String o_c = "bl"; //center odo, ch 0
    public static String o_r = "br"; //right odo, ch 1
    public static String o_l = "fl"; //left odo, ch 3
}
