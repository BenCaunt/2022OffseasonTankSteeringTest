package org.firstinspires.ftc.teamcode.Subsystems;

import com.ThermalEquilibrium.homeostasis.Systems.BasicSystem;
import com.ThermalEquilibrium.homeostasis.Utils.Vector;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Drivetrain extends Subsystem {


	DcMotorEx FrontLeft;
	DcMotorEx FrontRight;
	DcMotorEx BackRight;
	DcMotorEx BackLeft;

	@Override
	public void init(HardwareMap hwmap) {
		FrontLeft = hwmap.get(DcMotorEx.class, "FrontLeft");
		FrontRight = hwmap.get(DcMotorEx.class, "FrontRight");
		BackLeft = hwmap.get(DcMotorEx.class, "BackLeft");
		BackRight = hwmap.get(DcMotorEx.class, "BackRight");

		FrontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
		FrontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
		BackLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
		BackRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

		FrontLeft.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
		FrontRight.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
		BackLeft.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
		BackRight.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);

		FrontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
		FrontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
		BackLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
		BackRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

		FrontLeft.setDirection(DcMotorEx.Direction.FORWARD);
		FrontRight.setDirection(DcMotorEx.Direction.REVERSE);
		BackLeft.setDirection(DcMotorEx.Direction.FORWARD);
		BackRight.setDirection(DcMotorEx.Direction.REVERSE);
	}

	@Override
	public void update() {

	}

	public void setPower(double left, double right) {
		FrontLeft.setPower(left);
		FrontRight.setPower(right);
		BackLeft.setPower(left);
		BackRight.setPower(right);
	}

	/**
	 * set the power using a 2d vector
	 * @param v,  element 0 is the left power, element 1 is the right
	 */
	public void setPower(Vector v) {
		setPower(v.get(0), v.get(1));
	}


	/**
	 * set power using a forward and turn power
	 * @param forward power
	 * @param turn power
	 */
	public void robotRelative(double forward, double turn) {
		double left = forward + turn;
		double right = forward - turn;
		setPower(left,right);
	}

	public void robotRelative(Vector v) {
		robotRelative(v.get(0), v.get(1));
	}
}
