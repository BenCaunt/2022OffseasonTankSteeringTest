package org.firstinspires.ftc.teamcode.Command.AutoCommands.Drivetrain;

import android.os.Build;

import androidx.annotation.RequiresApi;

import org.firstinspires.ftc.teamcode.Command.Action;
import org.firstinspires.ftc.teamcode.Controls.Controllers.TurnOnlyControl;
import org.firstinspires.ftc.teamcode.Subsystems.Robot;

import java.util.function.DoubleSupplier;

public class BasicTurn extends Action {

	Robot robot;
	double referenceAngle;
	TurnOnlyControl controller;

	@RequiresApi(api = Build.VERSION_CODES.N)
	public BasicTurn(Robot robot, double referenceAngle) {
		this.robot = robot;
		this.referenceAngle = referenceAngle;
		this.controller = new TurnOnlyControl(new DoubleSupplier() {
			@Override
			public double getAsDouble() {
				return robot.odometry.getPosition().get(2);
			}
		},referenceAngle);
	}

	@RequiresApi(api = Build.VERSION_CODES.N)
	@Override
	public void startAction() {
	}

	@RequiresApi(api = Build.VERSION_CODES.N)
	@Override
	public void runAction() {
		robot.driveTrain.setPower(controller.calculate());
		isComplete = Math.abs(controller.getTrackingError()) < Math.toRadians(1)
				&& Math.abs(robot.odometry.getVelocity().get(2)) < Math.toRadians(10);

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
