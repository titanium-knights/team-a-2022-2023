package org.firstinspires.ftc.teamcode.auto;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.util.Claw;
import org.firstinspires.ftc.teamcode.util.ClawLift;
import org.firstinspires.ftc.teamcode.util.Lift;
import org.firstinspires.ftc.teamcode.util.MecanumDrive;

@Autonomous(name="RedTimeParkAuton", group="Linear Opmode")
@Config
public class RedTimeParkAuton extends LinearOpMode {
    protected MecanumDrive drive;
    protected Lift lift;
    protected Claw claw;
    public static long PARK_TIME = 1500;

    @Override
    public void runOpMode() throws InterruptedException {
        drive = new MecanumDrive(hardwareMap);
        lift = new Lift(hardwareMap);
        claw = new Claw(hardwareMap);

        waitForStart();

        drive.strafeLeftWithPower(0.7);
        sleep(PARK_TIME);
        drive.stop();


    }
}
