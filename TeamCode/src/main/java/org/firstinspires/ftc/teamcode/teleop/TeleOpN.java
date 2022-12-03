package org.firstinspires.ftc.teamcode.teleop;
//import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.util.*;

//import com.acmerobotics.dashboard.config.Config;
@TeleOp
public class TeleOpN extends OpMode {
    MecanumDrive drive; // aidawkfafhiuawf
    Lift lift;
    Claw claw;
    ClawLift clawLift;
    ClawSpin clawSpin;

    //woot
    public static double DRIVE_SPEED = .9; //idk we can play around w this

    public static double DRIVE_SPEED_SLOWMODE = .8;
    boolean isSlowmode = false;
    boolean clawClosed = false;
    boolean clawLiftUsed = false;

    int clawLiftLastPos = 0;

    public void init() { //everything when you press the play button before u start goes here (INITialize, get it?)
        drive = new MecanumDrive(hardwareMap);
        lift = new Lift(hardwareMap);
        claw = new Claw(hardwareMap);
        clawLift = new ClawLift(hardwareMap);
        clawSpin = new ClawSpin(hardwareMap);
    }

    public void loop() { //gamepad buttons that call util methods go here
        drive.teleOpRobotCentric(gamepad1, DRIVE_SPEED); //go drive vroom

        telemetry.addData("Slow mode on?:", isSlowmode);

        telemetry.addData("lmr", lift.getPositionR());
        telemetry.addData("lml", lift.getPositionL());

        lift.correctMotorPositions();
        if(Math.abs(gamepad2.left_stick_y)>0.1){
            lift.setPower(gamepad2.left_stick_y);
        }
        else{
            lift.setPower(0);
        }

        telemetry.addData("clawLift", clawLift.getCurrentPosition());

        if(Math.abs(gamepad2.right_stick_y)>0.1) {
            clawLift.setPower(gamepad2.right_stick_y, 0.07);
        }
        else {
            clawLift.setPower(0, 0);
        }

        //Code for opening and closing claw

        if(gamepad2.a){
            clawClosed = !clawClosed;
        }
        if(clawClosed){
            claw.closeCone();
        } else {
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

