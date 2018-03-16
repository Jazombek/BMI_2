package pl.edu.pwr.bmi2;

//import BMI;

/**
 * Created by bartek on 14.03.2018.
 */

public class BMI_USC extends BMI {



    BMI_USC(double mass, double height){
        this.mass=mass;
        this.height=height;
    }

    boolean correctData() {
        if (height<=30 || mass <=60 || height > 90 || mass > 700){
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
            return result*703;
        }

    }
}
