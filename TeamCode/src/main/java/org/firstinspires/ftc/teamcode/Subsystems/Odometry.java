package org.firstinspires.ftc.teamcode.Subsystems;

import com.ThermalEquilibrium.homeostasis.Utils.Vector;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Utils.AdditonalUtils;

public class Odometry extends Subsystem{
	private BNO055IMU imu;
	public DcMotorEx FrontLeft;
	public DcMotorEx FrontRight;
	private double leftPrev = 0;
	private double rightPrev = 0;
	double trackWidth = 35.70453809697589;

	Vector position = new Vector(3);

	@Override
	public void init(HardwareMap hwmap) {
		imu = hwmap.get(BNO055IMU.class, "imu");
		BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
		parameters.mode = BNO055IMU.SensorMode.IMU;
		parameters.angleUnit = BNO055IMU.AngleUnit.RADIANS;
		imu.initialize(parameters);

		FrontLeft = hwmap.get(DcMotorEx.class, "FrontLeft");
		FrontRight = hwmap.get(DcMotorEx.class, "FrontRight");

	}

	@Override
	public void update() throws Exception {
		double left = encoderTicksToInches(FrontLeft.getCurrentPosition());
		double right = encoderTicksToInches(FrontRight.getCurrentPosition());
		double leftDelta = left - leftPrev;
		double rightDelta = right - rightPrev;
		leftPrev = left;
		rightPrev = right;
		double xDelta = (leftDelta + rightDelta) / 2;
		double yDelta = 0;
		double thetaDelta = (rightDelta - leftDelta) / (trackWidth);
		double imuAngle = imu.getAngularOrientation().firstAngle;

		Vector nu = new Vector(new double[] {
				xDelta,
				yDelta,
				thetaDelta
		});

		AdditonalUtils.rotate(nu, imuAngle);

		position = position.add(nu);

		position.set(imuAngle,2);

	}

	public static double encoderTicksToInches(double ticks) {
		double WHEEL_RADIUS = 3.77953 / 2;
		double ticksPerRevolution = 28.0 * 13.7;
		return WHEEL_RADIUS * 2 * Math.PI * 1 * ticks / ticksPerRevolution;
	}

	public Vector getPosition() {
		return position;
	}

}
