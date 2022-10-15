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
import org.firstinspires.ftc.teamcode.util.MecanumDrive;

@Autonomous(name="RedOct15TimeAuton", group="Linear Opmode")
@Config
public class RedOct15TimeAuton extends LinearOpMode {
    protected MecanumDrive drive;
    protected ElapsedTime elapsedTime;
    protected Claw claw;
    protected ClawLift clawLift;
    protected Lift lift;
    public static final Pose2d startPose = new Pose2d(-35, -60, Math.toRadians(90));
//    public static double MOVEMENT_FACTOR = 0.025;
//    public static double TURN_TIME = 0.43;

//    private void updateDevices() {
//        if (liftPos != null) {
//            lift.runToPosition(liftPos, 0.9);
//        }
//    }
//
//    private void waitSeconds(double seconds) {
//        double start = elapsedTime.seconds();
//        while (opModeIsActive() && !Thread.currentThread().isInterrupted() && elapsedTime.seconds() - start < seconds) {
//            updateDevices();
//        }
//    }

    private void dumpAndLower() {
        lift.setPositionMid();
        sleep(1000);
        claw.open();
        sleep(500);
        lift.setPositionGround();
        sleep(1000);
        claw.closeCone();
        sleep(500);
    }

    @Override
    public void runOpMode() throws InterruptedException {
        drive = new MecanumDrive(hardwareMap);
        claw = new Claw(hardwareMap);
        clawLift = new ClawLift(hardwareMap);
        lift = new Lift(hardwareMap);
        elapsedTime = new ElapsedTime();

        waitForStart();
        elapsedTime.reset();

        //go forward to dump
        drive.forwardWithPower(0.8);
        sleep(800);
        drive.stop();

        //dump cone
        drive.turnRightWithPower(0.8);
        sleep(700);
        drive.stop();
        dumpAndLower();
        sleep(1000);

        //turn and go back to park
        drive.turnRightWithPower(0.8);
        sleep(300);
        drive.backwardWithPower(0.8);
        sleep(1000);
        drive.stop();

    }
}
