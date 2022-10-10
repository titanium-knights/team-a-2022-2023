//package org.firstinspires.ftc.teamcode.util;
//
//import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
//
//import com.qualcomm.robotcore.hardware.HardwareMap;
//
//import org.firstinspires.ftc.robotcore.external.Telemetry;
//import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
//import org.firstinspires.ftc.teamcode.pipelines.*;
//import org.firstinspires.ftc.teamcode.pipelines.SignalParkPipeline;
//import org.openftc.easyopencv.OpenCvCamera;
//import org.openftc.easyopencv.OpenCvCameraFactory;
//import org.openftc.easyopencv.OpenCvCameraRotation;
//import org.firstinspires.ftc.teamcode.util.*;
//
//public class SignalParkVision {
//    OpenCvCamera camera;
//    public SignalParkPipeline pipeline;
//
//    public SignalParkVision(HardwareMap hmap, Telemetry telemetry) {
//
//        int cameraMonitorViewId = hmap.appContext.getResources().getIdentifier("cameraMonitorViewId",
//                "id", hmap.appContext.getPackageName());
//        WebcamName webcamName = hmap.get(WebcamName.class, CONFIG.webcam);
//        camera = OpenCvCameraFactory.getInstance().createWebcam(webcamName, cameraMonitorViewId);
//        pipeline = new SignalParkPipeline(telemetry);
//
//        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
//        {
//            @Override
//            public void onOpened()
//            {
//                // Usually this is where you'll want to start streaming from the camera (see section 4)
//                camera.startStreaming(1920, 1080, OpenCvCameraRotation.UPRIGHT);
//                camera.setPipeline(pipeline);
//            }
//            @Override
//            public void onError(int errorCode)
//            {
//                /*
//                 * This will be called if the camera could not be opened
//                 */
//            }
//        });
//    }
//    public int getPosition(){
//        SignalParkPipeline.SignalZone signalZone = pipeline.getAnalysis();
//        if(signalZone == SignalParkPipeline.SignalZones.ZONE_ONE){
//            return 1;
//        }
//        else if (signalZone == SignalParkPipeline.SignalZones.ZONE_TWO) {
//            return 2;
//        }
//        else{
//            return 3;
//        }
//    }
//}
