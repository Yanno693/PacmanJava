package com.pacman;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Observable;
import java.util.Observer;

public class Main extends Application implements Observer // Fais office de vue
{

    private Jeu jeu;

    private Scene s;
    private GridPane gridView;

    private void actionClavier(KeyCode input)
    {
        switch (input)
        {
            case UP: jeu.getGrille().getPacman().changerDirection(0); break;
            case DOWN: jeu.getGrille().getPacman().changerDirection(1); break;
            case LEFT: jeu.getGrille().getPacman().changerDirection(2); break;
            case RIGHT: jeu.getGrille().getPacman().changerDirection(3); break;
            case P: jeu.pause(); break;// Pause
        }
    }

    private void afficherFrame()
    {
        gridView.getChildren().clear();
        //gridView = new GridPane();
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
                //Circle r = new Circle();
                //r.setRadius(10);

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

                for(int c = 0; c < jeu.getGrille().getFantomes().length; c++)
                {
                    if(jeu.getGrille().getFantomes()[c].getX() == i && jeu.getGrille().getFantomes()[c].getY() == j)
                    {
                        Rectangle f = new Rectangle(0,0,10,10);
                        f.setFill(Color.GREEN);
                        gridView.add(f,i,j);
                    }
                }

                if(jeu.getGrille().getPacman().getX() == i && jeu.getGrille().getPacman().getY() == j)
                {
                    Rectangle f = new Rectangle(0,0,10,10);
                    f.setFill(Color.RED);
                    gridView.add(f,i,j);
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

        //s = new Scene(gridView, Color.BLACK);
    }

    @Override
    public void update(Observable obs, Object c)
    {
        afficherFrame();
    }

    @Override
    public void start(Stage primaryStage) {

        jeu = new Jeu();
        jeu.addObserver(this);
        jeu.nouvellePartie();

        primaryStage.setTitle("Pac-Man");

        Timeline update = new Timeline(new KeyFrame(Duration.millis(150), ae -> jeu.boucleEvenement() ));
        update.setCycleCount(Animation.INDEFINITE);
        update.play();

        gridView = new GridPane();
        s = new Scene(gridView, Color.BLACK);
        s.setOnKeyPressed(new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent event)
            {
                actionClavier(event.getCode());
            }
        });
        afficherFrame();

        primaryStage.setResizable(false);
        primaryStage.setScene(s);
        primaryStage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
