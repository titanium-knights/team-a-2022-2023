package org.firstinspires.ftc.teamcode.teleop;
import com.acmerobotics.dashboard.FtcDashboard;
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

    //woot
    public static double DRIVE_SPEED = .9; //idk we can play around w this

    public static double DRIVE_SPEED_SLOWMODE = .8;
    boolean isSlowmode = false;

    public void init() { //everything when you press the play button before u start goes here (INITialize, get it?)
        drive = new MecanumDrive(hardwareMap);
        lift = new Lift(hardwareMap);
        claw = new Claw(hardwareMap);
    }

    public void loop() { //gamepad buttons that call util methods go here
        if (gamepad1.a) {
            isSlowmode = !isSlowmode;
        }

        if (!isSlowmode) {
            drive.teleOpRobotCentric(gamepad1, DRIVE_SPEED); //go drive vroom
        } else {
            drive.teleOpRobotCentric(gamepad1, DRIVE_SPEED_SLOWMODE);
        }

        telemetry.addData("Slow mode on?:", isSlowmode);

        lift.correctMotorPositions();
        if(Math.abs(gamepad2.left_stick_y)>0.1){
             lift.setPower(gamepad1.left_stick_y);
        }
        //claw
        if (gamepad1.a) {
            claw.open();
        }
        if (gamepad1.y){
            claw.closeCone();
        }


    }
    //we decided that right trigger would be lift up, left trigger lift down, a claw open, b claw close

}

