package tests;

import model.PacmanGame;
import model.PacmanPainter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Adèle Barbier
 * 25/10/2020
 **/

class PacmanPainterTest {

    private PacmanGame game;
    private PacmanPainter painter;

    @BeforeEach
    void setUp() {
        game = new PacmanGame("helpFilePacman.txt");
        painter = new PacmanPainter(game);
    }

    @Test
    void getWidth() {
        assert (painter.getWidth()==game.getWidth()*painter.getScale()):"La largeur de l'afficheur graphique devrait être "+game.getWidth()* painter.getScale();
    }

    @Test
    void getHeight() {
        assert (painter.getHeight()==game.getHeight()*painter.getScale()):"La hauteur de l'afficheur graphique devrait être "+game.getHeight()* painter.getScale();
    }
}