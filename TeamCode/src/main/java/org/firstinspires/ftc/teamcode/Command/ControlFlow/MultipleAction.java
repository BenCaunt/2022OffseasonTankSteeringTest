package org.firstinspires.ftc.teamcode.Command.ControlFlow;

import org.firstinspires.ftc.teamcode.Command.Action;


/**
 * runs multiple actions in parallel.  The end user must ensure that the actions they are running are compatible
 *
 *
 * The difference vs {@link RaceAction} is that this action will only return complete when ALL actions return complete
 *
 * Compatible actions would be like moving a drivetrain and an arm at the same time
 *
 * Incompatible actions would be like running a multiple drive to positions at the same time.
 *
 */
public class MultipleAction extends Action {


	protected Action[] actions;

	public MultipleAction(Action[] actions) {
		this.actions = actions;
	}

	@Override
	public void startAction() {
		for (Action a : actions) {
			a.startAction();
		}
	}

	@Override
	public void runAction() throws Exception {
		for (Action a : actions) {
			a.runAction();
		}
 	}

	@Override
	public void stopAction() {

		for (Action a : actions) {
			a.stopAction();
		}

	}

	/**
	 * stops only when all actions are complete
	 * @return true if complete, false if not
	 */
	@Override
	public boolean isActionComplete() {
		for (Action a : actions) {
			if (!a.isActionComplete()) {
				return false;
			}
		}
		return true;
	}

}
