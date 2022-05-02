package com.example.tipcalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.OnLifecycleEvent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText enteredAmount;
    private SeekBar seekBar;
    private Button calculateButton;
    private TextView totalResultTextView;
    private TextView seekBarTextView;
    private int seekBarPercentage;
    private float enteredBillFloat;
    private TextView totalBill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enteredAmount = (EditText) findViewById(R.id.billAmountId);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        calculateButton = (Button) findViewById(R.id.calculateButton);
        totalResultTextView = (TextView) findViewById(R.id.resultId);
        seekBarTextView = (TextView) findViewById(R.id.textViewSeekBar);
        totalBill = (TextView) findViewById(R.id.totalBillTextView);

        calculateButton.setOnClickListener(this);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                seekBarTextView.setText(String.valueOf(seekBar.getProgress()) + "%");

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                seekBarPercentage = seekBar.getProgress();
            }
        });
    }

    @Override
    public void onClick(View v){
        calculate();

    }

    public void calculate() {
        float result = 0.0f;

        if (!enteredAmount.getText().toString().equals("")){
            enteredBillFloat = Float.parseFloat(enteredAmount.getText().toString());
            result = enteredBillFloat * seekBarPercentage / 100;
            totalResultTextView.setText("Your tip will be " + "$" + String.valueOf(result));
            totalBill.setText("Total bill: " + String.valueOf(result + enteredBillFloat));

        }else {
            Toast.makeText(MainActivity.this, "Please enter a bill amount.", Toast.LENGTH_LONG).show();

        }
    }
}