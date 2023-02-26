package org.firstinspires.ftc.teamcode.auto;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.util.AprilTagVision;
import org.firstinspires.ftc.teamcode.util.MecanumDrive;

@Autonomous(name="P2G_TIME", group="Linear Opmode")
@Config
public class P2G_TIME extends LinearOpMode {

    protected MecanumDrive drive;
    protected AprilTagVision vision;

    public static int STOP_TIME = 500;
    public static int FORWARD1_TIME = 550;
    public static int STRAFE_TIME = 900;
    public static int STRAFE_TIME_1 = 2000;
    public static double POWER = .8;
    public static int position;

    protected void setupDevices(){
        drive = new MecanumDrive(hardwareMap);
        vision = new AprilTagVision(hardwareMap, telemetry);
    }

    @Override
    public void runOpMode() throws InterruptedException {
        setupDevices();
        position = vision.getPosition();

        waitForStart();


        telemetry.addData("Detected: ", position);

        drive.forwardWithPower(0.8);
        sleep(FORWARD1_TIME);

        drive.stop();
        sleep(STOP_TIME);



        }



    }
