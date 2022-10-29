package org.firstinspires.ftc.teamcode.teleop;
//import com.acmerobotics.dashboard.FtcDashboard;
//import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.util.*;

//import com.acmerobotics.dashboard.config.Config;
@TeleOp
public class TeleopPractice extends OpMode {
    MecanumDrive drive; // aidawkfafhiuawf
    Claw claw;
    ClawLift clawLift;
    Lift lift;
    //woot
    public static double DRIVE_SPEED = 1; //idk we can play around w this

    public static double LIFT_SPEED_POWER = -.5;
    public static double LIFT_NEGATE_MULTIPLIER = .05;

    public static double DRIVE_SPEED_SLOWMODE = .8;
    boolean isSlowmode = false;


    public void init() { //everything when you press the play button before u start goes here (INITialize, get it?)
        drive = new MecanumDrive(hardwareMap);
        claw = new Claw(hardwareMap);
        lift = new Lift(hardwareMap);
        clawLift = new ClawLift(hardwareMap);

        claw.openInit();
        lift.setInit();

    }
    public void loop() { //gamepad buttons that call util methods go here
        if (gamepad1.a) {
            isSlowmode = !isSlowmode;
        }

        if (!isSlowmode) {
            drive.teleOpRobotCentric(gamepad1, DRIVE_SPEED); //go drive vroom
        }
        else {
            drive.teleOpRobotCentric(gamepad1, DRIVE_SPEED_SLOWMODE);
        }

        telemetry.addData("Slow mode on?:", isSlowmode);

        //claw
        if (gamepad1.right_bumper) {
            claw.closeCone();
        }
        if (gamepad1.left_bumper) {
            claw.open();
        }
        telemetry.addData("Current Servo position", claw.getPosition());

        //there are 2 gamepads (gamepad1 & gamepad2, start typing gamepad1 to see its buttons (it's the same as gamepad2)
        //lift stuff-
        if (lift.getPosition() < lift.MAX_LIMIT && Math.abs(gamepad1.right_trigger) > 0.1) { //arm up
                lift.setPower(LIFT_SPEED_POWER);
            } else if (lift.getPosition() >= lift.INIT_LIMIT + 50 &&   Math.abs(gamepad1.left_trigger) > 0.1) { //arm down
                lift.setPower(-LIFT_SPEED_POWER);
            } else { lift.setPower(LIFT_SPEED_POWER * LIFT_NEGATE_MULTIPLIER);}

        telemetry.addData("Current Lift Encoder Val", lift.getPosition());

        if (gamepad1.dpad_down) {
            clawLift.setPickup();
        }
        }
        //if (gamepad1.b) {//close claw preset
            //clawLift.setPower(-0.5); //placeholder for preset here. Add ACTUAL preset value!!
        }

