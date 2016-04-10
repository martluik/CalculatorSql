package com.akaver.CalculatorSql;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by Martin on 18/03/16.
 */
public class CalculatorReceiver extends BroadcastReceiver {

    private CalculatorLogic calculatorLogic = new CalculatorLogic();

    private static String display;
    private static String answer;

    private static String num1;
    private static String num2;
    private static int operation;
    private static String res;
    private static String date;

    public static void setDisplay(String display) {
        CalculatorReceiver.display = display;
    }

    public static String getAnswer() {
        return answer;
    }

    public static void setAnswer(String answer) {
        CalculatorReceiver.answer = answer;
    }

    public static String getNum1() {
        return num1;
    }

    public static void setNum1(String num1) {
        CalculatorReceiver.num1 = num1;
    }

    public static String getNum2() {
        return num2;
    }

    public static void setNum2(String num2) {
        CalculatorReceiver.num2 = num2;
    }

    public static int getOperation() {
        return operation;
    }

    public static void setOperation(int operation) {
        CalculatorReceiver.operation = operation;
    }

    public static String getRes() {
        return res;
    }

    public static void setRes(String res) {
        CalculatorReceiver.res = res;
    }

    public static String getDate() {
        return date;
    }

    public static void setDate(String date) {
        CalculatorReceiver.date = date;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if(isOrderedBroadcast()) {
            Bundle extras = intent.getExtras();
            String symbol = "";
            if (extras != null) {
                symbol = extras.getString("buttonId");
                if(symbol.contains("button") || symbol.contains("dot")) {
                    calculatorLogic.number(symbol);
                } else if (symbol.contains("equ")) {
                    calculatorLogic.calculate();
                } else if (symbol.contains("clear")) {
                    calculatorLogic.clear();
                } else {
                    calculatorLogic.operation(symbol);
                }
            }

            display = calculatorLogic.getDisplay();
            answer = calculatorLogic.getAnswer();

            setResultData(display + "_" + answer);
            setResultCode(Activity.RESULT_OK);
        }
    }

    public static String getDisplay() {
        return display;
    }

    public static void Update() {
        MainActivity.UpdateDb();
    }



}
