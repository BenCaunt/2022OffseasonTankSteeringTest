package org.firstinspires.ftc.teamcode.Utils;

import com.ThermalEquilibrium.homeostasis.Utils.Vector;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;

import org.firstinspires.ftc.teamcode.Geometry.Pose2d;
import org.firstinspires.ftc.teamcode.Geometry.Rotation2d;
import org.firstinspires.ftc.teamcode.Geometry.Vector2d;

public class AdditonalUtils {

	/**
	 * rotate a 2 item vector by an angle radians
	 * @param v two item vector, x:0, y:1
	 * @param angleRadians angle in radians
	 */
	public static void rotate(Vector v, double angleRadians) {
		double cosA = Math.cos(angleRadians);
		double sinA = Math.sin(angleRadians);

		double x = v.get(0) * cosA - v.get(1) * sinA;
		double y = v.get(0) * sinA - v.get(1) * cosA;

		v.set(x,0);
		v.set(y,1);
	}

	public static double calculateDistance(Vector v1, Vector v2) {
		double x1 = v1.get(0);
		double x2 = v2.get(0);
		double y1 = v1.get(1);
		double y2 = v2.get(1);
		return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
	}


	public static void drawRobot(Vector position, TelemetryPacket packet) {

		Pose2d pose = new Pose2d(position.get(0),position.get(1),new Rotation2d(position.get(2)));
		double ROBOT_RADIUS = 8;
		Vector2d v = new Vector2d(Math.cos(position.get(2)), Math.sin(position.get(2))).times(ROBOT_RADIUS);

		double x1 = pose.getX() + v.getX() / 2;
		double y1 = pose.getY() + v.getY() / 2;
		double x2 = pose.getX() + v.getX();
		double y2 = pose.getY() + v.getY();


		packet.fieldOverlay()
				.setStroke("black")
				.strokeCircle(pose.getX(), pose.getY(), ROBOT_RADIUS)
				.strokeLine(x1, y1, x2, y2);


	}
}
