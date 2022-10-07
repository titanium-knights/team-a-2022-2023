package org.firstinspires.ftc.teamcode.util;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import static java.lang.Math.abs;

public class MecanumDrive {
    DcMotor fl, fr, bl, br;

    public MecanumDrive(HardwareMap hmap){
        fl = hmap.dcMotor.get(Config.frontLeftWheel);
        fr = hmap.dcMotor.get(Config.frontRightWheel);
        bl = hmap.dcMotor.get(Config.backLeftWheel);
        br = hmap.dcMotor.get(Config.backRightWheel);

        fl.setDirection(DcMotorSimple.Direction.REVERSE);
        fr.setDirection(DcMotorSimple.Direction.FORWARD);
        bl.setDirection(DcMotorSimple.Direction.REVERSE);
        br.setDirection(DcMotorSimple.Direction.FORWARD);
    }
    double[] powerArr = new double[4];
    public void teleOpFieldCentric(Gamepad g1, IMU imu, double multiplier){
        double angle = Math.toRadians(imu.getZAngle());
        double inputY = -g1.left_stick_y;
        double inputX = g1.left_stick_x;
        double rot = g1.right_stick_x;
        double x = Math.cos(angle) * inputX - Math.sin(angle) * inputY;
        double y = Math.sin(angle) * inputX + Math.cos(angle) * inputY;
        move(x*multiplier,y*multiplier,rot*multiplier);
    }
    public float deadzonify(float input) {
        if (abs(input) < 0.15) return 0;
        return input;
    }
    public void teleOpRobotCentric(Gamepad g1,double multiplier){
        move(deadzonify(g1.left_stick_x)*multiplier,-deadzonify(g1.left_stick_y)*multiplier,deadzonify(g1.right_stick_x)*multiplier);

    }
    public void move(double x, double y, double turn) {
        powerArr[0] = x+y+turn; //fl power
        powerArr[1] = y-x-turn; //Fr Power
        powerArr[2] = y-x+turn;//bl Power
        powerArr[3] = y+x-turn; //br Power

        double max = Math.max(abs(powerArr[0]), abs(powerArr[1]));
        double tempMax = Math.max(abs(powerArr[2]), abs(powerArr[3]));
        max = Math.max(max, tempMax);

        if(max>1){
            //We have to normalize
            for(int i=0;i<4;i++){
                powerArr[i]=powerArr[i]/max;
            }
        }

        fl.setPower(powerArr[0]);
        fr.setPower(powerArr[1]);
        bl.setPower(powerArr[2]);
        br.setPower(powerArr[3]);
    }
    public void driveForwardsWithPower(double power){
        move(0,power,0);
    }
    public void driveBackwardsWithPower(double power){
        move(0,-power,0);
    }

    public void strafeLeftWithPower(double power){
        move(-power,0,0);
    }
    public void strafeRightWithPower(double power){
        move(power,0,0);
    }
    public void turnLeftWithPower(double power){
        move(0,0,-power);
    }
    public void turnRightWithPower(double power){
        move(0,0, power);
    }
    public void stop(){
        move(0,0,0);
    }

}
