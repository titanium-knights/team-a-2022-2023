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
    public static int DUMP_TIME = 800;
    public static int TURNR_2_TIME = 80;
    public static int BACKWARD_TIME = 800;

    public static int LIFT_UP = 500;

    public static int CLAW_OPEN_WAIT = 200;

    public static int LIFTDOWN_TIME = 500;

    public static double LIFT_POWER_AUTON = -1;

    private void goUp() {
        if (lift.getPosition() < lift.MID_POSITION) {
            lift.setPower(-1);
        }
        else {
            lift.setPower(0);
        }
    }

    private void dumpAndLower2() {
        //bring lift up, pause, bring lift down
        lift.setPower(LIFT_POWER_AUTON);
        sleep(100);
        claw.open();
        claw.closeCone();
    }

    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart();
        claw = new Claw(hardwareMap);
        lift = new Lift(hardwareMap);

        telemetry.addData("Current Lift Encoder Val", lift.getPosition());

        goUp();

        claw.open();
        sleep(CLAW_OPEN_WAIT);
        claw.closeCone();

        lift.setPower(0);
        sleep(DUMP_TIME);

    }
}

