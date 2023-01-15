
package org.firstinspires.ftc.teamcode.pipelines;


import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

/*
 * This sample demonstrates a basic (but battle-tested and essentially
 * 100% accurate) method of detecting the skystone when lined up with
 * the sample regions over the first 3 stones.
 */

public class SignalParkPipelineRed extends OpenCvPipeline {
    protected int purple;
    protected int green;
    protected int orange;

    public SignalParkPipelineRed(Telemetry telemetry, int hue1, int hue2, int hue3){
        purple = hue1;
        green = hue2;
        orange = hue3;
        this.telemetry = telemetry;
    }

    public SignalParkPipelineRed(Telemetry telemetry) {
        this(telemetry,120, 80, 50);
    }

    private volatile SignalZone currentZone = SignalZone.ZONE_TWO;

    public enum SignalZone {
        ZONE_ONE,
        ZONE_TWO,
        ZONE_THREE
    }

    /*
     * Some color constants
     */
    static final Scalar PURPLE = new Scalar(168, 25, 250);
    static final Scalar GREEN = new Scalar(0, 255, 0);
    static final Scalar ORANGE = new Scalar(250, 136, 25);

    /*
     * The core values which define the location and size of the sample regions
     */

    //PLACE ALL REGIONS IN THE SAME PLACE (?)
    static final Point REGION_TOPLEFT_ANCHOR_POINT = new Point(520, 170);

    static final int REGION_WIDTH = 300;
    static final int REGION_HEIGHT = 400;
    //take the average of pixels and match it upn

    /*
     * Points which actually define the sample region rectangles, derived from above values
     *
     * Example of how points A and B work to define a rectangle
     *
     *   ------------------------------------
     *   | (0,0) Point A                    |
     *   |                                  |
     *   |                                  |
     *   |                                  |
     *   |                                  |
     *   |                                  |
     *   |                                  |
     *   |                  Point B (70,50) |
     *   ------------------------------------
     *
     */
    Point region1_pointA = new Point(
            REGION_TOPLEFT_ANCHOR_POINT.x,
            REGION_TOPLEFT_ANCHOR_POINT.y);
    Point region1_pointB = new Point(
            REGION_TOPLEFT_ANCHOR_POINT.x + REGION_WIDTH,
            REGION_TOPLEFT_ANCHOR_POINT.y + REGION_HEIGHT);


    /*
     * Working variables
     */
    Mat region1_Hue;
    Mat region1_Sat;
    Mat HSV = new Mat();
    Mat Hue = new Mat();
    Mat Sat = new Mat();
    double avg1;
    Telemetry telemetry;

    /*
     * This function takes the RGB frame, converts to HSV,
     * and extracts the Hue and Sat channels to the 'Hue' and 'Sat' variables
     */
    void inputToHSV(Mat input) {
        Imgproc.cvtColor(input, HSV, Imgproc.COLOR_RGB2HSV);
        Core.extractChannel(HSV, Hue, 0);
        Core.extractChannel(HSV, Sat, 1);
        /*
         * Submats are a persistent reference to a region of the parent
         * buffer. Any changes to the child affect the parent, and the
         * reverse also holds true.
         */
        region1_Hue = Hue.submat(new Rect(region1_pointA, region1_pointB));
        region1_Sat = Sat.submat(new Rect(region1_pointA, region1_pointB));
    }

    @Override
    public void init(Mat firstFrame) {
        /*
         * We need to call this in order to make sure the 'Cb'
         * object is initialized, so that the submats we make
         * will still be linked to it on subsequent frames. (If
         * the object were to only be initialized in processFrame,
         * then the submats would become delinked because the backing
         * buffer would be re-allocated the first time a real frame
         * was crunched)
         */
        inputToHSV(firstFrame);

    }

    @Override
    public Mat processFrame(Mat input) {

        inputToHSV(input);

        /*
         * Compute the average pixel value of each submat region. We're
         * taking the average of a single channel buffer, so the value
         * we need is at index 0. We could have also taken the average
         * pixel value of the 3-channel image, and referenced the value
         * at index 2 here.
         */

        double sum = 0;
        int count = 0;

        for (int hueH = 0; hueH < region1_Hue.height(); hueH++) {
            for (int hueW = 0; hueW < region1_Hue.width(); hueW++) {
                if (region1_Sat.get(hueH, hueW)[0] > 88) {
                    sum += region1_Hue.get(hueH, hueW)[0];
                    count++;
                }
            }
        }

//        find average hue of the region (neglecting the pixels that don't
//        surpass a certain threshold in saturation)
        if (count == 0) {
            avg1 = 0;
        } else {
            avg1 = sum / count;
        }
        if (telemetry != null) {
            telemetry.addData("AVG: ", avg1);
            telemetry.addData("Count: ", count);
            telemetry.update();
        }

        /*
         * Find which hue each region is closest to
         * Draw a rectangle in that color
         */
        double[] hues = {70, 30, 8};   //changed values not sure if they work try for next time

//            double closestHue = Arrays.stream(hues).map(hue -> {
//                return Math.min(Math.abs(avg1-hue), 180-Math.abs(avg1-hue));
//            }).min().getAsDouble();
        double minDistance = Double.POSITIVE_INFINITY;
        int minIndex = 0;
        for (int i = 0; i < 3; i++) {
            double dis = Math.min(Math.abs(avg1 - hues[i]), 180 - Math.abs(avg1 - hues[i]));
            if (dis < minDistance) {
                minDistance = dis;
                minIndex = i;
            }
        }

        if (minIndex == 0) { //closest to purple
            Imgproc.rectangle(
                    input, // Buffer to draw on
                    region1_pointA, // First point which defines the rectangle
                    region1_pointB, // Second point which defines the rectangle
                    PURPLE, // The color the rectangle is drawn in
                    2);
            currentZone = SignalZone.ZONE_ONE;
        } else if (minIndex == 1) { //closest to green
            Imgproc.rectangle(
                    input, // Buffer to draw on
                    region1_pointA, // First point which defines the rectangle
                    region1_pointB, // Second point which defines the rectangle
                    GREEN, // The color the rectangle is drawn in
                    2);
            currentZone = SignalZone.ZONE_TWO;

        } else {  //closest to orange
            Imgproc.rectangle(
                    input, // Buffer to draw on
                    region1_pointA, // First point which defines the rectangle
                    region1_pointB, // Second point which defines the rectangle
                    ORANGE, // The color the rectangle is drawn in
                    2); // Thickness of the rectangle lines
            currentZone = SignalZone.ZONE_THREE;


        }

        /*
         * Render the 'input' buffer to the viewport. But note this is not
         * simply rendering the raw camera feed, because we called functions
         * to add some annotations to this buffer earlier up.
         */
        return input;
    }


    /*
     * Call this from the OpMode thread to obtain the latest analysis
     */
    public SignalZone getAnalysis() {
        return currentZone;
    }
}
