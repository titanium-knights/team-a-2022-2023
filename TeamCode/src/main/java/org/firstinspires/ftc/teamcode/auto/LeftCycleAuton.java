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
@Autonomous(name = "Left Cycle Auton", group = "Linear OpMode")

public class LeftCycleAuton extends LinearOpMode  {
    public static int START_Y = 0;
    public static int START_X = 0;

    public static int FORWARD_CYCLE_X = 51;
    public static int FORWARD_CYCLE_Y = 0;
    public static Vector2d FORWARD_CYCLE = new Vector2d(FORWARD_CYCLE_X, FORWARD_CYCLE_Y);
    public static int FORWARD_CONE_ANG = -85;

    public static int FORWARD_CONE_X = 50;
    public static int FORWARD_CONE_Y = -28;
    public static Vector2d FORWARD_CONE = new Vector2d(FORWARD_CONE_X, FORWARD_CONE_Y);
//
//    public static int FORWARD_CYCLE_2_X = 50;
//    public static int FORWARD_CYCLE_2_Y = 0;
//    public static Vector2d FORWARD_CYCLE_2 = new Vector2d(FORWARD_CYCLE_2_X, FORWARD_CYCLE_2_Y);

    public static int TOWARD_HIGH_X = 30;
    public static int TOWARD_HIGH_Y = 1;
    public static Vector2d TOWARD_HIGH = new Vector2d(TOWARD_HIGH_X, TOWARD_HIGH_Y);

    //cycles

    public static double LIFT_POWER_UP = .4;
    public static double LIFT_POWER_DOWN = .3;

    public static int LIFT_HEIGHT = 875;

    public static int LIFT_MIDDLE = 150;

    public static int LIFT_LOWER_1 = -65;
    public static int LIFT_LOWER_2 = -240;
    public static int LIFT_LOWER_3 = -320;

    //   toCone
    public static Vector2d Z1_S2 = new Vector2d(20,20);
    public static Vector2d Z2_S2 = new Vector2d(20,5);
    public static Vector2d Z3_S2 = new Vector2d(25,-15);;

    public static Vector2d zoneAnalysis = Z1_S2;
    public static Vector2d ZONE_DEFAULT = new Vector2d(20,5);

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
        vision = new AprilTagVision(hardwareMap, telemetry);

        encServo = new EncServo(hardwareMap);
        encServo.setPosition(encServo.DOWNPOS);

         lift = new Lift(hardwareMap);
         lift.setInit();

        claw = new Claw(hardwareMap);
        clawLift = new ClawLift(hardwareMap);
        clawLift.setInit();
        clawSpin = new ClawSpin(hardwareMap);

        claw.open(); //moves upon init
        claw.setPosition(claw.openPos);
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
    }
    @Override
    public void runOpMode() throws InterruptedException {
        setupDevices();

        sleep(4000);
        int position =  vision.getPosition();
        sleep(1000);


        telemetry.addData("Detected: ", position);
        dashTelemetry.addData("Detected", position);

        telemetry.addData("forward X", FORWARD_CYCLE_X);
        telemetry.addData("forward Y", FORWARD_CYCLE_Y);

        telemetry.addData("to cone X", FORWARD_CONE_X);
        telemetry.addData("to cone Y", FORWARD_CONE_Y);

        telemetry.addData("to high X", TOWARD_HIGH_X);
        telemetry.addData("to high Y", TOWARD_HIGH_Y);


        TrajectorySequence sequence1 = drive.trajectorySequenceBuilder(new Pose2d())
                //cycle part
                .lineToConstantHeading(FORWARD_CYCLE)
                .turn(Math.toRadians(FORWARD_CONE_ANG))
                .waitSeconds(0)
                .addTemporalMarker(()->{
                    lift.setPosition(LIFT_LOWER_1, LIFT_POWER_DOWN);

                })
                .lineToConstantHeading(FORWARD_CONE)
                .waitSeconds(2)
                .addTemporalMarker(()-> {
                    claw.setPosition(claw.closedConePos);
                    claw.closeCone();

                    sleep(2000);

                    lift.setPosition(LIFT_MIDDLE, LIFT_POWER_UP);
                })
                .waitSeconds(1)
                .build();

        telemetry.update();
        waitForStart();

        drive.setPoseEstimate(sequence1.start());
        drive.followTrajectorySequence(sequence1);

        TrajectorySequence sequence2 = drive.trajectorySequenceBuilder(drive.getPoseEstimate())
                .lineToLinearHeading(new Pose2d(FORWARD_CYCLE, 0))
                .lineToConstantHeading(TOWARD_HIGH)
                .turn(Math.toRadians(357))
                .waitSeconds(1)
                .addTemporalMarker(() -> {
                    lift.setPosition(LIFT_HEIGHT, LIFT_POWER_UP);

                    clawSpin.setPosition(clawSpin.BACKPOS);

                    clawLift.setPosition(1729, false);

                    sleep(2000);
                    claw.setPosition(claw.openPos);
                    claw.open();
                })
                .addTemporalMarker( () -> {
                    sleep(2000);

                    claw.setPosition(claw.closedConePos);
                    claw.closeCone();

                    clawSpin.setPosition(clawSpin.FRONTPOS);

                    clawLift.setPosition(clawLift.FRONT_DUMP, true);

                    sleep(2000);

                    lift.setPosition(LIFT_MIDDLE, LIFT_POWER_UP);

                })
                .waitSeconds(2)
                .lineToConstantHeading(ZONE_DEFAULT)
                .waitSeconds(1)
                .lineToConstantHeading(zoneAnalysis)
                .build();

        drive.followTrajectorySequence(sequence2);

    }
}
