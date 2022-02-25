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


	protected double headingReference;
	protected DoubleSupplier robotAngle;

	BasicPID distanceControl = new BasicPID(Coefficients.distanceControl);
	BasicPID anglePID = new BasicPID(Coefficients.angleControl);
	AngleController angleControl = new AngleController(anglePID);

	double trackingError = 0;

	public DistanceDriveControl(DoubleSupplier robotAngle, double headingReference) {
		this.robotAngle = robotAngle;
		this.headingReference = headingReference;
	}

	/**
	 * returns the wheel powers as a vector
	 * @param reference target distance
	 * @param state current traveled distance
	 * @return 2 state vector, item 0 is left, item 1 is right
	 */
	@RequiresApi(api = Build.VERSION_CODES.N)
	public Vector calculate(double reference, double state) {

		trackingError = reference - state;

		Vector output = new Vector(2);

		double forward = distanceControl.calculate(reference,state);
		double heading = -anglePID.calculate(headingReference, robotAngle.getAsDouble());

		double left = forward + heading;
		double right = forward - heading;

		output.set(left, 0);
		output.set(right, 1);

		return output;
	}

	public double getTrackingError() {
		return trackingError;
	}

	public void setHeadingReference(double reference) {
		this.headingReference = reference;
	}

}
