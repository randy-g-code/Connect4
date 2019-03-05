package com.example.randy.connect4;

import android.content.Context;
import android.widget.Button;
import android.widget.GridLayout;

//Modified Version from Tic Tac Toe
public class Buttons extends GridLayout
{
    private int rows;
    private int column;
    private Button[][] buttons;

    public Buttons (Context context, int width, int newRow, int newColumn, OnClickListener listener ) {
        super( context );
        rows = newRow;
        column = newColumn;
        // Set # of rows and columns of this GridLayout
        setColumnCount(column);
        setRowCount( rows );

        // Create the buttons and add them to this GridLayout
        buttons = new Button[rows][column];
        for( int row = 0; row < rows; row++ ) {
            for( int col = 0; col < column; col++ ) {
                buttons[row][col] = new Button( context );
                buttons[row][col].setTextSize( ( int ) ( width * .2 ) );
                buttons[row][col].setOnClickListener( listener );
                addView( buttons[row][col], width, width );
            }
        }

        /*
        I Already Declare the Winner Via Pop Up Prompt So There's No Need For This
        status = new TextView( context );
        Spec rowSpec = GridLayout.spec( row, 1 );
        Spec columnSpec = GridLayout.spec( 0, column );
        LayoutParams lpStatus = new LayoutParams( rowSpec, columnSpec );
        status.setLayoutParams( lpStatus );

        // set up status' characteristics
        status.setWidth( row * width );
        status.setHeight( width );
        status.setGravity( Gravity.CENTER );
        status.setBackgroundColor( Color.GREEN );
        status.setTextSize( ( int ) ( width * .15 ) );

        addView( status );*/
    }

    public void update(int row, int col, int color) // Updates the Space Were the Connect 4 Chip Goes
    {
        //Log.w( "MainActivity", "Inside update: " + row + ", " + col );
        if(row != -1)
            buttons[row][col].setBackgroundColor(color);
    }

    public boolean isButton( Button b, int row, int column ) {
        return ( b == buttons[row][column] );
    }

    public void resetBoard( ) {
        for( int row = 0; row < this.rows; row++ )
            for( int col = 0; col < column; col++ )
                buttons[row][col].setBackgroundResource(android.R.drawable.btn_default);
    }

    public void enableButtons( boolean enabled ) {
        for( int row = 0; row < this.rows; row++ )
            for( int col = 0; col < column; col++ )
                buttons[row][col].setEnabled( enabled );
    }
}

