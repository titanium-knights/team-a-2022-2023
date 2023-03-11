package org.firstinspires.ftc.teamcode.util;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class EncServo {
    public Servo encServo; //servo

    public static double DOWNPOS = 0.89;
    public static double UPPOS = 0.2;

    public EncServo(HardwareMap hmap) {

        this.encServo = hmap.servo.get(CONFIG.encServo);
    }

    public void setPosition(double pos) {
        encServo.setPosition(pos);
    }

    public double getPosition() {
        return encServo.getPosition();
    }

    public boolean isUp() {
        if (getPosition() == UPPOS) {
            return true;
        }
        return false;
    }
}
