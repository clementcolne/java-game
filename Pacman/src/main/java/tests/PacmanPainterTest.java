package tests;

import engine.MapBuilder;
import model.PacmanGame;
import model.PacmanPainter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Adèle
 * 25/10/2020
 **/

class PacmanPainterTest {

    private PacmanGame game;
    private PacmanPainter painter;
    private MapBuilder map;

    @BeforeEach
    void setUp() {
        map = new MapBuilder("map.txt", 15, 15);
        game = new PacmanGame("helpFilePacman.txt", map);
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