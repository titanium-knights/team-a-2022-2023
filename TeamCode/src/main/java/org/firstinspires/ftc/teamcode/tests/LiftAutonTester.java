package org.firstinspires.ftc.teamcode.tests;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.util.Claw;
import org.firstinspires.ftc.teamcode.util.ClawLift;
import org.firstinspires.ftc.teamcode.util.Lift;
import org.firstinspires.ftc.teamcode.util.MecanumDrive;
import org.firstinspires.ftc.teamcode.util.SignalParkVision;

@Config
@Autonomous(name="LiftAutonTester", group="Linear Opmode")
public class LiftAutonTester extends LinearOpMode {
    protected Lift lift;
    protected Claw claw;

    public static int FORWARD1_TIME = 600;
    public static int TURNR_1_TIME = 350;
    public static int DUMP_TIME = 500;
    public static int TURNR_2_TIME = 80;
    public static int BACKWARD_TIME = 800;
    public static int BUFFER_ZONE = 100;
    public static double P = 0.005;
    public static int BUFFER_CLAW = 90;

    public static int LIFT_UP = 500;

    public static int CLAW_OPEN_WAIT = 200;

    public static int LIFTDOWN_TIME = 500;

    public static double LIFT_POWER_AUTON = -1;

    public void goToPosition(int targetPos){
        while (Math.abs(lift.getPosition()-targetPos)>BUFFER_ZONE){
            int error = targetPos - lift.getPosition();
            lift.setPower(-error*P);

            telemetry.addData("Lift Motor Pos", lift.getPosition());
            telemetry.addData("Error", error);
            telemetry.update();

            if(Math.abs(lift.getPosition()-targetPos) < BUFFER_CLAW && targetPos > 400){
                claw.open();
                sleep(200);
            }
        }
        lift.setPower(0);
    }

    public void goToPosition2(int targetPos){

        if(lift.getPosition() >= targetPos){
            targetPos -= 30;
        }
        else{
            targetPos += 140;
        }

        while (Math.abs(lift.getPosition()-targetPos)>20){
            int error = targetPos - lift.getPosition();
            lift.setPower(-error*P);

            telemetry.addData("Lift Motor Pos", lift.getPosition());
            telemetry.addData("Error", error);
            telemetry.update();

            if(Math.abs(lift.getPosition()-targetPos) < 40 && targetPos > 400){
                claw.open();
                sleep(200);
            }
        }
        lift.setPower(0);
    }

    private void dumpAndLower() {
        //bring lift up, pause, bring lift down
        claw.closeCone();
        sleep(200);
        goToPosition(Lift.MID_POSITION);
        sleep(1000);

        goToPosition(Lift.GROUND_POSITION);
        sleep(1000);
        claw.closeCone();
        sleep(500);
    }

    private void dumpAndLower2() {
        goToPosition2(Lift.MID_POSITION);
        sleep(1000);

        goToPosition2(Lift.GROUND_POSITION);
        sleep(1000);
        claw.closeCone();
        sleep(500);
    }

    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart();
        claw = new Claw(hardwareMap);
        lift = new Lift(hardwareMap);

        telemetry.addData("Current Lift Encoder Val", lift.getPosition());
        telemetry.update();

        claw.closeCone();
        sleep(300);

        dumpAndLower();

        sleep(DUMP_TIME);

    }
}

