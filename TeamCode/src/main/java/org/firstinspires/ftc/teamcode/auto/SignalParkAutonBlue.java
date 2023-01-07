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


@Config
@Autonomous(name = "Signal Park Auton Blue", group = "Linear OpMode")

public class SignalParkAutonBlue extends LinearOpMode  {
    TrajectorySequence detectPark;

    public static Vector2d ZONE_START_DROP_RIGHT = new Vector2d(0,24); //up at the corner

    public static Vector2d ZONE_START_2 = new Vector2d(-24,24); //up at the corner


    public static Vector2d Z1_S2 = new Vector2d(-24,-24);
    public static Vector2d Z2_S2 = new Vector2d(-24,0);
    public static Vector2d Z3_S2 = new Vector2d(-24,25);;

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
                .lineToConstantHeading(ZONE_START_2)
                .lineToConstantHeading(zoneAnalysis);

        detectPark = build.build();
    }
    @Override
    public void runOpMode() throws InterruptedException {
        setupDevices();

        sleep(4000);
        int position =  vision.getPosition();
        sleep(2000);


        telemetry.addData("Detected: ", position);
        dashTelemetry.addData("Detected", position);

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
