package org.firstinspires.ftc.teamcode.util;

import com.qualcomm.robotcore.hardware.*;

public class ClawLift {
    public Servo liftMotor;

    public int downPos = 100;
    public int upPos = 200;

    public ClawLift(HardwareMap hmap) {
        this.liftMotor = hmap.servo.get(CONFIG.clawLift);
    }

//    public setPosition()


}
