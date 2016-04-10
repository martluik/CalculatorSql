package com.akaver.CalculatorSql;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Martin on 07/03/16.
 */
public class CalculatorLogic {

    private static final String TAG = "CalculatorLogic";

    private static String firstNumber = "0";
    private static String secondNumber = "";
    private static String display = "0";
    private static String answer = "";
    private static int operationType = 0;
    private static int step = 1;
    private static boolean dotPressed = false;

    public CalculatorLogic() { }

//    public String getFirstNumber() { return firstNumber; }
//    public void setFirstNumber(String s) { firstNumber = s; }
//    public String getSecondNumber() { return secondNumber; }
//    public void setSecondNumber(String s) { secondNumber = s; }
    public String getDisplay() { return display; }
//    public void setDisplay(String s) { display = s; }
    public String getAnswer() { return answer; }
//    public void setAnswer(String s) { answer = s; }
//    public int getOperationType() { return operationType; }
//    public void setOperationType(int i) { operationType = i; }
//    public int getStep() { return step; }
//    public void setStep(int i) { step = i; }
//    public boolean getDotPressed() { return dotPressed; }
//    public void setDotPressed(boolean b) { dotPressed = b; }

    public void number(String id) {
        if(step == 3) {
            clear();
        }

        String numberString = "";
        switch (id.charAt(id.length()-1)) {
            case ('0'):
                if(step == 1) {
                    if(firstNumber.equals("0")) {
                        numberString = "";
                    }
                    else {
                        numberString = "0";
                    }
                } else if (step == 2) {
                    if(secondNumber.equals("") || !secondNumber.equals("0")) {
                        numberString = "0";
                    }
                }
                break;
            case ('1'):
                numberString = "1";
                break;
            case ('2'):
                numberString = "2";
                break;
            case ('3'):
                numberString = "3";
                break;
            case ('4'):
                numberString = "4";
                break;
            case ('5'):
                numberString = "5";
                break;
            case ('6'):
                numberString = "6";
                break;
            case ('7'):
                numberString = "7";
                break;
            case ('8'):
                numberString = "8";
                break;
            case ('9'):
                numberString = "9";
                break;
            case ('d'):
                if(!dotPressed) {
                    dotPressed = true;
                    if(step == 1 && firstNumber == "0" || step == 2 && secondNumber == "") {
                        numberString = "0.";
                    } else {
                        numberString = ".";
                    }
                }
                break;
        }
        switch (step) {
            case (1):
                firstNumber += numberString;
                break;
            case (2):
                secondNumber += numberString;
                break;
        }

        if(display == "0") {
            display = numberString;
        } else {
            display += numberString;
        }
    }

    public void operation(String s) {
        if(firstNumber.length() == 0)
            return;

        if(step == 3) {
            firstNumber = answer;
            secondNumber = "";
        }

        String operationString = "";
        step = 2;
        dotPressed = false;

        if(operationType != 0) {
            display = firstNumber;
        }

        switch (s) {
            case ("com.example.ml.calculatorii:id/mul"):
                operationString = " * ";
                operationType = 1;
                break;
            case ("com.example.ml.calculatorii:id/div"):
                operationString = " / ";
                operationType = 2;
                break;
            case ("com.example.ml.calculatorii:id/plu"):
                operationString = " + ";
                operationType = 3;
                break;
            case ("com.example.ml.calculatorii:id/min"):
                operationString = " - ";
                operationType = 4;
                break;
        }

        if(firstNumber.charAt(firstNumber.length() - 1) == '.') {
            String newNumber = firstNumber.substring(0, firstNumber.length()-1);
            firstNumber = newNumber;
            display = newNumber;
        }
        display += operationString;
    }

    public void calculate() {
        if(firstNumber.length() == 0 || secondNumber.length() == 0)
            return;

        step = 3;
        Double d = 0d;

        switch (operationType) {
            case(1):
                d = Double.valueOf(firstNumber) * Double.valueOf(secondNumber);
                break;
            case (2):
                if(Float.parseFloat(secondNumber) != 0) {
                    d = Double.valueOf(firstNumber) / Double.valueOf(secondNumber);
                } else {
                    d = 0d;
                }
                break;
            case (3):
                d = Double.valueOf(firstNumber) + Double.valueOf(secondNumber);
                break;
            case (4):
                d = Double.valueOf(firstNumber) - Double.valueOf(secondNumber);
                break;
        }

        String a = d.toString();

        if(a.charAt(a.length()-1) == '0' &&
                a.charAt(a.length()-2) == '.') {
            answer = d.toString().substring(0, d.toString().length()-2);
            //display = d.toString().substring(0, d.toString().length()-2);
        } else {
            answer = d.toString();
            //display = d.toString();
        }
        CalculatorReceiver.setNum1(firstNumber);
        CalculatorReceiver.setNum2(secondNumber);
        CalculatorReceiver.setOperation(operationType);
        CalculatorReceiver.setRes(answer);
        CalculatorReceiver.setDate(getDate());
        CalculatorReceiver.Update();
    }

    String getOperationFromType(int operationType) {
        switch (operationType) {
            case (1):
                return "*";
            case (2):
                return "/";
            case (3):
                return "+";
            case (4):
                return "-";
            default:
                return "NULL";
        }
    }

    private String getDate() {
        Date date = Calendar.getInstance().getTime();
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.format(date);
    }

    public void clear() {
        firstNumber = "0";
        secondNumber = "";
        operationType = 0;
        display = "0";
        answer = "";
        dotPressed = false;
        step = 1;
    }
}
