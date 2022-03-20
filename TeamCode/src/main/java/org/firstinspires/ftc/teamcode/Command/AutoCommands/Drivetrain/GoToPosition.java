package org.firstinspires.ftc.teamcode.Command.AutoCommands.Drivetrain;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.ThermalEquilibrium.homeostasis.Utils.Vector;

import org.firstinspires.ftc.teamcode.Command.Action;
import org.firstinspires.ftc.teamcode.Controls.Controllers.GoToGoalController;
import org.firstinspires.ftc.teamcode.Subsystems.Robot;

public class GoToPosition extends Action {

	GoToGoalController controller;
	Robot robot;
	Vector targetPose;
	GoToGoalController.driveDirection direction = GoToGoalController.driveDirection.FORWARD;

	public GoToPosition(Robot robot, Vector targetPose) {
		this.robot = robot;
		this.targetPose = targetPose;
	}

	public GoToPosition(Robot robot, Vector targetPose,GoToGoalController.driveDirection direction) {
		this.robot = robot;
		this.targetPose = targetPose;
		this.direction = direction;
	}


	@RequiresApi(api = Build.VERSION_CODES.N)
	@Override
	public void startAction() {
		this.controller = new GoToGoalController(robot, targetPose, direction);
	}

	@RequiresApi(api = Build.VERSION_CODES.N)
	@Override
	public void runAction() throws Exception {
		isComplete = controller.update();
	}

	@Override
	public void stopAction() {
		robot.driveTrain.setPower(0,0);
	}
}
