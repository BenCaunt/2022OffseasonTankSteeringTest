package org.firstinspires.ftc.teamcode.Controls;

import com.ThermalEquilibrium.homeostasis.Parameters.PIDCoefficients;
import com.ThermalEquilibrium.homeostasis.Utils.WPILibMotionProfile;

import org.firstinspires.ftc.teamcode.AsymmetricProfile.MotionConstraint;
import org.firstinspires.ftc.teamcode.Controls.Coefficient.SqrtCoefficients;

public class ControlConstants {
	public static PIDCoefficients distanceControl = new PIDCoefficients(0.1,0,0);
	public static PIDCoefficients angleControl = new PIDCoefficients(0.9,0,0);
	public static SqrtCoefficients angleControl2 = new SqrtCoefficients(0.45, 0.015,0);
	public static SqrtCoefficients driveAngleControl = new SqrtCoefficients(0.85, 0.015,0);
	public static WPILibMotionProfile.Constraints driveConstraints =
			new WPILibMotionProfile.Constraints(70,80);
	public static WPILibMotionProfile.Constraints angularConstraints =
			new WPILibMotionProfile.Constraints(Math.toRadians(300), Math.toRadians(80));


	public static MotionConstraint driveConstraintsNew = new MotionConstraint(40,-30,30);

}
