package org.firstinspires.ftc.teamcode.Autos;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.BaseAuto.BaseAuto;
import org.firstinspires.ftc.teamcode.Command.AutoCommands.Drivetrain.BasicDrive;
import org.firstinspires.ftc.teamcode.Command.AutoCommands.Drivetrain.BasicTurn;
import org.firstinspires.ftc.teamcode.Utils.Constants;

@Autonomous
public class DriveTest extends BaseAuto {
	@RequiresApi(api = Build.VERSION_CODES.N)
	@Override
	public void addActions() {
		for (int i = 0; i < 3; i++) {
			actions.add(new BasicDrive(robot, Constants.TILE));
			actions.add(new BasicTurn(robot, Math.toRadians(90)));
			actions.add(new BasicDrive(robot, Constants.TILE));
			actions.add(new BasicDrive(robot, -Constants.TILE));
			actions.add(new BasicTurn(robot, Math.toRadians(0)));
		}

		actions.add(new BasicDrive(robot, -Constants.FIELD / 2));
	}
}
