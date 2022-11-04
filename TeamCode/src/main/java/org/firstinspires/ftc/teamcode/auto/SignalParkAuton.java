package org.firstinspires.ftc.teamcode.auto;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.util.Claw;
import org.firstinspires.ftc.teamcode.util.ClawLift;
import org.firstinspires.ftc.teamcode.util.Lift;
import org.firstinspires.ftc.teamcode.util.MecanumDrive;
import org.firstinspires.ftc.teamcode.util.SignalParkVision;
import org.firstinspires.ftc.robotcore.external.Telemetry;


@Config
@Autonomous(name = "Signal Park Auton", group = "Linear OpMode")

public class SignalParkAuton extends LinearOpMode  {
    public static double DRIVE_POWER = 0.5;

    public static int FORWARD_TIME = 400;
    public static int STRAFE_TIME = 1000;
    public static int PARK_TIME = 1500;


    protected MecanumDrive drive;
    protected SignalParkVision vision;
    Telemetry dashTelemetry = FtcDashboard.getInstance().getTelemetry();


    @Override
    public void runOpMode() throws InterruptedException {
        drive = new MecanumDrive(hardwareMap);
        vision = new SignalParkVision(hardwareMap, null);

        try {
            vision = new SignalParkVision(hardwareMap, null);

            telemetry.addData("Detected: ", vision.getPosition());

            dashTelemetry.addData("Detected", vision.getPosition() == 1);
            telemetry.update();

            waitForStart();


            if (vision.getPosition() == 2) {
                drive.forwardWithPower(DRIVE_POWER);
                sleep(FORWARD_TIME);

            } else if (vision.getPosition() == 1) {
                drive.strafeLeftWithPower(DRIVE_POWER);
                sleep(STRAFE_TIME);

                drive.forwardWithPower(DRIVE_POWER);
                sleep(FORWARD_TIME);

            } else if (vision.getPosition() == 3) {
                drive.strafeRightWithPower(DRIVE_POWER);
                sleep(STRAFE_TIME);

                drive.forwardWithPower(DRIVE_POWER);
                sleep(FORWARD_TIME);
            }
        } catch (Exception ex) {
            waitForStart();

            drive.strafeRightWithPower(0.7);
            sleep(PARK_TIME);
            drive.stop();
        }
    }
}
