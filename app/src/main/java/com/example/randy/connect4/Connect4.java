package com.example.randy.connect4;

/**
 * Created by Randy on 10/05/2017.
 */

public class Connect4
{
    public static final int rows = 6;
    public static final int column = 7;
    private int turn;
    private int [][] game;

    public Connect4( )
    {
        game = new int[rows][column];
        resetGame( );
    }

    public int play( int row, int col )
    {
        if( row >= 0 && col >= 0 && row < rows && col < column && game[0][col] == 0 )
        {
            if( turn == 1 )
                turn = 2;
            else
                turn = 1;

            return insertPiece(col);
        }
        else
            return -1; // No Space Left in the Row
    }

    public int getTurn()
    {
        return turn;
    }

    private int insertPiece(int col)
    {
        if(game[5][col] == 0) // First Insert
        {
            game[5][col] = turn;
            return 5;
        }
        else // Insert the Piece in the Lowest Spot
        {
            for(int row = 5; row >= 0; row-- )
            {
                if(game[row][col] == 0)
                {
                    game[row][col] = turn;
                    return row;
                }
            }
        }
        return -1; // No Space Left in the Row
    }

    public int whoWon( )
    {
        return checkWin(game);
    }

    /*
    * PLEASE NOTE: This is Using Benjamin Xeri's Post From the Homework Help Forum.
    * His Post Links to a Page Which Explains This Algorithm in Full
    * This Algorithm is not only Fast, but in Fact, Very Effective.
    */
    public static int checkWin(int[][] board)
    {
        final int HEIGHT = board.length;
        final int WIDTH = board[0].length;
        final int EMPTY_SLOT = 0;
        for (int r = 0; r < HEIGHT; r++) { // iterate rows, bottom to top
            for (int c = 0; c < WIDTH; c++) { // iterate columns, left to right
                int player = board[r][c];
                if (player == EMPTY_SLOT)
                    continue; // don't check empty slots

                if (c + 3 < WIDTH &&
                        player == board[r][c+1] && // look right
                        player == board[r][c+2] &&
                        player == board[r][c+3])
                    return player;
                if (r + 3 < HEIGHT) {
                    if (player == board[r+1][c] && // look up
                            player == board[r+2][c] &&
                            player == board[r+3][c])
                        return player;
                    if (c + 3 < WIDTH &&
                            player == board[r+1][c+1] && // look up & right
                            player == board[r+2][c+2] &&
                            player == board[r+3][c+3])
                        return player;
                    if (c - 3 >= 0 &&
                            player == board[r+1][c-1] && // look up & left
                            player == board[r+2][c-2] &&
                            player == board[r+3][c-3])
                        return player;
                }
            }
        }
        return EMPTY_SLOT; // no winner found
    }

    public boolean canNotPlay( ) //Checks if Board is Full
    {
        for (int row = 0; row < rows; row++)
            for( int col = 0; col < column; col++ )
                if ( game[row][col] == 0 )
                    return false;
        return true;
    }

    public boolean isGameOver( ) {
        return canNotPlay( ) || ( whoWon( ) > 0 );
    }

    public void resetGame( ) { //Remove ALL Turns
        for (int row = 0; row < rows; row++)
            for( int col = 0; col < column; col++ )
                game[row][col] = 0;
        turn = 0;
    }

    public String result( ) //Receive Result of Winner or Tie
    {
        if( whoWon( ) > 0 )
            return "Player " + whoWon( ) + " Won!!";
        else if( canNotPlay( ) )
            return "Tie Game!";
        else
            return "";
    }
}
