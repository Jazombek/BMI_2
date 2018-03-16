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


import java.math.BigDecimal;


public class MainActivity extends AppCompatActivity {

    //private Button countBtn;
    private EditText massEditText;
    private EditText heightEditText;

    private Boolean unitsSI = false;
    private Switch unitSwitch;
    private static int WRONG_ARGS = -1;

    //private


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.author:
                startActivity(new Intent(this, AuthorScreen.class));
                return true;
            case R.id.save:
                SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString(getString(R.string.height), heightEditText.getText().toString());
                editor.putString(getString(R.string.mass), massEditText.getText().toString());
                editor.putBoolean(getString(R.string.units),unitsSI);
                editor.apply();
                return true;
            default:return false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar =  findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        ActionBar actionBar = getSupportActionBar();
        //actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);


        Button countBtn =  findViewById(R.id.countBtn);
        massEditText = findViewById(R.id.massEditText);
        heightEditText = findViewById(R.id.heightEditText);

        unitSwitch = findViewById(R.id.unitSwitch);



        unitSwitch.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        unitsSI=isChecked;
                        if(unitsSI){
                        massEditText.setHint(R.string.pound);
                        heightEditText.setHint(R.string.inch);}
                        else{
                            massEditText.setHint(R.string.kilogram);
                            heightEditText.setHint(R.string.centimeter);
                        }
                        massEditText.setText("");
                        heightEditText.setText("");
                    }
                });

        countBtn.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        Intent resIntent = new Intent(getApplicationContext(), Main2Activity.class);
                        double height=0;
                        double mass=0;
                        try {
                            height = Long.parseLong("0" + heightEditText.getText().toString());
                            mass = Long.parseLong("0" + massEditText.getText().toString());
                        }catch (Exception e){
                            resIntent.putExtra("error",getString(R.string.wrongArgs));
                            startActivity(resIntent);
                        }
                        //BMI count;
                        double res;
                        if(unitsSI){BMI_USC count = new BMI_USC(mass, height);
                            res = count.countBMI();
                             }
                            else{
                                BMI_SI count = new BMI_SI(mass, height);
                            res = count.countBMI();
                            }


                        if(res==WRONG_ARGS){
                            resIntent.putExtra("error",getString(R.string.wrongArgs));
                        }else {
                            //heightEditText.setText(res+"");

                            BigDecimal res2 = new BigDecimal(String.valueOf(res)).setScale(2, BigDecimal.ROUND_FLOOR);


                            resIntent.putExtra("result",res2);
                        }
                            startActivity(resIntent);

                    }
               });
    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        unitsSI=sharedPref.getBoolean(getString(R.string.units),false);
        unitSwitch.setChecked(unitsSI);
        massEditText.setText(sharedPref.getString(getString(R.string.mass), ""));
        heightEditText.setText(sharedPref.getString(getString(R.string.height), ""));


    }
}
