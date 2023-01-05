package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class BlueRightTermPush {

    public static int START_Y = 72;
    public static int START_X = -36;
    //cycles
    public static int CYCLES = 3;


//   toCone

    public static Vector2d ZONE_START_DROP_RIGHT = new Vector2d(-60,72); //up at the corner
    public static Vector2d ZONE_START_2 = new Vector2d(-60,32); //up at the corner


    public static Vector2d Z3_S2 = new Vector2d(-58,32);
    public static Vector2d Z2_S2 = new Vector2d(-36,32);
    public static Vector2d Z1_S2 = new Vector2d(-14,32);

    public static Vector2d zoneAnalysis = Z1_S2;


    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(600);

        zoneAnalysis = Z3_S2;

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                                drive.trajectorySequenceBuilder(new Pose2d(START_X, START_Y, Math.toRadians(90)))
                                        .lineToConstantHeading(ZONE_START_DROP_RIGHT)
                                        .lineToConstantHeading(ZONE_START_2)
                                        .lineToConstantHeading(zoneAnalysis)
                                        .build()

                );

        meepMeep.setBackground(MeepMeep.Background.FIELD_POWERPLAY_OFFICIAL)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}