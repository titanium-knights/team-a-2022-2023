package org.firstinspires.ftc.teamcode.util;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.*;

@Config
public class Lift {
    public DcMotor lift; //servo
    public static double LIFT_POWER = 1;
    public static int HIGH_POSITION = 950;
    public static int MID_POSITION = 800;
    public static int LOW_POSITION = 650;
    public static int GROUND_POSITION = 50;

    public static int MAX_LIMIT = 900;
    public static int INIT_LIMIT = -50;

    public Lift(HardwareMap hmap) {

        this.lift = hmap.dcMotor.get(CONFIG.liftMotor);
        this.lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER); //reset pos to 0
        this.lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER); //use encoder

    }

    public void setPower(double power) {
        lift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        lift.setPower(power);
    }

    public void setPosition(int pos) {
        lift.setTargetPosition(pos);
        lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        lift.setPower(LIFT_POWER);
    }



    public void setPositionGround() {
        lift.setTargetPosition(GROUND_POSITION);}

    //public void runToPosition()

    public void setInit() {
        lift.setTargetPosition(INIT_LIMIT);
    }

    public int getPosition() {
        return lift.getCurrentPosition();
    }


    public void runToPosition(int pos, double multiplier){
        int currentPos = lift.getCurrentPosition();
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

}
