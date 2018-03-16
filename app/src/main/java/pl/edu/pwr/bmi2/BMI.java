package pl.edu.pwr.bmi2;

import java.math.BigDecimal;

/**
 * Created by bartek on 08.03.2018.
 */

public abstract class BMI {
    double mass;
    double height;
    protected static int WRONG_ARGS = -1;

    public double getHeight() {
        return height;
    }

    public double getMass() {
        return mass;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }



    abstract boolean correctData();


    abstract double countBMI() ;



}
