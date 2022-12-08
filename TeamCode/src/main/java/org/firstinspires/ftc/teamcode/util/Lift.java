package org.firstinspires.ftc.teamcode.util;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.*;

@Config
public class Lift {
    public DcMotor lmr; //lift right
    public DcMotor lml; //lift left

    public static double LIFT_POWER = 1;
    public static int HIGH_POSITION = 950;
    public static int MID_POSITION = 800;
    public static int LOW_POSITION = 650;
    public static int GROUND_POSITION = 50;

    public static int MAX_LIMIT = 1000;
    public static int MIN_LIMIT = -380;
    public static int INIT_LIMIT = 300;

    public static int AVERAGE_BUFFER = 100;

    public Lift(HardwareMap hmap) {

        this.lmr = hmap.dcMotor.get(CONFIG.liftMotorRight);
        this.lml = hmap.dcMotor.get(CONFIG.liftMotorLeft);

        this.lmr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER); //reset pos to 0
        this.lml.setMode(DcMotor.RunMode.RUN_USING_ENCODER); //use encoder

    }

    public void setPower(double power) {
        lmr.setPower(power);
        lml.setPower(power);
    }

    public void setPosition(int pos) {
        lmr.setTargetPosition(pos);
        lmr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        lmr.setPower(LIFT_POWER);

        lml.setTargetPosition(pos);
        lml.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        lml.setPower(LIFT_POWER);
    }

    //public void runToPosition()

    public void setInit() {

        lmr.setTargetPosition(INIT_LIMIT);
        lml.setTargetPosition(INIT_LIMIT);
    }

    public int getPositionR() {
        return lmr.getCurrentPosition();
    }

    public int getPositionL() {
        return lml.getCurrentPosition();
    }

    public void correctMotorPositions() {
        int r_currentPos = getPositionR();
        int l_currentPos = getPositionL();

        int avg_targetPos = (r_currentPos + l_currentPos)/2;

        //if the difference between the two motors is larger than the difference
        if (Math.abs(r_currentPos - avg_targetPos) > AVERAGE_BUFFER) {
            if (r_currentPos > avg_targetPos) {
                lmr.setPower(-LIFT_POWER);
            }
            if (r_currentPos < avg_targetPos) {
                lmr.setPower(LIFT_POWER);
            }
        }
        if (Math.abs(l_currentPos - avg_targetPos) > AVERAGE_BUFFER) {
            if (l_currentPos > avg_targetPos) {
                lml.setPower(-LIFT_POWER);
            }
            if (l_currentPos < avg_targetPos) {
                lml.setPower(LIFT_POWER);
            }
        }
    }


    public void runToPosition(int pos, double multiplier){
        int currentPos = lml.getCurrentPosition();
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
