package org.firstinspires.ftc.teamcode.Command;

public abstract class Action {

	protected boolean isComplete = false;

	public abstract void startAction();

	public abstract void runAction() throws Exception;

	public abstract void stopAction();

	public boolean isActionComplete() {
		return isComplete;
	}

	public boolean isActionPersistent() {
		return false;
	}

	public boolean isAMultipleAction() {
		return false;
	}



}


