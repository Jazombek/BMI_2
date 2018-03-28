package pl.edu.pwr.bmi2;



public abstract class BMI {
    private static final int FIRSTTIER = 19;
    private static final int SECONDTIER = 25;
    private static final int THIRDTIER = 30;
    private static final int FOURTHTIER = 40;
    double mass;
    double height;
    double bmi=0;



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

    int countColor(double val){

        if(val<SECONDTIER){
            return val<FIRSTTIER?1:2;
        }else if(val>THIRDTIER)
            return val>FOURTHTIER?5:4;
        else return 3;
    }

    abstract boolean correctData();


    abstract double countBMI() ;



}
