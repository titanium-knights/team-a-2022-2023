package org.firstinspires.ftc.teamcode.util;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.*;

@Config
public class ClawLift {
    public Servo liftServo;

    public double pickupPos = .5;
    public double upPos = 1.0;

    public ClawLift(HardwareMap hmap) {
        this.liftServo = hmap.servo.get(CONFIG.clawLift);}


    public void setPickup () {
        liftServo.setPosition(pickupPos);
    }



}


