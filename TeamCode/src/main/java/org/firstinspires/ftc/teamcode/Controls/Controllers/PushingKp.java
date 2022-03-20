package org.firstinspires.ftc.teamcode.Controls.Controllers;

import com.ThermalEquilibrium.homeostasis.Controllers.Feedback.FeedbackController;

public class PushingKp implements FeedbackController {

	@Override
	public double calculate(double reference, double state) {
		double x = reference - state;
		double sign = Math.abs(x) / x;
		double bruh = Math.sqrt(Math.abs(x) * Math.abs(gain(x)));
		return bruh * sign;
	}

	public double gain(double x) {
		return 1 / (1 + Math.pow(Math.E,Math.abs(x)));
	}

}


