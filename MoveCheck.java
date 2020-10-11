package sample;

import java.util.ArrayList;


public class MoveCheck { //checks is selected move is valid
    public static int message(int player, int move, ArrayList<Integer> board){
        int message = 0; //if move is valid

        if(player == 1 && move <= 5){ //if move is selected from incorrect side P1
            message = 1;
        }
        if(player == 2 && move >= 7){ //if move is selected from incorrect side P2
            message = 2;
        }
        if(board.get(move) == 0){ //if empty pit is selected
            message = 3;
        }
        if(player == 1 && (board.get(move) + move) % 14 == 13 || player == 2 && (board.get(move) + move) % 14 == 6){ //if player gets another turn
            message = 4;
        }
        return message; //returns message value to main to print text
    }
}
