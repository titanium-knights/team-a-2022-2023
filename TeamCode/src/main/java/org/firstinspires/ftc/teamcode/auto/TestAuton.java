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

@Config  @Deprecated
@Autonomous(name = "test", group = "Linear OpMode")

public class TestAuton extends LinearOpMode  {

    protected SampleMecanumDrive drive;
    protected SignalParkVision vision;
    Telemetry dashTelemetry = FtcDashboard.getInstance().getTelemetry();

    @Override
    public void runOpMode() throws InterruptedException {
        drive = new SampleMecanumDrive(hardwareMap);
        vision = new SignalParkVision(hardwareMap, null);

        try {
            vision = new SignalParkVision(hardwareMap, null);

            telemetry.addData("Detected: ", vision.getPosition());

            dashTelemetry.addData("Detected", vision.getPosition() == 1);
            telemetry.update();

            waitForStart();

            if (vision.getPosition() == 2) {
                Trajectory detect2path = drive.trajectoryBuilder(new Pose2d())
                        .forward(30)
                        .build();

                drive.followTrajectory(detect2path);

            } else if (vision.getPosition() == 1) {

                Trajectory detect1path = drive.trajectoryBuilder(new Pose2d())
                        .strafeRight(24)
                        .forward(30)
                        .build();

                drive.followTrajectory(detect1path);

            } else if (vision.getPosition() == 3) {
                Trajectory detect3path = drive.trajectoryBuilder(new Pose2d())
                        .strafeLeft(24)
                        .forward(30)
                        .build();

                drive.followTrajectory(detect3path);
            }
        } catch (Exception ex) {
            waitForStart();

            Trajectory backUpPath = drive.trajectoryBuilder(new Pose2d())
                    .strafeLeft(24)
                    .build();
            drive.followTrajectory(backUpPath);

        }
    }
}