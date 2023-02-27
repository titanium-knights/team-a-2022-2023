package org.firstinspires.ftc.teamcode.auto;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.util.AprilTagVision;
import org.firstinspires.ftc.teamcode.util.Claw;
import org.firstinspires.ftc.teamcode.util.Lift;
import org.firstinspires.ftc.teamcode.util.MecanumDrive;
import org.firstinspires.ftc.teamcode.util.SignalParkVision;

@Autonomous(name="TIME AprilTagSignalParkAutonBlue", group="Linear Opmode")
@Config
public class AprilTagSignalParkAutonBlue extends LinearOpMode {

    protected MecanumDrive drive;
    protected AprilTagVision vision;

    public static int FORWARD1_TIME = 600;
    public static int STRAFE_TIME = 1200;
    public static int STOP_TIME = 500;
    public static int position = 2;
    public static double POWER = .8;


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

        drive.strafeRightWithPower(POWER);
        sleep(STRAFE_TIME);

        drive.stop();
        sleep(STOP_TIME);

        drive.forwardWithPower(0.8);
        sleep(FORWARD1_TIME);

        drive.stop();
        sleep(STOP_TIME);

        switch (position){
            case 1:
                drive.strafeLeftWithPower(0.8);
                sleep(STRAFE_TIME);
                break;
            case 2:
                drive.strafeRightWithPower(0.8);
                sleep(STRAFE_TIME);
                break;
            case 3:
                break;

        }

        drive.stop();
        sleep(STOP_TIME);



    }



}
