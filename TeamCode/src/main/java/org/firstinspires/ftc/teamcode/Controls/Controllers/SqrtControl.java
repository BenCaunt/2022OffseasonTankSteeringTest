package org.firstinspires.ftc.teamcode.Controls.Controllers;

import com.ThermalEquilibrium.homeostasis.Controllers.Feedback.FeedbackController;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Controls.Coefficient.SqrtCoefficients;

public class SqrtControl implements FeedbackController {

	SqrtCoefficients coefficients;

	ElapsedTime timer = new ElapsedTime();
	private double lasterror = 0;
	boolean hasRun = false;

	public SqrtControl(SqrtCoefficients coefficients) {
		this.coefficients = coefficients;
		timer.reset();
	}

	@Override
	public double calculate(double reference, double state) {
		double error = reference - state;
		double output;
		if (error > 0) {
			output = Math.sqrt(error) * coefficients.getK() + coefficients.getH();
		} else {
			output = -Math.sqrt(Math.abs(error)) * coefficients.getK() - coefficients.getH();
		}

		if (!hasRun) {
			lasterror = error;
			hasRun = true;
		}

		double derivative =( error - lasterror) / timer.seconds();
		timer.reset();
		lasterror = error;

		return output + coefficients.getKd() * derivative;
	}
}
