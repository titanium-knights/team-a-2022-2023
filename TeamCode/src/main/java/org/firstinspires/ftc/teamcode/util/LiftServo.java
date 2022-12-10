package org.firstinspires.ftc.teamcode.util;
import com.qualcomm.robotcore.hardware.*;
import com.acmerobotics.dashboard.config.Config;


@Config
public class LiftServo {
    public Servo LiftServo;
    public static double highPosition = .25; //TODO - add presets for putting on high position

    public static double middlePosition = 0.01; //TODO - add presets for putting on high position
    public static double lowPosition = .4; //TODO - add presets for putting on high position

    public LiftServo (HardwareMap hmap) {
        //this.LiftServo = hmap.servo.get(CONFIG.LiftServo);
        // I keep getting an error for the above line. help idk what im doing wrong?
    }


    public void highPosition() { LiftServo.setPosition(highPosition); } //sets position to a position in parameter

    public void middlePosition() { LiftServo.setPosition(middlePosition); }

    public void lowPosition() { LiftServo.setPosition(lowPosition); }

    public double getPosition() { return LiftServo.getPosition();
    }
}
