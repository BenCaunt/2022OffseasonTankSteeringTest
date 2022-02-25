package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;

public abstract class Subsystem {

	public abstract void init(HardwareMap hwmap);

	public void initNoReset(HardwareMap hwmap) {
		init(hwmap);
	}


}
