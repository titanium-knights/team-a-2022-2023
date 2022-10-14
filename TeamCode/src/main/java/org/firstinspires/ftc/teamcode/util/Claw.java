package org.firstinspires.ftc.teamcode.util;
import com.qualcomm.robotcore.hardware.*;

public class Claw {
    public Servo claw; //servo or motor???
    public static double openPos = .25; //open and ready to take a cone or TSE

    public static double closedConePos = 0.06; //closed for cone
    public static double initPos = .4; //to stay legal

    public Claw(HardwareMap hmap) {
        this.claw = hmap.servo.get(CONFIG.clawServo);
    }

    public boolean isClosedCone() { //check if closed
        if (claw.getPosition() == closedConePos) {
            return true;
        } else {
            return false;
        }

    }

    public void open() { claw.setPosition(openPos); } //sets position to a position in parameter

    public void closeCone() { claw.setPosition(closedConePos); }

    public void openInit() { claw.setPosition(initPos); }

    public double getPosition() { return claw.getPosition();
    }
}
