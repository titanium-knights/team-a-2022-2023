package org.firstinspires.ftc.teamcode.util;
import com.qualcomm.robotcore.hardware.*;

public class Lift {
    public DcMotor lift; //servo or motor???


    public static int HIGH_POSITION = 1000;
    public static int MID_POSITION = 500;
    public static int LOW_POSITION = 200;
    public static int GROUND_POSITION = 50;

    public Lift(HardwareMap hmap) {

        this.lift = hmap.dcMotor.get(CONFIG.liftMotor);
        this.lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER); //reset pos to 0
        this.lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER); //use encoder

    }

    public void setPower(double power) {
        lift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        lift.setPower(power);
    }

    public void setPositionMid() {
        lift.setTargetPosition(MID_POSITION);
    }
    public void setPositionGround() {lift.setTargetPosition(GROUND_POSITION);}

    //public void runToPosition()

}
