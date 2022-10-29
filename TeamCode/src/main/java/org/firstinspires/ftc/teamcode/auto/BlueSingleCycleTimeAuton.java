package org.firstinspires.ftc.teamcode.auto;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.util.*;

@Autonomous(name="BlueSingleCycleTimeAuton", group="Linear Opmode")
@Config
public class BlueSingleCycleTimeAuton extends LinearOpMode {
    public static int FORWARD1_T = 500;
    public static double DRIVEPOWER = 0.3;
    public static int RIGHTTIME = 100;
    public static int FORWARDTIME2 = 100;
    public static int SLEEPLIFT = 100;
    public static int SLEEPCLAW = 100;
    protected MecanumDrive drive;
    protected Claw claw;
    protected Lift lift;
    public void raiseDump() {
        lift.setPosition(lift.MID_POSITION);
        sleep(SLEEPLIFT);
    }

    @Override
    public void runOpMode() throws InterruptedException {
        //go straight, turn, straight, lift up, open claw, back, turn, straight and park
        drive = new MecanumDrive(hardwareMap);
        claw = new Claw(hardwareMap);
        lift = new Lift(hardwareMap);

        //drive straight
        drive.forwardWithPower(DRIVEPOWER);
        sleep(FORWARD1_T);

        //turn right
        drive.turnRightWithPower(DRIVEPOWER);
        sleep (RIGHTTIME);

        //drive straight
        drive.forwardWithPower(DRIVEPOWER);
        sleep(FORWARDTIME2);

        //lift up
        raiseDump();

        //open claw
        claw.open(); //is this right?
        sleep (SLEEPCLAW);

        //drive backwards to part 2



    }
}
