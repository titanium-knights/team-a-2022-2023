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


@Config @Deprecated
@Autonomous(name = "[TEST] Blue Left cycle", group = "Linear OpMode")

public class BlueCycleTest extends LinearOpMode  {
    TrajectorySequence fullTraj;

    public static int CYCLES = 3;

    public static int FORWARD_CONE_ANG = -88;
    public static int TOWARD_HIGH_ANG = 45;

    public static Pose2d FORWARD_CYCLE = new Pose2d(-50, 0, Math.toRadians(FORWARD_CONE_ANG));
    public static Pose2d FORWARD_CONE = new Pose2d(-50, -26, Math.toRadians(0));
    public static Pose2d TOWARD_HIGH = new Pose2d(-56, 6, TOWARD_HIGH_ANG);

    //cycles
    public static double LIFT_POWER_UP = .4;
    public static double LIFT_POWER_DOWN = .3;

    public static int LIFT_LOWER_1 = -120;
    public static int LIFT_LOWER_2 = -240;
    public static int LIFT_LOWER_3 = -320;

    public static int[] LIFT_LOWERS = {LIFT_LOWER_1, LIFT_LOWER_2, LIFT_LOWER_3};

    public static Pose2d FORWARD_CONE_1 = new Pose2d(-50, -26, Math.toRadians(0));
    public static Pose2d FORWARD_CONE_2 = new Pose2d(-50, -26, Math.toRadians(0));
    public static Pose2d FORWARD_CONE_3 = new Pose2d(-50, -26, Math.toRadians(0));

    public static Pose2d[] FORWARD_CONES = {FORWARD_CONE_1, FORWARD_CONE_2, FORWARD_CONE_3};

    //starting pos to reset for analysis one mats from starting pos, this is the same as zone 2
    public static Vector2d ZONE_START_CENTER = new Vector2d(-24,0); //up at the corner

    public static Vector2d Z1_S2 = new Vector2d(-24,-24);
    public static Vector2d Z2_S2 = new Vector2d(-24,0);
    public static Vector2d Z3_S2 = new Vector2d(-24,24);

    public static Vector2d zoneAnalysis = Z1_S2;

    protected SampleMecanumDrive drive;
    protected SignalParkVision vision;
    protected Lift lift;
    protected Claw claw;
    protected ClawLift clawLift;
    protected ClawSpin clawSpin;
    Telemetry dashTelemetry = FtcDashboard.getInstance().getTelemetry();

    protected void setupDevices(){
        drive = new SampleMecanumDrive(hardwareMap);
        vision = new SignalParkVision(hardwareMap, null);
        lift = new Lift(hardwareMap);
        claw = new Claw(hardwareMap);
        clawLift = new ClawLift(hardwareMap);
        clawSpin = new ClawSpin(hardwareMap);
        claw.open(); //moves upon init
    }

    public void initTraj() {
        //getting analysis
        if (vision.getPosition() == 1) {
            zoneAnalysis = Z1_S2;
        } else if (vision.getPosition() == 2) {
            zoneAnalysis = Z2_S2;
        } else {
            zoneAnalysis = Z3_S2;
        }

        //PART 1 -- starting the cycling by going straight, lining up with the cone stack
        TrajectorySequenceBuilder traj = drive.trajectorySequenceBuilder(new Pose2d())
                .lineToSplineHeading(FORWARD_CYCLE); //starts the cycling
        //PART 2 -- cycling
        try {
        for (int i = 0; i <= CYCLES; i++) { //cycling
            int liftLowerTo = LIFT_LOWERS[i];

            traj.setReversed(false)
             .addTemporalMarker(()->{
                        lift.setPosition(liftLowerTo, LIFT_POWER_DOWN);
                    })
                    .lineToSplineHeading(FORWARD_CONES[i])
                    .waitSeconds(0.5)
                    .addTemporalMarker(()-> {
                        claw.closeCone();
                    })
                    .waitSeconds(2) //wait to pick up claw
                    .addTemporalMarker(() -> {
                        lift.setPosition(lift.MAX_LIMIT, LIFT_POWER_UP);
                    })
                    .waitSeconds(1)
                    .lineToSplineHeading(FORWARD_CYCLE)
                    .lineToSplineHeading(TOWARD_HIGH)
                    .addTemporalMarker(() -> {
                        clawLift.setPosition(clawLift.FRONT_DUMP, true);
                        clawSpin.setPosition(clawSpin.BACKPOS);
                        sleep(2000);
                        claw.open();
                    })
                    .lineToSplineHeading(FORWARD_CYCLE) //reset
                    .addTemporalMarker(() -> {
                        clawLift.setPosition(clawLift.BACK_DUMP, true);
                    });
        } } catch (Exception ex){ //if something bad happens, itll just do 1 cycle
            traj.setReversed(false)
                    .addTemporalMarker(()->{
                lift.setPosition(LIFT_LOWER_1, LIFT_POWER_DOWN);
            })
                    .lineToSplineHeading(FORWARD_CONE)
                    .waitSeconds(0.5)
                    .addTemporalMarker(()-> {
                        claw.closeCone();
                    })
                    .waitSeconds(2) //wait to pick up claw
                    .addTemporalMarker(() -> {
                        lift.setPosition(lift.MAX_LIMIT, LIFT_POWER_UP);
                    })
                    .waitSeconds(1)
                    .lineToSplineHeading(FORWARD_CYCLE)
                    .lineToSplineHeading(TOWARD_HIGH)
                    .addTemporalMarker(() -> {
                        clawLift.setPosition(clawLift.FRONT_DUMP, true);
                        claw.open();
                    })
                    .lineToSplineHeading(FORWARD_CYCLE); //reset
        }
        //PART 3 -- parking to last part
        traj.setReversed(false) //analysis pt
                .lineToConstantHeading(ZONE_START_CENTER)
                .lineToConstantHeading(zoneAnalysis);


        fullTraj = traj.build();

    }
    @Override
    public void runOpMode() throws InterruptedException {
        setupDevices();

        sleep(2000); //lowered from 4 seconds to 2
        int position =  vision.getPosition();
        telemetry.addData("Detected: ", position);
        dashTelemetry.addData("Detected", position);

        initTraj();
        telemetry.update();
        waitForStart();

        drive.setPoseEstimate(fullTraj.start());
        drive.followTrajectorySequence(fullTraj);

        while (opModeIsActive() && !Thread.currentThread().isInterrupted() && drive.isBusy()) {
            drive.update();
            claw.keepPosition();
        }


    }
}
