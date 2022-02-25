package org.firstinspires.ftc.teamcode.Controls.Controllers;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.ThermalEquilibrium.homeostasis.Controllers.Feedback.AngleController;
import com.ThermalEquilibrium.homeostasis.Controllers.Feedback.BasicPID;
import com.ThermalEquilibrium.homeostasis.Utils.MathUtils;
import com.ThermalEquilibrium.homeostasis.Utils.Vector;

import org.firstinspires.ftc.teamcode.Controls.Coefficients;
import org.firstinspires.ftc.teamcode.Subsystems.Dashboard;

import java.util.function.DoubleSupplier;

public class TurnOnlyControl {
	protected double headingReference;
	protected DoubleSupplier robotAngle;

	BasicPID distanceControl = new BasicPID(Coefficients.distanceControl);
	SqrtControl angleController = new SqrtControl(Coefficients.angleControl2);
	AngleController angleControl = new AngleController(angleController);

	double trackingError = 0;

	public TurnOnlyControl(DoubleSupplier robotAngle, double headingReference) {
		this.robotAngle = robotAngle;
		this.headingReference = headingReference;
	}

	/**
	 * returns the wheel powers as a vector
	 * @return 2 state vector, item 0 is left, item 1 is right
	 */
	@RequiresApi(api = Build.VERSION_CODES.N)
	public Vector calculate() {

		Vector output = new Vector(2);
		trackingError = MathUtils.normalizedHeadingError(headingReference, robotAngle.getAsDouble());
		Dashboard.packet.put("Turn Tracking Error", trackingError);
		Dashboard.packet.put("Turn reference", headingReference);
		Dashboard.packet.put("Robot Angle", robotAngle.getAsDouble());
		double heading = -angleControl.calculate(headingReference, robotAngle.getAsDouble());

		double left = + heading;
		double right = - heading;

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
