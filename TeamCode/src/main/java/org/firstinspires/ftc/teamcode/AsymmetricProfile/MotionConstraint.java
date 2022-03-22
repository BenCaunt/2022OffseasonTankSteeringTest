package org.firstinspires.ftc.teamcode.AsymmetricProfile;

public class MotionConstraint {

    public double max_acceleration;
    public double max_deceleration;
    public double max_velocity;


    public MotionConstraint(double max_acceleration, double max_deceleration, double max_velocity) {
        this.max_acceleration = max_acceleration;
        this.max_deceleration = max_deceleration;
        this.max_velocity = max_velocity;
    }


}
