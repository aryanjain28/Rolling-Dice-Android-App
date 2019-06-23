package com.example.diceroll;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    static int number = 5;
    static int rollNumber = 6, counter=0;
    ImageView d1,d2,d3,d4,d5,d6;
    Thread thread;
    private SoundPlayer sound;

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            d1.setVisibility(d1.INVISIBLE);
            d2.setVisibility(d2.INVISIBLE);
            d3.setVisibility(d3.INVISIBLE);
            d4.setVisibility(d4.INVISIBLE);
            d5.setVisibility(d5.INVISIBLE);
            d6.setVisibility(d6.INVISIBLE);
            switch (rollNumber){
                case 1: d1.setVisibility(d1.VISIBLE);
                    break;
                case 2: d2.setVisibility(d2.VISIBLE);
                    break;
                case 3: d3.setVisibility(d3.VISIBLE);
                    break;
                case 4: d4.setVisibility(d4.VISIBLE);
                    break;
                case 5: d5.setVisibility(d5.VISIBLE);
                    break;
                case 6: d6.setVisibility(d6.VISIBLE);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        d1 = findViewById(R.id.diceOne);
        d2 = findViewById(R.id.diceTwo);
        d3 = findViewById(R.id.diceThree);
        d4 = findViewById(R.id.diceFour);
        d5 = findViewById(R.id.diceFive);
        d6 = findViewById(R.id.diceSix);

        d4.setVisibility(View.VISIBLE);

        sound = new SoundPlayer(this);
    }

    public void generate(View view){

        diceRoll();

        final  Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                result();
            }
        }, 1200);
    }

    public void result(){

        d1.setVisibility(d1.INVISIBLE);
        d2.setVisibility(d2.INVISIBLE);
        d3.setVisibility(d3.INVISIBLE);
        d4.setVisibility(d4.INVISIBLE);
        d5.setVisibility(d5.INVISIBLE);
        d6.setVisibility(d6.INVISIBLE);

        Random random = new Random();
        number = random.nextInt(6)+1;

        switch (number){
            case 1: d1.setVisibility(d1.VISIBLE);
                break;
            case 2: d2.setVisibility(d2.VISIBLE);
                break;
            case 3: d3.setVisibility(d3.VISIBLE);
                break;
            case 4: d4.setVisibility(d4.VISIBLE);
                break;
            case 5: d5.setVisibility(d5.VISIBLE);
                break;
            case 6: d6.setVisibility(d6.VISIBLE);
                break;
        }
    }

    public void diceRoll(){

        counter =0;

        Runnable r = new Runnable() {
            @Override
            public void run() {
                while (counter < 10) {
                    counter+=1;
                    synchronized (this) {
                        try {
                            wait(100);
                        } catch (Exception e) {}
                        rollNumber-=1;
                        if(rollNumber == 0)
                            rollNumber = 6;
                    }
                    handler.sendEmptyMessage(0);
                }
            }
        };

        sound.playSound();

        thread = new Thread(r);
        thread.start();
    }
}
