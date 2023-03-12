package org.firstinspires.ftc.teamcode.auto;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.rr.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.rr.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.rr.trajectorysequence.TrajectorySequenceBuilder;
import org.firstinspires.ftc.teamcode.util.AprilTagVision;
import org.firstinspires.ftc.teamcode.util.Claw;
import org.firstinspires.ftc.teamcode.util.ClawLift;
import org.firstinspires.ftc.teamcode.util.ClawSpin;
import org.firstinspires.ftc.teamcode.util.EncServo;
import org.firstinspires.ftc.teamcode.util.Lift;
import org.firstinspires.ftc.teamcode.util.SignalParkVision;


@Config

@Autonomous(name = "Preload", group = "Linear OpMode")

public class Preload extends LinearOpMode  {
    TrajectorySequence detectPark;

    public static Vector2d ZONE_START_DROP_RIGHT = new Vector2d(0,-11); //up at the corner

    public static Vector2d ZONE_START_2 = new Vector2d(26,-11); //up at the corner
//
//    public static int TURN = -90;

    public static Vector2d Z1_S2 = new Vector2d(26,35);
    public static Vector2d Z2_S2 = new Vector2d(26,18);
    public static Vector2d Z3_S2 = new Vector2d(26,-10);;

    public static Vector2d TOWARD_LOW = new Vector2d(5, 5);

    public static Vector2d zoneAnalysis = Z1_S2;

    public static double turnToLow = Math.toRadians(-45);
    public static int RAISE_TO_LOW = 500;

    protected SampleMecanumDrive drive;
    protected AprilTagVision vision;

    protected EncServo encServo;
    protected Lift lift;
    protected Claw claw;
    protected ClawLift clawLift;
    protected ClawSpin clawSpin;
    Telemetry dashTelemetry = FtcDashboard.getInstance().getTelemetry();

    protected void setupDevices(){
        drive = new SampleMecanumDrive(hardwareMap);
        vision = new AprilTagVision(hardwareMap, dashTelemetry);

        encServo = new EncServo(hardwareMap);
        encServo.setPosition(encServo.DOWNPOS);

        lift = new Lift(hardwareMap);
        lift.setInit();

        claw = new Claw(hardwareMap);
        clawLift = new ClawLift(hardwareMap);
        clawLift.setInit();
        clawSpin = new ClawSpin(hardwareMap);

        claw.setPosition(claw.closedConePos);
        claw.closeCone();

        sleep(4000);

        claw.keepPosition();

        clawSpin.setPosition(clawSpin.FRONTPOS);
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
                .turn(turnToLow)
                .addTemporalMarker(() -> {
                    clawLift.setPosition(RAISE_TO_LOW, true);
                })
                .lineToConstantHeading(TOWARD_LOW)
                .addTemporalMarker(() -> {
                    claw.setPosition(claw.openPos);
                    claw.open();
                });

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
