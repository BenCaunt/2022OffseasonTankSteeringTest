package org.firstinspires.ftc.teamcode.opmodes;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.BaseOpmodes.BaseAuto;
import org.firstinspires.ftc.teamcode.Command.AutoCommands.Drivetrain.BasicDrive;
import org.firstinspires.ftc.teamcode.Command.AutoCommands.Drivetrain.BasicTurn;


@Autonomous
public class DormDriveTest extends BaseAuto {
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void addActions() {
        actions.add(new BasicDrive(robot,50));
        actions.add(new BasicTurn(robot, Math.toRadians(180)));
        actions.add(new BasicDrive(robot,50));
        actions.add(new BasicTurn(robot, Math.toRadians(0)));

    }
}
