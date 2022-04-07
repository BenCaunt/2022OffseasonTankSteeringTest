package org.firstinspires.ftc.teamcode.Command.ControlFlow;

import android.os.Build;

import androidx.annotation.RequiresApi;

import org.firstinspires.ftc.teamcode.Command.Action;

import java.util.function.BooleanSupplier;

/**
 * Run another action normally but will finish if a given condition is true
 *
 * Could be used to do something like ensure that an action completes
 * before a given time to force parking at the end of auto
 *
 */
public class EarlyStopping extends Action {

	Action mainAction;
	BooleanSupplier stoppingCondition;

	public EarlyStopping(Action mainAction, BooleanSupplier stoppingCondition) {
		this.mainAction = mainAction;
		this.stoppingCondition = stoppingCondition;
	}

	@Override
	public void startAction() {
		mainAction.startAction();
	}

	@Override
	public void runAction() throws Exception {
		mainAction.runAction();
	}

	@Override
	public void stopAction() {
		mainAction.stopAction();
	}

	/**
	 * stop if the signal from the stoppingCondition supplier
	 * is true or if the action in question is complete
	 * @return true if complete, false if not
	 */
	@RequiresApi(api = Build.VERSION_CODES.N)
	@Override
	public boolean isActionComplete() {
		return super.isActionComplete() || stoppingCondition.getAsBoolean();
	}
}
