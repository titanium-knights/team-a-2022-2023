package org.firstinspires.ftc.teamcode.auto;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.rr.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.rr.trajectorysequence.TrajectorySequence;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.rr.trajectorysequence.TrajectorySequenceBuilder;
import org.firstinspires.ftc.teamcode.util.SignalParkVision;
import org.firstinspires.ftc.teamcode.util.*;


@Config
@Autonomous(name = "Tester coords", group = "Linear OpMode")

public class TesterAutonThrowaway extends LinearOpMode  {
    TrajectorySequence tester;

    public static int TEST_1_X = 5;
    public static int TEST_1_Y = 0;
//
    public static int TEST_2_X = 5;
    public static int TEST_2_Y = 10;


    public static Vector2d TEST_1 = new Vector2d(TEST_1_X, TEST_1_Y);
    public static Vector2d TEST_2 = new Vector2d(TEST_2_X, TEST_2_Y);

    protected SampleMecanumDrive drive;
    protected AprilTagVision vision;
    protected EncServo encServo;

    Telemetry dashTelemetry = FtcDashboard.getInstance().getTelemetry();

    protected void setupDevices(){
        drive = new SampleMecanumDrive(hardwareMap);
        vision = new AprilTagVision(hardwareMap, telemetry);
        encServo = new EncServo(hardwareMap);
    }

    public void initTraj() {
        TrajectorySequenceBuilder analysis = drive.trajectorySequenceBuilder(new Pose2d())
                .lineToConstantHeading(TEST_1);
                //.lineToConstantHeading(TEST_2);

        tester = analysis.build();

    }
    @Override
    public void runOpMode() throws InterruptedException {
        setupDevices();

        initTraj();
        telemetry.update();
        waitForStart();

        drive.setPoseEstimate(tester.start());
        drive.followTrajectorySequence(tester);

        while (opModeIsActive() && !Thread.currentThread().isInterrupted() && drive.isBusy()) {
            drive.update();
//            claw.keepPosition();

//            telemetry.addData("lmr", lift.getPositionR());
//            telemetry.addData("lml", lift.getPositionL());
        }


    }
}
