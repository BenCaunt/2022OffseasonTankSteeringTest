package org.firstinspires.ftc.teamcode.Controls;

import com.ThermalEquilibrium.homeostasis.Parameters.PIDCoefficients;
import com.ThermalEquilibrium.homeostasis.Utils.WPILibMotionProfile;

import org.firstinspires.ftc.teamcode.AsymmetricProfile.MotionConstraint;
import org.firstinspires.ftc.teamcode.Controls.Coefficient.SqrtCoefficients;

public class ControlConstants {
	public static PIDCoefficients distanceControl = new PIDCoefficients(0.1,0,0);
	public static SqrtCoefficients angleControl = new SqrtCoefficients(0.45, 0.05,0);
	public static MotionConstraint driveConstraintsNew = new MotionConstraint(90,
			-30,60);
	public static MotionConstraint turnConstraintsNew =
			new MotionConstraint(Math.toRadians(300),
								Math.toRadians(90),
								Math.toRadians(300));

}
