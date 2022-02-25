package org.firstinspires.ftc.teamcode.Controls.Controllers;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.ThermalEquilibrium.homeostasis.Controllers.Feedback.AngleController;
import com.ThermalEquilibrium.homeostasis.Controllers.Feedback.BasicPID;
import com.ThermalEquilibrium.homeostasis.Controllers.Feedback.FeedbackController;
import com.ThermalEquilibrium.homeostasis.Controllers.Feedforward.BasicFeedforward;
import com.ThermalEquilibrium.homeostasis.Utils.Vector;

import org.firstinspires.ftc.teamcode.Controls.Coefficients;

import java.util.function.DoubleSupplier;

public class DistanceDriveControl  {



	BasicPID distanceControl = new BasicPID(Coefficients.distanceControl);
	TurnOnlyControl turnControl;
	double trackingError = 0;

	public DistanceDriveControl(DoubleSupplier robotAngle, double headingReference) {
		turnControl = new TurnOnlyControl(robotAngle, headingReference);
	}

	/**
	 * returns the wheel powers as a vector
	 * @param reference target distance
	 * @param state current traveled distance
	 * @return 2 state vector, item 0 is left, item 1 is right
	 */
	@RequiresApi(api = Build.VERSION_CODES.N)
	public Vector calculate(double reference, double state) {


		double direction = Math.signum(reference);
		reference = Math.abs(reference);
		trackingError = reference - state;

		Vector output = new Vector(2);

		double forward = distanceControl.calculate(reference,state) * direction;
		Vector turn = turnControl.calculate();

		output.set(forward, 0);
		output.set(forward, 1);

		try {
			return output.add(turn);
		} catch (Exception e) {
			e.printStackTrace();
			return output;
		}
	}

	public double getTrackingError() {
		return trackingError;
	}

	public void setHeadingReference(double reference) {
		this.turnControl.setHeadingReference(reference);
	}

}
