package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Random;

public class Main extends Application{

Stage window;
Scene mainMenu, mancalaGame, instructions, gameResults, scoreDisplay;
boolean compPlay;
FileWriter file = new FileWriter();

    //Creates stone labels for player 1
    Label label0 = new Label("4");
    Label label1 = new Label("4");
    Label label2 = new Label("4");
    Label label3 = new Label("4");
    Label label4 = new Label("4");
    Label label5 = new Label("4");
    Label label6 = new Label("0");

    //Creates stone labels for player 2
    Label label7 = new Label("4");
    Label label8 = new Label("4");
    Label label9 = new Label("4");
    Label label10 = new Label("4");
    Label label11 = new Label("4");
    Label label12 = new Label("4");
    Label label13 = new Label("0");

    //Labels for score box
    Label P1Score = new Label("0");
    Label P2Score = new Label("0");

    //Final score for game over screen
    Label P1FinalScore = new Label();
    Label P2FinalScore = new Label();

    //label to display winner
    Label winner = new Label("0");

    Text topScores = new Text();

    //game text
    Text turn = new Text();
    Text gameText = new Text();

    Button scores = new Button();

    Button AIMove = new Button();

    Button scoreReport = new Button();

    @Override
    public void start(Stage primaryStage) throws Exception{
        Mancala mancala = new Mancala();

        window = primaryStage;
        window.setTitle("Mancala Game");

        //main menu layout
        Image image = new Image(new FileInputStream("/Users/libbymccaffrey/IdeaProjects/MancalaGame/src/sample/Screen Shot 2020-07-30 at 3.38.46 PM.png"));
        ImageView imageView = new ImageView(image); //setting image as screen background

        imageView.setFitHeight(455);
        imageView.setFitWidth(500);
        imageView.setPreserveRatio(true);
        Group root = new Group(imageView);

        Button newSPGame = new Button(); //creation of each button on main menu
        newSPGame.setTranslateX(-10);
        newSPGame.setTranslateY(-45);
        newSPGame.setPrefWidth(175);
        newSPGame.setPrefHeight(40);
        newSPGame.setStyle("-fx-background-color: transparent;");

        Button newMPGame = new Button();
        newMPGame.setTranslateX(-10);
        newMPGame.setTranslateY(15);
        newMPGame.setPrefWidth(175);
        newMPGame.setPrefHeight(40);
        newMPGame.setStyle("-fx-background-color: transparent;");

        Button gameRules = new Button();
        gameRules.setTranslateX(-10);
        gameRules.setTranslateY(75);
        gameRules.setPrefWidth(175);
        gameRules.setPrefHeight(40);
        gameRules.setStyle("-fx-background-color: transparent;");

        Button exitGame = new Button();
        exitGame.setTranslateX(-10);
        exitGame.setTranslateY(130);
        exitGame.setPrefWidth(175);
        exitGame.setPrefHeight(40);
        exitGame.setStyle("-fx-background-color: transparent;");

        scoreReport.setOnAction(e -> {
            window.setScene(scoreDisplay);
        });

        newSPGame.setOnAction(e -> { //action when button is pressed, goes to new screen
            window.setScene(mancalaGame);
            compPlay = true;
            AIMove.setTranslateX(-238);
            AIMove.setTranslateY(-15);
//            System.out.println(compPlay);
        });
        newMPGame.setOnAction(e -> {
            window.setScene(mancalaGame);
            compPlay = false;
            AIMove.setTranslateX(-500);
//            System.out.println(compPlay);
        });
        gameRules.setOnAction(e -> window.setScene(instructions));
        exitGame.setOnAction(e -> window.close());

//        Button random = new Button();
//        random.setOnAction(e -> {
//            window.setScene(gameResults);
//        });

        StackPane mainMenuLayout = new StackPane();
        mainMenuLayout.getChildren().addAll(root, newSPGame, newMPGame, gameRules, exitGame);
        mainMenu = new Scene(mainMenuLayout, 500, 320);

        //mancala game layout
        Image image2 = new Image(new FileInputStream("/Users/libbymccaffrey/IdeaProjects/MancalaGame/src/sample/Screen Shot 2020-08-15 at 11.57.27 AM.png"));
        ImageView imageView2 = new ImageView(image2);

        imageView2.setFitHeight(600);
        imageView2.setFitWidth(600);
        imageView2.setPreserveRatio(true);
        Group root2 = new Group(imageView2);

        ///////////

        turn.setText("1");
        turn.setTranslateX(-188);
        turn.setTranslateY(-85);

        gameText.setText("Let's Play!");
        gameText.setTranslateX(-135);
        gameText.setTranslateY(-55);

        label0.setTranslateX(180);
        label0.setTranslateY(76);

        label1.setTranslateX(115);
        label1.setTranslateY(76);

        label2.setTranslateX(50);
        label2.setTranslateY(76);

        label3.setTranslateX(-25);
        label3.setTranslateY(76);

        label4.setTranslateX(-93);
        label4.setTranslateY(76);

        label5.setTranslateX(-161);
        label5.setTranslateY(76);

        label6.setTranslateX(-218);
        label6.setTranslateY(90);

        label7.setTranslateX(-161);
        label7.setTranslateY(97);

        label8.setTranslateX(-93);
        label8.setTranslateY(97);

        label9.setTranslateX(-25);
        label9.setTranslateY(97);

        label10.setTranslateX(50);
        label10.setTranslateY(97);

        label11.setTranslateX(115);
        label11.setTranslateY(97);

        label12.setTranslateX(180);
        label12.setTranslateY(97);

        label13.setTranslateX(240);
        label13.setTranslateY(90);

        P1Score.setTranslateX(205);
        P1Score.setTranslateY(-122);

        P2Score.setTranslateX(205);
        P2Score.setTranslateY(-75);

        P1FinalScore.setTranslateX(-195);
        P1FinalScore.setTranslateY(-100);
        P1FinalScore.setFont(new Font("Arial", 16));

        P2FinalScore.setTranslateX(173);
        P2FinalScore.setTranslateY(-100);
        P2FinalScore.setFont(new Font("Arial", 16));

        winner.setTranslateX(-13);
        winner.setTranslateY(-107);
        winner.setFont(new Font("Arial", 30));

        //////////

        //P2
        Button button0 = new Button();
        button0.setTranslateX(180);
        button0.setTranslateY(-13);

        Button button1 = new Button();
        button1.setTranslateX(115);
        button1.setTranslateY(-13);

        Button button2 = new Button();
        button2.setTranslateX(50);
        button2.setTranslateY(-13);

        Button button3 = new Button();
        button3.setTranslateX(-25);
        button3.setTranslateY(-13);

        Button button4 = new Button();
        button4.setTranslateX(-93);
        button4.setTranslateY(-13);

        Button button5 = new Button();
        button5.setTranslateX(-161);
        button5.setTranslateY(-13);

        //button actions
        AIMove.setOnAction(e -> {
            mancala.playerMove(12, compPlay);
            messageText(mancala);
            gameOver(mancala);
            winnerDisplay(mancala);
        });

        button0.setOnAction(e -> {
            mancala.playerMove(0, compPlay);
            messageText(mancala);
            gameOver(mancala);
            winnerDisplay(mancala);
        });
        button1.setOnAction(e -> {
            mancala.playerMove(1, compPlay);
            messageText(mancala);
            gameOver(mancala);
            winnerDisplay(mancala);
        });
        button2.setOnAction(e -> {
            mancala.playerMove(2, compPlay);
            messageText(mancala);
            gameOver(mancala);
            winnerDisplay(mancala);
        });
        button3.setOnAction(e -> {
            mancala.playerMove(3, compPlay);
            messageText(mancala);
            gameOver(mancala);
            winnerDisplay(mancala);
        });
        button4.setOnAction(e -> {
            mancala.playerMove(4, compPlay);
            messageText(mancala);
            gameOver(mancala);
            winnerDisplay(mancala);
        });
        button5.setOnAction(e -> {
            mancala.playerMove(5, compPlay);
            messageText(mancala);
            gameOver(mancala);
            winnerDisplay(mancala);
        });

        //P1
        Button button7 = new Button();
        button7.setTranslateX(-161);
        button7.setTranslateY(193);

        Button button8 = new Button();
        button8.setTranslateX(-93);
        button8.setTranslateY(193);

        Button button9 = new Button();
        button9.setTranslateX(-25);
        button9.setTranslateY(193);

        Button button10 = new Button();
        button10.setTranslateX(50);
        button10.setTranslateY(193);

        Button button11 = new Button();
        button11.setTranslateX(115);
        button11.setTranslateY(193);

        Button button12 = new Button();
        button12.setTranslateX(180);
        button12.setTranslateY(193);


        //button actions
        button7.setOnAction(e -> {
            mancala.playerMove(7, compPlay);
            messageText(mancala);
            gameOver(mancala);
            winnerDisplay(mancala);
        });

        button8.setOnAction(e -> {
            mancala.playerMove(8, compPlay);

            messageText(mancala);
            gameOver(mancala);
            winnerDisplay(mancala);
        });

        button9.setOnAction(e -> {
            mancala.playerMove(9, compPlay);
            messageText(mancala);
            gameOver(mancala);
            winnerDisplay(mancala);
        });

        button10.setOnAction(e -> {
            mancala.playerMove(10, compPlay);
            messageText(mancala);
            gameOver(mancala);
            winnerDisplay(mancala);
        });

        button11.setOnAction(e -> {
            mancala.playerMove(11, compPlay);
            messageText(mancala);
            gameOver(mancala);
            winnerDisplay(mancala);
        });

        button12.setOnAction(e -> {
            mancala.playerMove(12, compPlay);
            messageText(mancala);
            gameOver(mancala);
            winnerDisplay(mancala);
        });

        //setting text for buttons
        button0.setText("0");
        button1.setText("1");
        button2.setText("2");
        button3.setText("3");
        button4.setText("4");
        button5.setText("5");

        button7.setText("7");
        button8.setText("8");
        button9.setText("9");
        button10.setText("10");
        button11.setText("11");
        button12.setText("12");

        scores.setText("Go to results");
        scores.setTranslateX(20);
        scores.setTranslateY(-85);

        AIMove.setText("Activate AI Move");

        StackPane mancalaGameLayout = new StackPane();
        mancalaGameLayout.getChildren().addAll(root2, button0, button1, button2, button3, button4,
                button5, button7, button8, button9, button10, button11,
                button12, AIMove, label0, label1, label2, label3, label4, label5, label6, label7, label8, label9,
                label10, label11, label12, label13, turn, gameText, scores, P1Score, P2Score);
        mancalaGame = new Scene(mancalaGameLayout, 600, 440);

        //instructions layout
        Image image3 = new Image(new FileInputStream("/Users/libbymccaffrey/IdeaProjects/MancalaGame/src/sample/Screen Shot 2020-08-14 at 3.18.20 PM.png"));
        ImageView imageView3 = new ImageView(image3);

        imageView3.setFitHeight(455);
        imageView3.setFitWidth(500);
        imageView3.setPreserveRatio(true);
        Group root3 = new Group(imageView3);

        Button buttonMain = new Button();
        buttonMain.setText("Back to main menu");
        buttonMain.setTranslateX(0);
        buttonMain.setTranslateY(207);

        buttonMain.setOnAction(e -> window.setScene(mainMenu));

        StackPane instructionsLayout = new StackPane();
        instructionsLayout.getChildren().addAll(root3, buttonMain);
        instructions = new Scene(instructionsLayout);

        //Game over layout
        Image image4 = new Image(new FileInputStream("/Users/libbymccaffrey/IdeaProjects/MancalaGame/src/sample/Screen Shot 2020-08-14 at 3.52.24 PM.png"));
        ImageView imageView4 = new ImageView(image4);

        imageView4.setFitHeight(455);
        imageView4.setFitWidth(500);
        imageView4.setPreserveRatio(true);
        Group root4 = new Group(imageView4);

        Button exit = new Button();
        exit.setText("Exit");
        exit.setTranslateX(40);
        exit.setTranslateY(-147);

        scoreReport.setText("View Scores");
        scoreReport.setTranslateX(-60);
        scoreReport.setTranslateY(-147);

        exit.setOnAction(e -> window.close());

        StackPane gameOverLayout = new StackPane();
        gameOverLayout.getChildren().addAll(root4, exit, P1FinalScore, P2FinalScore, winner, scoreReport);
        gameResults = new Scene(gameOverLayout);

        //Scores Display Layout
        Image image5 = new Image(new FileInputStream("/Users/libbymccaffrey/IdeaProjects/MancalaGame/src/sample/Screen Shot 2020-09-07 at 10.32.01 AM.png"));
        ImageView imageView5 = new ImageView(image5);

        Button exit2 = new Button();
        exit2.setText("Exit");
        exit2.setTranslateX(200);
        exit2.setTranslateY(-147);
        exit2.setOnAction(e -> window.close());

        imageView5.setFitHeight(455);
        imageView5.setFitWidth(500);
        imageView5.setPreserveRatio(true);
        Group root5 = new Group(imageView5);

        StackPane scoreReportLayout = new StackPane();
        scoreReportLayout.getChildren().addAll(root5, topScores, exit2);
        scoreDisplay = new Scene(scoreReportLayout);


        window.setScene(mainMenu);
        window.show();

    }
    public void buttonPress(Mancala mancala){ //updates stone labels and scores upon each move
        int[] stones;
        stones = mancala.mancalaBoard();
        label0.setText(Integer.toString(stones[0]));
        label1.setText(Integer.toString(stones[1]));
        label2.setText(Integer.toString(stones[2]));
        label3.setText(Integer.toString(stones[3]));
        label4.setText(Integer.toString(stones[4]));
        label5.setText(Integer.toString(stones[5]));
        label6.setText(Integer.toString(stones[6]));
        label7.setText(Integer.toString(stones[7]));
        label8.setText(Integer.toString(stones[8]));
        label9.setText(Integer.toString(stones[9]));
        label10.setText(Integer.toString(stones[10]));
        label11.setText(Integer.toString(stones[11]));
        label12.setText(Integer.toString(stones[12]));
        label13.setText(Integer.toString(stones[13]));

        P1Score.setText(Integer.toString(stones[13]));
        P2Score.setText(Integer.toString(stones[6]));

        P1FinalScore.setText(Integer.toString(stones[13]));
        P2FinalScore.setText(Integer.toString(stones[6]));

    }

