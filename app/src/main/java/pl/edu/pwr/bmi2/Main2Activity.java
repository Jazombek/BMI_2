package pl.edu.pwr.bmi2;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.Locale;

public class Main2Activity extends AppCompatActivity {
    private static String BMI = "BMI";
    private static String COL = "col";


    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    public static void start(Context context, Double bmi, int color) {
        Intent starter = new Intent(context, Main2Activity.class);
        starter.putExtra(BMI, bmi);

        starter.putExtra(COL, color);
        context.startActivity(starter);
    }

    private void setToolbar() {
        Toolbar myToolbar = findViewById(R.id.my_toolbar2);
        setSupportActionBar(myToolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);

            actionBar.setDisplayShowCustomEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        setToolbar();
        setBMI();


    }

    protected void setBMI() {

        TextView resultTextView = findViewById(R.id.resTextView);
        try {
            Double res = getIntent().getDoubleExtra(BMI,0);
            resultTextView.setText(String.format(Locale.ENGLISH,"%4.2f",res));
        }catch(Exception e){
            resultTextView.setText(R.string.wrongArgs);//never should happen tbh
        }

        switch (getIntent().getIntExtra(COL, 2)) {
            case (1):
                resultTextView.setTextColor(Color.rgb(0, 0, 200));
                break;
            case (2):
                resultTextView.setTextColor((Color.rgb(0, 200, 0)));
                break;
            case (3):
                resultTextView.setTextColor(Color.rgb(200, 200, 0));
                break;
            case (4):
                resultTextView.setTextColor(Color.rgb(200, 150, 0));
                break;
            case (5):
                resultTextView.setTextColor(Color.rgb(200, 0, 0));
                break;
        }
    }
}
