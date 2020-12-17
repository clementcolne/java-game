package start;

import engine.MapBuilder;
import model.PacmanPainter;
import engine.GameEngineGraphical;
import model.PacmanController;
import model.PacmanGame;

import java.util.ArrayList;
import java.util.List;

/**
 * lancement du moteur avec le jeu
 */
public class Main {

	public static void main(String[] args) throws InterruptedException {

		// creation du jeu particulier et de son afficheur
		List<String> maps = new ArrayList();
		maps.add("map1.txt");
		maps.add("map2.txt");
		maps.add("map3.txt");
		MapBuilder map = new MapBuilder(maps);
		PacmanGame game = new PacmanGame("helpFilePacman.txt", map);
		PacmanPainter painter = new PacmanPainter(game);
		PacmanController controller = new PacmanController();

		

		// classe qui lance le moteur de jeu generique
		GameEngineGraphical engine = new GameEngineGraphical(game, painter, controller);
		engine.run();
	}

}