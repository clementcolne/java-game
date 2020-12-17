package tests;

import engine.MapBuilder;
import model.PacmanGame;
import model.PacmanPainter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

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
        List<String> maps = new ArrayList();
        maps.add("map1.txt");
        maps.add("map2.txt");
        maps.add("map3.txt");
        map = new MapBuilder(maps);
        game = new PacmanGame("helpFilePacman.txt", map);
        painter = new PacmanPainter(game);
    }

    @Test
    void getWidth() {
        assert (painter.getWidth()==game.getWidth()*game.getScale()):"La largeur de l'afficheur graphique devrait être "+game.getWidth()* game.getScale();
    }

    @Test
    void getHeight() {
        assert (painter.getHeight()==game.getHeight()*game.getScale()):"La hauteur de l'afficheur graphique devrait être "+game.getHeight()* game.getScale();
    }
}