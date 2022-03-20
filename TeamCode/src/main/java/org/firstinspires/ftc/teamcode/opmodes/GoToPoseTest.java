package org.firstinspires.ftc.teamcode.opmodes;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.ThermalEquilibrium.homeostasis.Utils.Vector;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.BaseOpmodes.BaseAuto;
import org.firstinspires.ftc.teamcode.Command.AutoCommands.Drivetrain.GoToPosition;
import org.firstinspires.ftc.teamcode.Controls.Controllers.GoToGoalController;

import static org.firstinspires.ftc.teamcode.Controls.Controllers.GoToGoalController.driveDirection.REVERSE;

@Autonomous
public class GoToPoseTest extends BaseAuto {

	Vector target1 = new Vector(new double[] {
			35,10,Math.toRadians(90)
	});

	Vector target2 = new Vector(new double[] {
			0,0,Math.toRadians(0)
	});

	@RequiresApi(api = Build.VERSION_CODES.N)
	@Override
	public void addActions() {
		actions.add(new GoToPosition(robot, target1));
		actions.add(new GoToPosition(robot, target2, REVERSE));
	}
}
