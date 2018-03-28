package pl.edu.pwr.bmi2;



class BMI_SI extends BMI {
    private double mass;
    private double height;

    BMI_SI(double mass, double height) {
        this.mass = mass;
        this.height = height;
    }

    boolean correctData() {

        return (height > 100 || mass > 30 || height < 220 || mass < 350);

    }

    double countBMI() {
        if (!correctData()) {
            throw new IllegalArgumentException();

        } else {
            double result = mass / (height * height);
            bmi = result * 10000;
            return bmi;
        }

    }


}
