package org.firstinspires.ftc.teamcode.teleop;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.util.CONFIG;
import org.firstinspires.ftc.teamcode.util.EncServo;

@Config
@TeleOp
public class EncodersDown extends OpMode {
    EncServo encServo;
    Telemetry dashTelemetry = FtcDashboard.getInstance().getTelemetry();


    @Override
    public void init() {
        encServo = new EncServo(hardwareMap);
    }

    @Override
    public void loop() {
        if (gamepad1.y) {
            encServo.setPosition(encServo.UPPOS);
        }
        if (gamepad1.a) {
            encServo.setPosition(encServo.DOWNPOS);
        }

        telemetry.addData("Servo position", encServo.retPosition());
        telemetry.update();
    }

}
