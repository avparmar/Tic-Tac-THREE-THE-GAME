package com.example.avparmar.tictacthree;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    int activePlayer = 0; // 0=yellow, 1=red

    boolean gameActive = true;

    int hold = 0;

    int[] game = {2, 2, 2, 2, 2, 2, 2, 2, 2}; // 2 = unplayed

    int[][] winCond = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};


    public void dropIn(View view) {

        ImageView counter = (ImageView) view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if(game[tappedCounter] == 2 && gameActive) {

            game[tappedCounter] = activePlayer;

            counter.setTranslationY(-1000f);

            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }

            counter.animate().translationYBy(1000f).rotation(360f).setDuration(300);
        }

        for (int[] winningPos : winCond) {
            if (game[winningPos[0]] == game[winningPos[1]] &&
                    game[winningPos[1]] == game[winningPos[2]] &&
                    game[winningPos[0]] != 2) {

                LinearLayout won = (LinearLayout) findViewById(R.id.paLayout);

                won.setVisibility(View.VISIBLE);

                TextView winMes = (TextView) findViewById(R.id.winnerMessage);


                String winnerofGame = "Red";
                if (game[winningPos[0]] == 0) {
                    winnerofGame = "Yellow";
                }
                winMes.setText(winnerofGame + " has won!!");

                gameActive = false;

                if (hold == 0) {
                    won.animate().translationYBy(1000f).setDuration(500);
                }
                hold++;

            } else {

                boolean gameIsOver = true;

                for (int counterState : game) {
                    if (counterState == 2) {
                        gameIsOver = false;
                    }
                }

                if (gameIsOver) {

                    LinearLayout won = (LinearLayout) findViewById(R.id.paLayout);

                    won.setVisibility(View.VISIBLE);

                    TextView winMes = (TextView) findViewById(R.id.winnerMessage);

                    winMes.setText("It's a draw!!");

                    gameActive = false;

                    if (hold == 0) {
                        won.animate().translationYBy(1000f).setDuration(500);
                    }
                    hold++;

                }
            }
        }
    }

    public void playAgain(View view) {

        LinearLayout layout = (LinearLayout) findViewById(R.id.paLayout);

        layout.setVisibility(View.INVISIBLE);

        layout.setTranslationY(-1000f);

        activePlayer = 0;

        for (int i = 0; i < 9; i++) {
            game[i] = 2;
        }

        GridLayout grid = (GridLayout) findViewById(R.id.gridLayout);

        for (int i = 0; i < grid.getChildCount(); i++) {

            ((ImageView) grid.getChildAt(i)).setImageResource(0);
        }
        gameActive = true;
        hold=0;



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.paLayout).setTranslationY(-1000f);
    }
}
