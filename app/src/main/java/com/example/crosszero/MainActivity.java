package com.example.crosszero;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int[] gameState = {2,2,2,2,2,2,2,2,2};

    int[][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    int activePlayer = 0;

    boolean gameActive = true;


    public void zero (View view) {


        ImageView counter = (ImageView) view;
        int tapPosition = Integer.parseInt(counter.getTag().toString());

        if (gameState[tapPosition] == 2 && gameActive) {

            Log.i("Pressed Position ", counter.getTag().toString());
            gameState[tapPosition] = activePlayer;

            counter.setTranslationY(-1500);

            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.xx);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.o);
                activePlayer = 0;
            }

            counter.animate().translationYBy(1500).setDuration(300);

            for (int[] winningPosition : winningPositions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {
                    String winner = "";
                    if (activePlayer == 1) {
                        winner = "Cross";
                    } else {
                        winner = "Zero";
                    }
                    gameActive = false;
                    TextView textView = (TextView) findViewById(R.id.textView);
                    Button button = (Button)  findViewById(R.id.button);
                    textView.setText(winner + " has won! ");
                    textView.setVisibility(View.VISIBLE);
                    button.setVisibility(View.VISIBLE);
                }


            }

        }
    }
    public void playAgain(View view) {
        TextView textView = (TextView) findViewById(R.id.textView);
        Button button = (Button) findViewById(R.id.button);
        textView.setVisibility(View.INVISIBLE);
        button.setVisibility(View.INVISIBLE);
        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        for(int i=1; i<gridLayout.getChildCount();i++)
        {
            ImageView counter = (ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }
        for(int i=0;i<gameState.length;i++) {
            gameState[i] = 2;
        }

        activePlayer = 0;

        gameActive = true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}