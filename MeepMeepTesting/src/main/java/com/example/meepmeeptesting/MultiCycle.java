//package com.example.meepmeeptesting;
//
//import com.acmerobotics.roadrunner.geometry.Pose2d;
//import com.acmerobotics.roadrunner.geometry.Vector2d;
//import com.noahbres.meepmeep.MeepMeep;
//import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
//import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;
//import com.noahbres.meepmeep.roadrunner.trajectorysequence.TrajectorySequenceBuilder;
//
//public class MultiCycle {
//    public static int START_Y = 72;
//    public static int START_X = 36;
//
//    public static int FORWARD_CONE_ANG = -88;
//    public static int TOWARD_HIGH_ANG = 45;
//
//    public static Pose2d FORWARD_CYCLE = new Pose2d(36, 12, Math.toRadians(FORWARD_CONE_ANG));
//    public static Pose2d FORWARD_CONE = new Pose2d(60, 12, Math.toRadians(0));
//    public static Pose2d TOWARD_HIGH = new Pose2d(30, 6, TOWARD_HIGH_ANG);
//
//    //cycles
//    public int CYCLES = 3;
//    public static double LIFT_POWER_UP = .6;
//    public static double LIFT_POWER_DOWN = .4;
//
//    public static int LIFT_LOWER_1 = -120;
//    public static int LIFT_LOWER_2 = -240;
//    public static int LIFT_LOWER_3 = -320;
//
//    public static int[] LIFT_LOWERS = {LIFT_LOWER_1, LIFT_LOWER_2, LIFT_LOWER_3};
//
//
////   toCone
//
//    public static Vector2d ZONE_START_DROP_RIGHT = new Vector2d(0+ START_Y,24+ START_X); //up at the corner
//
//    public static Vector2d Z1_S2 = new Vector2d(-24 +START_Y,-24+ START_X);
//    public static Vector2d Z2_S2 = new Vector2d(-24 +START_Y,0+ START_X);
//    public static Vector2d Z3_S2 = new Vector2d(-24 + START_Y,24+ START_X);;
//
//    public static Vector2d zoneAnalysis = Z1_S2;
//
//    public void initTraj() {
//        //getting analysis
////        if (vision.getPosition() == 1) {
////            zoneAnalysis = Z1_S2;
////        } else if (vision.getPosition() == 2) {
////            zoneAnalysis = Z2_S2;
////        } else {
////            zoneAnalysis = Z3_S2;
////        }
//
//        //PART 1 -- starting the cycling by going straight, lining up with the cone stack
//        TrajectorySequenceBuilder traj = drive.trajectorySequenceBuilder(new Pose2d())
//                .lineToSplineHeading(FORWARD_CYCLE); //starts the cycling
//        //PART 2 -- cycling
//        try {
//            for (int i = 0; i <= CYCLES; i++) { //cycling
//                int liftLowerTo = LIFT_LOWERS[i];
//
//                traj.setReversed(false)
//                        .addTemporalMarker(()->{
//                            lift.setPosition(liftLowerTo, LIFT_POWER_DOWN);
//                        })
//                        .lineToSplineHeading(FORWARD_CONE)
//                        .waitSeconds(0.5)
//                        .addTemporalMarker(()-> {
//                            claw.closeCone();
//                        })
//                        .waitSeconds(2) //wait to pick up claw
//                        .addTemporalMarker(() -> {
//                            lift.setPosition(lift.MAX_LIMIT, LIFT_POWER_UP);
//                        })
//                        .waitSeconds(1)
//                        .lineToSplineHeading(FORWARD_CYCLE)
//                        .lineToSplineHeading(TOWARD_HIGH)
//                        .addTemporalMarker(() -> {
//                            clawLift.setPosition(clawLift.FRONT_DUMP);
//                            claw.open();
//                        })
//                        .lineToSplineHeading(FORWARD_CYCLE); //reset
//            } } catch (Exception ex){ //if something bad happens, itll just do 1 cycle
//            traj.setReversed(false)
//                    .addTemporalMarker(()->{
//                        lift.setPosition(LIFT_LOWER_1, LIFT_POWER_DOWN);
//                    })
//                    .lineToSplineHeading(FORWARD_CONE)
//                    .waitSeconds(0.5)
//                    .addTemporalMarker(()-> {
//                        claw.closeCone();
//                    })
//                    .waitSeconds(2) //wait to pick up claw
//                    .addTemporalMarker(() -> {
//                        lift.setPosition(lift.MAX_LIMIT, LIFT_POWER_UP);
//                    })
//                    .waitSeconds(1)
//                    .lineToSplineHeading(FORWARD_CYCLE)
//                    .lineToSplineHeading(TOWARD_HIGH)
//                    .addTemporalMarker(() -> {
//                        clawLift.setPosition(clawLift.FRONT_DUMP);
//                        claw.open();
//                    })
//                    .lineToSplineHeading(FORWARD_CYCLE); //reset
//        }
//        //PART 3 -- parking to last part
//        traj.setReversed(false) //analysis pt
//                .lineToConstantHeading(ZONE_START_CENTER)
//                .lineToConstantHeading(zoneAnalysis);
//
//        fullTraj = traj.build();
//
//    }
//
//
//    public static void main(String[] args) {
//        MeepMeep meepMeep = new MeepMeep(600);
//
//        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
//                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
//                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
//                .followTrajectorySequence(drive ->
//                        drive.trajectorySequenceBuilder(new Pose2d(START_X, START_Y, Math.toRadians(90)))
//                                .lineToSplineHeading(FORWARD_CYCLE); //starts the cycling
//        //PART 2 -- cycling
//        try {
//            for (int i = 0; i <= CYCLES; i++) { //cycling
//                int liftLowerTo = LIFT_LOWERS[i];
//
//                traj.setReversed(false)
//                        .addTemporalMarker(()->{
////                            lift.setPosition(liftLowerTo, LIFT_POWER_DOWN);
//                        })
//                        .lineToSplineHeading(FORWARD_CONE)
//                        .waitSeconds(0.5)
//                        .addTemporalMarker(()-> {
////                            claw.closeCone();
//                        })
//                        .waitSeconds(2) //wait to pick up claw
//                        .addTemporalMarker(() -> {
////                            lift.setPosition(lift.MAX_LIMIT, LIFT_POWER_UP);
//                        })
//                        .waitSeconds(1)
//                        .lineToSplineHeading(FORWARD_CYCLE)
//                        .lineToSplineHeading(TOWARD_HIGH)
//                        .addTemporalMarker(() -> {
////                            clawLift.setPosition(clawLift.FRONT_DUMP);
////                            claw.open();
//                        })
//                        .lineToSplineHeading(FORWARD_CYCLE); //reset
//            } } catch (Exception ex){ //if something bad happens, itll just do 1 cycle
//            traj.setReversed(false)
//                    .addTemporalMarker(()->{
//                        lift.setPosition(LIFT_LOWER_1, LIFT_POWER_DOWN);
//                    })
//                    .lineToSplineHeading(FORWARD_CONE)
//                    .waitSeconds(0.5)
//                    .addTemporalMarker(()-> {
////                        claw.closeCone();
//                    })
//                    .waitSeconds(2) //wait to pick up claw
//                    .addTemporalMarker(() -> {
//                        lift.setPosition(lift.MAX_LIMIT, LIFT_POWER_UP);
//                    })
//                    .waitSeconds(1)
//                    .lineToSplineHeading(FORWARD_CYCLE)
//                    .lineToSplineHeading(TOWARD_HIGH)
//                    .addTemporalMarker(() -> {
////                        clawLift.setPosition(clawLift.FRONT_DUMP);
////                        claw.open();
//                    })
//                    .lineToSplineHeading(FORWARD_CYCLE); //reset
//        }
//        //PART 3 -- parking to last part
//        traj.setReversed(false) //analysis pt
//                .lineToConstantHeading(ZONE_START_CENTER)
//                .lineToConstantHeading(zoneAnalysis);
//
//        fullTraj = traj.build();
//
//                );
//
//        meepMeep.setBackground(MeepMeep.Background.FIELD_POWERPLAY_OFFICIAL)
//                .setDarkMode(true)
//                .setBackgroundAlpha(0.95f)
//                .addEntity(myBot)
//                .start();
//    }
//}