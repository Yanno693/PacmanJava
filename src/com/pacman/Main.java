package com.pacman;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Observable;
import java.util.Observer;

public class Main extends Application implements Observer // Fait office de vue
{

    private Jeu jeu;
    private Scene s;
    private GridPane gridView;
    private VBox verticalView;
    private Text scoreView;
    private Text gameoverView;
    private HBox vieView;
    private Image[] fantomeImg;
    private Image[] pacmanImg;
    private int positionPacmanImg;

    private void actionClavier(KeyCode input)
    {
        switch (input)
        {
            case UP: jeu.getGrille().getPacman().changerDirection(0); break;
            case DOWN: jeu.getGrille().getPacman().changerDirection(1); break;
            case LEFT: jeu.getGrille().getPacman().changerDirection(2); break;
            case RIGHT: jeu.getGrille().getPacman().changerDirection(3); break;
            case P: jeu.pause(); break; // Pause
        }
    }

    private void afficherFrame()
    {
        scoreView.setText("Score : " + jeu.getScore());
        vieView.getChildren().clear();

        for(int i = 0; i < jeu.getVie(); i++)
        {
            ImageView vie = new ImageView(pacmanImg[0]);
            vieView.getChildren().add(vie);
        }

        if(jeu.estGameover())
            gameoverView.setText("GAME OVER");

       //scoreView.setFont(Font.font ("Comic Sans MS", 20));
        gridView.getChildren().clear();
        //gridView = new GridPane();
        //gridView.setGridLinesVisible(true);
        gridView.setHgap(1);
        gridView.setVgap(1);

        /*Text t = new Text("a");
        t.setFill(Color.RED);*/

        positionPacmanImg = (positionPacmanImg + 1) % pacmanImg.length;


        for(int j = 0; j < jeu.getGrille().getHauteur(); j++)
        {
            for(int i = 0; i < jeu.getGrille().getLargeur(); i++)
            {
                Rectangle r = new Rectangle(0,0,20,20);
                r.setArcWidth(10);
                r.setArcHeight(10);
                //Circle r = new Circle();
                //r.setRadius(10);

                if(jeu.getGrille().etatGrille[i][j].estVide())
                    r.setFill(Color.BLACK);
                else
                    r.setFill(Color.CORNFLOWERBLUE);

                gridView.add(r,i,j);

                if(jeu.getGrille().etatGrille[i][j].getType() == '*' && !jeu.estGameover())
                {
                    Circle c = new Circle();
                    c.setTranslateX(8);
                    c.setRadius(2);
                    c.setFill(Color.YELLOW);
                    gridView.add(c,i,j);
                }

                if(jeu.getGrille().etatGrille[i][j].getType() == 'Y' && !jeu.estGameover())
                {
                    Circle c = new Circle();
                    c.setTranslateX(6);
                    c.setRadius(4);
                    c.setFill(Color.YELLOW);
                    gridView.add(c,i,j);
                }

                for(int c = 0; c < jeu.getGrille().getFantomes().length; c++)
                {
                    if(jeu.getGrille().getFantomes()[c].getX() == i && jeu.getGrille().getFantomes()[c].getY() == j && !jeu.estGameover())
                    {
                        ImageView imgView;

                        if(jeu.getGrille().getFantomes()[c].estVulnerable())
                            imgView = new ImageView(fantomeImg[4]);
                        else
                            imgView = new ImageView(fantomeImg[c]);
                        gridView.add(imgView,i,j);
                    }
                }

                if(jeu.getGrille().getPacman().getX() == i && jeu.getGrille().getPacman().getY() == j && !jeu.estGameover())
                {
                    ImageView imgView = new ImageView(pacmanImg[positionPacmanImg]);

                    switch (jeu.getGrille().getPacman().getDirection())
                    {
                        case 0 : imgView.setRotate(90); break;
                        case 1 : imgView.setRotate(270); break;
                        case 2 : break;
                        default: imgView.setRotate(180); break;
                    }
                    imgView.setTranslateX(3);

                    gridView.add(imgView,i,j);
                }
            }
        }

        BackgroundFill fill = new BackgroundFill(Paint.valueOf("222222"), CornerRadii.EMPTY, Insets.EMPTY);
        Background b = new Background(fill);
        gridView.setBackground(b);

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

        fantomeImg = new Image[5];
        fantomeImg[0] = new Image("file:images/fantome1.png");
        fantomeImg[1] = new Image("file:images/fantome2.png");
        fantomeImg[2] = new Image("file:images/fantome3.png");
        fantomeImg[3] = new Image("file:images/fantome4.png");
        fantomeImg[4] = new Image("file:images/fantomebleu.png");

        pacmanImg = new Image[3];
        pacmanImg[0] = new Image("file:images/pacman1.png");
        pacmanImg[1] = new Image("file:images/pacman2.png");
        pacmanImg[2] = new Image("file:images/pacman3.png");
        positionPacmanImg = 0;

        primaryStage.setTitle("Pac-Man");

        Timeline update = new Timeline(new KeyFrame(Duration.millis(150), ae -> jeu.boucleEvenement() ));
        update.setCycleCount(Animation.INDEFINITE);
        update.play();

        verticalView = new VBox();
        verticalView.setPadding(new Insets(10,0,0,0));
        verticalView.setSpacing(10);
        scoreView = new Text("0");
        scoreView.setFill(Color.WHITE);
        gameoverView = new Text("");
        gameoverView.setFill(Color.RED);
        //scoreView.setFont(Font.fon);
        vieView = new HBox();

        System.out.println(javafx.scene.text.Font.getFamilies());

        gridView = new GridPane();

        verticalView.getChildren().add(scoreView);
        verticalView.getChildren().add(gridView);
        verticalView.getChildren().add(vieView);
        verticalView.getChildren().add(gameoverView);
        verticalView.setAlignment(Pos.CENTER);

        s = new Scene(verticalView, Color.BLACK);
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

    public static void main()
    {
        launch();
    }
}
