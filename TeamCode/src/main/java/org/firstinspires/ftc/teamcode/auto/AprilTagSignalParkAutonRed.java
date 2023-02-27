package org.firstinspires.ftc.teamcode.auto;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.util.AprilTagVision;
import org.firstinspires.ftc.teamcode.util.MecanumDrive;
import org.firstinspires.ftc.teamcode.util.SignalParkVision;

@Autonomous(name="TIME AprilTagSignalParkAutonRed", group="Linear Opmode")
@Config
public class AprilTagSignalParkAutonRed extends LinearOpMode {

    protected MecanumDrive drive;
    protected AprilTagVision vision;

    public static int STOP_TIME = 500;
    public static int FORWARD1_TIME = 550;
    public static int STRAFE_TIME_2 = 900;
    public static int STRAFE_TIME_1 = 900;
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
        drive.stop();
        sleep(STOP_TIME);

        drive.forwardWithPower(0.8);
        sleep(FORWARD1_TIME);

        drive.stop();
        sleep(STOP_TIME);

        switch (position){
            case 1:
                drive.strafeLeftWithPower(POWER);
                sleep(STRAFE_TIME_1);
                break;
            case 2:
                drive.stop();
                sleep(STOP_TIME);
                break;
            case 3:
                drive.strafeRightWithPower(POWER);
                sleep(STRAFE_TIME_2);
                break;

        }


        }



    }
