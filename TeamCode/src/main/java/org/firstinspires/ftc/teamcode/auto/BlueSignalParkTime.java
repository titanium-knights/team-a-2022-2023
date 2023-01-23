package org.firstinspires.ftc.teamcode.auto;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.util.Claw;
import org.firstinspires.ftc.teamcode.util.Lift;
import org.firstinspires.ftc.teamcode.util.MecanumDrive;
import org.firstinspires.ftc.teamcode.util.SignalParkVision;

@Autonomous(name="TIME BlueSignalParkTime", group="Linear Opmode")
@Config
public class BlueSignalParkTime extends LinearOpMode {

    protected MecanumDrive drive;
    protected SignalParkVision vision;

    public static int FORWARD1_TIME = 600;
    public static int STRAFE_TIME = 1200;
    public static int position;

    protected void setupDevices(){
        drive = new MecanumDrive(hardwareMap);
        vision = new SignalParkVision(hardwareMap, null);
    }

    @Override
    public void runOpMode() throws InterruptedException {
        setupDevices();
        vision.getPosition();

        waitForStart();


        telemetry.addData("Detected: ", position);

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

        drive.forwardWithPower(0.8);
        sleep(FORWARD1_TIME);


        }



    }
