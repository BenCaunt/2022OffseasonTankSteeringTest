package org.firstinspires.ftc.teamcode.Command.ControlFlow;


import org.firstinspires.ftc.teamcode.Command.Action;

/**
 * runs multiple actions in parallel.  The end user must ensure that the actions they are running are compatible
 *
 * The difference vs {@link MultipleAction} is that this will end when
 * a single action returns true on its is complete method
 *
 * Compatible actions would be like moving a drivetrain and an arm at the same time
 *
 * Incompatible actions would be like running a multiple drive to positions at the same time.
 *
 */
public class RaceAction extends MultipleAction {


	public RaceAction(Action[] actions) {
		super(actions);
	}

	/**
	 *  Returns complete when at least one action is finished.
	 * @return true when complete, false when not
	 */
	@Override
	public boolean isActionComplete() {
		for (Action a : actions) {
			if (a.isActionComplete()) {
				return true;
			}
		}
		return false;
	}
}
