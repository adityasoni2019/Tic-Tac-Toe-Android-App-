package com.aditya.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageHelper;

import android.annotation.SuppressLint;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    // 0 - yellow, 1- red
    int activePlayer = 0;

    boolean gameIsActive = true;

    // 2 = un-played.
    int[] gameState = {2,2,2,2,2,2,2,2,2};

    int[][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    @SuppressLint("SetTextI18n")
    public void dropIn(View view){
        ImageView counter = (ImageView) view;
        counter.setTranslationY(-1000f);
        System.out.println(counter.getTag().toString());

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if ( gameState[tappedCounter] ==2  && gameIsActive){

            gameState[tappedCounter] = activePlayer;

            if (activePlayer ==0){
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            }else{
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }

            counter.animate().translationY(1000f).rotation(3600).setDuration(300);

            for (int[] winningPosition : winningPositions){

                if ( gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] &&
                        gameState[winningPosition[0]] != 2){
                    String winner = "RED";

                    if( gameState[winningPosition[0]] == 0){
                        winner = "YELLOW";
                    }

                    TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);
                    winnerMessage.setText(winner + "HAS WON!");

                    System.out.println(gameState[winningPosition[0]]);

                    // someone has won!

                    gameIsActive = false;
                    LinearLayout playAgainLayout = (LinearLayout) findViewById(R.id.playAgainLayout);
                    playAgainLayout.setVisibility(View.VISIBLE);
                }else{
                    boolean gameIsOver = true;

                    for ( int counterState : gameState){
                        if ( counterState == 2) gameIsOver = false;
                    }
                    if ( gameIsOver){
                        TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);
                        winnerMessage.setText("IT'S A DRAW");


//                        System.out.println(gameState[winningPosition[0]]);

                        // someone has won!

//                        gameIsActive = false;
                        LinearLayout playAgainLayout = (LinearLayout) findViewById(R.id.playAgainLayout);
                        playAgainLayout.setVisibility(View.VISIBLE);
                    }
                }
            }
        }
    }

    public void playAgain(View view){
        LinearLayout playAgainLayout = (LinearLayout) findViewById(R.id.playAgainLayout);
        playAgainLayout.setVisibility(View.INVISIBLE);
        // 0 - yellow, 1- red
        activePlayer = 0;

        gameIsActive = true;

        // 2 = un-played.
//        gameState = new int[]{2, 2, 2, 2, 2, 2, 2, 2, 2};

        Arrays.fill(gameState, 2);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}