package pl.edu.pwr.bmi2;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void BMI_isCorrect_for_valid_data(){
        assertEquals(25.51,new BMI_SI(50,140).countBMI(),0.01);

    }

    @Test
    public void BMI_is_minus1_for_zeros(){
        assertEquals(-1,new BMI_SI(0,0).countBMI(),0.01);

    }

    @Test
    public void BMI_USC_isCorrect_for_valid_data(){
        assertEquals(22.96,new BMI_USC(160,70).countBMI(),0.1);

    }


}