package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView resultTextView;
    private StringBuilder numberBuilder;
    private double operand1, operand2;
    private String operator;
    private boolean isResultDisplayed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTextView = findViewById(R.id.resultTextView);
        numberBuilder = new StringBuilder();
        isResultDisplayed = false;

        ButtonClickListener buttonClickListener = new ButtonClickListener();

        // Set click listeners for number buttons
        for (int i = 0; i <= 9; i++) {
            int buttonId = getResources().getIdentifier("button" + i, "id", getPackageName());
            Button button = findViewById(buttonId);
            button.setOnClickListener(buttonClickListener);
        }

        // Set click listeners for operator buttons
        int[] operatorButtonIds = {R.id.buttonAdd, R.id.buttonSubtract, R.id.buttonMultiply, R.id.buttonDivide};
        for (int buttonId : operatorButtonIds) {
            Button button = findViewById(buttonId);
            button.setOnClickListener(buttonClickListener);
        }

        // Set click listener for clear button
        Button buttonClear = findViewById(R.id.buttonClear);
        buttonClear.setOnClickListener(buttonClickListener);

        // Set click listener for enter button
        Button buttonEnter = findViewById(R.id.buttonEnter);
        buttonEnter.setOnClickListener(buttonClickListener);
    }

    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Button button = (Button) v;
            String buttonText = button.getText().toString();

            if (isResultDisplayed) {
                clearResult();
            }

            if (buttonText.matches("[0-9]")) {
                numberBuilder.append(buttonText);
                resultTextView.append(buttonText);
            } else if (buttonText.equals("C")) {
                clearResult();
            } else if (buttonText.equals("=")) {
                calculateResult();
            } else {
                operator = buttonText;
                operand1 = Double.parseDouble(numberBuilder.toString());
                resultTextView.append(" " + operator + " ");
                numberBuilder.setLength(0);
            }
        }
    }

    private void calculateResult() {
        operand2 = Double.parseDouble(numberBuilder.toString());
        double result = 0;

        switch (operator) {
            case "+":
                result = operand1 + operand2;
                break;
            case "-":
                result = operand1 - operand2;
                break;
            case "*":
                result = operand1 * operand2;
                break;
            case "/":
                result = operand1 / operand2;
                break;
        }

        numberBuilder.setLength(0);
        numberBuilder.append(result);
        resultTextView.setText(numberBuilder.toString());
        isResultDisplayed = true;
    }

    private void clearResult() {
        numberBuilder.setLength(0);
        resultTextView.setText("");
        isResultDisplayed = false;
    }
}