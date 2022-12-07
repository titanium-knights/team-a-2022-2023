package org.firstinspires.ftc.teamcode.teleop;
//import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.util.*;

//import com.acmerobotics.dashboard.config.Config;
@TeleOp
public class TeleOpTEST extends OpMode {
    MecanumDrive drive; // aidawkfafhiuawf
    Lift lift;
    Claw claw;
    ClawLift clawLift;
    ClawSpin clawSpin;

    //woot
    public static double DRIVE_SPEED = .9; //idk we can play around w this

    boolean isSlowmode = false;
    boolean clawClosed = false;

    boolean lastState = true;
    boolean state = false;

    public void init() { //everything when you press the play button before u start goes here (INITialize, get it?)
        drive = new MecanumDrive(hardwareMap);
        lift = new Lift(hardwareMap);
        lift.setInit();
        claw = new Claw(hardwareMap);
        clawLift = new ClawLift(hardwareMap);
        clawSpin = new ClawSpin(hardwareMap);
    }

    public void loop() { //gamepad buttons that call util methods go here
        drive.teleOpRobotCentric(gamepad1, DRIVE_SPEED); //go drive vroom

        telemetry.addData("Slow mode on?:", isSlowmode);

        telemetry.addData("lmr", lift.getPositionR());
        telemetry.addData("lml", lift.getPositionL());
        telemetry.addData("liftAverage", lift.getAverage());

        lift.correctMotorVed(gamepad2.left_stick_y);

        telemetry.addData("clawLift", clawLift.getPosition());
        telemetry.addData("LiftDif", lift.getDIFFERENCE());

        //clawLift PLZZZ
        clawLift.setPower(gamepad2.right_stick_y);

        //Code for opening and closing claw

        if(gamepad2.a) {
            claw.closeCone();
        }
        if(gamepad2.y) {
            claw.open();
        }


        //Code for spinning claw
        if(gamepad2.right_stick_x>0.1){
            clawSpin.setPosition(clawSpin.FRONTPOS);
        } else if(gamepad2.right_stick_x<-0.1){
            clawSpin.setPosition(clawSpin.BACKPOS);
        }



    }
}

