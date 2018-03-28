package pl.edu.pwr.bmi2;


 class BMI_USC extends BMI {



    BMI_USC(double mass, double height){
        this.mass=mass;
        this.height=height;
    }

    boolean correctData() {
        return (height > 30 || mass > 60 || height < 90 || mass < 700);
    }


    double countBMI() {
        if (!correctData()){
            throw new IllegalArgumentException();

        }else{
            double result = mass / (height * height);
            bmi =  result*703;
            return bmi;
        }

    }
}
