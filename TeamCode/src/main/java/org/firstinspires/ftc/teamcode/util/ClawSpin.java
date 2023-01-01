package org.firstinspires.ftc.teamcode.util;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class ClawSpin {
    public Servo clawSpin; //servo

    public static double FRONTPOS = -0.05;
    public static double BACKPOS = 0.6;

    public ClawSpin(HardwareMap hmap) {

        this.clawSpin = hmap.servo.get(CONFIG.clawSpin);
    }

    public void setPosition(double pos) {
        clawSpin.setPosition(pos);
    }
}
