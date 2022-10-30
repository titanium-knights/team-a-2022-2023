package org.firstinspires.ftc.teamcode.auto;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.util.Claw;
import org.firstinspires.ftc.teamcode.util.ClawLift;
import org.firstinspires.ftc.teamcode.util.Lift;
import org.firstinspires.ftc.teamcode.util.MecanumDrive;
import org.firstinspires.ftc.teamcode.util.SignalParkVision;

@Autonomous(name="RedSingleCycleTimeAuton", group="Linear Opmode")
@Config

public class RedSingleCycleTimeAuton extends LinearOpMode {
    protected MecanumDrive drive;
    protected ElapsedTime elapsedTime;
    protected Claw claw;
    protected Lift lift;

    //public static final Pose2d startPose = new Pose2d(-35, -60, Math.toRadians(90));

    public static int FORWARD1_TIME = 600;
    public static int TURNR_1_TIME = 350;
    public static int DUMP_TIME = 1300;
    public static int TURNR_2_TIME = 80;
    public static int BACKWARD_TIME = 800;

    public static double LIFT_POWER = -.5;
    public static int LIFT_POWERUP_TIME = 500;
    public static double P = 0.002;
    public static int BUFFER_ZONE = 50;

    Integer liftPos = null;

    //protected SignalParkVision vision;

//    public static double MOVEMENT_FACTOR = 0.025;
//    public static double TURN_TIME = 0.43;

//    private void updateDevices() {
//        if (liftPos != null) {
//            lift.runToPosition(liftPos, 0.9);
//        }
//    }
//
//    private void waitSeconds(double seconds) {
//        double start = elapsedTime.seconds();
//        while (opModeIsActive() && !Thread.currentThread().isInterrupted() && elapsedTime.seconds() - start < seconds) {
//            updateDevices();
//        }
//    }

    protected void setupDevices(){
        drive = new MecanumDrive(hardwareMap);
        claw = new Claw(hardwareMap);
        lift = new Lift(hardwareMap);
        elapsedTime = new ElapsedTime();
        //vision = new SignalParkVision(hardwareMap, null);
    }

//    private void dumpAndLower() {
//        lift.liftUp();
//        sleep(LIFT_POWERUP_TIME);
//
//        claw.open();
//        sleep(500);
//        lift.setPositionGround();
//        sleep(1000);
//        claw.closeCone();
//        sleep(500);
//    }private void updateDevices() {
////        if (liftPos != null) {
////            lift.setPosition(liftPos);
////        }
////    }
////
////    private void waitSeconds(double seconds) {
////        double start = elapsedTime.seconds();
////        while (opModeIsActive() && !Thread.currentThread().isInterrupted() && elapsedTime.seconds() - start < seconds) {
////            updateDevices();
////        }
////    }
    public void goToPosition(int targetPos){
        while (Math.abs(lift.getPosition()-targetPos)>BUFFER_ZONE){
            int error = targetPos - lift.getPosition();
            lift.setPower(-error*P);

            telemetry.addData("Lift Motor Pos", lift.getPosition());
            telemetry.addData("Error", error);
            telemetry.update();
        }
        lift.setPower(0);
    }

    private void dumpAndLower() {
        //bring lift up, pause, bring lift down

        goToPosition(Lift.MID_POSITION);
        sleep(500);
        claw.open();
        sleep(500);

        goToPosition(Lift.GROUND_POSITION);
        sleep(1000);
        claw.closeCone();
        sleep(500);
    }

    @Override
    public void runOpMode() throws InterruptedException {
        setupDevices();

        waitForStart();
        elapsedTime.reset();

        //liftPos = lift.MID_POSITION;
        //waitSeconds(LIFT_POWERUP_TIME);
        //liftPos = null;

        //go forward to dump
        drive.forwardWithPower(0.8);
        sleep(FORWARD1_TIME);
        drive.stop();

        //dump cone
        drive.turnRightWithPower(0.8);
        sleep(TURNR_1_TIME);
        drive.stop();

        dumpAndLower();

        sleep(DUMP_TIME);

//        if(vision.getPosition() == 1){
//            //go first zone
//        } else if (vision.getPosition() == 2){
//            //go second zone
//        } else{
//            //go third zone
//        }

        //turn and go back to park
//        drive.turnLeftWithPower(0.8);
//        sleep(TURNR_2_TIME);

        //temporary only if color vision doesnt work
        drive.backwardWithPower(0.8);
        sleep(BACKWARD_TIME);
        drive.stop();
    }
}
