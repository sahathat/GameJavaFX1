/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamefx;

import java.util.Random;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author sahat
 */
public class GameFX extends Application {

    public static int random() {
        Random ran = new Random();
        return (int) Math.ceil(ran.nextDouble() * 100);
    }
    
    int count = 0;
    int random = random();

    @Override
    public void start(Stage primaryStage) {
        
        //grid pane 1 set number to test
        Text find = new Text("Guess number Between 1 to 100");
        TextField tf_find = new TextField("0");
        Button submit = new Button();
        submit.setText("submit");
        Button reset = new Button();
        reset.setText("reset");

        GridPane gp1 = new GridPane();

        //add grid pane 1
        gp1.add(find, 0, 0);
        gp1.add(tf_find, 0, 1);
        gp1.add(submit, 0, 2);
        
        //property of grid pane 1
        gp1.setVgap(10);
        gp1.setPadding(new Insets(20, 20, 20, 20));

        //grid pane 2 result of number
        Text result = new Text("Result :");
        Label getResult = new Label("more than or less than");
        Label counter = new Label("Count : 0");
        
        GridPane gp2 = new GridPane();
        gp2.add(result, 0, 0);
        gp2.add(getResult, 0, 1);
        gp2.add(counter, 0, 2);
        
        //when submit on button
        submit.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                int number = Integer.parseInt(tf_find.getText());
                if(tf_find.getText() == ""){
                    tf_find.setText("0");
                    number = 0;
                }
                if (number == random) {
                    getResult.setText("you win");
                    gp1.getChildren().remove(submit);
                    gp1.add(reset,0,2);
                } else if (number < random) {
                    getResult.setText("the random number is more than " + String.valueOf(number));
                } else if (number > random) {
                    getResult.setText("the random number is less than " + String.valueOf(number));
                }
                GameFX.this.count += 1;
                counter.setText("Count : " + String.valueOf(GameFX.this.count));
            }
        });
        
        //reset event
        reset.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                gp1.getChildren().remove(reset);
                gp1.add(submit,0,2);
                GameFX.this.random = random();
                GameFX.this.count = 0;
                tf_find.setText("0");
                getResult.setText("more than or less than");
                counter.setText("Count : " + String.valueOf(GameFX.this.count));
            }
        });
        
        //property of grid pane 2
        gp2.setVgap(10);
        gp2.setPadding(new Insets(20, 20, 20, 20));
        gp2.setAlignment(Pos.CENTER_LEFT);

        //combine grid pane by 2 session
        GridPane gp = new GridPane();
        gp.add(gp1, 0, 0);
        gp.add(gp2, 0, 1);

        //property of grid pane
        gp.setVgap(50);
        gp.setPadding(new Insets(20, 20, 20, 20));
        
        //scene
        Scene scene = new Scene(gp, 300, 400);

        primaryStage.setTitle("Guess number");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        System.out.println("random number is " + random());
    }

}
