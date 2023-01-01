package org.firstinspires.ftc.teamcode.util;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.*;

@Config
public class ClawLift {
    public DcMotor clawLift;
    public int FRONT_PICKUP_POS = -920;
    public int BACK_PICKUP_POS = 0;

    public ClawLift(HardwareMap hmap) {
        this.clawLift = hmap.dcMotor.get(CONFIG.clawLift);
    }

    public void setInit() {
        clawLift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        clawLift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

    public void setPower(double power) {
        clawLift.setPower(power);
    }

    public int getPosition() {
        return clawLift.getCurrentPosition();
    }

        public int getPosition() {
            return clawLift.getCurrentPosition();
        }

}


