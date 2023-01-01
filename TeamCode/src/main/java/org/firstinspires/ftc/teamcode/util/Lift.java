package org.firstinspires.ftc.teamcode.util;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.*;

@Config
public class Lift {
    public DcMotor lmr; //lift right
    public DcMotor lml; //lift left

    public static double LIFT_POWER = .8;
    public static double LIFT_POWER_MULTIPLYER = .8;

    public static int HIGH_POSITION = 950;
    public static int MID_POSITION = 800;
    public static int LOW_POSITION = 650;
    public static int GROUND_POSITION = 50;

    public static int MAX_LIMIT = 1000;
    public static int MIN_LIMIT = -348;

    public static int AVERAGE_BUFFER = 15;

    public static double DIFFERENCE = 0;

    public static double SENSITIVITY = 0.05;
    public static double TOLERANCE = 20;

    public static double Y = 0;

    public double LEFT_JOYSTICK_Y_Positive = 0;
    public double LEFT_JOYSTICK_Y_Negative = 0;

    public Lift(HardwareMap hmap) {
        this.lmr = hmap.dcMotor.get(CONFIG.liftMotorRight);
        this.lml = hmap.dcMotor.get(CONFIG.liftMotorLeft);
    }

    public void setPower(double power) {
        lmr.setPower(-power * LIFT_POWER_MULTIPLYER);
        lml.setPower(-power * LIFT_POWER_MULTIPLYER);
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
        lmr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lml.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        lmr.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        lml.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

    public int getPositionR() {
        return lmr.getCurrentPosition();
    }

    public int getPositionL() {
        return lml.getCurrentPosition();
    }
    
    public int getAverage() {
        return (getPositionL() + getPositionR())/2;
    }

    public void correctMotorPositions(double pressedVal) {
        //if the difference between the two motors is larger than the difference

        if(-pressedVal > 0) {
            if(getAverage() < 900){
                LEFT_JOYSTICK_Y_Positive = -pressedVal;
            }
            else{
                LEFT_JOYSTICK_Y_Positive = 0;
            }
        }


        else if(-pressedVal < 0){
            if(getAverage() > -300){
                LEFT_JOYSTICK_Y_Negative = -pressedVal;
            }
            else{
                LEFT_JOYSTICK_Y_Negative = 0;
            }
        }

        else{
            LEFT_JOYSTICK_Y_Positive = 0;
            LEFT_JOYSTICK_Y_Negative = 0;
        }


        if(Math.abs(-pressedVal)>0.1){
            if(-pressedVal > 0){
                lmr.setPower(LEFT_JOYSTICK_Y_Positive);
                lml.setPower(LEFT_JOYSTICK_Y_Positive);
            }
            else if(-pressedVal < 0){
                lmr.setPower(LEFT_JOYSTICK_Y_Negative);
                lml.setPower(LEFT_JOYSTICK_Y_Negative);
            }
        }

        else {
            setPower(0);

            if (Math.abs(getPositionR() - Math.abs(getPositionL())) < AVERAGE_BUFFER) {
                lmr.setPower(0);
            }
            else if (Math.abs(getPositionR() - Math.abs(getPositionL())) > AVERAGE_BUFFER) {
                if (getPositionR() > getPositionL()) {
                    lmr.setPower(-LIFT_POWER * .4); //multiply by .8 since gracity helps
                }
                if (getPositionR() < getPositionL())
                    lmr.setPower(LIFT_POWER * .6);
                }
        }
    }



        public void correctMotorVed(double gamepadVal) {

            DIFFERENCE = (getPositionR() - getPositionL());

            if(Math.abs(gamepadVal)>0.1)
            {
                lmr.setPower((SENSITIVITY * -DIFFERENCE) + (0.9 * Y));
                lml.setPower((SENSITIVITY * DIFFERENCE) + (0.9 * Y));
            }

            else
            {
                if (DIFFERENCE > TOLERANCE)
                {
                    lmr.setPower((SENSITIVITY * -DIFFERENCE));
                    lml.setPower((SENSITIVITY * DIFFERENCE));
                }
                else
                {
                    lmr.setPower(0);
                    lml.setPower(0);
                }
            }

        }


    public double getDIFFERENCE() {
        return DIFFERENCE;
    }


    public void runToPosition(int pos, double multiplier){
        int currentPos =             lml.getCurrentPosition();
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
