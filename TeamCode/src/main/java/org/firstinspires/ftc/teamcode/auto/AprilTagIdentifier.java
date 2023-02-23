package org.firstinspires.ftc.teamcode.auto;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.rr.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.rr.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.util.AprilTagVision;
//import org.firstinspires.ftc.teamcode.util.SignalParkVision;


@Config
@Autonomous(name = "April Tag Tester", group = "Linear OpMode")

public class AprilTagIdentifier extends LinearOpMode  {
    TrajectorySequence detectPark;

    public static Vector2d ZONE_START_DROP_RIGHT = new Vector2d(0,24); //up at the corner

    public static Vector2d Z3_S2 = new Vector2d(-24,24);

    protected SampleMecanumDrive drive;
    protected AprilTagVision vision;
    Telemetry dashTelemetry = FtcDashboard.getInstance().getTelemetry();

    protected void setupDevices(){
        drive = new SampleMecanumDrive(hardwareMap);
        vision = new AprilTagVision(hardwareMap, dashTelemetry);
    }

    @Override
    public void runOpMode() throws InterruptedException {
        setupDevices();

        sleep(4000);
        int position =  vision.getPosition();
        sleep(1000);


        telemetry.addData("Detected: ", position);
        dashTelemetry.addData("Detected", position);

        telemetry.update();

        waitForStart();

        sleep(1000);


    }
}
