package org.firstinspires.ftc.teamcode.Controls;

import com.ThermalEquilibrium.homeostasis.Parameters.PIDCoefficients;

public class Coefficients {
	public static PIDCoefficients distanceControl = new PIDCoefficients(0.01,0,0);
	public static PIDCoefficients angleControl = new PIDCoefficients(0.01,0,0);
}
