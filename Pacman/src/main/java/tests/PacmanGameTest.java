package tests;

import engine.Cmd;
import engine.MapBuilder;
import model.PacmanCharacter;
import model.PacmanGame;
import model.effect.AsyncEffect;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PacmanGameTest {

	private PacmanGame game, gamePassage, gamePassageTwo, gameWithoutPassage, gameEmpty;
	private PacmanCharacter character, characterPassage, characterPassageTwo, characterWithoutPassage, characterEmpty;
	private MapBuilder map, mapPassage, mapPassageTwo, mapWithoutPassage, mapEmpty;

	@BeforeEach
	void setUp() {
		map = new MapBuilder("test.txt");
		game = new PacmanGame("helpFilePacman.txt", map);
		character = game.getCharacter();

		mapPassage = new MapBuilder("testPassage.txt");
		gamePassage = new PacmanGame("helpFilePacman.txt", mapPassage);
		characterPassage = gamePassage.getCharacter();

		mapPassageTwo = new MapBuilder("testPassage2.txt");
		gamePassageTwo = new PacmanGame("helpFilePacman.txt", mapPassageTwo);
		characterPassageTwo = gamePassageTwo.getCharacter();

		mapWithoutPassage = new MapBuilder("testWithoutPassage.txt");
		gameWithoutPassage = new PacmanGame("helpFilePacman.txt", mapWithoutPassage);
		characterWithoutPassage = gameWithoutPassage.getCharacter();

		mapEmpty = new MapBuilder("testEmpty.txt");
		gameEmpty = new PacmanGame("helpFilePacman.txt", mapEmpty);
		characterEmpty = gameEmpty.getCharacter();
	}

	/**
	 * S'assurer qu'un joueur sans effet fantôme ne peut pas sortir de la map (vers le haut)
	 * @author Raphaël
	 * @throws Exception
	 */
	@Test
	void testMoveUpWithoutGhostEffectBoundary() throws Exception {
		characterWithoutPassage.setPosX(11);
		characterWithoutPassage.setPosY(2);
		gameWithoutPassage.evolve(Cmd.UP);
		
		assertEquals(11, characterWithoutPassage.getPosX());
		assertEquals(2, characterWithoutPassage.getPosY());
	}
	
	/**
	 * S'assurer qu'un joueur sans effet fantôme ne peut pas sortir de la map (vers le bas)
	 * @author Raphaël
	 * @throws Exception
	 */
	@Test
	void testMoveDownWithoutGhostEffectBoundary() throws Exception {
		characterWithoutPassage.setPosX(11);
		characterWithoutPassage.setPosY(2);
		gameWithoutPassage.evolve(Cmd.DOWN);
		
		assertEquals(11, characterWithoutPassage.getPosX());
		assertEquals(2, characterWithoutPassage.getPosY());
	}
	
	/**
	 * S'assurer qu'un joueur sans effet fantôme ne peut pas sortir de la map (vers la droite)
	 * @author Raphaël
	 * @throws Exception
	 */
	@Test
	void testMoveRightWithoutGhostEffectBoundary() throws Exception {
		characterWithoutPassage.setPosX(11);
		characterWithoutPassage.setPosY(2);
		gameWithoutPassage.evolve(Cmd.RIGHT);
		
		assertEquals(11, characterWithoutPassage.getPosX());
		assertEquals(2, characterWithoutPassage.getPosY());
	}
	
	/**
	 * S'assurer qu'un joueur sans effet fantôme ne peut pas sortir de la map (vers la gauche)
	 * @author Raphaël
	 * @throws Exception
	 */
	@Test
	void testMoveLeftWithoutGhostEffectBoundary() throws Exception {
		characterWithoutPassage.setPosX(11);
		characterWithoutPassage.setPosY(2);
		gameWithoutPassage.evolve(Cmd.LEFT);
		
		assertEquals(11, characterWithoutPassage.getPosX());
		assertEquals(2, characterWithoutPassage.getPosY());
	}
	
	/**
	 * S'assurer qu'un joueur peut traverser des sols accessibles (vers le bas)
	 * @author Raphaël
	 * @throws Exception
	 */
	@Test
	void testMoveDownWithoutEffectRight() throws Exception {
		character.setPosX(9);
		character.setPosY(7);
		game.evolve(Cmd.DOWN);

		assertEquals(9, character.getPosX());
		assertEquals(8, character.getPosY());
	}
	
	/**
	 * S'assurer qu'un joueur peut traverser des sols accessibles (vers le haut)
	 * @author Raphaël
	 * @throws Exception
	 */
	@Test
	void testMoveUpWithoutEffectRight() throws Exception {
		character.setPosX(9);
		character.setPosY(7);
		game.evolve(Cmd.UP);

		assertEquals(9, character.getPosX());
		assertEquals(6, character.getPosY());
	}
	
	/**
	 * S'assurer qu'un joueur peut traverser des sols accessibles (vers la gauche)
	 * @author Raphaël
	 * @throws Exception
	 */
	@Test
	void testMoveLeftWithoutEffectRight() throws Exception {
		character.setPosX(9);
		character.setPosY(7);
		game.evolve(Cmd.LEFT);

		assertEquals(8, character.getPosX());
		assertEquals(7, character.getPosY());
	}
	
	/**
	 * S'assurer qu'un joueur peut traverser des sols accessibles (vers la droite)
	 * @throws Exception
	 */
	@Test
	void testMoveRightWithoutEffectRight() throws Exception {
		character.setPosX(9);
		character.setPosY(7);
		game.evolve(Cmd.RIGHT);

		assertEquals(10, character.getPosX());
		assertEquals(7, character.getPosY());
	}

	/**
	 * S'assurer qu'un joueur ne peut pas traverser les murs avec un effet de vitesse
	 * @author Raphaël
	 * @throws Exception
	 */
	@Test
	void testMoveWithSpeedEffectBoundary() throws Exception {
		character.setPosX(2);
		character.setPosY(1);
		game.evolve(Cmd.IDLE);
		

		testMoveUpWithoutGhostEffectBoundary();
		testMoveDownWithoutGhostEffectBoundary();
		testMoveRightWithoutGhostEffectBoundary();
		testMoveLeftWithoutGhostEffectBoundary();
	}

	/**
	 * S'assurer qu'un joueur peut traverser les cases accessibles avec un effet de vitesse
	 * @author Raphaël
	 * @throws Exception
	 */
	@Test
	void testMoveWithSpeedEffectRight() throws Exception {
		character.setPosX(2);
		character.setPosY(1);
		game.evolve(Cmd.IDLE);
		

		character.setPosX(9);
		character.setPosY(7);
		game.evolve(Cmd.DOWN);

		assertEquals(9, character.getPosX(), "La position du pacman ne doit pas changer en abscisse");
		assertEquals(9, character.getPosY(), "La position du pacman doit augmenter puisqu'il descend");

		character.setPosX(9);
		character.setPosY(7);
		game.evolve(Cmd.UP);

		assertEquals(9, character.getPosX(), "La position du pacman ne doit pas changer en abscisse");
		assertEquals(5, character.getPosY(), "La position du pacman doit diminuer en ordonnée puisqu'il monte");

		character.setPosX(9);
		character.setPosY(7);
		game.evolve(Cmd.LEFT);

		assertEquals(7, character.getPosX(), "La position du pacman doit décrementer en abscisse puisqu'il va à gauche");
		assertEquals(7, character.getPosY(), "La position du pacman ne doit pas changer en ordonnée");

		character.setPosX(9);
		character.setPosY(7);
		game.evolve(Cmd.RIGHT);

		assertEquals(11, character.getPosX(), "La position du pacman doit incrémenter en abscisse puisqu'il va à droite");
		assertEquals(7, character.getPosY(), "La position du pacman ne doit pas changer en ordonnée");
	}
	
	/**
	 * S'assurer qu'un joueur peut traverser les cases accessibles avec un effet de vitesse (vers le bas)
	 * @author Raphaël
	 * @throws Exception
	 */
	@Test
	void testMoveDownWithSpeedEffectRight() throws Exception {
		character.setPosX(2);
		character.setPosY(1);
		game.evolve(Cmd.IDLE);
		

		character.setPosX(9);
		character.setPosY(7);
		game.evolve(Cmd.DOWN);

		assertEquals(9, character.getPosX());
		assertEquals(9, character.getPosY());
	}
	
	/**
	 * S'assurer qu'un joueur peut traverser les cases accessibles avec un effet de vitesse (vers le haut)
	 * @author Raphaël
	 * @throws Exception
	 */
	@Test
	void testMoveUpWithSpeedEffectRight() throws Exception {
		character.setPosX(2);
		character.setPosY(1);
		game.evolve(Cmd.IDLE);
		

		character.setPosX(9);
		character.setPosY(7);
		game.evolve(Cmd.UP);

		assertEquals(9, character.getPosX());
		assertEquals(5, character.getPosY());
	}
	
	/**
	 * S'assurer qu'un joueur peut traverser les cases accessibles avec un effet de vitesse (vers la gauche)
	 * @author Raphaël
	 * @throws Exception
	 */
	@Test
	void testMoveLeftWithSpeedEffectRight() throws Exception {
		character.setPosX(2);
		character.setPosY(1);
		game.evolve(Cmd.IDLE);
		
		
		character.setPosX(9);
		character.setPosY(7);
		game.evolve(Cmd.LEFT);

		assertEquals(7, character.getPosX(), "La position du pacman doit décrementer en abscisse puisqu'il va à gauche");
		assertEquals(7, character.getPosY(), "La position du pacman ne doit pas changer en ordonnée");
	}
	
	/**
	 * S'assurer qu'un joueur peut traverser les cases accessibles avec un effet de vitesse (vers la droite)
	 * @author Raphaël
	 * @throws Exception
	 */
	@Test
	void testMoveRightWithSpeedEffectRight() throws Exception {
		character.setPosX(2);
		character.setPosY(1);
		game.evolve(Cmd.IDLE);
		
		
		character.setPosX(9);
		character.setPosY(7);
		game.evolve(Cmd.RIGHT);

		assertEquals(11, character.getPosX(), "La position du pacman doit incrémenter en abscisse puisqu'il va à droite");
		assertEquals(7, character.getPosY(), "La position du pacman ne doit pas changer en ordonnée");
	}

	/**
	 * S'assurer qu'un joueur sans effet fantôme ne peut pas traverser les murs
	 * @author Raphaël
	 * @throws Exception
	 */
	@Test
	void testMoveWithSlowEffectBoundary() throws Exception {
		character.setPosX(5);
		character.setPosY(1);
		game.evolve(Cmd.IDLE);
		

		testMoveUpWithoutGhostEffectBoundary();
		testMoveDownWithoutGhostEffectBoundary();
		testMoveRightWithoutGhostEffectBoundary();
		testMoveLeftWithoutGhostEffectBoundary();
	}

	/**
	 * S'assurer qu'un joueur avec un effet lenteur se déplace moins lentement que sans effet lié à la vitesse (de 0.5 cases)
	 * @author Raphaël
	 * @throws Exception
	 */
	@Test
	void testMoveWithSlowEffectRight() throws Exception {
		character.setPosX(5);
		character.setPosY(1);
		game.evolve(Cmd.IDLE);
		

		character.setPosX(9);
		character.setPosY(7);
		game.evolve(Cmd.DOWN);

		assertEquals(9, character.getPosX(), "La position du pacman ne doit pas changer en abscisse");
		assertEquals(7.5, character.getPosY(), "La position du pacman doit augmenter puisqu'il descend");

		character.setPosX(9);
		character.setPosY(7);
		game.evolve(Cmd.UP);

		assertEquals(9, character.getPosX(), "La position du pacman ne doit pas changer en abscisse");
		assertEquals(6.5, character.getPosY(), "La position du pacman doit diminuer en ordonnée puisqu'il monte");

		character.setPosX(9);
		character.setPosY(7);
		game.evolve(Cmd.LEFT);

		assertEquals(8.5, character.getPosX(), "La position du pacman doit décrementer en abscisse puisqu'il va à gauche");
		assertEquals(7, character.getPosY(), "La position du pacman ne doit pas changer en ordonnée");

		character.setPosX(9);
		character.setPosY(7);
		game.evolve(Cmd.RIGHT);

		assertEquals(9.5, character.getPosX(), "La position du pacman doit incrémenter en abscisse puisqu'il va à droite");
		assertEquals(7, character.getPosY(), "La position du pacman ne doit pas changer en ordonnée");
	}
	
	/**
	 * S'assurer qu'un joueur avec un effet lenteur se déplace moins lentement que sans effet lié à la vitesse (de 0.5 cases) (vers le bas)
	 * @author Raphaël
	 * @throws Exception
	 */
	@Test
	void testMoveDownWithSlowEffectRight() throws Exception {
		character.setPosX(5);
		character.setPosY(1);
		game.evolve(Cmd.IDLE);
		

		character.setPosX(9);
		character.setPosY(7);
		game.evolve(Cmd.DOWN);

		assertEquals(9, character.getPosX());
		assertEquals(7.5, character.getPosY());
	}
	
	/**
	 * S'assurer qu'un joueur avec un effet lenteur se déplace moins lentement que sans effet lié à la vitesse (de 0.5 cases) (vers le haut)
	 * @author Raphaël
	 * @throws Exception
	 */
	@Test
	void testMoveUpWithSlowEffectRight() throws Exception {
		character.setPosX(5);
		character.setPosY(1);
		game.evolve(Cmd.IDLE);
		

		character.setPosX(9);
		character.setPosY(7);
		game.evolve(Cmd.UP);

		assertEquals(9, character.getPosX());
		assertEquals(6.5, character.getPosY());
	}
	
	/**
	 * S'assurer qu'un joueur avec un effet lenteur se déplace moins lentement que sans effet lié à la vitesse (de 0.5 cases) (vers la gauche)
	 * @author Raphaël
	 * @throws Exception
	 */
	@Test
	void testMoveLeftWithSlowEffectRight() throws Exception {
		character.setPosX(5);
		character.setPosY(1);
		game.evolve(Cmd.IDLE);
		

		character.setPosX(9);
		character.setPosY(7);
		game.evolve(Cmd.LEFT);

		assertEquals(8.5, character.getPosX());
		assertEquals(7, character.getPosY());
	}
	
	/**
	 * S'assurer qu'un joueur avec un effet lenteur se déplace moins lentement que sans effet lié à la vitesse (de 0.5 cases) (vers la droite)
	 * @author Raphaël
	 * @throws Exception
	 */
	@Test
	void testMoveRightWithSlowEffectRight() throws Exception {
		character.setPosX(5);
		character.setPosY(1);
		game.evolve(Cmd.IDLE);
		

		character.setPosX(9);
		character.setPosY(7);
		game.evolve(Cmd.RIGHT);

		assertEquals(9.5, character.getPosX());
		assertEquals(7, character.getPosY());
	}

	/**
	 * S'assurer qu'un joueur ne peut pas bouger avec un effet d'arrêt s'il est bloqué
	 * @author Raphaël
	 * @throws Exception
	 */
	@Test
	void testMoveWithStopEffectBoundary() throws Exception {
		character.setPosX(6);
		character.setPosY(1);
		game.evolve(Cmd.IDLE);
		

		testMoveUpWithoutGhostEffectBoundary();
		testMoveDownWithoutGhostEffectBoundary();
		testMoveRightWithoutGhostEffectBoundary();
		testMoveLeftWithoutGhostEffectBoundary();
	}

	/**
	 * S'assurer qu'un joueur ne peut pas bouger avec un effet arrêt même s'il y a des cases accessibles autour de lui (vers le bas)
	 * @author Raphaël
	 * @throws Exception
	 */
	@Test
	void testMoveDownWithStopEffectRight() throws Exception {
		character.setPosX(6);
		character.setPosY(1);
		game.evolve(Cmd.IDLE);
		

		character.setPosX(9);
		character.setPosY(7);
		game.evolve(Cmd.DOWN);

		assertEquals(9, character.getPosX());
		assertEquals(7, character.getPosY());
	}
	
	/**
	 * S'assurer qu'un joueur ne peut pas bouger avec un effet arrêt même s'il y a des cases accessibles autour de lui (vers le haut)
	 * @author Raphaël
	 * @throws Exception
	 */
	@Test
	void testMoveUpWithStopEffectRight() throws Exception {
		character.setPosX(6);
		character.setPosY(1);
		game.evolve(Cmd.IDLE);
		
		
		character.setPosX(9);
		character.setPosY(7);
		game.evolve(Cmd.UP);

		assertEquals(9, character.getPosX());
		assertEquals(7, character.getPosY());
	}
	
	/**
	 * S'assurer qu'un joueur ne peut pas bouger avec un effet arrêt même s'il y a des cases accessibles autour de lui (vers la gauche)
	 * @author Raphaël
	 * @throws Exception
	 */
	@Test
	void testMoveLeftWithStopEffectRight() throws Exception {
		character.setPosX(6);
		character.setPosY(1);
		game.evolve(Cmd.IDLE);
		
		
		character.setPosX(9);
		character.setPosY(7);
		game.evolve(Cmd.LEFT);

		assertEquals(9, character.getPosX());
		assertEquals(7, character.getPosY());
	}
	
	/**
	 * S'assurer qu'un joueur ne peut pas bouger avec un effet arrêt même s'il y a des cases accessibles autour de lui (vers la droite)
	 * @author Raphaël
	 * @throws Exception
	 */
	@Test
	void testMoveRightWithStopEffectRight() throws Exception {
		character.setPosX(6);
		character.setPosY(1);
		game.evolve(Cmd.IDLE);
		

		character.setPosX(9);
		character.setPosY(7);
		game.evolve(Cmd.RIGHT);

		assertEquals(9, character.getPosX());
		assertEquals(7, character.getPosY());
	}
	
	

	/**
	 * S'assurer qu'un joueur ne peut pas se déplacer avec un effet étourdi s'il est bloqué
	 * @author Raphaël
	 * @throws Exception
	 */
	@Test
	void testMoveWithStunEffectBoundary() throws Exception {
		character.setPosX(7);
		character.setPosY(1);
		game.evolve(Cmd.IDLE);
		

		testMoveUpWithoutGhostEffectBoundary();
		testMoveDownWithoutGhostEffectBoundary();
		testMoveRightWithoutGhostEffectBoundary();
		testMoveLeftWithoutGhostEffectBoundary();
	}

	/**
	 * S'assurer qu'un joueur peut se déplacer avec un effet étourdi s'il y a des cases accessibles autour de lui (vers le bas)
	 * @author Raphaël
	 * @throws Exception
	 */
	@Test
	void testMoveDownWithStunEffectRight() throws Exception {
		character.setPosY(1);
		character.setPosX(7);
		game.evolve(Cmd.UP);
		

		character.setPosX(9);
		character.setPosY(7);
		game.evolve(Cmd.DOWN);

		assertEquals(10, character.getPosX());
		assertEquals(7, character.getPosY());
	}
	
	/**
	 * S'assurer qu'un joueur peut pas se déplacer avec un effet étourdi s'il y a des cases accessibles autour de lui (vers le haut)
	 * @author Raphaël
	 * @throws Exception
	 */
	@Test
	void testMoveUpWithStunEffectRight() throws Exception {
		character.setPosY(1);
		character.setPosX(7);
		game.evolve(Cmd.UP);
		

		character.setPosX(9);
		character.setPosY(7);
		game.evolve(Cmd.UP);

		assertEquals(8, character.getPosX());
		assertEquals(7, character.getPosY());
	}
	
	/**
	 * S'assurer qu'un joueur peut pas se déplacer avec un effet étourdi s'il y a des cases accessibles autour de lui (vers la gauche)
	 * @author Raphaël
	 * @throws Exception
	 */
	@Test
	void testMoveLeftWithStunEffectRight() throws Exception {
		character.setPosY(1);
		character.setPosX(7);
		game.evolve(Cmd.UP);
		
		
		character.setPosX(9);
		character.setPosY(7);
		game.evolve(Cmd.LEFT);

		assertEquals(9, character.getPosX());
		assertEquals(6, character.getPosY());
	}
	
	/**
	 * S'assurer qu'un joueur peut pas se déplacer avec un effet étourdi s'il y a des cases accessibles autour de lui (vers la droite)
	 * @author Raphaël
	 * @throws Exception
	 */
	@Test
	void testMoveRightWithStunEffectRight() throws Exception {
		character.setPosY(1);
		character.setPosX(7);
		game.evolve(Cmd.UP);
		
		
		character.setPosX(9);
		character.setPosY(7);
		game.evolve(Cmd.RIGHT);

		assertEquals(9, character.getPosX());
		assertEquals(8, character.getPosY());
	}

	/**
	 * S'assurer qu'un joueur avec un effet fantôme ne peut pas sortir de la map (coin haut gauche, déplacement = haut)
	 * @author Raphaël
	 * @throws Exception
	 */
	@Test
	void testTopLeftMoveUpWithGhostEffectBoundary() throws Exception {
		characterWithoutPassage.setPosX(3);
		characterWithoutPassage.setPosY(1);
		gameWithoutPassage.evolve(Cmd.IDLE);
		

		characterWithoutPassage.setPosX(0);
		characterWithoutPassage.setPosY(0);
		gameWithoutPassage.evolve(Cmd.UP);

		assertEquals(0, characterWithoutPassage.getPosX());
		assertEquals(0, characterWithoutPassage.getPosY());
	}
	
	/**
	 * S'assurer qu'un joueur avec un effet fantôme ne peut pas sortir de la map (coin haut gauche, déplacement = gauche)
	 * @author Raphaël
	 * @throws Exception
	 */
	@Test
	void testTopLeftMoveLeftWithGhostEffectBoundary() throws Exception {
		characterWithoutPassage.setPosX(3);
		characterWithoutPassage.setPosY(1);
		gameWithoutPassage.evolve(Cmd.IDLE);
		

		characterWithoutPassage.setPosX(0);
		characterWithoutPassage.setPosY(0);
		gameWithoutPassage.evolve(Cmd.LEFT);

		assertEquals(0, characterWithoutPassage.getPosX());
		assertEquals(0, characterWithoutPassage.getPosY());
	}
	
	/**
	 * S'assurer qu'un joueur avec un effet fantôme ne peut pas sortir de la map (coin bas gauche, déplacement = gauche)
	 * @author Raphaël
	 * @throws Exception
	 */
	@Test
	void testBottomLeftMoveLeftWithGhostEffectBoundary() throws Exception {
		characterWithoutPassage.setPosX(3);
		characterWithoutPassage.setPosY(1);
		gameWithoutPassage.evolve(Cmd.IDLE);
		
		
		characterWithoutPassage.setPosX(0);
		characterWithoutPassage.setPosY(10);
		gameWithoutPassage.evolve(Cmd.DOWN);

		assertEquals(0, characterWithoutPassage.getPosX());
		assertEquals(10, characterWithoutPassage.getPosY());
	}
	
	/**
	 * S'assurer qu'un joueur avec un effet fantôme ne peut pas sortir de la map (coin bas gauche, déplacement = bas)
	 * @author Raphaël
	 * @throws Exception
	 */
	@Test
	void testBottomLeftMoveDownWithGhostEffectBoundary() throws Exception {
		characterWithoutPassage.setPosX(3);
		characterWithoutPassage.setPosY(1);
		gameWithoutPassage.evolve(Cmd.IDLE);
		
		
		characterWithoutPassage.setPosX(0);
		characterWithoutPassage.setPosY(10);
		gameWithoutPassage.evolve(Cmd.DOWN);

		assertEquals(0, characterWithoutPassage.getPosX());
		assertEquals(10, characterWithoutPassage.getPosY());
	}
	
	/**
	 * S'assurer qu'un joueur avec un effet fantôme ne peut pas sortir de la map (coin haut droit, déplacement = droite)
	 * @author Raphaël
	 * @throws Exception
	 */
	@Test
	void testTopRightMoveRightWithGhostEffectBoundary() throws Exception {
		characterWithoutPassage.setPosX(3);
		characterWithoutPassage.setPosY(1);
		gameWithoutPassage.evolve(Cmd.IDLE);
		
		
		characterWithoutPassage.setPosX(15);
		characterWithoutPassage.setPosY(0);
		gameWithoutPassage.evolve(Cmd.RIGHT);

		assertEquals(15, characterWithoutPassage.getPosX());
		assertEquals(0, characterWithoutPassage.getPosY());
	}
	
	/**
	 * S'assurer qu'un joueur avec un effet fantôme ne peut pas sortir de la map (coin haut droit, déplacement = haut)
	 * @author Raphaël
	 * @throws Exception
	 */
	@Test
	void testTopRightMoveUpWithGhostEffectBoundary() throws Exception {
		characterWithoutPassage.setPosX(3);
		characterWithoutPassage.setPosY(1);
		gameWithoutPassage.evolve(Cmd.IDLE);
		
		
		characterWithoutPassage.setPosX(15);
		characterWithoutPassage.setPosY(0);
		gameWithoutPassage.evolve(Cmd.UP);

		assertEquals(15, characterWithoutPassage.getPosX());
		assertEquals(0, characterWithoutPassage.getPosY());
	}
	
	/**
	 * S'assurer qu'un joueur avec un effet fantôme ne peut pas sortir de la map (coin bas droit, déplacement = bas)
	 * @author Raphaël
	 * @throws Exception
	 */
	@Test
	void testBottomRightMoveDownWithGhostEffectBoundary() throws Exception {
		characterWithoutPassage.setPosX(3);
		characterWithoutPassage.setPosY(1);
		gameWithoutPassage.evolve(Cmd.IDLE);
		

		characterWithoutPassage.setPosX(15);
		characterWithoutPassage.setPosY(10);
		gameWithoutPassage.evolve(Cmd.DOWN);

		assertEquals(15, characterWithoutPassage.getPosX());
		assertEquals(10, characterWithoutPassage.getPosY());
	}
	
	/**
	 * S'assurer qu'un joueur avec un effet fantôme ne peut pas sortir de la map (coin bas droit, déplacement = droite)
	 * @author Raphaël
	 * @throws Exception
	 */
	@Test
	void testBottomRightMoveRightWithGhostEffectBoundary() throws Exception {
		characterWithoutPassage.setPosX(3);
		characterWithoutPassage.setPosY(1);
		gameWithoutPassage.evolve(Cmd.IDLE);
		

		characterWithoutPassage.setPosX(15);
		characterWithoutPassage.setPosY(10);
		gameWithoutPassage.evolve(Cmd.RIGHT);

		assertEquals(15, characterWithoutPassage.getPosX());
		assertEquals(10, characterWithoutPassage.getPosY());
	}
	
	/**
	 * S'assurer qu'un joueur avec un effet fantôme et vitesse ne peut pas sortir de la map (coin haut gauche, déplacement = haut)
	 * @author Raphaël
	 * @throws Exception
	 */
	@Test
	void testTopLeftMoveUpWithGhostSpeedEffectBoundary() throws Exception {
		characterWithoutPassage.setPosX(2);
		characterWithoutPassage.setPosY(1);
		gameWithoutPassage.evolve(Cmd.IDLE);
		

		characterWithoutPassage.setPosX(3);
		characterWithoutPassage.setPosY(1);
		gameWithoutPassage.evolve(Cmd.IDLE);
		

		characterWithoutPassage.setPosX(0);
		characterWithoutPassage.setPosY(0);
		gameWithoutPassage.evolve(Cmd.UP);
		
		assertEquals(0, characterWithoutPassage.getPosX());
		assertEquals(0, characterWithoutPassage.getPosY());
	}
	
	/**
	 * S'assurer qu'un joueur avec un effet fantôme et vitesse ne peut pas sortir de la map (coin haut gauche, déplacement = gauche)
	 * @author Raphaël
	 * @throws Exception
	 */
	@Test
	void testTopLeftMoveLeftWithGhostSpeedEffectBoundary() throws Exception {
		characterWithoutPassage.setPosX(2);
		characterWithoutPassage.setPosY(1);
		gameWithoutPassage.evolve(Cmd.IDLE);
		

		characterWithoutPassage.setPosX(3);
		characterWithoutPassage.setPosY(1);
		gameWithoutPassage.evolve(Cmd.IDLE);
		

		characterWithoutPassage.setPosX(0);
		characterWithoutPassage.setPosY(0);
		gameWithoutPassage.evolve(Cmd.LEFT);

		assertEquals(0, characterWithoutPassage.getPosX());
		assertEquals(0, characterWithoutPassage.getPosY());
	}
	
	/**
	 * S'assurer qu'un joueur avec un effet fantôme et vitesse ne peut pas sortir de la map (coin bas gauche, déplacement = gauche)
	 * @author Raphaël
	 * @throws Exception
	 */
	@Test
	void testBottomLeftMoveLeftWithGhostSpeedEffectBoundary() throws Exception {
		characterWithoutPassage.setPosX(2);
		characterWithoutPassage.setPosY(1);
		gameWithoutPassage.evolve(Cmd.IDLE);
		

		characterWithoutPassage.setPosX(3);
		characterWithoutPassage.setPosY(1);
		gameWithoutPassage.evolve(Cmd.IDLE);
		
		
		characterWithoutPassage.setPosX(0);
		characterWithoutPassage.setPosY(10);
		gameWithoutPassage.evolve(Cmd.DOWN);

		assertEquals(0, characterWithoutPassage.getPosX());
		assertEquals(10, characterWithoutPassage.getPosY());
	}
	
	/**
	 * S'assurer qu'un joueur avec un effet fantôme et vitesse ne peut pas sortir de la map (coin bas gauche, déplacement = bas)
	 * @author Raphaël
	 * @throws Exception
	 */
	@Test
	void testBottomLeftMoveDownWithGhostSpeedEffectBoundary() throws Exception {
		characterWithoutPassage.setPosX(2);
		characterWithoutPassage.setPosY(1);
		gameWithoutPassage.evolve(Cmd.IDLE);
		

		characterWithoutPassage.setPosX(3);
		characterWithoutPassage.setPosY(1);
		gameWithoutPassage.evolve(Cmd.IDLE);
		
		
		characterWithoutPassage.setPosX(0);
		characterWithoutPassage.setPosY(10);
		gameWithoutPassage.evolve(Cmd.DOWN);

		assertEquals(0, characterWithoutPassage.getPosX());
		assertEquals(10, characterWithoutPassage.getPosY());
	}
	
	/**
	 * S'assurer qu'un joueur avec un effet fantôme et vitesse ne peut pas sortir de la map (coin haut droit, déplacement = droite)
	 * @author Raphaël
	 * @throws Exception
	 */
	@Test
	void testTopRightMoveRightWithGhostSpeedEffectBoundary() throws Exception {
		characterWithoutPassage.setPosX(2);
		characterWithoutPassage.setPosY(1);
		gameWithoutPassage.evolve(Cmd.IDLE);
		

		characterWithoutPassage.setPosX(3);
		characterWithoutPassage.setPosY(1);
		gameWithoutPassage.evolve(Cmd.IDLE);
		
		
		characterWithoutPassage.setPosX(15);
		characterWithoutPassage.setPosY(0);
		gameWithoutPassage.evolve(Cmd.RIGHT);

		assertEquals(15, characterWithoutPassage.getPosX());
		assertEquals(0, characterWithoutPassage.getPosY());
	}
	
	/**
	 * S'assurer qu'un joueur avec un effet fantôme et vitesse ne peut pas sortir de la map (coin haut droit, déplacement = haut)
	 * @author Raphaël
	 * @throws Exception
	 */
	@Test
	void testTopRightMoveUpWithGhostSpeedEffectBoundary() throws Exception {
		characterWithoutPassage.setPosX(2);
		characterWithoutPassage.setPosY(1);
		gameWithoutPassage.evolve(Cmd.IDLE);
		

		characterWithoutPassage.setPosX(3);
		characterWithoutPassage.setPosY(1);
		gameWithoutPassage.evolve(Cmd.IDLE);
		
		
		characterWithoutPassage.setPosX(15);
		characterWithoutPassage.setPosY(0);
		gameWithoutPassage.evolve(Cmd.UP);

		assertEquals(15, characterWithoutPassage.getPosX());
		assertEquals(0, characterWithoutPassage.getPosY());
	}
	
	/**
	 * S'assurer qu'un joueur avec un effet fantôme et vitesse ne peut pas sortir de la map (coin bas droit, déplacement = bas)
	 * @author Raphaël
	 * @throws Exception
	 */
	@Test
	void testBottomRightMoveDownWithGhostSpeedEffectBoundary() throws Exception {
		characterWithoutPassage.setPosX(2);
		characterWithoutPassage.setPosY(1);
		gameWithoutPassage.evolve(Cmd.IDLE);
		

		characterWithoutPassage.setPosX(3);
		characterWithoutPassage.setPosY(1);
		gameWithoutPassage.evolve(Cmd.IDLE);
		

		characterWithoutPassage.setPosX(15);
		characterWithoutPassage.setPosY(10);
		gameWithoutPassage.evolve(Cmd.DOWN);

		assertEquals(15, characterWithoutPassage.getPosX());
		assertEquals(10, characterWithoutPassage.getPosY());
	}
	
	/**
	 * S'assurer qu'un joueur avec un effet fantôme et vitesse ne peut pas sortir de la map (coin bas droit, déplacement = droite)
	 * @author Raphaël
	 * @throws Exception
	 */
	@Test
	void testBottomRightMoveRightWithGhostSpeedEffectBoundary() throws Exception {
		characterWithoutPassage.setPosX(2);
		characterWithoutPassage.setPosY(1);
		gameWithoutPassage.evolve(Cmd.IDLE);
		

		characterWithoutPassage.setPosX(3);
		characterWithoutPassage.setPosY(1);
		gameWithoutPassage.evolve(Cmd.IDLE);
		

		characterWithoutPassage.setPosX(15);
		characterWithoutPassage.setPosY(10);
		gameWithoutPassage.evolve(Cmd.RIGHT);

		assertEquals(15, characterWithoutPassage.getPosX());
		assertEquals(10, characterWithoutPassage.getPosY());
	}
	
	/**
	 * S'assurer qu'un joueur avec un effet fantôme et lent ne peut pas sortir de la map (coin haut gauche, déplacement = haut)
	 * @author Raphaël
	 * @throws Exception
	 */
	@Test
	void testTopLeftMoveUpWithGhostSlowEffectBoundary() throws Exception {
		characterWithoutPassage.setPosX(5);
		characterWithoutPassage.setPosY(1);
		gameWithoutPassage.evolve(Cmd.IDLE);
		

		characterWithoutPassage.setPosX(4);
		characterWithoutPassage.setPosY(1);
		gameWithoutPassage.evolve(Cmd.LEFT);
		

		characterWithoutPassage.setPosX(0);
		characterWithoutPassage.setPosY(0);
		gameWithoutPassage.evolve(Cmd.UP);
		
		assertEquals(0, characterWithoutPassage.getPosX());
		assertEquals(0, characterWithoutPassage.getPosY());
	}
	
	/**
	 * S'assurer qu'un joueur avec un effet fantôme et lent ne peut pas sortir de la map (coin haut gauche, déplacement = gauche)
	 * @author Raphaël
	 * @throws Exception
	 */
	@Test
	void testTopLeftMoveLeftWithGhostSlowEffectBoundary() throws Exception {
		characterWithoutPassage.setPosX(5);
		characterWithoutPassage.setPosY(1);
		gameWithoutPassage.evolve(Cmd.IDLE);
		

		characterWithoutPassage.setPosX(4);
		characterWithoutPassage.setPosY(1);
		gameWithoutPassage.evolve(Cmd.LEFT);
		

		characterWithoutPassage.setPosX(0);
		characterWithoutPassage.setPosY(0);
		gameWithoutPassage.evolve(Cmd.LEFT);

		assertEquals(0, characterWithoutPassage.getPosX());
		assertEquals(0, characterWithoutPassage.getPosY());
	}
	
	/**
	 * S'assurer qu'un joueur avec un effet fantôme et lent ne peut pas sortir de la map (coin bas gauche, déplacement = gauche)
	 * @author Raphaël
	 * @throws Exception
	 */
	@Test
	void testBottomLeftMoveLeftWithGhostSlowEffectBoundary() throws Exception {
		characterWithoutPassage.setPosX(5);
		characterWithoutPassage.setPosY(1);
		gameWithoutPassage.evolve(Cmd.IDLE);
		

		characterWithoutPassage.setPosX(4);
		characterWithoutPassage.setPosY(1);
		gameWithoutPassage.evolve(Cmd.LEFT);
		
		
		characterWithoutPassage.setPosX(0);
		characterWithoutPassage.setPosY(10);
		gameWithoutPassage.evolve(Cmd.DOWN);

		assertEquals(0, characterWithoutPassage.getPosX());
		assertEquals(10, characterWithoutPassage.getPosY());
	}
	
	/**
	 * S'assurer qu'un joueur avec un effet fantôme et lent ne peut pas sortir de la map (coin bas gauche, déplacement = bas)
	 * @author Raphaël
	 * @throws Exception
	 */
	@Test
	void testBottomLeftMoveDownWithGhostSlowEffectBoundary() throws Exception {
		characterWithoutPassage.setPosX(5);
		characterWithoutPassage.setPosY(1);
		gameWithoutPassage.evolve(Cmd.IDLE);
		

		characterWithoutPassage.setPosX(4);
		characterWithoutPassage.setPosY(1);
		gameWithoutPassage.evolve(Cmd.LEFT);
		
		
		characterWithoutPassage.setPosX(0);
		characterWithoutPassage.setPosY(10);
		gameWithoutPassage.evolve(Cmd.DOWN);

		assertEquals(0, characterWithoutPassage.getPosX());
		assertEquals(10, characterWithoutPassage.getPosY());
	}
	
	/**
	 * S'assurer qu'un joueur avec un effet fantôme et lent ne peut pas sortir de la map (coin haut droit, déplacement = droite)
	 * @author Raphaël
	 * @throws Exception
	 */
	@Test
	void testTopRightMoveRightWithGhostSlowEffectBoundary() throws Exception {
		characterWithoutPassage.setPosX(5);
		characterWithoutPassage.setPosY(1);
		gameWithoutPassage.evolve(Cmd.IDLE);
		

		characterWithoutPassage.setPosX(4);
		characterWithoutPassage.setPosY(1);
		gameWithoutPassage.evolve(Cmd.LEFT);
		
		
		characterWithoutPassage.setPosX(15);
		characterWithoutPassage.setPosY(0);
		gameWithoutPassage.evolve(Cmd.RIGHT);

		assertEquals(15, characterWithoutPassage.getPosX());
		assertEquals(0, characterWithoutPassage.getPosY());
	}
	
	/**
	 * S'assurer qu'un joueur avec un effet fantôme et lent ne peut pas sortir de la map (coin haut droit, déplacement = haut)
	 * @author Raphaël
	 * @throws Exception
	 */
	@Test
	void testTopRightMoveUpWithGhostSpeedSlowBoundary() throws Exception {
		characterWithoutPassage.setPosX(5);
		characterWithoutPassage.setPosY(1);
		gameWithoutPassage.evolve(Cmd.IDLE);
		

		characterWithoutPassage.setPosX(4);
		characterWithoutPassage.setPosY(1);
		gameWithoutPassage.evolve(Cmd.LEFT);
		
		
		characterWithoutPassage.setPosX(15);
		characterWithoutPassage.setPosY(0);
		gameWithoutPassage.evolve(Cmd.UP);

		assertEquals(15, characterWithoutPassage.getPosX());
		assertEquals(0, characterWithoutPassage.getPosY());
	}
	
	/**
	 * S'assurer qu'un joueur avec un effet fantôme et lent ne peut pas sortir de la map (coin bas droit, déplacement = bas)
	 * @author Raphaël
	 * @throws Exception
	 */
	@Test
	void testBottomRightMoveDownWithGhostSlowEffectBoundary() throws Exception {
		characterWithoutPassage.setPosX(5);
		characterWithoutPassage.setPosY(1);
		gameWithoutPassage.evolve(Cmd.IDLE);
		

		characterWithoutPassage.setPosX(4);
		characterWithoutPassage.setPosY(1);
		gameWithoutPassage.evolve(Cmd.LEFT);
		

		characterWithoutPassage.setPosX(15);
		characterWithoutPassage.setPosY(10);
		gameWithoutPassage.evolve(Cmd.DOWN);

		assertEquals(15, characterWithoutPassage.getPosX());
		assertEquals(10, characterWithoutPassage.getPosY());
	}
	
	/**
	 * S'assurer qu'un joueur avec un effet fantôme et lent ne peut pas sortir de la map (coin bas droit, déplacement = droite)
	 * @author Raphaël
	 * @throws Exception
	 */
	@Test
	void testBottomRightMoveRightWithGhostSlowEffectBoundary() throws Exception {
		characterWithoutPassage.setPosX(5);
		characterWithoutPassage.setPosY(1);
		gameWithoutPassage.evolve(Cmd.IDLE);
		

		characterWithoutPassage.setPosX(4);
		characterWithoutPassage.setPosY(1);
		gameWithoutPassage.evolve(Cmd.LEFT);
		

		characterWithoutPassage.setPosX(15);
		characterWithoutPassage.setPosY(10);
		gameWithoutPassage.evolve(Cmd.RIGHT);

		assertEquals(15, characterWithoutPassage.getPosX());
		assertEquals(10, characterWithoutPassage.getPosY());
	}

	/**
	 * S'assurer qu'un joueur avec un effet fantôme peut traverser les murs (vers le bas)
	 * @author Raphaël
	 * @throws Exception
	 */
	@Test
	void testMoveDownWithGhostEffectRight() throws Exception {
		character.setPosX(3);
		character.setPosY(1);
		game.evolve(Cmd.IDLE);
		

		game.evolve(Cmd.DOWN);
		game.evolve(Cmd.DOWN);

		assertEquals(3, character.getPosX());
		assertEquals(3, character.getPosY());
	}
	
	/**
	 * S'assurer qu'un joueur avec un effet fantôme peut traverser les murs (vers le haut)
	 * @author Raphaël
	 * @throws Exception
	 */
	@Test
	void testMoveUpWithGhostEffectRight() throws Exception {
		character.setPosX(3);
		character.setPosY(1);
		game.evolve(Cmd.IDLE);
		
		
		character.setPosX(3);
		character.setPosY(3);
		game.evolve(Cmd.UP);

		assertEquals(3, character.getPosX());
		assertEquals(2, character.getPosY());
	}
	
	/**
	 * S'assurer qu'un joueur avec un effet fantôme peut traverser les murs (vers la gauche)
	 * @author Raphaël
	 * @throws Exception
	 */
	@Test
	void testMoveLeftWithGhostEffectRight() throws Exception {
		character.setPosX(3);
		character.setPosY(1);
		game.evolve(Cmd.IDLE);
		
		
		character.setPosX(3);
		character.setPosY(2);
		game.evolve(Cmd.LEFT);

		assertEquals(2, character.getPosX());
		assertEquals(2, character.getPosY());
		game.evolve(Cmd.RIGHT);

		assertEquals(3, character.getPosX());
		assertEquals(2, character.getPosY());
	}
	
	/**
	 * S'assurer qu'un joueur avec un effet fantôme peut traverser les murs (vers la droite)
	 * @author Raphaël
	 * @throws Exception
	 */
	@Test
	void testMoveRightWithGhostEffectRight() throws Exception {
		character.setPosX(3);
		character.setPosY(1);
		game.evolve(Cmd.IDLE);
		
		
		character.setPosX(2);
		character.setPosY(2);
		game.evolve(Cmd.RIGHT);

		assertEquals(3, character.getPosX());
		assertEquals(2, character.getPosY());
	}

	/**
	 * S'assurer qu'un joueur peut ramasser tous les effets à une vitesse normale
	 * @author Raphaël
	 * @throws Exception
	 */
	@Test
	void testPickupItemsWithNormalSpeedRight() throws Exception {
		character.setPosX(1);
		character.setPosY(1);

		for (int i = 0; i < 3; i++) {
			character.setSpeed(1);
			game.evolve(Cmd.RIGHT);
		}

		assertEquals(3, AsyncEffect.getEffects().size());
	}
	
	/**
	 * S'assurer qu'un joueur ne peut pas ramasser d'effets à une vitesse normale s'il les a déjà tous obtenu
	 * @author Raphaël
	 * @throws Exception
	 */
	@Test
	void testPickupItemsWithNormalSpeedBoundary() throws Exception {
		character.setPosX(1);
		character.setPosY(1);

		for (int i = 0; i < 6; i++) {
			character.setSpeed(1);
			game.evolve(Cmd.RIGHT);
			Thread.sleep(10);
		}

		assertEquals(5, AsyncEffect.getEffects().size());
	}

	/**
	 * S'assurer qu'un joueur peut ramasser tous les effets à une vitesse réduite
	 * @author Raphaël
	 * @throws Exception
	 */
	@Test
	void testPickupItemsWithSlowSpeedRight() throws Exception {
		character.setPosX(1);
		character.setPosY(1);

		for (int i = 0; i < 6; i++) {
			character.setSpeed(0.5);
			game.evolve(Cmd.RIGHT);
			
		}

		assertEquals(3, AsyncEffect.getEffects().size());
	}
	
	/**
	 * S'assurer qu'un joueur ne peut pas ramasser d'effets à une vitesse réduite s'il les a déjà tous obtenu
	 * @author Raphaël
	 * @throws Exception
	 */
	@Test
	void testPickupItemsWithSlowSpeedBoundary() throws Exception {
		character.setPosX(1);
		character.setPosY(1);

		for (int i = 0; i < 12; i++) {
			character.setSpeed(0.5);
			game.evolve(Cmd.RIGHT);
			
		}

		assertEquals(5, AsyncEffect.getEffects().size());
	}

	/**
	 * S'assurer qu'un joueur avec une vitesse normale peut se téléporter puis se déplacer et se retéléporter (vers le haut)
	 * @author Raphaël
	 * @throws Exception
	 */
	@Test
	void testPassageUpRight() throws Exception {
		
		characterPassageTwo.setPosX(6);
		characterPassageTwo.setPosY(0);
		gamePassageTwo.evolve(Cmd.UP);
		

		assertEquals(1, characterPassageTwo.getPosX());
		assertEquals(2, characterPassageTwo.getPosY());
	}
	
	/**
	 * S'assurer qu'un joueur avec une vitesse normale peut se téléporter puis se déplacer et se retéléporter (vers la droite)
	 * @author Raphaël
	 * @throws Exception
	 */
	@Test
	void testPassageRightRight() throws Exception {
		
		characterPassageTwo.setPosX(1);
		characterPassageTwo.setPosY(2);
		gamePassageTwo.evolve(Cmd.RIGHT);
		

		assertEquals(2, characterPassageTwo.getPosX());
		assertEquals(2, characterPassageTwo.getPosY());
	}
	
	/**
	 * S'assurer qu'un joueur avec une vitesse normale peut se téléporter puis se déplacer et se retéléporter (vers la gauche)
	 * @author Raphaël
	 * @throws Exception
	 */
	@Test
	void testPassageLeftRight() throws Exception {
		
		characterPassageTwo.setPosX(2);
		characterPassageTwo.setPosY(2);
		gamePassageTwo.evolve(Cmd.LEFT);
		

		assertEquals(6, characterPassageTwo.getPosX());
		assertEquals(0, characterPassageTwo.getPosY());
	}
	
	/**
	 * S'assurer qu'un joueur avec une vitesse lente ne se retéléporte pas directement lorsqu'il est encore à l'intérieur du portail
	 * @author Raphaël
	 * @throws Exception
	 */
	@Test
	void testPassageBlockedBoundary() throws Exception {
		characterPassageTwo.setSpeed(0.5);
		characterPassageTwo.setPosX(6);
		characterPassageTwo.setPosY(0);
		gamePassageTwo.evolve(Cmd.UP);

		assertEquals(1, characterPassageTwo.getPosX());
		assertEquals(2, characterPassageTwo.getPosY());
		gamePassageTwo.evolve(Cmd.DOWN);

		assertEquals(1, characterPassageTwo.getPosX());
		assertEquals(2, characterPassageTwo.getPosY());
	}

	/**
	 * S'assurer qu'un joueur avec une vitesse lente ne se retéléporte pas directement lorsqu'il se déplace en dehors du portail
	 * @author Raphaël
	 * @throws Exception
	 */
	@Test
	void testNearPassageBoundary() throws Exception {
		characterPassageTwo.setSpeed(0.5);
		characterPassageTwo.setPosX(6);
		characterPassageTwo.setPosY(0);
		gamePassageTwo.evolve(Cmd.UP);

		assertEquals(1, characterPassageTwo.getPosX());
		assertEquals(2, characterPassageTwo.getPosY());
		gamePassageTwo.evolve(Cmd.RIGHT);

		assertEquals(1.5, characterPassageTwo.getPosX());
		assertEquals(2, characterPassageTwo.getPosY());
	}
	
	/**
	 * S'assurer qu'un joueur avec une vitesse lente ne se retéléporte pas directement lorsqu'il se déplace en dehors du portail + tester si la téléportation fonctionne dans les deux sens
	 * @author Raphaël
	 * @throws Exception
	 */
	@Test
	void testTwoWayPassageBoundary() throws Exception {
		characterPassageTwo.setSpeed(0.5);
		characterPassageTwo.setPosX(6);
		characterPassageTwo.setPosY(0);
		gamePassageTwo.evolve(Cmd.UP);

		assertEquals(1, characterPassageTwo.getPosX());
		assertEquals(2, characterPassageTwo.getPosY());
		gamePassageTwo.evolve(Cmd.RIGHT);

		assertEquals(1.5, characterPassageTwo.getPosX());
		assertEquals(2, characterPassageTwo.getPosY());
		gamePassageTwo.evolve(Cmd.RIGHT);

		assertEquals(2, characterPassageTwo.getPosX());
		assertEquals(2, characterPassageTwo.getPosY());
		gamePassageTwo.evolve(Cmd.LEFT);

		assertEquals(6, characterPassageTwo.getPosX());
		assertEquals(0, characterPassageTwo.getPosY());
	}

	/**
	 * S'assurer qu'un joueur bloqué dans un portail peut tout de même se téléporter à un autre portail (vers le haut)
	 * @author Raphaël
	 * @throws Exception
	 */
	@Test
	void testUpCompletelyBlockedPassageBoundary() throws Exception {
		characterPassage.setPosX(6);
		characterPassage.setPosY(0);
		gamePassage.evolve(Cmd.UP);

		assertEquals(1, characterPassage.getPosX());
		assertEquals(2, characterPassage.getPosY());
	}
	
	/**
	 * S'assurer qu'un joueur bloqué dans un portail peut tout de même se téléporter à un autre portail (vers le bas)
	 * @author Raphaël
	 * @throws Exception
	 */
	@Test
	void testDownCompletelyBlockedPassageBoundary() throws Exception {
		characterPassage.setPosX(6);
		characterPassage.setPosY(0);
		gamePassage.evolve(Cmd.DOWN);

		assertEquals(1, characterPassage.getPosX());
		assertEquals(2, characterPassage.getPosY());
	}
	
	/**
	 * S'assurer qu'un joueur bloqué dans un portail peut tout de même se téléporter à un autre portail (vers la droite)
	 * @author Raphaël
	 * @throws Exception
	 */
	@Test
	void testRightCompletelyBlockedPassageBoundary() throws Exception {
		characterPassage.setPosX(6);
		characterPassage.setPosY(0);
		gamePassage.evolve(Cmd.RIGHT);

		assertEquals(1, characterPassage.getPosX());
		assertEquals(2, characterPassage.getPosY());
	}
	
	/**
	 * S'assurer qu'un joueur bloqué dans un portail peut tout de même se téléporter à un autre portail (vers la gauche)
	 * @author Raphaël
	 * @throws Exception
	 */
	@Test
	void testLeftCompletelyBlockedPassageBoundary() throws Exception {
		characterPassage.setPosX(6);
		characterPassage.setPosY(0);
		gamePassage.evolve(Cmd.LEFT);

		assertEquals(1, characterPassage.getPosX());
		assertEquals(2, characterPassage.getPosY());
	}

	/**
	 * Test avec une carte totalement vide (le joueur ne peut pas se déplacer) (vers le haut)
	 * @author Raphaël
	 * @throws Exception
	 */
	@Test
	void testEmpty() throws Exception {
		gameEmpty.evolve(Cmd.UP);
		assertEquals(0, characterEmpty.getPosX());
		assertEquals(0, characterEmpty.getPosY());
	}
	
	/**
	 * Test avec une carte totalement vide (le joueur ne peut pas se déplacer) (vers le bas)
	 * @author Raphaël
	 * @throws Exception
	 */
	@Test
	void testDownEmpty() throws Exception {
		gameEmpty.evolve(Cmd.DOWN);
		assertEquals(0, characterEmpty.getPosX());
		assertEquals(0, characterEmpty.getPosY());
	}
	
	/**
	 * Test avec une carte totalement vide (le joueur ne peut pas se déplacer) (vers la droite)
	 * @author Raphaël
	 * @throws Exception
	 */
	@Test
	void testRightEmpty() throws Exception {
		gameEmpty.evolve(Cmd.RIGHT);
		assertEquals(0, characterEmpty.getPosX());
		assertEquals(0, characterEmpty.getPosY());
	}
	
	/**
	 * Test avec une carte totalement vide (le joueur ne peut pas se déplacer) (vers la gauche)
	 * @author Raphaël
	 * @throws Exception
	 */
	@Test
	void testLeftEmpty() throws Exception {
		gameEmpty.evolve(Cmd.LEFT);
		assertEquals(0, characterEmpty.getPosX());
		assertEquals(0, characterEmpty.getPosY());
	}
}