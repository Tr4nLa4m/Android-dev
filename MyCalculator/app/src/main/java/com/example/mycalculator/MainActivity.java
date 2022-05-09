package com.example.mycalculator;

import androidx.appcompat.app.AppCompatActivity;
import org.mariuszgromada.math.mxparser.*;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText display;
    private TextView smallText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.display);
        display.setShowSoftInputOnFocus(false);

        smallText = findViewById(R.id.smallText);

        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getString(R.string.display).equals(display.getText().toString()) ){
                    display.setText("");
                }
            }
        });
    }

    public void updateText(String strToAdd){
        String oldStr  = display.getText().toString();
        int cursorPos = display.getSelectionStart(); //position of cursor in the string in the editText
        String leftStr = oldStr.substring(0, cursorPos);
        String rightStr = oldStr.substring(cursorPos);
        if(getString(R.string.display).equals(display.getText().toString())){
            display.setText(strToAdd);
        }else{
            display.setText(String.format("%s%s%s", leftStr, strToAdd,rightStr) );
        }
        display.setSelection(cursorPos + 1);
    }

    public void updateSmallText(String text){
        String displayText = display.getText().toString();
        String oldSmallText = smallText.getText().toString();
        if(oldSmallText.length() == 0) {
            smallText.setText(String.format("%s%s%s", displayText, " ",text));
        } else {
            smallText.setText(String.format("%s%s%s%s%s", oldSmallText , " ", displayText, " ",text));
        }
        display.setText("0");
    }

    public boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }

    public String solve(){
        String userExp = String.format(("%s%s"),smallText.getText().toString(), display.getText().toString());
        userExp = userExp.replaceAll("÷", "/");
        userExp = userExp.replaceAll("×", "*");
        Expression exp = new Expression(userExp);
        String res = String.valueOf(exp.calculate());
        if(res.length() >= 14)  res = res.substring(0, 13);
        int len = res.length();
        if(res.charAt(len - 1) == '0' && res.charAt(len - 2) == '.'){
            res = res.substring(0, len - 2 );
        }
        return res;
    }

    public void zeroBtn(View view){
        updateText("0");
    }

    public void oneBtn(View view){
        updateText("1");
    }

    public void twoBtn(View view){
        updateText("2");
    }

    public void threeBtn(View view){
        updateText("3");
    }

    public void fourBtn(View view){
        updateText("4");
    }

    public void fiveBtn(View view){
        updateText("5");
    }

    public void sixBtn(View view){
        updateText("6");
    }

    public void sevenBtn(View view){
        updateText("7");
    }

    public void eightBtn(View view){
        updateText("8");
    }

    public void nineBtn(View view){
        updateText("9");
    }

    public void CE_Btn(View view){
        display.setText("0");
    }

    public void C_Btn(View view){
        display.setText("0");
        smallText.setText("");
    }

    public void BS_Btn(View view){
        int cursorPos = display.getSelectionStart();
        int textLen = display.getText().length();

        if(cursorPos != 0 && textLen !=0){
            SpannableStringBuilder selection = (SpannableStringBuilder) display.getText();
            selection.replace(cursorPos - 1, cursorPos, "");
            display.setText(selection);
            display.setSelection(cursorPos - 1);
        }
    }

    public void addBtn(View view){
        updateSmallText("+");
    }

    public void subtractBtn(View view){
        updateSmallText("-");
    }

    public void multiplyBtn(View view){
        updateSmallText("×");
    }

    public void divideBtn(View view){
        updateSmallText("÷");
    }

    public void equalBtn(View view){
        display.setText(solve());
        int cursorPos = display.getSelectionStart();
        display.setSelection(cursorPos + 1);
        smallText.setText("");
    }

    public void pointBtn(View view){
        updateText(".");
    }

    public void plus_minus_Btn(View view){
        String oldTxt = display.getText().toString();
        if(oldTxt.equals("0")){
            return;
        }else if(oldTxt.length() != 0){
            if(oldTxt.charAt(0) == '-'){
                oldTxt = oldTxt.substring(1, oldTxt.length());
            }else {
                oldTxt = "-" + oldTxt;
            }
        }
        display.setText(oldTxt);
    }


}