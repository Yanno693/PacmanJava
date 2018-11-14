package com.pacman;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.concurrent.TimeUnit;

public class Main extends Application {

    static int i = 0;

    public static Text t;

    public static void fun()
    {
        i++;
        t.setText(i + "");
    }

    @Override
    public void start(Stage primaryStage) {

        Timeline tl = new Timeline(new KeyFrame(Duration.millis(1000), ae -> fun() ));
        tl.setCycleCount(Animation.INDEFINITE);
        tl.play();

        t = new Text(i + "");
        t.setFill(Color.RED);
        t.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                fun();
            }
        });

        BorderPane b = new BorderPane();
        b.setCenter(t);
        //b.setTop(t);

        Scene s = new Scene(b, Color.LIGHTGRAY);

        primaryStage.setResizable(false);
        primaryStage.setWidth(500);
        primaryStage.setHeight(500);

        s.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.A)
                {
                    t.setFill(Color.GREEN);
                }
            }
        });

        primaryStage.setScene(s);

        primaryStage.show();
    }

    public static void main(String[] args)
    {

        int nb = 0;

        Jeu j = new Jeu();
        j.nouvellePartie();

        while(true)
        {
            try{Thread.sleep(1000);}catch(InterruptedException e){System.out.println(e);}
            j.boucleEvenement();
            j.pause();
            System.out.println(j.toString());
        }

        /*int c = 5;

        for(int i = 0; i < 6; i++)
        {
            c++;
            System.out.println(c % 5);
            System.out.println(Math.floorMod(c,5));
        }*/


        //launch(args);
    }
}
