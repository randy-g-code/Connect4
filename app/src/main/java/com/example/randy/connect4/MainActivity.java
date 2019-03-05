package com.example.randy.connect4;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;

/*
* A Lot of This Code Was Modified From Tic Tac Toe V1 and V5
*
* If I Were to Improve This Program:
* - Add Drawble Resources to the Buttons
* - Remove Clicking OFF the Dialog Box
* - Create Visual Effects When Buttons Are Clicked
* - Add More Game Modes (Checkers, Japanese GO, etc)
* - Clean Up a Bit of Code
*/
public class MainActivity extends AppCompatActivity
{
    private Buttons BGV;
    private Connect4 game;

    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        game = new Connect4( );
        Point size = new Point( );
        getWindowManager().getDefaultDisplay( ).getSize( size );
        int w = size.x / Connect4.column;
        ButtonHandler bh = new ButtonHandler( );
        BGV = new Buttons( this, w, Connect4.rows, Connect4.column ,bh );
        setContentView( BGV );
    }

    public void showNewGameDialog( ) // Dialog Box That Pops Up When Game is Over
    {
        AlertDialog.Builder alert = new AlertDialog.Builder( this );
        alert.setTitle( "Game OVER" );
        alert.setMessage( game.result() + " Continue?" );
        PlayDialog playAgain = new PlayDialog( );
        alert.setPositiveButton( "YES", playAgain );
        alert.setNegativeButton( "NO", playAgain );
        alert.setCancelable(false);
        alert.show( );
    }

    private class PlayDialog implements DialogInterface.OnClickListener {
        public void onClick( DialogInterface dialog, int id ) {
            if( id == -1 ) // Yes button
            {
                game.resetGame( );
                BGV.resetBoard();
                BGV.enableButtons( true );
            }
            else if( id == -2 ) // No button
                MainActivity.this.finish( );
        }
    }

    private class ButtonHandler implements View.OnClickListener //Button Handler That Executes Per Button Click
    {
        public void onClick( View v )
        {
            //Log.w("MainActivity", "Inside onClick, v = " + v);
            for (int row = 0; row < 6; row++)
            {
                for (int column = 0; column < 7; column++)
                {
                    if (BGV.isButton((Button) v, row, column)) //Checks What Was Clicked
                    {
                        int rowinsert = game.play(row, column); // Checks What Row To Insert To (Since Connect 4 Deals with Gravity)
                        if (game.getTurn() == 1)
                        {
                            BGV.update(rowinsert, column, Color.RED); // Set Player 1 Color
                        }
                        else if (game.getTurn() == 2)
                        {
                            BGV.update(rowinsert, column, Color.BLUE); // Set Player 2 Color
                        }

                        if( game.isGameOver( )) // Checks if the Game is OVER
                        {
                            BGV.enableButtons(false);
                            showNewGameDialog();
                        }

                    }
                }
            }
        }
    }
}
