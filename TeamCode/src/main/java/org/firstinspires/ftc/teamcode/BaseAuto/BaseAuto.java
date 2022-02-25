package org.firstinspires.ftc.teamcode.BaseAuto;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;


import org.firstinspires.ftc.teamcode.Command.Action;
import org.firstinspires.ftc.teamcode.Command.Scheduler;
import org.firstinspires.ftc.teamcode.Subsystems.Robot;

import java.util.ArrayList;


public abstract class BaseAuto extends LinearOpMode {

	protected Robot robot;


	protected ArrayList<Action> actions = new ArrayList<>();

	public abstract void addActions();


	@Override
	public void runOpMode() {
		robot = new Robot();
		robot.init(hardwareMap);



		waitForStart();

		addActions();
		Scheduler scheduler = new Scheduler(hardwareMap, actions, robot.getSubsystems());

		while (opModeIsActive()) {
			scheduler.updateStateMachineAndRobot();
		}

	}
}
