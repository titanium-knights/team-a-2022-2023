package org.firstinspires.ftc.teamcode.teleop;
//import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.util.*;

//import com.acmerobotics.dashboard.config.Config;
@TeleOp
public class EncDown extends OpMode {
    MecanumDrive drive; // aidawkfafhiuawf
    Lift lift;
    Claw claw;
    ClawLift clawLift;
    ClawSpin clawSpin;
    EncServo encServo;

    public void init() { //everything when you press the play button before u start goes here (INITialize, get it?)
        drive = new MecanumDrive(hardwareMap);
        lift = new Lift(hardwareMap);
        encServo = new EncServo(hardwareMap);
        claw = new Claw(hardwareMap);
        clawLift = new ClawLift(hardwareMap);
        clawSpin = new ClawSpin(hardwareMap);

        clawLift.setInit();
        lift.setInit();

    }

    @Override
    public void loop() {
        telemetry.addData("pos: ", encServo.getPosition());
        telemetry.addData("isUp: ", encServo.isUp());


        if (gamepad1.a) {
            encServo.setPosition(encServo.UPPOS);
        }
        if (gamepad1.y) {
            encServo.setPosition(encServo.DOWNPOS);
        }

    }
}