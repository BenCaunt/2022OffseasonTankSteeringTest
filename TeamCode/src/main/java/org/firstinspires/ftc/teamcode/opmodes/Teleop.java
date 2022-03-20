package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.BaseOpmodes.BaseTeleop;
import org.firstinspires.ftc.teamcode.Command.TeleopCommands.CheesyDrive;


@TeleOp
public class Teleop extends BaseTeleop {
	@Override
	public void addActions() {
		actions.add(new CheesyDrive(robot, gamepad1, gamepad2));
	}
}
