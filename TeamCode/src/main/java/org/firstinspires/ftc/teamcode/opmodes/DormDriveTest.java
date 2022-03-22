package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.BaseOpmodes.BaseAuto;
import org.firstinspires.ftc.teamcode.Command.AutoCommands.Drivetrain.BasicDrive;


@Autonomous
public class DormDriveTest extends BaseAuto {
    @Override
    public void addActions() {
        actions.add(new BasicDrive(robot,20));
        actions.add(new BasicDrive(robot,-20));
    }
}
