package com.example.app2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Declare all the variables
    EditText edWeight, edFeet, edInches, edTemp;
    TextView tvDisplay, appTitle, edTitle;
    LinearLayout edTextGrp, bmiBtnLayout, temBtnLayout;
    AppCompatButton calculateBtn, resetBtn, tempBtn, bmiBtn, celsiusBtn, fahrenheitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        find view by id
        edWeight = findViewById(R.id.ed_weight);
        edFeet = findViewById(R.id.ed_feet);
        edInches = findViewById(R.id.ed_inches);
        edTemp = findViewById(R.id.ed_temp);
        tempBtn = findViewById(R.id.temp_btn);
        bmiBtn = findViewById(R.id.bmi_btn);
        celsiusBtn = findViewById(R.id.celsius_btn);
        fahrenheitBtn = findViewById(R.id.fahrenheit_btn);
        edTextGrp = findViewById(R.id.ed_text_grp);
        bmiBtnLayout = findViewById(R.id.bmi_btn_layout);
        temBtnLayout = findViewById(R.id.temp_btn_layout);
        tvDisplay = findViewById(R.id.tvDisplay);
        appTitle = findViewById(R.id.app_title);
        edTitle = findViewById(R.id.ed_title);
        calculateBtn = findViewById(R.id.calculate_btn);
        resetBtn = findViewById(R.id.reset_btn);


//        Calculate the BMI value
        calculateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bmiCalculation();

            }
        });

        
//        Reset button
        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                reset();

            }
        });


//        Converting BMI UI to Temp UI
        tempBtn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                edTextGrp.setVisibility(View.GONE);
                bmiBtnLayout.setVisibility(View.GONE);
                temBtnLayout.setVisibility(View.VISIBLE);

                appTitle.setText("Temperature");
                edTitle.setText("Enter a number");

                edWeight.setVisibility(View.GONE);
                edTemp.setVisibility(View.VISIBLE);

                bmiBtn.setVisibility(View.VISIBLE);
                tempBtn.setVisibility(View.GONE);


                return false;

            }
        });


//        Celsius Calculator
        celsiusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                calculateCelsius();

            }
        });

//        Fahrenheit calculator
        fahrenheitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                calculateFahrenheit();

            }
        });

//        Reset button for the Temp UI
        bmiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();

                Toast.makeText(MainActivity.this, "Long press for activate temperature calculation features", Toast.LENGTH_SHORT).show();
            }
        });


//        Temp to BMI UI converter
        bmiBtn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                edTextGrp.setVisibility(View.VISIBLE);
                bmiBtnLayout.setVisibility(View.VISIBLE);
                temBtnLayout.setVisibility(View.GONE);

                appTitle.setText("BMI Calculator");
                edTitle.setText("Weight");

                edWeight.setVisibility(View.VISIBLE);
                edTemp.setVisibility(View.GONE);

                bmiBtn.setVisibility(View.GONE);
                tempBtn.setVisibility(View.VISIBLE);

                return false;
            }
        });
    }


//    --------------------------------- All the method built here ----------------------------------

//    Fahrenheit calculation method
    private void calculateFahrenheit() {

        String temperatureValue = edTemp.getText().toString();

        if (!temperatureValue.equals("")){

            Float c = Float.parseFloat(temperatureValue);
            Float fahrenheit = ((c*9)/5) + 32;

            tvDisplay.setText("Temperature: "+fahrenheit+"F");
        }
    }


//    Celsius calculation method
    private void calculateCelsius() {

        String temperatureValue = edTemp.getText().toString();

        if (!temperatureValue.equals("")){

            Float f = Float.parseFloat(temperatureValue);
            Float celsius = ((f - 32)*5)/9;

            tvDisplay.setText("Temperature: "+celsius+"C");
        }
    }

//    Reset method
    private void reset() {

        tvDisplay.setText("");
        edWeight.setText("");
        edFeet.setText("");
        edInches.setText("");
        edTemp.setText("");

    }

//    BMI calculation method
    private void bmiCalculation() {

        String weight = edWeight.getText().toString();
        String feet = edFeet.getText().toString();
        String inches = edInches.getText().toString();

        if (!weight.equals("") && !feet.equals("") && !inches.equals("")){

            float getWeight = Float.parseFloat(weight);
            float getFeet = Float.parseFloat(feet);
            float getInches = Float.parseFloat(inches);

            float height = (float) (getFeet*0.3048 + getInches*0.0254);

            float bmiResult = getWeight / (height * height);


            if (bmiResult <= 18.5){

                tvDisplay.setText("Your BMI score is: " + bmiResult + "\nYou are underweight.");

            } else if (bmiResult >= 18.5 &&  bmiResult <= 24.9) {

                tvDisplay.setText("Your BMI score is: " + bmiResult + "\nYour weight is normal");

            } else if (bmiResult >= 25 &&  bmiResult <= 29.9) {

                tvDisplay.setText("Your BMI score is: " + bmiResult + "\nYour are overweight");

            }else {

                tvDisplay.setText("Your BMI score is: " + bmiResult + "\nYou are obese");

            }

        }
        else {

            Toast.makeText(MainActivity.this, "Please input the wanted details", Toast.LENGTH_LONG).show();
        }
    }
}