package org.firstinspires.ftc.teamcode.util;
import com.qualcomm.robotcore.hardware.*;

public class Lift {
    public DcMotor Lift; //servo or motor???


    public static int HIGH_POSITION = 1000;
    public static int MID_POSITION = 500;
    public static int LOW_POSITION = 200;
    public static int GROUND_POSITION = 50;

    public Lift(HardwareMap hmap) {
        this.Lift = hmap.dcMotor.get(CONFIG.liftMotor);
    }

    public void setPower(double power) {
        Lift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        Lift.setPower(power);

        Lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER); //reset pos to 0
        Lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER); //use encoder
    }

    public void setPosition(int position) {

    }

}
