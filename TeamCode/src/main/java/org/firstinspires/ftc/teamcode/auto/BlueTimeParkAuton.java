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
import org.firstinspires.ftc.teamcode.util.SignalParkVision;

@Autonomous(name="BlueTimeParkAuton", group="Linear Opmode")
@Config  @Deprecated
public class BlueTimeParkAuton extends LinearOpMode {
    public static double DRIVE_POWER = 0.5;

    public static int FORWARD_TIME = 1200;

    public static int PARK_TIME = 1100;

    public static int STOP_TIME = 200;

    protected MecanumDrive drive;
    protected SignalParkVision vision;


    @Override
    public void runOpMode() throws InterruptedException {
        drive = new MecanumDrive(hardwareMap);

        waitForStart();

        drive.strafeRightWithPower(0.7);
        sleep(PARK_TIME);
        drive.stop();


    }
}
