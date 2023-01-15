package org.firstinspires.ftc.teamcode.util;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.pipelines.SignalParkPipeline;
import org.firstinspires.ftc.teamcode.pipelines.SignalParkPipelineRed;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;

public class SignalParkVisionRed {
    OpenCvCamera camera;
    public SignalParkPipelineRed pipeline;

    public SignalParkVisionRed(HardwareMap hmap, Telemetry telemetry, int hue1, int hue2, int hue3) {
        int cameraMonitorViewId = hmap.appContext.getResources().getIdentifier("cameraMonitorViewId",
                "id", hmap.appContext.getPackageName());
        WebcamName webcamName = hmap.get(WebcamName.class, "Webcam 1");
        camera = OpenCvCameraFactory.getInstance().createWebcam(webcamName, cameraMonitorViewId);
        pipeline = new SignalParkPipelineRed(telemetry, hue1, hue2, hue3);

        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
        {
            @Override
            public void onOpened()
            {
                // Usually this is where you'll want to start streaming from the camera (see section 4)
                camera.startStreaming(1280, 720, OpenCvCameraRotation.UPRIGHT);
                FtcDashboard.getInstance().startCameraStream(camera, 0);
                camera.setPipeline(pipeline);
            }
            @Override
            public void onError(int errorCode)
            {
                /*
                 * This will be called if the camera could not be opened
                 */
            }
        });
    }

    public SignalParkVisionRed(HardwareMap hmap, Telemetry telemetry){
        this(hmap, telemetry, 120, 80, 50);
    }

    public int getPosition(){
        SignalParkPipelineRed.SignalZone signalZone = pipeline.getAnalysis();
        if(signalZone == SignalParkPipelineRed.SignalZone.ZONE_ONE){
            return 1;
        }
        else if (signalZone == SignalParkPipelineRed.SignalZone.ZONE_TWO) {
            return 2;
        }
        else {
            return 3;
        }
    }
}
