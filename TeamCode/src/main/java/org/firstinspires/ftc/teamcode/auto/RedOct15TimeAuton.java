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
    public static int FORWARD1_TIME = 600;
    public static int TURNR_1_TIME = 350;
    public static int DUMP_TIME = 800;
    public static int TURNR_2_TIME = 80;
    public static int BACKWARD_TIME = 800;

    public static int LIFT_POWERUP_TIME = 200;

    protected MecanumDrive drive;
    protected ElapsedTime elapsedTime;
    protected Claw claw;
    protected ClawLift clawLift;
    protected Lift lift;
    public static final Pose2d startPose = new Pose2d(-35, -60, Math.toRadians(90));

    private void dumpAndLower() {
        lift.setPower(.5);
        sleep(200);
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

        lift.liftUp();
        sleep(LIFT_POWERUP_TIME);

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

        //turn and go back to park
        drive.turnLeftWithPower(0.8);
        sleep(TURNR_2_TIME);
        drive.backwardWithPower(0.8);
        sleep(BACKWARD_TIME);
        drive.stop();

    }
}
