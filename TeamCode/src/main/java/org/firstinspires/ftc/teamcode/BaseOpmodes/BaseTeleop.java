package org.firstinspires.ftc.teamcode.BaseOpmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Command.Scheduler;
import org.firstinspires.ftc.teamcode.Command.TeleopAction;
import org.firstinspires.ftc.teamcode.Subsystems.Robot;

import java.util.ArrayList;

public abstract class BaseTeleop extends LinearOpMode {


	public ArrayList<TeleopAction> actions = new ArrayList<>();

	public Robot robot;
	Scheduler scheduler;

	public abstract void addActions();

	@Override
	public void runOpMode() throws InterruptedException {


		robot = new Robot();
		robot.init(hardwareMap);
		addActions();
		scheduler = new Scheduler(robot.getSubsystems(), actions, hardwareMap);
		waitForStart();
		while (opModeIsActive()) {
			try {
				scheduler.updateTeleop();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}
