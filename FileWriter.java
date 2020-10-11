package sample;

import javafx.scene.control.Label;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class FileWriter extends Mancala {
    String score1 = "";
    String score2 = "";
    String scoreList = "";
    public String writer(ArrayList<Integer> board){ //writes pit 13 and 6 (score pits) into text file
        score1 = Integer.toString(board.get(13));
        score2 = Integer.toString(board.get(6));

        try {
            BufferedWriter writer = new BufferedWriter(new java.io.FileWriter("./scores.txt", true));

            writer.append(score1 + "                                                     ");
            writer.append(score2 + "\n");

            writer.close(); //creates new writer and text file and appends scores

            ArrayList<String> result = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader(new File("./scores.txt"))); //creates new reader

            while(reader.ready()){
                result.add(reader.readLine()); //reads each line of text file
            }
            StringBuffer sb = new StringBuffer();
            for(String s : result){ //creates new string appending read values
                sb.append(s);
                sb.append(" ");
                sb.append("\n");
            }
            scoreList = sb.toString();
            System.out.println(scoreList);


        } catch (IOException e) { //exception to explain error
            e.printStackTrace();
        }
        return scoreList; //returns read scores to main to be added to GUI

    }
}
