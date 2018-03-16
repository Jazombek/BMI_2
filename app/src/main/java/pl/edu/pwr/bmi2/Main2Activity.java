package pl.edu.pwr.bmi2;


import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {





    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        Toolbar myToolbar = findViewById(R.id.my_toolbar2);
        setSupportActionBar(myToolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);

            actionBar.setDisplayShowCustomEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }

        if(getIntent().hasExtra("error")){
            TextView resultTextView =  findViewById(R.id.resTextView);
            resultTextView.setText(getIntent().getExtras().get("error").toString());

        }


        else if(getIntent().hasExtra("result")){
            TextView resultTextView =  findViewById(R.id.resTextView);
            double res = Double.parseDouble(getIntent().getExtras().get("result").toString());
            resultTextView.setText(getIntent().getExtras().get("result").toString());
            if(res<19)
                resultTextView.setTextColor(Color.rgb(0,0,200));
            else if(res<25)
                resultTextView.setTextColor((Color.rgb(0,200,0)));
            else if(res<30)
                resultTextView.setTextColor(Color.rgb(200,200,0));
            else if(res<40)
                resultTextView.setTextColor(Color.rgb(200,150,0));
            else
                resultTextView.setTextColor(Color.rgb(200,0,0));




        }
    }
}
