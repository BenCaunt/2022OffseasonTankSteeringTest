package org.firstinspires.ftc.teamcode.Controls.Controllers;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.ThermalEquilibrium.homeostasis.Controllers.Feedback.AngleController;
import com.ThermalEquilibrium.homeostasis.Controllers.Feedback.FeedbackController;
import com.ThermalEquilibrium.homeostasis.Utils.MathUtils;
import com.ThermalEquilibrium.homeostasis.Utils.Vector;
import com.ThermalEquilibrium.homeostasis.Utils.WPILibMotionProfile;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Controls.Coefficient.SqrtCoefficients;
import org.firstinspires.ftc.teamcode.Controls.ControlConstants;
import org.firstinspires.ftc.teamcode.Subsystems.Dashboard;

import java.util.function.DoubleSupplier;

public class TurnOnlyControl {
	protected double headingReference;
	protected double endGoalError = 1000;
	protected DoubleSupplier robotAngle;
	protected double previousReference = 100000000;
	ElapsedTime timer = new ElapsedTime();
	WPILibMotionProfile profile;

	SqrtControl angleController = new SqrtControl(ControlConstants.angleControl2);
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

		regenerateProfile(robotAngle.getAsDouble(), headingReference);
		double profileState = profile.calculate(timer.seconds()).position;
		Vector output = new Vector(2);
		endGoalError = MathUtils.normalizedHeadingError(headingReference, robotAngle.getAsDouble());
		trackingError = MathUtils.normalizedHeadingError(profileState, robotAngle.getAsDouble());
		Dashboard.packet.put("Turn Tracking Error", trackingError);
		Dashboard.packet.put("End Goal Error", endGoalError);
		Dashboard.packet.put("Turn reference", headingReference);
		Dashboard.packet.put("Robot Angle", robotAngle.getAsDouble());
		double heading = -angleControl.calculate(0,  endGoalError);

		double left = + heading;
		double right = - heading;

		output.set(left, 0);
		output.set(right, 1);

		return output;
	}

	public double getTrackingError() {
		return trackingError;
	}

	public double getEndGoalError() {
		return endGoalError;
	}

	public void setHeadingReference(double reference) {
		this.headingReference = reference;
	}

	public void setCoefficients(SqrtCoefficients coefficients) {
		angleController.setCoefficients(coefficients);
	}

	public void regenerateProfile(double reference, double state) {
		if (reference != previousReference) {
			WPILibMotionProfile.State goal = new WPILibMotionProfile.State(0,0);
			WPILibMotionProfile.State current = new WPILibMotionProfile.State(MathUtils.normalizedHeadingError(headingReference, state),0);
			profile = new WPILibMotionProfile(ControlConstants.angularConstraints, goal, current);
			timer.reset();
		}
		previousReference = reference;
	}

}
