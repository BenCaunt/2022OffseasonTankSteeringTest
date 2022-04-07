package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Subsystems.Robot;

public class TestLinearOpMode extends LinearOpMode {

	Robot robot = new Robot();

	@Override
	public void runOpMode() throws InterruptedException {
		robot.init(hardwareMap);
		waitForStart();

	}
}
