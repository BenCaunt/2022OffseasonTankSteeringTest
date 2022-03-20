package org.firstinspires.ftc.teamcode.Command;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.Subsystems.Robot;

/**
 * action that is used to run in the teleop period
 */
public abstract class TeleopAction {
	public Gamepad gamepad1;
	public Gamepad gamepad2;
	public Robot robot;

	public TeleopAction(Robot robot, Gamepad gamepad1, Gamepad gamepad2) {
		this.gamepad1 = gamepad1;
		this.gamepad2 = gamepad2;
		this.robot = robot;
	}

	/**
	 * is executed on the first run of the action, generally when the button is first pressed
	 */
	public abstract void initialRun();

	/**
	 * periodically ran until the action is complete
	 */
	public abstract void periodic();

	/**
	 * signal that the action is complete
	 *
	 * @return true if complete
	 */
	public abstract boolean isComplete();

	/**
	 * assess if we should run this action
	 *
	 * @return true if we should run this action
	 */
	public abstract boolean shouldRun();

	/**
	 * reset states to where the action can be used again
	 */
	public abstract void reset();

	/**
	 * check if the initial run has occurred
	 *
	 * @return returns true if this is the case
	 */
	public abstract boolean hasPerformedInitialRun();
}
