package com.pacman;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {

        Text t;
        t = new Text("Bonjour");
        t.setFill(Color.RED);
        t.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                t.setText("aaa");
            }
        });

        BorderPane b = new BorderPane();
        b.setCenter(t);
        //b.setTop(t);


        Scene s = new Scene(b, Color.LIGHTGRAY);

        //primaryStage.setResizable(false);
        primaryStage.setWidth(500);
        primaryStage.setHeight(500);

        primaryStage.setScene(s);



        primaryStage.show();


    }

    public static void main(String[] args)
    {

        int nb = 0;

        Grille g = new Grille();

        launch(args);
    }
}
