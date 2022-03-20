package org.firstinspires.ftc.teamcode.Command.TeleopCommands;

import com.ThermalEquilibrium.homeostasis.Utils.Vector;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.Command.TeleopAction;
import org.firstinspires.ftc.teamcode.Subsystems.Robot;


public class CheesyDrive extends TeleopAction {

	double turnOnlyThreshold = 0.05;

	public CheesyDrive(Robot robot, Gamepad gamepad1, Gamepad gamepad2) {
		super(robot, gamepad1, gamepad2);
	}

	@Override
	public void initialRun() {

	}

	@Override
	public void periodic() {
		double throttle = -gamepad1.right_stick_y;
		double turn = gamepad1.left_stick_x;

		Vector power;

		if (Math.abs(throttle) < turnOnlyThreshold) {
			power = new Vector(new double[] {0,turn});
		}
		else {
			turn *= Math.abs(throttle);
			power = new Vector(new double[] {throttle, turn});
		}

		robot.driveTrain.robotRelative(power);

	}

	@Override
	public boolean isComplete() {
		return false;
	}

	@Override
	public boolean shouldRun() {
		return true;
	}

	@Override
	public void reset() {

	}

	@Override
	public boolean hasPerformedInitialRun() {
		return true;
	}
}
