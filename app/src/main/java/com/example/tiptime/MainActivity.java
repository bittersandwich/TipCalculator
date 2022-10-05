package com.example.tiptime;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.tiptime.databinding.ActivityMainBinding;

import java.text.NumberFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());

        View view = binding.getRoot();
        setContentView(view);

        binding.calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateTip();
            }
        });
    }

    private void calculateTip(){

        String stringInTextField = binding.costOfService.getText().toString();
        if(stringInTextField.isEmpty()){
            stringInTextField = "0";
        }

        double cost = parseStringToDouble(stringInTextField);

        double tipPercentage;
        if (binding.tipOptions.getCheckedRadioButtonId() == R.id.amazing_services) {
            tipPercentage = 0.20;
        } else if (binding.tipOptions.getCheckedRadioButtonId() == R.id.good_services) {
            tipPercentage = 0.18;
        } else {
            tipPercentage = 0.15;
        }

        double tip = tipPercentage * cost;

        if(binding.roundUpSwitch.isChecked()){
            tip = Math.ceil(tip);
        }

        displayTip(tip);
    }


    private void displayTip(double tip){
        NumberFormat formattedTip = NumberFormat.getCurrencyInstance(Locale.US);
        String hasil = getString(R.string.tip_amount, formattedTip.format(tip));
        binding.tipResult.setText(hasil);
    }

    private void displayTip2(double tip){
        NumberFormat formattedTip = NumberFormat.getCurrencyInstance(Locale.US);
        String hasil = getString(R.string.tip_amount, formattedTip.format(tip));
        binding.tipResult.setText(hasil);
    }

    private static double parseStringToDouble(String value) {
        return value == null || value.isEmpty() ? 0.0 : Double.parseDouble(value);
    }
}