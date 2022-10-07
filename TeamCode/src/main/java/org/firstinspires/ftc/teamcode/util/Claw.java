package org.firstinspires.ftc.teamcode.util;
import com.qualcomm.robotcore.hardware.*;


public class Claw {
    public Servo claw; //servo or motor???
    public static double openPos = 1; //open and ready to take a cone or TSE

    public static double closedConePos = .5; //closed for cone
    public static double closedTSEPos = .4; //closed for TSE

    public Claw(HardwareMap hardwareMap) {
        this.claw = hardwareMap.servo.get("claw");
    }

    public boolean isClosedCone() { //check if closed
        if (claw.getPosition() == closedConePos) {
            return true;
        } else {
            return false;
        }

    }

    public void open() {
        claw.setPosition(openPos); //sets position to a position in parameter
    }

    public void closeCone() {
        claw.setPosition(closedConePos);
    }

    public void closeTSE() {
        claw.setPosition(closedTSEPos);
    }

}
