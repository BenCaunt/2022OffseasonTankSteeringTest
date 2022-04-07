package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.ArrayList;

public class Robot extends Subsystem{

	public Drivetrain driveTrain = new Drivetrain();
	public Odometry odometry = new Odometry();
	public Dashboard dashboard = new Dashboard();

	ArrayList<Subsystem> subsystems = new ArrayList<>();

	@Override
	public void init(HardwareMap hwmap) {

		subsystems.add(driveTrain);
		subsystems.add(odometry);
		subsystems.add(dashboard);

		for (Subsystem s: subsystems) {
			s.init(hwmap);
		}

	}

	@Override
	public void update() {

	}

	public ArrayList<Subsystem> getSubsystems() {
		return subsystems;
	}
}
