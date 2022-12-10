package org.firstinspires.ftc.teamcode.auto;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.rr.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.rr.trajectorysequence.TrajectorySequence;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.rr.trajectorysequence.TrajectorySequenceBuilder;
import org.firstinspires.ftc.teamcode.util.Claw;
import org.firstinspires.ftc.teamcode.util.ClawLift;
import org.firstinspires.ftc.teamcode.util.ClawSpin;
import org.firstinspires.ftc.teamcode.util.Lift;
import org.firstinspires.ftc.teamcode.util.MecanumDrive;
import org.firstinspires.ftc.teamcode.util.SignalParkVision;
import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.Vector;


@Config
@Autonomous(name = "Signal Park Auton", group = "Linear OpMode")

public class SignalParkAuton extends LinearOpMode  {
    TrajectorySequence detectPark;

    public static final Pose2d startPose = new Pose2d(-12, 63,Math.toRadians(90));

    public static double DRIVE_POWER = 0.5;

    public static int FORWARD_TIME = 400;
    public static int STRAFE_TIME = 1000;
    public static int PARK_TIME = 1500;

    public static Vector2d ZONE_START_DROP_RIGHT = new Vector2d(0,24); //up at the corner
    public static Vector2d ZONE_START_DROP_LEFT = new Vector2d(0,24); //up at the corner

    public static Vector2d Z3_S2 = new Vector2d(-24,24);
    public static Vector2d Z2_S2 = new Vector2d(-24,0);
    public static Vector2d Z1_S2 = new Vector2d(-24,-24);

    public static Vector2d zoneAnalysis = Z1_S2;

    protected SampleMecanumDrive drive;
    protected SignalParkVision vision;
    Telemetry dashTelemetry = FtcDashboard.getInstance().getTelemetry();

    protected void setupDevices(){
        drive = new SampleMecanumDrive(hardwareMap);
        vision = new SignalParkVision(hardwareMap, null);
    }

    public void initTraj() {
        if (vision.getPosition() == 1) {
            zoneAnalysis = Z1_S2;
        } else if (vision.getPosition() == 2) {
            zoneAnalysis = Z2_S2;
        } else {
            zoneAnalysis = Z3_S2;
        }

        TrajectorySequenceBuilder build = drive.trajectorySequenceBuilder(new Pose2d())
                .lineToConstantHeading(ZONE_START_DROP_RIGHT)
                .lineToConstantHeading(zoneAnalysis);

        detectPark = build.build();
    }
    @Override
    public void runOpMode() throws InterruptedException {
        setupDevices();

        initTraj();
        telemetry.update();
        waitForStart();

        drive.setPoseEstimate(detectPark.start());
        drive.followTrajectorySequenceAsync(detectPark);
        while (opModeIsActive() && !Thread.currentThread().isInterrupted() && drive.isBusy()) {
            drive.update();
        }


    }
}
