package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

/* A quick caution to Charlotte: it still doesn't go perfectly straight, I think the problem might be weight distribution but
 we can't do much about that. It's worse when you go slowly, so don't do that very often. we can try to figure something out if we 
 really need to. I''ll create a test program just in case.*/

@TeleOp(name = "TeleopDriveWorlds", group = "OpModes")
public class TeleopDriveWorlds extends OpMode {

    DcMotor Two;
    DcMotor Ten;
    DcMotor Four;
    DcMotor Eight;

    DcMotor Lift;
    DcMotor ColorBall;
    DcMotor Sweeper;

    Servo RackPinion;
    Servo ArmRelease;
    Servo BallRelease;
    Servo Clamp;

    public TeleopDriveWorlds() {

    }

    @Override
    public void init() {

        Two = hardwareMap.dcMotor.get("Two");
        Ten = hardwareMap.dcMotor.get("Ten");
        Eight = hardwareMap.dcMotor.get("Eight");
        Four = hardwareMap.dcMotor.get("Four");

        Lift = hardwareMap.dcMotor.get("Lift");
        ColorBall = hardwareMap.dcMotor.get("ColorBall");
        Sweeper = hardwareMap.dcMotor.get("Sweeper");

        RackPinion = hardwareMap.servo.get("RackPinion");
        ArmRelease = hardwareMap.servo.get("ArmRelease");
        BallRelease = hardwareMap.servo.get("BallRelease");
        Clamp = hardwareMap.servo.get("Clamp");

        ArmRelease.setPosition(0.8);
        BallRelease.setPosition(0);
        Clamp.setPosition(0.0);
        Lift.setPower(0.0);

    }

    @Override
    public void loop() {


        float gamepad1LeftY = -gamepad1.left_stick_y;
        float gamepad1RightX = gamepad1.right_stick_x;
        float Turnclock = -gamepad1.right_trigger;
        float TurnCclock = gamepad1.left_trigger;

        float FrontLeft = -gamepad1LeftY - gamepad1RightX + Turnclock + TurnCclock;
        float FrontRight = gamepad1LeftY - gamepad1RightX + Turnclock + TurnCclock;
        float BackRight = gamepad1LeftY + gamepad1RightX + Turnclock + TurnCclock;
        float BackLeft = -gamepad1LeftY + gamepad1RightX + Turnclock + TurnCclock;

        FrontRight = Range.clip(FrontRight, -1, 1);
        FrontLeft = Range.clip(FrontLeft, -1, 1);
        BackLeft = Range.clip(BackLeft, -1, 1);
        BackRight = Range.clip(BackRight, -1, 1);

/* Without accounting for original reverse, all off of gamepad1...
      Forward: front left +, front right -; back right -; back left +;
      Backward: front left -; front right +; back right +; back left +;
      Left: front left +; front right +; back right -; back left -;
      Right: front left -; front right -; back right +; back left +;
      Clockwise: all -;
      Counterclockwise: all +;
*/

        Two.setPower(FrontRight / 2);
        Ten.setPower(FrontLeft / 2);
        Eight.setPower(BackLeft / 2);
        Four.setPower(BackRight / 2);

        if(gamepad1.a){
            BallRelease.setPosition(0.5);
        }
        else {
            BallRelease.setPosition(0);
        }

        if(gamepad2.a){
            ArmRelease.setPosition(0.4);
        }
        else {
            ArmRelease.setPosition(0.8);
        }

        if(gamepad2.x){
            Sweeper.setPower(-1.0);
        }
        else if (gamepad2.b){
            Sweeper.setPower(1.0);
        }
        else {
            Sweeper.setPower(0.0);
        }

        if (gamepad2.y){
            ColorBall.setPower(-1.0);
        }
        else {
            ColorBall.setPower(0.0);
        }

        if (gamepad2.right_bumper){
            RackPinion.setPosition(1.0);
        }
        else if (gamepad2.left_bumper){
            RackPinion.setPosition(0.0);
        }
        else{
            RackPinion.setPosition(0.5);
        }

        if (gamepad2.right_trigger > 0.3) {
            Lift.setPower(-1);
        }
        else if (gamepad2.left_trigger > 0.3) {
            Lift.setPower(1);
        }
        else {
            Lift.setPower(0);
        }

        if (gamepad2.dpad_up){
            Clamp.setPosition(1.0);

        }
        else if (gamepad2.dpad_down){
            Clamp.setPosition(0.0);
        }
        else if (gamepad2.dpad_right){
            Clamp.setPosition(0.7);
        }
        else{
            Clamp.setPosition(0.0);
        }

    }

    @Override
    public void stop() {

    }



}
