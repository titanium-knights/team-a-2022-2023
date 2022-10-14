package org.firstinspires.ftc.teamcode.tests;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@Config
@TeleOp
public class MotorTester extends OpMode {
    DcMotor motor;
    public static String MOTOR_NAME = "lift";

    @Override
    public void init() {
        motor = hardwareMap.get(DcMotor.class, MOTOR_NAME);
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        telemetry = new MultipleTelemetry(FtcDashboard.getInstance().getTelemetry(), telemetry);
    }

    @Override
    public void loop() {
        motor.setPower(gamepad1.left_stick_y/2);
        telemetry.addData("Motor Encoder Val", motor.getCurrentPosition());
        telemetry.update();
    }
}