package org.firstinspires.ftc.teamcode.auto;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.util.Claw;
import org.firstinspires.ftc.teamcode.util.Lift;
import org.firstinspires.ftc.teamcode.util.MecanumDrive;
import org.firstinspires.ftc.teamcode.util.SignalParkVision;

@Autonomous(name="ComputerVisionAuton", group="Linear Opmode")
@Config  @Deprecated
public class ComputerVisionAuton extends LinearOpMode {

    protected MecanumDrive drive;
    protected SignalParkVision vision;

    public static int FORWARD1_TIME = 600;
    public static int STRAFE_TIME = 1200;

    protected void setupDevices(){
        drive = new MecanumDrive(hardwareMap);

        vision = new SignalParkVision(hardwareMap, null);
    }

    @Override
    public void runOpMode() throws InterruptedException {
        setupDevices();

        waitForStart();

        if(vision.getPosition() == 1){
            drive.strafeLeftWithPower(0.8);
            sleep(STRAFE_TIME);
        } else if (vision.getPosition() == 3){
            drive.strafeRightWithPower(0.8);
            sleep(STRAFE_TIME);
        }

        drive.forwardWithPower(0.8);
        sleep(FORWARD1_TIME);
    }
}
