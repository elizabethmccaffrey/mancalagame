package sample;

import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Mancala{
    ArrayList<Integer> board = new ArrayList<>(); //creates new arrayList to act as the board
    final int NUM_OF_PITS = 14; //declares total pits
    final int PLAY_PITS = 6; //declares # of playPits
    final int P1_MANCALA = 13; //declares player 1's mancala, pit 13
    final int P2_MANCALA = 6; //declares player 2's mancala, pit 6
    boolean gameOver = false; //sets game over to false
    private boolean isPlayer1Turn = true; //player 1's turn is first
    private boolean isPlayer2Turn = false; //not player 2's turn
    private int player = 1; //sets player #
    int message;
    int position;

    public Mancala(){ //adds correct amount of stones to each pit
        board.add(4); //0
        board.add(4); //1
        board.add(4); //2
        board.add(4); //3
        board.add(4); //4
        board.add(4); //5
        board.add(0); //6
        board.add(4); //7
        board.add(4); //8
        board.add(4); //9
        board.add(4); //10
        board.add(4); //11
        board.add(4); //12
        board.add(0); //13
    }

    public int[] mancalaBoard(){ //creates board copy to be sent to Main
        int[] boardCopy = new int[14];
        for(int i = 0; i < 14; i++){
            boardCopy[i] = board.get(i);
        }
        return boardCopy;
    }

    /**
     * Moves stones based on pit selected, does basic and special move types
     * @param move int, Corresponds to which button is pressed
     * @param computer boolean, If the AIPlayer is playing
     * @return Returns ArrayList board so it can be updated based on the move
     */

    public ArrayList<Integer> playerMove(int move, boolean computer){ //moves stones based on mancala selected
        AIPlayer aiPlayer = new AIPlayer();
        if(isPlayer1Turn){ //sets which player is playing
            player = 1;
        }
        else if (isPlayer2Turn && computer == true){
            move = aiPlayer.AIMove(board);
         }
        else{
            player = 2;
        }
        position = move;

        message = MoveCheck.message(player, move, board); //sets message based on MoveCheck class

        if(message == 0 || message == 4 && gameOver == false){ //only allows move if another turn or valid move
            int stones = board.get(position); //stones at selected position
            board.set(position, 0); //sets chosen position to 0
            while(stones > 0) { //while there are stones left
                position = (position + 1) % NUM_OF_PITS; //deposit stone at next pit, mod to account for circular path
                if(position == ((isPlayer1Turn) ? P2_MANCALA : P1_MANCALA)){
                    continue; //if player 1's turn skip player 2's mancala
                }
                board.set(position, board.get(position) + 1); //sets pit its new stone number
                stones--; //subtracts stones available
            }
            if(isPlayer1Turn && position != P1_MANCALA && message != 1 && message != 3){ //sets next player
                isPlayer1Turn = false;
                isPlayer2Turn = true;
                player = 2;

            }
            else if(isPlayer2Turn && position != P2_MANCALA && message != 2 && message != 3){ //sets next player
                isPlayer2Turn = false;
                isPlayer1Turn = true;
                player = 1;

            }
            //advanced moves
            int mancala = ((isPlayer1Turn) ? P1_MANCALA : P2_MANCALA); //determines current player's mancala
            int mancalaReverse = ((isPlayer1Turn) ? P2_MANCALA : P1_MANCALA); //mancala to be used in capture move
            if(board.get(position) == 1 && (12 - position) > 0 && (mancalaReverse - position <= PLAY_PITS)){
                //if last stone placed on current player's side
                int oppositePit =  12 - position; //determines opposite pit to collect from
                if(board.get(oppositePit) > 0){ //only collect if opposite pit is > than 0
                    //collect stones from own pit
                    board.set(mancalaReverse, board.get(mancalaReverse) + 1);
                    board.set(position, 0);
                    //collect stones from opposite pit
                    board.set(mancalaReverse, board.get(mancalaReverse) + board.get(oppositePit));
                    board.set(oppositePit, 0);
                    System.out.println("hello"); //check if if statement was entered
                }

            }
            boolean noMoves = true;
            for(position = mancala - PLAY_PITS; position < mancala; position++){ //checks if a player has no moves
                if(board.get(position) > 0){
                    noMoves = false;
                }
            }
            if(noMoves){ //if the player has no moves collect and distribute stones and end game
                mancala = (mancala + PLAY_PITS + 1) % NUM_OF_PITS;
                for(position = mancala - PLAY_PITS; position < mancala; position++){
                    board.set(mancala, board.get(position) + board.get(mancala));
                    board.set(position, 0);
                    gameOver = true;
                }
            }

            mancalaBoard(); //update mancala board
            gameResults(gameOver); //send game over to game results
        }
        return board;
    }

    public int getPlayer() { //gets player to send to main
        return player;
    }

    public boolean gameResults(boolean gameOver){ //returns gameover to access in main
        return gameOver;
    }

    public int winner(int player){ //determine the winner based on player scores and return for access in main
        if(board.get(P1_MANCALA) > board.get(P2_MANCALA)){
            player = 1; //player 1 = winner
        }
        else if(board.get(P2_MANCALA) > board.get(P1_MANCALA)){
            player = 2; //player 2 = winner
        }
        else{
            player = 0; //tie
        }
        return player;
    }

}

