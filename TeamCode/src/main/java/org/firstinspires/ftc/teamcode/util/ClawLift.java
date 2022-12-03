package org.firstinspires.ftc.teamcode.util;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.*;

@Config
public class ClawLift {
    public DcMotor clawLift;

    public double clawLiftPOWER = .75;
    public double MIDDLE_POSITION = 500;
    public double MIDDLE_POSITION_BUFFER = 50;
    public int FRONT_PICKUP_POS = 400;
    public int BACK_PICKUP_POS = -1200;

    public ClawLift(HardwareMap hmap) {
        this.clawLift = hmap.dcMotor.get(CONFIG.clawLift);
    }


    public void setPower(double power, double negator) {
        if (clawLift.getCurrentPosition() > MIDDLE_POSITION + MIDDLE_POSITION_BUFFER) {
            clawLift.setPower(power * negator);
        }
        else if (clawLift.getCurrentPosition() < MIDDLE_POSITION + MIDDLE_POSITION_BUFFER) {
            clawLift.setPower(power * -negator);
        }
    }

    public int getCurrentPosition() {
        return clawLift.getCurrentPosition();
    }

}


