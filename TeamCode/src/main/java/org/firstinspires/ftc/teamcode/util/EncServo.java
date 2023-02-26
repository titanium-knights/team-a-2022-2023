package org.firstinspires.ftc.teamcode.util;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class EncServo {
    public Servo encServo; //servo

    public static double DOWNPOS = 0.89;
    public static double UPPOS = 0.0;

    public EncServo(HardwareMap hmap) {

        this.encServo = hmap.servo.get(CONFIG.encServo);
    }

    public void setPosition(double pos) {
        encServo.setPosition(pos);
    }

    public String retPosition() {
        if (encServo.getPosition() == DOWNPOS) {
            return "down: " + encServo.getPosition();
        } else {
            return "up: " + encServo.getPosition();
        }
    }
}
