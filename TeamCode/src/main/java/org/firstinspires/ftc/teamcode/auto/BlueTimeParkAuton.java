package org.firstinspires.ftc.teamcode.auto;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.util.Claw;
import org.firstinspires.ftc.teamcode.util.ClawLift;
import org.firstinspires.ftc.teamcode.util.Lift;
import org.firstinspires.ftc.teamcode.util.MecanumDrive;

@TeleOp(name="BlueTimeParkAuton", group="Linear Opmode")
@Config
public class BlueTimeParkAuton extends LinearOpMode {
    protected MecanumDrive drive;
    protected Lift lift;
    protected ClawLift clawLift;
    protected Claw claw;
    public static final Pose2d startPose = new Pose2d(-35, 60, Math.toRadians(-90));

    @Override
    public void runOpMode() throws InterruptedException {
        drive = new MecanumDrive(hardwareMap);
        clawLift = new ClawLift(hardwareMap);
        lift = new Lift(hardwareMap);
        claw = new Claw(hardwareMap);

        waitForStart();

        lift.setInit();
        claw.openInit();


        drive.strafeRightWithPower(0.7);
        sleep(1200);
        drive.stop();


    }
}
