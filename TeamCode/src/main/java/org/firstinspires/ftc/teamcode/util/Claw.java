package org.firstinspires.ftc.teamcode.util;
import com.qualcomm.robotcore.hardware.*;
import com.acmerobotics.dashboard.config.Config;


@Config
public class Claw {
    public Servo claw; //servo or motor???
    public static double openPos = .08; //open and ready to take a cone or TSE

    public static double closedConePos = 0.24; //closed for cone
    public static boolean isClosed = false;
    public static boolean isOpen = false;

    public Claw(HardwareMap hmap) {
        this.claw = hmap.servo.get(CONFIG.clawServo);
    }

    public void setPosition(double pos) {
        claw.setPosition(pos);
    }

    public void open() { isOpen = true; isClosed = false;} //sets position to a position in parameter

    public void closeCone() { isClosed = true; isOpen = false;}

    public double getPosition() { return claw.getPosition();
    }

    //keep position holder
    public void keepPosition() {
        if (isOpen) {
            open();
        }
        else {
            closeCone();
        }
    }
}
