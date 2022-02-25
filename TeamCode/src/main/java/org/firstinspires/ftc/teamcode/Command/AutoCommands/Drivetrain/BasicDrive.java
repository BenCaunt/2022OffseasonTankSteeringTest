package org.firstinspires.ftc.teamcode.Command.AutoCommands.Drivetrain;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.ThermalEquilibrium.homeostasis.Utils.MathUtils;
import com.ThermalEquilibrium.homeostasis.Utils.Vector;

import org.firstinspires.ftc.teamcode.Command.Action;
import org.firstinspires.ftc.teamcode.Controls.Controllers.DistanceDriveControl;
import org.firstinspires.ftc.teamcode.Subsystems.Robot;
import org.firstinspires.ftc.teamcode.Utils.AdditonalUtils;

import java.util.function.DoubleSupplier;

public class BasicDrive extends Action {

	DistanceDriveControl control;
	Robot robot;

	double targetDistance;

	Vector initialPosition;

	public BasicDrive(Robot robot, double targetDistance) {
		this.robot = robot;
		this.targetDistance = targetDistance;
	}

	@RequiresApi(api = Build.VERSION_CODES.N)
	@Override
	public void startAction() {
		initialPosition = robot.odometry.getPosition();
		double initialAngle = robot.odometry.getPosition().get(2);

		control = new DistanceDriveControl(new DoubleSupplier() {
			@Override
			public double getAsDouble() {
				return robot.odometry.getPosition().get(2);
			}
		}, initialAngle);


	}

	@RequiresApi(api = Build.VERSION_CODES.N)
	@Override
	public void runAction() {

		double traveled = AdditonalUtils.calculateDistance(initialPosition,
															robot.odometry.getPosition());
		robot.driveTrain.setPower(control.calculate(targetDistance,traveled));
		isComplete = Math.abs(control.getTrackingError()) < 1 && robot.odometry.getVelocity().get(0) < 2;
	}

	@Override
	public void stopAction() {
		robot.driveTrain.setPower(0,0);
	}

	@Override
	public boolean isActionPersistent() {
		return true;
	}
}
