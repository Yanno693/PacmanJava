package com.pacman;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.concurrent.TimeUnit;

public class Main extends Application { // Fais office de vue

    private Jeu jeu;

    @Override
    public void start(Stage primaryStage) {

        jeu = new Jeu();
        jeu.nouvellePartie();

        primaryStage.setTitle("Pac-Man");

        Timeline update = new Timeline(new KeyFrame(Duration.millis(1000), ae -> jeu.boucleEvenement() ));
        update.setCycleCount(Animation.INDEFINITE);
        update.play();

        GridPane gridView = new GridPane();
        //gridView.setGridLinesVisible(true);
        gridView.setHgap(1);
        gridView.setVgap(1);

        /*Text t = new Text("a");
        t.setFill(Color.RED);*/

        for(int j = 0; j < jeu.getGrille().getHauteur(); j++)
        {
            for(int i = 0; i < jeu.getGrille().getLargeur(); i++)
            {
                Rectangle r = new Rectangle(0,0,20,20);

                if(jeu.getGrille().etatGrille[i][j].estVide())
                    r.setFill(Color.BLACK);
                else
                    r.setFill(Color.CORNFLOWERBLUE);

                gridView.add(r,i,j);

                if(jeu.getGrille().etatGrille[i][j].getType() == '*')
                {
                    Circle c = new Circle();
                    c.setTranslateX(8);
                    c.setRadius(2);
                    c.setFill(Color.YELLOW);
                    gridView.add(c,i,j);
                }

                if(jeu.getGrille().etatGrille[i][j].getType() == 'Y')
                {
                    Circle c = new Circle();
                    c.setTranslateX(6);
                    c.setRadius(4);
                    c.setFill(Color.YELLOW);
                    gridView.add(c,i,j);
                }
            }
        }

        BackgroundFill fill = new BackgroundFill(Paint.valueOf("222222"), CornerRadii.EMPTY, Insets.EMPTY);
        Background b = new Background(fill);
        gridView.setBackground(b);

        //gridView.add(t,0,20);

        /*t = new Text(i + "");
        t.setFill(Color.RED);
        t.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                fun();
            }
        });

        BorderPane b = new BorderPane();
        b.setCenter(t);
        //b.setTop(t);*/

        Scene s = new Scene(gridView, Color.BLACK);

        /*primaryStage.setResizable(false);
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
        });*/

        primaryStage.setResizable(false);
        primaryStage.setScene(s);
        primaryStage.show();

        primaryStage.setWidth(primaryStage.getWidth() - 10);
        primaryStage.setHeight(primaryStage.getHeight() - 10);
    }

    public static void main(String[] args)
    {

        launch(args);
    }
}
