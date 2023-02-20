package org.firstinspires.ftc.teamcode.auto;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.rr.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.rr.trajectorysequence.TrajectorySequence;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.rr.trajectorysequence.TrajectorySequenceBuilder;
import org.firstinspires.ftc.teamcode.util.SignalParkVision;
import org.firstinspires.ftc.teamcode.util.*;


@Config
@Autonomous(name = "CycleOppBlue", group = "Linear OpMode")

public class CycleOppStackBlue extends LinearOpMode  {
    TrajectorySequence tester;

    public static int START_Y = 0;
    public static int START_X = 0;

    public static Vector2d FORWARD_CYCLE = new Vector2d(50, 0);
    public static int FORWARD_CONE_ANG = -88;
    public static Vector2d FORWARD_CONE = new Vector2d(50, -26);
    public static int TOWARD_HIGH_X = 60 ;
    public static int TOWARD_HIGH_Y = 6;

    //used as a starting pose2d for cycling, using vector cause want linear movement
    public static Pose2d FORWARD_CONE_POS = new Pose2d(TOWARD_HIGH_X, TOWARD_HIGH_Y, FORWARD_CONE_ANG);

    public static Vector2d TOWARD_HIGH = new Vector2d(TOWARD_HIGH_X, TOWARD_HIGH_Y);
    public static int TOWARD_HIGH_ANG = -135;

    public static int CYCLES = 3;

    //cycles
    public static double LIFT_POWER_UP = .6;
    public static double LIFT_POWER_DOWN = .4;

    public static int LIFT_LOWER_1 = -120;
    public static int LIFT_LOWER_2 = -240;
    public static int LIFT_LOWER_3 = -320;

    public static int[] LIFT_LOWER_VALS = {LIFT_LOWER_1, LIFT_LOWER_2, LIFT_LOWER_3};

    //   toCone
    public static Vector2d Z1_S2 = new Vector2d(24,-24);
    public static Vector2d Z2_S2 = new Vector2d(24,0);
    public static Vector2d Z3_S2 = new Vector2d(24,24);;

    public static Vector2d zoneAnalysis = Z1_S2;

    protected SampleMecanumDrive drive;
    protected SignalParkVision vision;

    protected EncServo encServo;
     protected Lift lift;
     protected Claw claw;
    protected ClawLift clawLift;
    protected ClawSpin clawSpin;

    Telemetry dashTelemetry = FtcDashboard.getInstance().getTelemetry();

    protected void setupDevices(){
        drive = new SampleMecanumDrive(hardwareMap);
        vision = new SignalParkVision(hardwareMap, null);

        encServo = new EncServo(hardwareMap);
        encServo.setPosition(encServo.DOWNPOS);

         lift = new Lift(hardwareMap);
         lift.setInit();

        claw = new Claw(hardwareMap);
        clawLift = new ClawLift(hardwareMap);
        clawLift.setInit();
         clawSpin = new ClawSpin(hardwareMap);

        claw.open(); //moves upon init
    }

    public TrajectorySequence cycle(int LIFT_LOWER) {
        TrajectorySequenceBuilder cycle = drive.trajectorySequenceBuilder(FORWARD_CONE_POS)
                .addTemporalMarker(() -> { //make sure the claw is front and the dump is also front
                    clawSpin.setPosition(clawSpin.FRONTPOS);
                    clawLift.setPosition(clawLift.FRONT_DUMP,true);
                })
                .lineToConstantHeading(FORWARD_CONE)
                .waitSeconds(0.5)
                .addTemporalMarker(()-> { //lower the lift
                    lift.setPosition(LIFT_LOWER, LIFT_POWER_DOWN);
                    claw.closeCone(); //close cone and pick up cone
                })
                .waitSeconds(2) //wait to pick up claw
                .addTemporalMarker(() -> { //bring lift up
                    lift.setPosition(lift.MAX_LIMIT, LIFT_POWER_UP);
                })
                .waitSeconds(1)
                //go towards the junction
                .lineToConstantHeading(FORWARD_CYCLE)
                .turn(Math.toRadians(TOWARD_HIGH_ANG))
                .lineToConstantHeading(TOWARD_HIGH)
                //dumping
                .addTemporalMarker(() -> {
                         clawLift.setPosition(clawLift.BACK_DUMP, true);
                         clawSpin.setPosition(clawSpin.BACKPOS);
                    sleep(2000);
                         claw.open();
                })
                .lineToConstantHeading(FORWARD_CONE)
                .waitSeconds(1); //cycle complete

                return cycle.build();
    }
    public void initTraj() {
        if (vision.getPosition() == 1) {
            zoneAnalysis = Z1_S2;
        } else if (vision.getPosition() == 2) {
            zoneAnalysis = Z2_S2;
        } else {
            zoneAnalysis = Z3_S2;
        }

        TrajectorySequenceBuilder analysis = drive.trajectorySequenceBuilder(new Pose2d())
                //cycle part
                .lineToConstantHeading(FORWARD_CYCLE)
                .turn(Math.toRadians(FORWARD_CONE_ANG))
                .waitSeconds(0)
                .addTemporalMarker(()->{ //set lift to high
                        lift.setPosition(Lift.MAX_LIMIT, LIFT_POWER_DOWN);
                })
                .addTemporalMarker(()-> {
                    for (int i = 0; i < CYCLES; i++) {
                        cycle(LIFT_LOWER_VALS[i]);
                    }
                });



        //detection part
//                .lineToConstantHeading(FORWARD_CYCLE) //go to pos 1
//                .lineToConstantHeading(ZONE_START_DROP_RIGHT)
//                .lineToConstantHeading(zoneAnalysis);

        tester = analysis.build();

    }
    @Override
    public void runOpMode() throws InterruptedException {
        setupDevices();

        sleep(4000);
        int position =  vision.getPosition();
        sleep(1000);


        telemetry.addData("Detected: ", position);
        dashTelemetry.addData("Detected", position);

        initTraj();
        telemetry.update();
        waitForStart();

        drive.setPoseEstimate(tester.start());
        drive.followTrajectorySequence(tester);

        while (opModeIsActive() && !Thread.currentThread().isInterrupted() && drive.isBusy()) {
            drive.update();
            //   claw.keepPosition();

            // telemetry.addData("lmr", lift.getPositionR());
            // telemetry.addData("lml", lift.getPositionL());
        }


    }
}
