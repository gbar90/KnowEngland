package com.rng.knowengland;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.Random;

public class LearnShiresActivity extends AppCompatActivity {

    private ImageView chosenMap;
    private TextView ende;
    private Button Buttons[] = new Button[4];
    private Button finalBtn;
    private String ShireNames[] = {
            "Bedfordshire",
            "Berkshire",
            "Buckinghamshire",
            "Cambridgeshire",
            "Cheshire",
            "City of Bristol",
            "Cornwall",
            "Cumbria",
            "Derbyshire",
            "Devon",
            "Dorset",
            "Durham",
            "East Riding of Yorkshire",
            "East Sussex",
            "Essex",
            "Gloucestershire",
            "Greater London",
            "Greater Manchester",
            "Hampshire",
            "Herefordshire",
            "Hertfordshire",
            "Isle of Wight",
            "Kent",
            "Lancashire",
            "Leicestershire",
            "Lincolnshire",
            "Merseyside",
            "Norfolk",
            "Northamptonshire",
            "Northumberland",
            "North Yorkshire",
            "Nottinghamshire",
            "Oxfordshire",
            "Rutland",
            "Shropshire",
            "Somerset",
            "South Yorkshire",
            "Staffordshire",
            "Suffolk",
            "Surrey",
            "Tyne and Wear",
            "Warwickshire",
            "West Midlands",
            "West Sussex",
            "West Yorkshire",
            "Wiltshire",
            "Worcestershire"
    };

    private static final Integer[] ShireButtons = {
            R.id.button1,
            R.id.button2,
            R.id.button3,
            R.id.button4
    };
    private static final Integer[] ShireMaps = {
            R.drawable.bedfordshire,
            R.drawable.berkshire,
            R.drawable.buckinghamshire,
            R.drawable.cambridgeshire,
            R.drawable.cheshire,
            R.drawable.cityofbristol,
            R.drawable.cornwall,
            R.drawable.cumbria,
            R.drawable.derbyshire,
            R.drawable.devon,
            R.drawable.dorset,
            R.drawable.durham,
            R.drawable.eastridingofyorkshire,
            R.drawable.eastsussex,
            R.drawable.essex,
            R.drawable.gloucestershire,
            R.drawable.greaterlondon,
            R.drawable.greatermanchester,
            R.drawable.hampshire,
            R.drawable.herefordshire,
            R.drawable.hertfordshire,
            R.drawable.isleofwight,
            R.drawable.kent,
            R.drawable.lancashire,
            R.drawable.leicestershire,
            R.drawable.lincolnshire,
            R.drawable.merseyside,
            R.drawable.norfolk,
            R.drawable.northamptonshire,
            R.drawable.northumberland,
            R.drawable.northyorkshire,
            R.drawable.nottinghamshire,
            R.drawable.oxfordshire,
            R.drawable.rutland,
            R.drawable.shropshire,
            R.drawable.somerset,
            R.drawable.southyorkshire,
            R.drawable.staffordshire,
            R.drawable.suffolk,
            R.drawable.surrey,
            R.drawable.tyneandwear,
            R.drawable.warwickshire,
            R.drawable.westmidlands,
            R.drawable.westsussex,
            R.drawable.westyorkshire,
            R.drawable.wiltshire,
            R.drawable.worcestershire
    };
    private int[] roundMemory =  new int[ShireNames.length];
    private int roundCount = 0;
    private int score = 0;
    private int total = ShireNames.length;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_shires);
        chosenMap = findViewById(R.id.chosenMap);
        ende = findViewById(R.id.ende);
        finalBtn = findViewById(R.id.finalBtn);
        finalBtn.setEnabled(false);
        setPuzzle();




    }

    public void puzzleButtonOnClick(View btnPressed){
        for(Button btn : Buttons){
                btn.setEnabled(false);
                btn.setVisibility(View.INVISIBLE);
        }
        if(btnPressed.getId() == Buttons[0].getId()){
                finalBtn.setText(getResources().getString(R.string.correct));
                finalBtn.setBackgroundColor(getResources().getColor(R.color.englandGreen));
                score++;
        }else {
                finalBtn.setText(getResources().getString(R.string.wrong));
                finalBtn.setBackgroundColor(getResources().getColor(R.color.englandRed));
        }
        finalBtn.setEnabled(true);
        finalBtn.setVisibility(View.VISIBLE);
    }

    public void restartButton(View btnPressed){
        finalBtn.setVisibility(View.INVISIBLE);
        finalBtn.setEnabled(false);
        if(roundCount == roundMemory.length){
            // Change background color
            ConstraintLayout thisLayout = findViewById(R.id.ShiresLayout);
            thisLayout.setBackgroundColor(getResources().getColor(R.color.englandWhite));
            // Show results
            float percent = (float) score/total*100;
            chosenMap.setVisibility(View.INVISIBLE);
            ende.setText(getResources().getString(R.string.ende, score, total, percent));
            ende.setVisibility(View.VISIBLE);
        }else{
            setPuzzle();
        }

    }

    public void setPuzzle(){

        // Create 4 distinct random numbers from available maps
        int[] n = new int[4];
        n[0] = new Random().nextInt(ShireMaps.length);  // Gives n such that 0 <= n < ShireMaps.length
        while(checkHistory(n[0])){                          //check if this has been asked before.
            n[0] = new Random().nextInt(ShireMaps.length);
        }
        n[1] = new Random().nextInt(ShireMaps.length);
        while(n[0]==n[1]){
            n[1] = new Random().nextInt(ShireMaps.length);
        }
        n[2] = new Random().nextInt(ShireMaps.length);
        while(n[0]==n[2] || n[1]==n[2]){
            n[2] = new Random().nextInt(ShireMaps.length);
        }
        n[3] = new Random().nextInt(ShireMaps.length);
        while(n[0]==n[3] || n[1]==n[3] || n[2]==n[3]){
            n[3] = new Random().nextInt(ShireMaps.length);
        }
        // Save all the map no. that already have been played
        roundMemory[roundCount] = n[0];
        // ---------------------------------
        // Create 4 distinct random numbers for the four buttons
        int[] btn = new int[4];
        btn[0] = new Random().nextInt(Buttons.length);  // Gives n such that 0 <= n < ShireMaps.length
        btn[1] = new Random().nextInt(Buttons.length);
        while(btn[0]==btn[1]){
            btn[1] = new Random().nextInt(Buttons.length);
        }
        btn[2] = new Random().nextInt(Buttons.length);
        while(btn[0]==btn[2] || btn[1]==btn[2]){
            btn[2] = new Random().nextInt(Buttons.length);
        }
        btn[3] = new Random().nextInt(Buttons.length);
        while(btn[0]==btn[3] || btn[1]==btn[3] || btn[2]==btn[3]){
            btn[3] = new Random().nextInt(Buttons.length);
        }
        // ---------------------------------

        chosenMap.setImageResource(ShireMaps[n[0]]);        // Sets chosenMap as random Image

        // Set text to the four buttons and enable them
        for (int i= 0; i<Buttons.length; i++){
            Buttons[i] = findViewById(ShireButtons[btn[i]]);
            Buttons[i].setText(ShireNames[n[i]]);
            Buttons[i].setEnabled(true);
            Buttons[i].setVisibility(View.VISIBLE);
        }
        // --------------------------------------
        roundCount++;           // a new round begins

    }

    private boolean checkHistory(int check){
        boolean history = false;
        for(int ii=0;ii<roundCount;ii++){
            if(check == roundMemory[ii]){
                history = true;
                break;
            }
        }
        return history;

    }
}
