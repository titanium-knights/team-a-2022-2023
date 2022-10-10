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

    public void init() { //everything when you press the play button before u start goes here (INITialize, get it?)
        drive = new MecanumDrive(hardwareMap);
        claw = new Claw(hardwareMap);
    }
    public void loop() { //gamepad buttons that call util methods go here
        //there are 2 gamepads (gamepad1 & gamepad2, start typing gamepad1 to see its buttons (it's the same as gamepad2)

        //driving
        drive.teleOpRobotCentric(gamepad1, DRIVE_SPEED); //go drive vroom

        //claw
        //[add claw stuff here!]

        //lift
        //[add lift stuff here!]
    }
}