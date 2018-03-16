package pl.edu.pwr.bmi2;

import android.widget.TextView;

/**
 * Created by bartek on 05.03.2018.
 */



public class BMI_SI extends BMI {
    private double mass;
    private double height;

    public BMI_SI (double mass, double height){
        this.mass=mass;
        this.height=height;
    }
    boolean correctData() {

        if (height<=100 || mass <=30 || height > 220 || mass > 350){
            return false;
        }
        else return true;
    }

    double countBMI() {
        if (!correctData()){
            //throw IllegalArgumentException
            return WRONG_ARGS;
        }else{
            double result = mass / (height * height);
            return result*10000;
        }

    }



}
