package org.firstinspires.ftc.teamcode.AsymmetricProfile;

public class MotionState {
    protected double x;
    protected double v;
    protected double a;

    /**
     * Construct a motion state object
     * @param x position
     * @param v velocity
     * @param a acceleration
     */
    public MotionState(double x, double v, double a) {
        this.x = x;
        this.v = v;
        this.a = a;
    }

    public double getA() {
        return a;
    }

    public double getV() {
        return v;
    }

    public double getX() {
        return x;
    }
}