    public void player(Mancala mancala){ //sets player label
        int player;
        player = mancala.getPlayer();
        turn.setText(Integer.toString(player));
    }

    public void messageText(Mancala mancala){ //uses return from MoveCheck to print corresponding text
        int message;
        message = mancala.message;
//        System.out.println(mancala.message);

       if(message == 1){ //only lets player 1 pick pits 7-12
            gameText.setText("It is player 1's turn, only pits 7 - 12 available");
        }
       else if(message == 2){ //only lets player 2 pick pits 0-5
            gameText.setText("It is player 2's turn, only pits 0 - 5 available");
        }
        else if(message == 3){ //does not let player pick an empty pit
            gameText.setText("Invalid move, no stones in pit ");
        }
        else if(message == 4){
           if(mancala.gameOver == true){
               System.out.println("true");
               gameOver(mancala);
           }
            gameText.setText("Nice move, get another turn");
            buttonPress(mancala);
       }
        else if (message == 0){
            gameText.setText("Nice move!!");
            buttonPress(mancala);
            player(mancala);
        }
    }

    public void gameOver(Mancala mancala){ //game over occurences
        boolean gameOver = mancala.gameResults(mancala.gameOver);
        if(gameOver == true){
//            window.setScene(gameResults);
            gameText.setText("Click button to go to results");
            scores.setOnAction(e -> {
                window.setScene(gameResults);
                String theScore = file.writer(mancala.board);
                //scoreReport layout
//        String theScore = file.scoreList;
//        topScores.setText(theScore);
                topScores.setText(theScore);
                topScores.setTranslateX(-10);
                topScores.setTranslateY(0);
                topScores.setFont(Font.font(16));
//        System.out.println(file.scoreList);
            });
        }
    }


    public void winnerDisplay(Mancala mancala){ //sets values of the game over screen
        int player = mancala.winner(mancala.getPlayer());
        if(player == 1){
            winner.setText("1");
        }
        if(player == 2){
            winner.setText("2");
        }
        if(player == 0){
            winner.setText("Tie");
        }
    }

    public boolean compSend(){
        return compPlay;
    }


    public static void main(String[] args) {
        launch(args);
    }
}
