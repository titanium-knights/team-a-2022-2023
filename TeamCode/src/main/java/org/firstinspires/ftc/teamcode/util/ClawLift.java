package org.firstinspires.ftc.teamcode.util;

import com.qualcomm.robotcore.hardware.*;

public class ClawLift {
    public Servo liftServo;

    public int downPos = 100;
    public int upPos = 200;

    public ClawLift(HardwareMap hmap) {
        liftServo = hmap.servo.get(CONFIG.clawLift);}


    public void setPosition(double position) {
        liftServo.setPosition(position);
        }


    }


