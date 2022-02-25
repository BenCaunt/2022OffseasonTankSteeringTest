package org.firstinspires.ftc.teamcode.Command;

public abstract class Action {

	protected boolean isComplete = false;

	public abstract void startAction();

	public abstract void runAction();

	public abstract void stopAction();

	public boolean isActionComplete() {
		return isComplete;
	}

	boolean isActionPersistent() {
		return false;
	}

	public boolean isAMultipleAction() {
		return false;
	}



}


