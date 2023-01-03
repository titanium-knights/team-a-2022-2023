package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class SingleCycle {

    public static int START_Y = 72;
    public static int START_X = 36;

    public static Vector2d FORWARD_CYCLE = new Vector2d(36, 12);
    public static int FORWARD_CONE_ANG = -88;
    public static Vector2d FORWARD_CONE = new Vector2d(60, 12);
    public static Vector2d TOWARD_HIGH = new Vector2d(30, 6);
    public static int TOWARD_HIGH_ANG = 45;

//    public static Vector2d FORWARD_CYCLE = new Vector2d(-50, 0);
//    public static int FORWARD_CONE_ANG = -88;
//    public static Vector2d FORWARD_CONE = new Vector2d(-50, -26);
//    public static Vector2d TOWARD_HIGH = new Vector2d(-56, 6);
//    public static int TOWARD_HIGH_ANG = 45;

    //cycles
    public static int CYCLES = 3;

    public static double LIFT_POWER_UP = .6;
    public static double LIFT_POWER_DOWN = .4;

    public static int LIFT_LOWER_1 = -120;
    public static int LIFT_LOWER_2 = -240;
    public static int LIFT_LOWER_3 = -320;


//   toCone

    public static Vector2d ZONE_START_DROP_RIGHT = new Vector2d(0+ START_Y,24+ START_X); //up at the corner

    public static Vector2d Z1_S2 = new Vector2d(-24 +START_Y,-24+ START_X);
    public static Vector2d Z2_S2 = new Vector2d(-24 +START_Y,0+ START_X);
    public static Vector2d Z3_S2 = new Vector2d(-24 + START_Y,24+ START_X);;

    public static Vector2d zoneAnalysis = Z1_S2;


    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(600);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(START_X, START_Y, Math.toRadians(90)))
                                .lineToConstantHeading(FORWARD_CYCLE)
                                .turn(Math.toRadians(FORWARD_CONE_ANG))
                                .waitSeconds(0)

                                .lineToConstantHeading(FORWARD_CONE)
                                .waitSeconds(0.5)

                                .waitSeconds(2) //wait to pick up claw

                                .waitSeconds(1)
                                .lineToConstantHeading(FORWARD_CYCLE) //go to pos 1
                                .turn(Math.toRadians(TOWARD_HIGH_ANG)) //turn to high
                                .lineToConstantHeading(TOWARD_HIGH)
                                .build()
                );

        meepMeep.setBackground(MeepMeep.Background.FIELD_POWERPLAY_OFFICIAL)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}