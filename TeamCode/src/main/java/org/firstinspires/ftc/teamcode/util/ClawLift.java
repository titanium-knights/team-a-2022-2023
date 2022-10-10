package org.firstinspires.ftc.teamcode.util;

import com.qualcomm.robotcore.hardware.*;

public class ClawLift {
    public DcMotor liftMotor;

    public int downPos = 100;
    public int upPos = 200;

    public ClawLift(HardwareMap hmap) {
        this.liftMotor = hmap.dcMotor.get(CONFIG.clawLift);
    }


}
