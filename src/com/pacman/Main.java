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

    //Verification de la touche appuie sur le clavier du joueur
    //les fleches pour controler Pac-Man, R -> commencer une nouvelle partie, P -> mettre en pause
    private void actionClavier(KeyCode input)
    {
        switch (input)
        {
            case UP: jeu.getGrille().getPacman().changerDirection(0); break;
            case DOWN: jeu.getGrille().getPacman().changerDirection(1); break;
            case LEFT: jeu.getGrille().getPacman().changerDirection(2); break;
            case RIGHT: jeu.getGrille().getPacman().changerDirection(3); break;
            case R: jeu.nouvellePartie(); break;
            case P: jeu.pause(); break; // Pause
        }
    }

    //Affichage de la scene
    private void afficherFrame(Jeu _jeu)
    {
        //Le bar tout en haut qui affiche le résultat du joueur
        scoreView.setText("Score : " + _jeu.getScore());
        vieView.getChildren().clear();

        //le bar en bas qui affiche les vies du joueur
        for(int i = 0; i < _jeu.getVie(); i++)
        {
            ImageView vie = new ImageView(pacmanImg[0]);
            vieView.getChildren().add(vie);
        }

        if(_jeu.estGameover())
            gameoverView.setText("GAME OVER");
        else
            gameoverView.setText("");

        gridView.getChildren().clear();
        gridView.setHgap(1);
        gridView.setVgap(1);

        positionPacmanImg = (positionPacmanImg + 1) % pacmanImg.length;

        //Remplissage de la grille du jeu
        for(int j = 0; j < _jeu.getGrille().getHauteur(); j++)
        {
            for(int i = 0; i < _jeu.getGrille().getLargeur(); i++)
            {
                //création des petits carreaux et leur positionement aux coordonnées i, j
                Rectangle r = new Rectangle(0,0,20,20);
                r.setArcWidth(10);
                r.setArcHeight(10);

                if(_jeu.getGrille().etatGrille[i][j].estVide())
                    r.setFill(Color.BLACK);
                else
                    r.setFill(Color.CORNFLOWERBLUE);


                gridView.add(r,i,j);

                //Positionement de pacGommes
                if(_jeu.getGrille().etatGrille[i][j].getType() == '*' && !_jeu.estGameover())
                {
                    Circle c = new Circle();
                    c.setTranslateX(8);
                    c.setRadius(2);
                    c.setFill(Color.YELLOW);
                    gridView.add(c,i,j);
                }


                //Positionement de super pacGommes
                if(_jeu.getGrille().etatGrille[i][j].getType() == 'Y' && !_jeu.estGameover())
                {
                    Circle c = new Circle();
                    c.setTranslateX(6);
                    c.setRadius(4);
                    c.setFill(Color.YELLOW);
                    gridView.add(c,i,j);
                }

                //Affichage des images de fantomes sur la grille en fonction de
                //s'ils sont vulnerables ou dans son état normal
                for(int c = 0; c < _jeu.getGrille().getFantomes().length; c++)
                {
                    if(_jeu.getGrille().getFantomes()[c].getX() == i && _jeu.getGrille().getFantomes()[c].getY() == j && !_jeu.estGameover())
                    {
                        ImageView imgView;

                        if(_jeu.getGrille().getFantomes()[c].estVulnerable())
                            imgView = new ImageView(fantomeImg[4]);
                        else
                            imgView = new ImageView(fantomeImg[c]);

                        imgView.setTranslateX(2);
                        gridView.add(imgView,i,j);
                    }
                }

                //Affichage de Pac-Man sur la grille
                if(_jeu.getGrille().getPacman().getX() == i && _jeu.getGrille().getPacman().getY() == j && !_jeu.estGameover())
                {
                    ImageView imgView = new ImageView(pacmanImg[positionPacmanImg]);

                    //On tourne l'image de Pac-Man en fonction de sa direction
                    switch (_jeu.getGrille().getPacman().getDirection())
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

        //Affichage du background
        BackgroundFill fill = new BackgroundFill(Paint.valueOf("222222"), CornerRadii.EMPTY, Insets.EMPTY);
        Background b = new Background(fill);
        gridView.setBackground(b);
    }

    @Override
    public void update(Observable obs, Object c)
    {
        afficherFrame((Jeu)obs);
    }

    @Override
    public void start(Stage primaryStage) {

        this.jeu = new Jeu();
        this.jeu.addObserver(this);
        this.jeu.nouvellePartie();

        fantomeImg = new Image[5];
        fantomeImg[0] = new Image(this.getClass().getResourceAsStream("/fantome1.png"));
        fantomeImg[1] = new Image(this.getClass().getResourceAsStream("/fantome2.png"));
        fantomeImg[2] = new Image(this.getClass().getResourceAsStream("/fantome3.png"));
        fantomeImg[3] = new Image(this.getClass().getResourceAsStream("/fantome4.png"));
        fantomeImg[4] = new Image(this.getClass().getResourceAsStream("/fantomebleu.png"));

        pacmanImg = new Image[3];
        pacmanImg[0] = new Image(this.getClass().getResourceAsStream("/pacman1.png"));
        pacmanImg[1] = new Image(this.getClass().getResourceAsStream("/pacman2.png"));
        pacmanImg[2] = new Image(this.getClass().getResourceAsStream("/pacman3.png"));
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
        vieView = new HBox();

        gridView = new GridPane();

        //Remplissage de la fenetre avec les views
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
        afficherFrame(this.jeu);

        primaryStage.setResizable(false);
        primaryStage.setScene(s);
        primaryStage.show();
    }

    public static void main()
    {
        launch();
    }
}
