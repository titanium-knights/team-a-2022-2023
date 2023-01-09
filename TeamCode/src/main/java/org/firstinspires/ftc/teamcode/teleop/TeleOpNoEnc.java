package org.firstinspires.ftc.teamcode.teleop;
//import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.util.*;

//import com.acmerobotics.dashboard.config.Config;
@TeleOp
public class TeleOpNoEnc extends OpMode {
    MecanumDrive drive; // aidawkfafhiuawf
    Lift lift;
    Claw claw;
    ClawLift clawLift;
    ClawSpin clawSpin;

    //woot


    public double RIGHT_JOYSTICK_Y_Positive = 0;
    public double RIGHT_JOYSTICK_Y_Negative = 0;

    public double DRIVE_SPEED_CURRENT = DRIVE_SPEED_FASTMODE;
    public double DRIVE_SPEED_SLOWMODE = .4;
    public static double DRIVE_SPEED_FASTMODE = .9; //idk we can play around w this

    boolean isSlowmode = false;

    public void init() { //everything when you press the play button before u start goes here (INITialize, get it?)
        drive = new MecanumDrive(hardwareMap);
        lift = new Lift(hardwareMap);
        lift.setInit();

        claw = new Claw(hardwareMap);
        clawLift = new ClawLift(hardwareMap);

        clawLift.setInit();

        clawSpin = new ClawSpin(hardwareMap);
    }

    public void loop() { //gamepad buttons that call util methods go here

        if (gamepad1.x) {
            isSlowmode = !isSlowmode;
        }

        if (!isSlowmode) {
            DRIVE_SPEED_CURRENT = DRIVE_SPEED_FASTMODE;
        } else {
            DRIVE_SPEED_CURRENT = DRIVE_SPEED_SLOWMODE;
        }

        drive.teleOpRobotCentric(gamepad1, DRIVE_SPEED_CURRENT); //go drive vroom

        telemetry.addData("Slow mode on?:", isSlowmode);

        telemetry.addData("lmr", lift.getPositionR());
        telemetry.addData("lml", lift.getPositionL());
        telemetry.addData("liftAverage", lift.getAverage());
        telemetry.addData("LiftDif", lift.getDIFFERENCE());

        telemetry.addData("clawLift", clawLift.getPosition());
        telemetry.addData("Right Joystick Y", (-1 * gamepad2.right_stick_y));
        telemetry.addData("RIGHT_JOYSTICK_Y_Positive", RIGHT_JOYSTICK_Y_Positive);
        telemetry.addData("RIGHT_JOYSTICK_Y_Negative", RIGHT_JOYSTICK_Y_Negative);
        telemetry.addData("LEFT Joystick Y", gamepad2.left_stick_y);

        //lift
        lift.correctMotorPositions(gamepad2.left_stick_y);

        //clawLift
        if(Math.abs(gamepad2.right_stick_y)>0.1){
            clawLift.setPower(gamepad2.right_stick_y);
        } else {
            clawLift.setPower(0);
        }

        //Code for opening and closing claw

        if(gamepad1.a || gamepad2.a) {
            claw.closeCone();
        }
        if(gamepad1.y || gamepad2.y) {
            claw.open();
        }


        //Code for spinning claw
//        if(gamepad2.right_stick_x>0.1){
//            clawSpin.setPosition(clawSpin.FRONTPOS);
//        } else if(gamepad2.right_stick_x<-0.1){
//            clawSpin.setPosition(clawSpin.BACKPOS);
//        }



    }
}

