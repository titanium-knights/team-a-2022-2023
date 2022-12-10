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


    public void setPower(double power) {
        clawLift.setPower(power);
    }

    public void runToPosition(int pos, double multiplier){
        int currentPos = clawLift.getCurrentPosition();
        //double multiplier = Math.min(1, Math.max(0, Math.abs(pos - currentPos) / 150.0));
        if(pos - currentPos > 30){
            setPower(-1 * multiplier);
        }
        else if(pos - currentPos < -30){
            setPower(1 * multiplier);
        }
        else if (pos == 0) {
            setPower(0);
        } else {
            setPower(0);
        }
    }

        public int getPosition() {
            return clawLift.getCurrentPosition();
        }

}


