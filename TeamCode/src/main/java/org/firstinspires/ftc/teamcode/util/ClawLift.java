package org.firstinspires.ftc.teamcode.util;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.*;

@Config
public class ClawLift {
    public DcMotor clawLift;
    public double POWER = .7;

    public int FRONT_DUMP = 0;
    public int BACK_DUMP = 9100;

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

    public void setPosition(int pos) {
        clawLift.setTargetPosition(pos);
        clawLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        clawLift.setPower(POWER);
    }



}


