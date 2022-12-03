package org.firstinspires.ftc.teamcode.util;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.*;

@Config
public class ClawLift {
    public DcMotor clawLift;

    public int MIDDLE_POSITION = 500;
    public int MIDDLE_POSITION_BUFFER = 150;

    public int RUN_BUFFER = 50;

    public double clawLiftPOWER = .75;
    public int targetPos = 300;

    public double P = 0.002;

    public ClawLift(HardwareMap hmap) {
        this.clawLift = hmap.dcMotor.get(CONFIG.clawLift);
    }


    public void setPower(double power) {
        if (power == 0) {
            clawLift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }
        clawLift.setPower(power);
    }

    public int getPosition() {
        return clawLift.getCurrentPosition();
    }
}


