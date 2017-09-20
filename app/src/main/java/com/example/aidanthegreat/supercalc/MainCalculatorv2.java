package com.example.aidanthegreat.supercalc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class MainCalculatorv2 extends AppCompatActivity {

    private static TextView numbersView;

    //Array that stores the contents of the TextView
    private static ArrayList<String> calculationContents = new ArrayList<String>();

    private static boolean quickDelete = false;

    //Method to build a string from an ArrayList
    private static String buildString(ArrayList<String> a){
        String string = "";

        for(String s: a){
            string += s;
            string += "|";
        }

        return string;
    }

    //Method to add text to the view
    private void addTextInView(String addedString){
        quickDelete = false;
        if(calculationContents.size() != 0 && !calculationContents.get(0).equals("Enter numbers here")){
            if(!isOperator(addedString) && !isOperator(calculationContents.get(calcContentLast()))){
                addedString = calculationContents.get(calcContentLast()) + addedString;
                calculationContents.set(calcContentLast(), addedString);
            }else{
                calculationContents.add(addedString);
            }
        }else if(calculationContents.size() != 0){
            calculationContents.set(0, addedString);
        }else{
            calculationContents.add(addedString);
        }

        updateTextView();
     }

    //Method to delete an item from the view
    private void backspace(){
        if(calculationContents.size() != 0){
            if(!isOperator(calculationContents.get(calcContentLast())) && calculationContents.get(calcContentLast()).length() > 0 && !quickDelete){
                String s = calculationContents.get(calcContentLast());
                calculationContents.set(calcContentLast(), s.substring(0, s.length() - 1));
            }
            else{
                calculationContents.remove(calcContentLast());
            }
        }
        else{
            calculationContents.add(getString(R.string.defaultMessage));
            quickDelete = true;
        }
        updateTextView();
    }

    //Method to update the text being displayed in the TextView, in order to sync it with the contents of
    //calculationContents
    private void updateTextView(){
        numbersView.setText(Html.fromHtml(buildString(calculationContents)));
        System.out.println(buildString(calculationContents));
    }

    //-------------------Number button click methods----------------------
    public void oneClick(View v){
        addTextInView(getString(R.string.one));
    }
    public void twoClick(View v){
        addTextInView(getString(R.string.two));
    }
    public void threeClick(View v){
        addTextInView(getString(R.string.three));
    }
    public void fourClick(View v){
        addTextInView(getString(R.string.four));
    }
    public void fiveClick(View v){
        addTextInView(getString(R.string.five));
    }
    public void sixClick(View v){
        addTextInView(getString(R.string.six));
    }
    public void sevenClick(View v){
        addTextInView(getString(R.string.seven));
    }
    public void eightClick(View v){
        addTextInView(getString(R.string.eight));
    }
    public void nineClick(View v){
        addTextInView(getString(R.string.nine));
    }
    public void zeroClick(View v){
        addTextInView(getString(R.string.zero));
    }

    //-----------------Operations and other items click methods----------
    public void addClick(View v){
        addTextInView(getString(R.string.add));
    }
    public void subClick(View v){
        addTextInView(getString(R.string.sub));
    }
    public void multClick(View v){
        addTextInView(getString(R.string.mul));
    }
    public void divClick(View v){
        addTextInView(getString(R.string.div));
    }

    public void sinClick(View v){
        addTextInView(getString(R.string.sin));
    }
    public void cosClick(View v){
        addTextInView(getString(R.string.cos));
    }
    public void tanClick(View v){
        addTextInView(getString(R.string.tan));
    }

    public void leftBracketClick(View v){
        addTextInView(getString(R.string.left));
    }
    public void rightBracketClick(View v){
        addTextInView(getString(R.string.right));
    }

    public void delClick(View v){
        backspace();
    }

    //-----------Convenience methods------------
    private int calcContentLast(){
        return calculationContents.size() -1;
    }
    //Determine if a string is an operator or a number
    //TODO Add determination for if a string is a trig function
    private boolean isOperator(String value){
        if(value.equals("+") || value.equals("/") || value.equals("-") || value.equals("*")
                || value.equals("sin") || value.equals("cos") || value.equals("tan")
                || value.equals("(") || value.equals(")")){
            return true;
        }else{
            return false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_calculatorv2);

        //Programmatic representation of the TextView
        numbersView = (TextView)(findViewById(R.id.numbersView));

        //Set the text view to have the default message
        calculationContents.add(getString(R.string.defaultMessage));

        updateTextView();
    }

}