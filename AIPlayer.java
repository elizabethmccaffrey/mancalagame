package sample;

import javafx.scene.control.RadioMenuItem;

import java.util.ArrayList;
import java.util.Random;

public class AIPlayer extends Mancala {
    int moveSelection;
    int[] moveRating = new int[6];
    int[] boardCopy = new int[14];

    public int AIMove(ArrayList<Integer> board) { //creates copy of the mancala board to test each possible move
        for (int i = 0; i <= 5; i++) {
            for (int j = 0; j < 14; j++) {
                boardCopy[j] = board.get(j);
            }

            if(board.get(i) > 0){
                int mancala = ((getPlayer() == 1) ? P2_MANCALA : P1_MANCALA); //determine player mancala
                int finalHole = board.get(position) + position; //determine final hole stone will land in
                playerMove(i, true); //run player move in Mancala
                if (board.get(position) + position == P2_MANCALA) { //if move lands in mancala
                    moveRating[i] = 4; //best move
                }
                //if move ends in a capture
                else if(board.get(finalHole) == 1 && (mancala - position) > 0 && (mancala - position <= PLAY_PITS)) {
                    moveRating[i] = 3; //second best move
                }
                else if (board.get(position) + position > P2_MANCALA) { //if move lands in mancala sometime during the move
                    moveRating[i] = 2; //third best move
                }
                else { //if none of the conditions apply make a random move
                    Random random = new Random();
                    int upperbound = 5;
                    int randomMove = random.nextInt(upperbound);
                    moveRating[i] = 1; //worst move
                }
            }
        }

        moveSelection = getMax(moveRating); //select move with the highest rating
        for(int x = 0; x < 6; x ++){ //console print out to see move ratings
            System.out.print(moveRating[x]);
        }
        System.out.println();
        System.out.println(moveSelection);

        for (int j = 0; j < 14; j++) { //set chosen move to the play board
            board.set(j, boardCopy[j]);
        }
        return moveSelection;

    }

    public static int getMax(int[] inputArray) { //get the max index in the array to determine chosen move
        int maxIndex = 0;
        int maxValue = inputArray[0];
        for (int i = 1; i < inputArray.length; i++) {
            if (inputArray[i] > maxValue) {
                maxValue = inputArray[i];
                maxIndex = i;

            }
        }
        return maxIndex;

    }
}