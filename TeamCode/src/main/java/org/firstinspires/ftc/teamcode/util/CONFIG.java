package org.firstinspires.ftc.teamcode.util;


import com.acmerobotics.dashboard.config.Config;

@Config
public class CONFIG {
    //drivetrain wheels :OOO
    public static String FRONT_LEFT = "fl"; //ch 2
    public static String FRONT_RIGHT = "fr"; //ch 3
    public static String BACK_LEFT = "bl"; //ch 1
    public static String BACK_RIGHT = "br"; //ch 0

    //webcam
    public static String webcam = "Webcam 1";

    //subsystems
    public static String clawServo = "claw"; //

    public static String clawLift = "clawLift"; //

    public static String clawSpin = "clawSpin"; //

    public static String liftMotorRight = "lmr"; //exp 0
    public static String liftMotorLeft = "lml";  //exp 1

    public static String O_C = "br"; //center odo, ch 0
    public static String O_L = "fr"; //left odo, ch 1
    public static String O_R = "bl"; //right odo, ch 3

    //OLD
    public static String liftMotor = "lm"; //

}
