package com.bawp.charismaticprimaryschool;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bawp.charismaticprimaryschool.utill.Prefs;


public class CalculatorActivity extends AppCompatActivity{
    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0,
            btnAdd, btnMinus, btnDivide, btnMultiply,
            btnEquals, btnClear, btnC, btnDot, btnModulus;
    TextView textView;
    RelativeLayout calculator_layout;
    ViewGroup root;

    double firstNumber = 0;
    double secondNumber = 0;
    String operation = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calulator);

        textView = findViewById(R.id.result_tv);
        btn1 = findViewById(R.id.button_1);
        btn2 = findViewById(R.id.button_2);
        btn3 = findViewById(R.id.button_3);
        btn4 = findViewById(R.id.button_4);
        btn5 = findViewById(R.id.button_5);
        btn6 = findViewById(R.id.button_6);
        btn7 = findViewById(R.id.button_7);
        btn8 = findViewById(R.id.button_8);
        btn9 = findViewById(R.id.button_9);
        btn0 = findViewById(R.id.button_0);
        btnAdd = findViewById(R.id.button_plus);
        btnMinus = findViewById(R.id.button_minus);
        btnMultiply = findViewById(R.id.button_multiply);
        btnDivide = findViewById(R.id.button_divide);
        btnEquals = findViewById(R.id.button_equals);
        btnClear = findViewById(R.id.button_ac);
        btnDot = findViewById(R.id.button_dot);
        btnModulus = findViewById(R.id.button_modulus);
        calculator_layout = findViewById(R.id.calculator_layout);
        root = findViewById(android.R.id.content);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        int colorId = prefs.getInt("backgroundColor", R.color.white);
        root.setBackgroundResource(colorId);


        btn1.setOnClickListener(view -> {
            String value = btn1.getText().toString();
            appendToTextView(value);
        });

        btn2.setOnClickListener(view -> {
            String value = btn2.getText().toString();
            appendToTextView(value);
        });

        btn3.setOnClickListener(view -> {
            String value = btn3.getText().toString();
            appendToTextView(value);
        });

        btn4.setOnClickListener(view -> {
            String value = btn4.getText().toString();
            appendToTextView(value);
        });

        btn5.setOnClickListener(view -> {
            String value = btn5.getText().toString();
            appendToTextView(value);
        });

        btn6.setOnClickListener(view -> {
            String value = btn6.getText().toString();
            appendToTextView(value);
        });

        btn7.setOnClickListener(view -> {
            String value = btn7.getText().toString();
            appendToTextView(value);
        });

        btn8.setOnClickListener(view -> {
            String value = btn8.getText().toString();
            appendToTextView(value);
        });

        btn9.setOnClickListener(view -> {
            String value = btn9.getText().toString();
            appendToTextView(value);
        });

        btn0.setOnClickListener(view -> {
            String value = btn0.getText().toString();
            appendToTextView(value);
        });

        btnDot.setOnClickListener(view -> {
            String value = btnDot.getText().toString();
            appendToTextView(value);
        });



        btnAdd.setOnClickListener(view -> {
            String value = textView.getText().toString();
            if (!value.equals("")) {
                firstNumber = Double.parseDouble(value);
                operation = "addition";
                textView.setText("");
                textView.setHint("+");
            }
        });

        btnMinus.setOnClickListener(view -> {
            String value = textView.getText().toString();
            if (!value.equals("")) {
                firstNumber = Double.parseDouble(value);
                operation = "subtraction";
                textView.setText("");
                textView.setHint("-");
            }
        });

        btnMultiply.setOnClickListener(view -> {
            String value = textView.getText().toString();
            if (!value.equals("")) {
                firstNumber = Double.parseDouble(value);
                operation = "multiplication";
                textView.setText("");
                textView.setHint("*");
            }
        });

        btnDivide.setOnClickListener(view -> {
            String value = textView.getText().toString();
            if (!value.equals("")) {
                firstNumber = Double.parseDouble(value);
                operation = "division";
                textView.setText("");
                textView.setHint("/");
            }
        });

        btnModulus.setOnClickListener(view -> {
            String value = textView.getText().toString();
            if (!value.equals("")) {
                firstNumber = Double.parseDouble(value);
                operation = "modulus";
                textView.setText("");
                textView.setHint("%");
            }
        });

        btnEquals.setOnClickListener(view -> {
            String value = textView.getText().toString();
            if (!value.equals("")) {
                secondNumber = Double.parseDouble(value);
                if (operation.equals("addition")) {
                    textView.setText(Double.toString(firstNumber + secondNumber));
                } else if (operation.equals("subtraction")) {
                    textView.setText(Double.toString(firstNumber - secondNumber));
                } else if (operation.equals("multiplication")) {
                    textView.setText(Double.toString(firstNumber * secondNumber));
                } else if (operation.equals("division")) {
                    if (secondNumber == 0) {
                        textView.setText("Error");
                    } else {
                        textView.setText(Double.toString(firstNumber / secondNumber));
                    }
                } else if(operation.equals("modulus")){
                    textView.setText(Double.toString(firstNumber % secondNumber));
                }
            }
        });

        btnClear.setOnClickListener(view -> {
            textView.setText("");
            firstNumber = 0;
            secondNumber = 0;
            operation = "";
            textView.setHint("0");
        });

    }


    private void appendToTextView(String value) {
        String currentValue = textView.getText().toString();
        textView.setText(currentValue + value);
    }

}
