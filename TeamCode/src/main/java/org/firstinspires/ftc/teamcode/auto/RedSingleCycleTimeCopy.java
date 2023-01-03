package org.firstinspires.ftc.teamcode.auto;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.util.Claw;
import org.firstinspires.ftc.teamcode.util.ClawLift;
import org.firstinspires.ftc.teamcode.util.Lift;
import org.firstinspires.ftc.teamcode.util.LiftOld;
import org.firstinspires.ftc.teamcode.util.MecanumDrive;
import org.firstinspires.ftc.teamcode.util.SignalParkVision;

@Config @Deprecated
@Autonomous(name="RedSingleCycleTimeAutonTEST", group="Linear Opmode")
public class RedSingleCycleTimeCopy extends LinearOpMode {
    protected MecanumDrive drive;
    protected ElapsedTime elapsedTime;
    protected Claw claw;
    protected LiftOld lift;

    public static int FORWARD1_TIME = 600;
    public static int TURNR_1_TIME = 350;
    public static int DUMP_TIME = 800;
    public static int TURNR_2_TIME = 80;
    public static int BACKWARD_TIME = 800;

    public static int LIFTUP_TIME = 500;

    public static int LIFTDOWN_TIME = 500;

    public static int LIFT_POWERUP_TIME = 500;

    private void dumpAndLower() {
        //bring lift up, pause, bring lift down
        lift.setPosition(-Lift.MID_POSITION);
        claw.open();
        lift.setPosition(-Lift.GROUND_POSITION);
        claw.closeCone();
    }

    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart();
        drive = new MecanumDrive(hardwareMap);
        claw = new Claw(hardwareMap);
        lift = new LiftOld(hardwareMap);

            //go forward to dump
            drive.forwardWithPower(0.8);
            sleep(FORWARD1_TIME);
            drive.stop();

            //dump cone
            drive.turnRightWithPower(0.8);
            sleep(TURNR_1_TIME);
            drive.stop();

            dumpAndLower();

            sleep(DUMP_TIME);

            //temporary only if color vision doesnt work
            drive.turnRightWithPower(0.8);
            sleep(300);
            drive.backwardWithPower(0.8);
            sleep(BACKWARD_TIME);
            drive.stop();
        }
    }

