package org.firstinspires.ftc.teamcode.Controls.Controllers;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.ThermalEquilibrium.homeostasis.Utils.MathUtils;
import com.ThermalEquilibrium.homeostasis.Utils.Vector;
import com.ThermalEquilibrium.homeostasis.Utils.WPILibMotionProfile;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Controls.ControlConstants;
import org.firstinspires.ftc.teamcode.Subsystems.Dashboard;
import org.firstinspires.ftc.teamcode.Subsystems.Robot;
import org.firstinspires.ftc.teamcode.Utils.AdditonalUtils;

import java.util.function.DoubleSupplier;

public class GoToGoalController {

	Robot robot;
	Vector targetPose;
	double minimumDistance = 3;
	double minHeadingError = Math.toRadians(2);
	DistanceDriveControl controller;
	driveDirection direction = driveDirection.FORWARD;


	double forwardVelo = 10;
	double forwardAccl = 10;

	WPILibMotionProfile profile_m;

	ElapsedTime timer = new ElapsedTime();

	@RequiresApi(api = Build.VERSION_CODES.N)
	public GoToGoalController(Robot robot) {
		this.robot = robot;
		initializeController();
	}
	@RequiresApi(api = Build.VERSION_CODES.N)
	public GoToGoalController(Robot robot, Vector targetPose) {
		this.robot = robot;
		this.targetPose = targetPose;
		initializeController();
	}
	@RequiresApi(api = Build.VERSION_CODES.N)
	public GoToGoalController(Robot robot, Vector targetPose, driveDirection direction) {
		this.robot = robot;
		this.targetPose = targetPose;
		this.direction = direction;
		initializeController();
	}
	public void setTargetPose(Vector targetPose) {
		this.targetPose = targetPose;
	}


	@RequiresApi(api = Build.VERSION_CODES.N)
	public boolean update() throws Exception {
		AdditonalUtils.drawRobotTarget(targetPose, Dashboard.packet);
		Vector robotPose = robot.odometry.getPosition();
		switch (direction) {
			case FORWARD:
				controller.setHeadingReference(AdditonalUtils.atan2(targetPose.minus(robotPose)));
				break;
			case REVERSE:
				controller.setHeadingReference(AdditonalUtils.atan2(targetPose.minus(robotPose)) - Math.PI);
				break;
		}
		double distance = profile_m.calculate(timer.seconds()).position;
		Vector powers;
		if (Math.abs(calculateDistance()) < minimumDistance) {
//			controller.turnControl.setCoefficients(ControlConstants.angleControl2);
			controller.setHeadingReference(targetPose.get(2));
			powers = controller.calculate(0, 0);
		} else {
//			controller.turnControl.setCoefficients(ControlConstants.driveAngleControl);
			powers = controller.calculate(0, distance);
		}
		robot.driveTrain.setPower(powers);
		return isComplete();
	}

	protected boolean isComplete() {
		Vector robotPos = robot.odometry.getPosition();
		boolean isWithinDistance = AdditonalUtils.calculateDistance(targetPose,robotPos) < minimumDistance;
		boolean isWithinTargetHeading = Math.abs(MathUtils.normalizedHeadingError(targetPose.get(2), robotPos.get(2))) < minHeadingError;
		boolean speedIsLowEnough = Math.abs(robot.odometry.getVelocity().get(2)) < Math.toRadians(5);
		return isWithinDistance && isWithinTargetHeading && speedIsLowEnough;
	}

	@RequiresApi(api = Build.VERSION_CODES.N)
	protected void initializeController() {
		this.controller = new DistanceDriveControl(new DoubleSupplier() {
			@Override
			public double getAsDouble() {
				return robot.odometry.getPosition().get(2);
			}
		}, 0);
		double distance = calculateDistance();

		WPILibMotionProfile.Constraints constraints = new WPILibMotionProfile.Constraints(forwardVelo,forwardAccl);
		WPILibMotionProfile.State initial = new WPILibMotionProfile.State(distance, 0);
		WPILibMotionProfile.State end = new WPILibMotionProfile.State(0,0);

		profile_m = new WPILibMotionProfile(constraints,initial,end);
		timer.reset();

	}


	public enum driveDirection {
		FORWARD,
		REVERSE
	}


	protected double calculateDistance() {
		double distance = AdditonalUtils.calculateDistance(robot.odometry.getPosition(), targetPose);
		if (direction.equals(driveDirection.FORWARD)) distance = -distance;
		return distance;
	}

}
