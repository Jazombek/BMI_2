package pl.edu.pwr.bmi2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;





public class MainActivity extends AppCompatActivity {

    private Button countBtn;
    private EditText massEditText;
    private EditText heightEditText;

    private Boolean unitsSI = false;
    private Switch unitSwitch;
    private static final String MASS = "mass";
    private static final String HEIGHT = "height";
    private static final String UNITS = "Units";

    //private


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.author:
                startActivity(new Intent(this, AuthorScreen.class));
                return true;
            case R.id.save:
                SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString(HEIGHT, heightEditText.getText().toString());
                editor.putString(MASS, massEditText.getText().toString());
                editor.putBoolean(UNITS, unitsSI);
                editor.apply();
                return true;
            default:
                return false;
        }
    }

    protected void makeToolbar() {
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
        actionBar.setDisplayShowTitleEnabled(false);
    }

    protected void assignItems() {
        countBtn = findViewById(R.id.countBtn);
        massEditText = findViewById(R.id.massEditText);
        heightEditText = findViewById(R.id.heightEditText);

        unitSwitch = findViewById(R.id.unitSwitch);
    }

    private BMI makeBMI() {

        BMI count;
        Long height = 0L;
        Long mass = 0L;

        try {
            height = Long.parseLong(heightEditText.getText().toString());
            mass = Long.parseLong(massEditText.getText().toString());

        } finally {


            if (unitsSI) {
                count = new BMI_USC(mass, height);

            } else {
                count = new BMI_SI(mass, height);

            }

        }
        return count;


    }



    protected void setListeners() {
        unitSwitch.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        unitsSI = isChecked;
                        if (unitsSI) {
                            massEditText.setHint(R.string.pound);
                            heightEditText.setHint(R.string.inch);
                        } else {
                            massEditText.setHint(R.string.kilogram);
                            heightEditText.setHint(R.string.centimeter);
                        }
                        massEditText.setText("");
                        heightEditText.setText("");
                    }
                });

        countBtn.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {

                        BMI count;
                        double res;
                        try {
                            count = makeBMI();
                            res = count.countBMI();

                            Main2Activity.start(getApplicationContext(), res, count.countColor(res));
                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), R.string.wrongArgs,
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        makeToolbar();
        assignItems();
        setListeners();

    }


    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        unitsSI = sharedPref.getBoolean(UNITS, false);
        unitSwitch.setChecked(unitsSI);
        massEditText.setText(sharedPref.getString(MASS, ""));
        heightEditText.setText(sharedPref.getString(HEIGHT, ""));


    }
}
