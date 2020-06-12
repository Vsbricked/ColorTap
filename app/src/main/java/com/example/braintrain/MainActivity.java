package com.example.braintrain;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

class pair{
    String x;
    String s;
    pair(String x,String s){
        this.x = x;
        this.s = s;
    }
}
public class MainActivity extends AppCompatActivity {
    Button startButton;
    Button color1;
    Button color2;
    Button color3;
    Button color4;
    TextView score;
    TextView timer;
    TextView question;
    TextView feedback;
    ArrayList<pair> colorList = new ArrayList<>();
    Random random = new Random();
    int ans;
    int points;
    int noOfQuestions;
    CountDownTimer countDownTimer;
    MediaPlayer finish;

    public void start(View view){
        startButton.setVisibility(View.INVISIBLE);
        selectans();
        createColor();
        makeVisible();
        score.setText("0");
        question.setVisibility(View.VISIBLE);
        score.setVisibility(View.VISIBLE);
        timer.setVisibility(View.VISIBLE);
        feedback.setVisibility(View.INVISIBLE);
        countDownTimer = new CountDownTimer(30000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                String time = String.valueOf(millisUntilFinished/1000);
                timer.setText("00:"+time);
            }

            @Override
            public void onFinish() {
                score.setVisibility(View.INVISIBLE);
                question.setVisibility(View.INVISIBLE);
                timer.setVisibility(View.INVISIBLE);
                color1.setVisibility(View.INVISIBLE);
                color2.setVisibility(View.INVISIBLE);
                color3.setVisibility(View.INVISIBLE);
                color4.setVisibility(View.INVISIBLE);
                finish = MediaPlayer.create(getApplicationContext(),R.raw.ding);
                finish.start();
                feedback.setVisibility(View.INVISIBLE);
                startButton.setVisibility(View.VISIBLE);
                startButton.setText("Restart");


            }
        }.start();



    }



    public void answer(View view){
        Button pressdButton = (Button) view;


        if(Integer.parseInt(pressdButton.getTag().toString())==ans){
           feedback.setText("Correct :)");
           points++;
           updateScore();
        }
        else{
            feedback.setText("Wrong :(");
            updateScore();

        }
        selectans();
        createColor();
    }

    private void updateScore() {
        score.setText(points+"/"+noOfQuestions);

    }

    private void selectans() {
        ans = random.nextInt(4);

    }

    private void makeVisible() {
        color1.setVisibility(View.VISIBLE);
        color2.setVisibility(View.VISIBLE);
        color3.setVisibility(View.VISIBLE);
        color4.setVisibility(View.VISIBLE);

    }

    private void createColor() {
        int color;
        noOfQuestions++;
        boolean used[] = new boolean[8];
        for(int i=0;i<4;i++){
            while(true){
                color = random.nextInt(8);
                if(!used[color]) break;
            }
            used[color] = true;
            if(ans==i){
                question.setText(colorList.get(color).s);
                question.setTextColor(Color.parseColor(colorList.get(color).x));
            }
            if(i==0){
                color1.setBackgroundColor(Color.parseColor(colorList.get(color).x));

            }
            if(i==1){
                color2.setBackgroundColor(Color.parseColor(colorList.get(color).x));
            }
            if(i==2){
                color3.setBackgroundColor(Color.parseColor(colorList.get(color).x));
            }
            if(i==3){
                color4.setBackgroundColor(Color.parseColor(colorList.get(color).x));
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startButton = findViewById(R.id.startbutton);

        color1 = findViewById(R.id.button0);
        color2 = findViewById(R.id.button1);
        color3 = findViewById(R.id.button2);
        color4 = findViewById(R.id.button3);

        color1.setVisibility(View.INVISIBLE);
        color2.setVisibility(View.INVISIBLE);
        color3.setVisibility(View.INVISIBLE);
        color4.setVisibility(View.INVISIBLE);


        score = findViewById(R.id.score);
        question = findViewById(R.id.question);
        timer = findViewById(R.id.Timer);
        feedback = (TextView) findViewById(R.id.feedback);

        score.setVisibility(View.INVISIBLE);
        timer.setVisibility(View.INVISIBLE);

        colorList.add(new pair("#DA0311","Red"));
        colorList.add(new pair("#03DAC5","Blue"));
        colorList.add(new pair("#FF9800","Orange"));
        colorList.add(new pair("#DA0BE1","Pink"));
        colorList.add(new pair("#3DA812","Green"));
        colorList.add(new pair("#FFEB3B","Yellow"));
        colorList.add(new pair("#000000","Black"));
        colorList.add(new pair("#9C27B0","Purple"));


    }
}
