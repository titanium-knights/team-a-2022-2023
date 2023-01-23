package org.firstinspires.ftc.teamcode.util;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class ClawSpin {
    public Servo clawSpin; //servo

    public static double FRONTPOS = 0;
    public static double BACKPOS = 0.64;

    public ClawSpin(HardwareMap hmap) {
        this.clawSpin = hmap.servo.get(CONFIG.clawSpin);
    }

    public void setPosition(double pos) {
        clawSpin.setPosition(pos);
    }

    public boolean isForward() {
        if (clawSpin.getPosition() == FRONTPOS) {
            return true;
        }
        return false;
    }

    public boolean isBack() {
        if (clawSpin.getPosition() == FRONTPOS) {
            return true;
        }
        return false;
    }
}
