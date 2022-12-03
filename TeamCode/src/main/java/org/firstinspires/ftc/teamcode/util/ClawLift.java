package org.firstinspires.ftc.teamcode.util;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.*;

@Config
public class ClawLift {
    public DcMotor clawLift;

    public double clawLiftPOWER = .75;
    public double MIDDLE_POSITION = 500;
    public double MIDDLE_POSITION_BUFFER = 50;

    public ClawLift(HardwareMap hmap) {
        this.clawLift = hmap.dcMotor.get(CONFIG.clawLift);
    }


    public void setPower(double power, double negator) {
        if (clawLift.getCurrentPosition() > MIDDLE_POSITION + MIDDLE_POSITION_BUFFER) {
            clawLift.setPower(power);
        }
        else if (clawLift.getCurrentPosition() < MIDDLE_POSITION + MIDDLE_POSITION_BUFFER) {
            clawLift.setPower(power * negator);
        }
    }

    public int getCurrentPosition() {
        return clawLift.getCurrentPosition();
    }

}


