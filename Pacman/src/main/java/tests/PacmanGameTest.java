package tests;

import engine.Cmd;
import engine.MapBuilder;
import model.PacmanCharacter;
import model.PacmanGame;
import model.effect.AsyncEffect;
import model.effect.Effect;
import model.movingStrategy.DefaultMovingStrategy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PacmanGameTest {
	
    private PacmanGame game, gamePassage, gamePassageTwo, gameWithoutPassage, gameEmpty;
	private PacmanCharacter character, characterPassage, characterPassageTwo, characterWithoutPassage, characterEmpty;
	private MapBuilder map, mapPassage, mapPassageTwo, mapWithoutPassage, mapEmpty;
	private int index = 0;

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
     * S'assurer qu'un joueur sans effet fantôme ne peut pas sortir de la map
     * @throws InterruptedException
     */
    @Test
    void testMoveWithoutGhostEffectBoundary() throws InterruptedException {
    	AsyncEffect.end(Effect.class);
    	characterWithoutPassage.setPosX(11);
    	characterWithoutPassage.setPosY(2);
    	gameWithoutPassage.evolve(Cmd.UP);
    	Thread.sleep(10);
    	
    	assertEquals(11, characterWithoutPassage.getPosX(), "La position du pacman ne doit pas changer");
    	assertEquals(2, characterWithoutPassage.getPosY(), "La position du pacman ne doit pas changer");

		gameWithoutPassage.evolve(Cmd.DOWN);
    	assertEquals(11, characterWithoutPassage.getPosX(), "La position du pacman ne doit pas changer");
    	assertEquals(2, characterWithoutPassage.getPosY(), "La position du pacman ne doit pas changer");

		gameWithoutPassage.evolve(Cmd.RIGHT);
    	assertEquals(11, characterWithoutPassage.getPosX(), "La position du pacman ne doit pas changer");
    	assertEquals(2, characterWithoutPassage.getPosY(), "La position du pacman ne doit pas changer");

		gameWithoutPassage.evolve(Cmd.LEFT);
    	assertEquals(11, characterWithoutPassage.getPosX(), "La position du pacman ne doit pas changer");
    	assertEquals(2, characterWithoutPassage.getPosY(), "La position du pacman ne doit pas changer");
    	AsyncEffect.end(Effect.class);
    }
    
    /**
     * S'assurer qu'un joueur peut traverser des sols accessibles
     * @throws InterruptedException
     */
    @Test
    void testMoveWithoutEffectRight() throws InterruptedException { 
    	AsyncEffect.end(Effect.class);
    	character.setPosX(9);
    	character.setPosY(7);
    	game.evolve(Cmd.DOWN);
    	Thread.sleep(10);
    	
    	assertEquals(9, character.getPosX(), "La position du pacman ne doit pas changer en abscisse");
    	assertEquals(8, character.getPosY(), "La position du pacman doit augmenter puisqu'il descend");
    	
    	character.setPosX(9);
    	character.setPosY(7);
    	game.evolve(Cmd.UP);
    	
    	assertEquals(9, character.getPosX(), "La position du pacman ne doit pas changer en abscisse");
    	assertEquals(6, character.getPosY(), "La position du pacman doit diminuer en ordonnée puisqu'il monte");
    	
    	character.setPosX(9);
    	character.setPosY(7);
    	game.evolve(Cmd.LEFT);
    	
    	assertEquals(8, character.getPosX(), "La position du pacman doit décrementer en abscisse puisqu'il va à gauche");
    	assertEquals(7, character.getPosY(), "La position du pacman ne doit pas changer en ordonnée");
    	
    	character.setPosX(9);
    	character.setPosY(7);
    	game.evolve(Cmd.RIGHT);
    	
    	assertEquals(10, character.getPosX(), "La position du pacman doit incrémenter en abscisse puisqu'il va à droite");
    	assertEquals(7, character.getPosY(), "La position du pacman ne doit pas changer en ordonnée");
    	AsyncEffect.end(Effect.class);
    }
    
    /**
     * S'assurer qu'un joueur ne peut pas traverser les murs avec un effet de vitesse
     * @throws InterruptedException
     */
    @Test
    void testMoveWithSpeedEffectBoundary() throws InterruptedException {
    	AsyncEffect.end(Effect.class);
    	character.setPosX(2);
    	character.setPosY(1);
    	game.evolve(Cmd.IDLE);
    	Thread.sleep(10);
    	
    	testMoveWithoutGhostEffectBoundary();
    	AsyncEffect.end(Effect.class);
    }
    
    /**
     * S'assurer qu'un joueur peut traverser les cases accessibles avec un effet de vitesse
     * @throws InterruptedException
     */
    @Test
    void testMoveWithSpeedEffectRight() throws InterruptedException {
    	AsyncEffect.end(Effect.class);
    	character.setPosX(2);
    	character.setPosY(1);
    	game.evolve(Cmd.IDLE);
    	Thread.sleep(10);
    	
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
    	AsyncEffect.end(Effect.class);
    }
    
    /**
     * S'assurer qu'un joueur sans effet fantôme ne peut pas traverser les murs
     * @throws InterruptedException
     */
    @Test
    void testMoveWithSlowEffectBoundary() throws InterruptedException {
    	AsyncEffect.end(Effect.class);
    	character.setPosX(5);
    	character.setPosY(1);
    	game.evolve(Cmd.IDLE);
    	Thread.sleep(10);
    	
    	testMoveWithoutGhostEffectBoundary();
    	AsyncEffect.end(Effect.class);
    }
    
    /**
     * S'assurer qu'un joueur avec un effet lenteur se déplace moins lentement que sans effet lié à la vitesse (de 0.5 cases)
     * @throws InterruptedException
     */
    @Test
    void testMoveWithSlowEffectRight() throws InterruptedException {
    	AsyncEffect.end(Effect.class);
    	character.setPosX(5);
    	character.setPosY(1);
    	game.evolve(Cmd.IDLE);
    	Thread.sleep(10);
    	
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
    	AsyncEffect.end(Effect.class);
    }
    
    /**
     * S'assurer qu'un joueur ne peut pas bouger avec un effet d'arrêt s'il est bloqué
     * @throws InterruptedException
     */
    @Test
    void testMoveWithStopEffectBoundary() throws InterruptedException {
    	AsyncEffect.end(Effect.class);
    	character.setPosX(6);
    	character.setPosY(1);
    	game.evolve(Cmd.IDLE);
    	Thread.sleep(10);
    	
    	testMoveWithoutGhostEffectBoundary();
    	AsyncEffect.end(Effect.class);
    }
    
    /**
     * S'assurer qu'un joueur ne peut pas bouger avec un effet arrêt même s'il y a des cases accessibles autour de lui
     * @throws InterruptedException
     */
    @Test
    void testMoveWithStopEffectRight() throws InterruptedException {
    	AsyncEffect.end(Effect.class);
    	character.setPosX(6);
    	character.setPosY(1);
    	game.evolve(Cmd.IDLE);
    	Thread.sleep(10);
    	
    	character.setPosX(9);
    	character.setPosY(7);
    	game.evolve(Cmd.DOWN);
    	
    	assertEquals(9, character.getPosX(), "La position du pacman ne doit pas changer");
    	assertEquals(7, character.getPosY(), "La position du pacman ne doit pas changer");
    	
    	character.setPosX(9);
    	character.setPosY(7);
    	game.evolve(Cmd.UP);
    	
    	assertEquals(9, character.getPosX(), "La position du pacman ne doit pas changer");
    	assertEquals(7, character.getPosY(), "La position du pacman ne doit pas changer");
    	
    	character.setPosX(9);
    	character.setPosY(7);
    	game.evolve(Cmd.LEFT);
    	
    	assertEquals(9, character.getPosX(), "La position du pacman ne doit pas changer");
    	assertEquals(7, character.getPosY(), "La position du pacman ne doit pas changer");
    	
    	character.setPosX(9);
    	character.setPosY(7);
    	game.evolve(Cmd.RIGHT);
    	
    	assertEquals(9, character.getPosX(), "La position du pacman ne doit pas changer");
    	assertEquals(7, character.getPosY(), "La position du pacman ne doit pas changer");
    	AsyncEffect.end(Effect.class);
    }
    
    /**
     * S'assurer qu'un joueur ne peut pas se déplacer avec un effet étourdi s'il est bloqué
     * @throws InterruptedException
     */
    @Test
    void testMoveWithStunEffectBoundary() throws InterruptedException {
    	AsyncEffect.end(Effect.class);
    	character.setPosX(7);
    	character.setPosY(1);
    	game.evolve(Cmd.IDLE);
    	Thread.sleep(10);
    	
    	testMoveWithoutGhostEffectBoundary();
    	AsyncEffect.end(Effect.class);
    }
    
    /**
     * S'assurer qu'un joueur ne peut pas se déplacer avec un effet étourdi même s'il y a des cases accessibles autour de lui
     * @throws InterruptedException
     */
    @Test
    void testMoveWithStunEffectRight() throws InterruptedException {
    	AsyncEffect.end(Effect.class);
    	character.setPosY(1);
    	character.setPosX(7);
    	game.evolve(Cmd.UP);
    	Thread.sleep(10);
    	
    	character.setPosX(9);
    	character.setPosY(7);
    	game.evolve(Cmd.DOWN);
    	
    	assertEquals(10, character.getPosX(), "La position du pacman doit incrémenter (axe y = axe x)");
    	assertEquals(7, character.getPosY(), "La position du pacman ne doit pas changer");
    	
    	character.setPosX(9);
    	character.setPosY(7);
    	game.evolve(Cmd.UP);
    	
    	assertEquals(8, character.getPosX(), "La position du pacman doit décrémenter (axe y = axe x)");
    	assertEquals(7, character.getPosY(), "La position du pacman ne doit pas changer");
    	
    	character.setPosX(9);
    	character.setPosY(7);
    	game.evolve(Cmd.LEFT);
    	
    	assertEquals(9, character.getPosX(), "La position du pacman ne doit pas changer");
    	assertEquals(6, character.getPosY(), "La position du pacman doit décrémenter (axe x = axe y)");
    	
    	character.setPosX(9);
    	character.setPosY(7);
    	game.evolve(Cmd.RIGHT);
    	
    	assertEquals(9, character.getPosX(), "La position du pacman ne doit pas changer");
    	assertEquals(8, character.getPosY(), "La position du pacman doit incrémenter (axe x = axe y)");
    	AsyncEffect.end(Effect.class);
    }
    
    /**
     * S'assurer qu'un joueur avec un effet fantôme ne peut pas sortir de la map
     * @throws InterruptedException
     */
    @Test
    void testMoveWithGhostEffectBoundary() throws InterruptedException {	
    	AsyncEffect.end(Effect.class);
    	characterWithoutPassage.setPosX(3);
    	characterWithoutPassage.setPosY(1);
    	gameWithoutPassage.evolve(Cmd.IDLE);
    	Thread.sleep(10);
    	
    	characterWithoutPassage.setPosX(0);
    	characterWithoutPassage.setPosY(0);
    	gameWithoutPassage.evolve(Cmd.UP);
    	
    	assertEquals(0, characterWithoutPassage.getPosX(), "Le pacman ne doit pas pouvoir changer sa position en abscisse");
    	assertEquals(0, characterWithoutPassage.getPosY(), "Le pacman ne doit pas pouvoir sortir du jeu");
    	gameWithoutPassage.evolve(Cmd.LEFT);
    
    	assertEquals(0, characterWithoutPassage.getPosX(), "Le pacman ne doit pas pouvoir sortir du jeu");
    	assertEquals(0, characterWithoutPassage.getPosY(),  "Le pacman ne doit pas pouvoir changer sa position en ordonnée");
    	
    	characterWithoutPassage.setPosX(0);
    	characterWithoutPassage.setPosY(10);
    	gameWithoutPassage.evolve(Cmd.LEFT);
    	
    	assertEquals(0, characterWithoutPassage.getPosX(), "Le pacman ne doit pas pouvoir sortir du jeu");
    	assertEquals(10, characterWithoutPassage.getPosY(),  "Le pacman ne doit pas pouvoir changer sa position en ordonnée");
    	gameWithoutPassage.evolve(Cmd.DOWN);
    	
    	assertEquals(0, characterWithoutPassage.getPosX(), "Le pacman ne doit pas pouvoir changer sa position en abscisse");
    	assertEquals(10, characterWithoutPassage.getPosY(),  "Le pacman ne doit pas pouvoir sortir du jeu");
    	
    	characterWithoutPassage.setPosX(15);
    	characterWithoutPassage.setPosY(0);
    	gameWithoutPassage.evolve(Cmd.RIGHT);
    	
    	assertEquals(15, characterWithoutPassage.getPosX(), "Le pacman ne doit pas pouvoir sortir du jeu");
    	assertEquals(0, characterWithoutPassage.getPosY(), "Le pacman ne doit pas pouvoir changer sa position en ordonnée");
    	gameWithoutPassage.evolve(Cmd.UP);
    	
    	assertEquals(15, characterWithoutPassage.getPosX(), "Le pacman ne doit pas pouvoir changer sa position en abscisse");
    	assertEquals(0, characterWithoutPassage.getPosY(), "Le pacman ne doit pas pouvoir sortir du jeu");
    	
    	characterWithoutPassage.setPosX(15);
    	characterWithoutPassage.setPosY(10);
    	gameWithoutPassage.evolve(Cmd.DOWN);
    	
    	assertEquals(15, characterWithoutPassage.getPosX(),  "Le pacman ne doit pas pouvoir changer sa position en abscisse");
    	assertEquals(10, characterWithoutPassage.getPosY(), "Le pacman ne doit pas pouvoir sortir du jeu");
    	gameWithoutPassage.evolve(Cmd.RIGHT);
    	
    	assertEquals(15, characterWithoutPassage.getPosX(),  "Le pacman ne doit pas pouvoir sortir du jeu");
    	assertEquals(10, characterWithoutPassage.getPosY(), "Le pacman ne doit pas pouvoir changer sa position en ordonnée");
    	AsyncEffect.end(Effect.class);
    }
    
    /**
     * S'assurer qu'un joueur avec un effet fantôme ne peut pas sortir de la map, même avec un effet de vitesse
     * @throws InterruptedException
     */
    @Test
    void testMoveWithGhostDoubleSpeedEffectBoundary() throws InterruptedException {	
    	AsyncEffect.end(Effect.class);
    	characterWithoutPassage.setPosX(2);
    	characterWithoutPassage.setPosY(1);
    	gameWithoutPassage.evolve(Cmd.IDLE);
    	Thread.sleep(10);
    	
    	characterWithoutPassage.setPosX(3);
    	characterWithoutPassage.setPosY(1);
    	gameWithoutPassage.evolve(Cmd.IDLE);
    	Thread.sleep(10);
    	
    	characterWithoutPassage.setPosX(0);
    	characterWithoutPassage.setPosY(0);
    	gameWithoutPassage.evolve(Cmd.UP);
    	
    	assertEquals(0, characterWithoutPassage.getPosX(), "Le pacman ne doit pas pouvoir changer sa position en abscisse");
    	assertEquals(0, characterWithoutPassage.getPosY(), "Le pacman ne doit pas pouvoir sortir du jeu");
    	gameWithoutPassage.evolve(Cmd.LEFT);
    
    	assertEquals(0, characterWithoutPassage.getPosX(), "Le pacman ne doit pas pouvoir sortir du jeu");
    	assertEquals(0, characterWithoutPassage.getPosY(),  "Le pacman ne doit pas pouvoir changer sa position en ordonnée");
    	
    	characterWithoutPassage.setPosX(0);
    	characterWithoutPassage.setPosY(10);
    	gameWithoutPassage.evolve(Cmd.LEFT);
    	
    	assertEquals(0, characterWithoutPassage.getPosX(), "Le pacman ne doit pas pouvoir sortir du jeu");
    	assertEquals(10, characterWithoutPassage.getPosY(),  "Le pacman ne doit pas pouvoir changer sa position en ordonnée");
    	gameWithoutPassage.evolve(Cmd.DOWN);
    	
    	assertEquals(0, characterWithoutPassage.getPosX(), "Le pacman ne doit pas pouvoir changer sa position en abscisse");
    	assertEquals(10, characterWithoutPassage.getPosY(),  "Le pacman ne doit pas pouvoir sortir du jeu");
    	
    	characterWithoutPassage.setPosX(15);
    	characterWithoutPassage.setPosY(0);
    	gameWithoutPassage.evolve(Cmd.RIGHT);
    	
    	assertEquals(15, characterWithoutPassage.getPosX(), "Le pacman ne doit pas pouvoir sortir du jeu");
    	assertEquals(0, characterWithoutPassage.getPosY(), "Le pacman ne doit pas pouvoir changer sa position en ordonnée");
		gameWithoutPassage.evolve(Cmd.UP);
    	
    	assertEquals(15, characterWithoutPassage.getPosX(), "Le pacman ne doit pas pouvoir changer sa position en abscisse");
    	assertEquals(0, characterWithoutPassage.getPosY(), "Le pacman ne doit pas pouvoir sortir du jeu");
    	
    	characterWithoutPassage.setPosX(15);
    	characterWithoutPassage.setPosY(10);
    	gameWithoutPassage.evolve(Cmd.DOWN);
    	
    	assertEquals(15, characterWithoutPassage.getPosX(),  "Le pacman ne doit pas pouvoir changer sa position en abscisse");
    	assertEquals(10, characterWithoutPassage.getPosY(), "Le pacman ne doit pas pouvoir sortir du jeu");
    	gameWithoutPassage.evolve(Cmd.RIGHT);
    	
    	assertEquals(15, characterWithoutPassage.getPosX(),  "Le pacman ne doit pas pouvoir sortir du jeu");
    	assertEquals(10, characterWithoutPassage.getPosY(), "Le pacman ne doit pas pouvoir changer sa position en ordonnée");
    	AsyncEffect.end(Effect.class);
    }
    
    /**
     * S'assurer qu'un joueur avec effet lent ne peut pas sortir de la map
     * @throws InterruptedException
     */
    @Test
    void testMoveWithGhostSlowEffectBoundary() throws InterruptedException {	
    	AsyncEffect.end(Effect.class);
    	characterWithoutPassage.setPosX(5);
    	characterWithoutPassage.setPosY(1);
    	gameWithoutPassage.evolve(Cmd.IDLE);
    	Thread.sleep(10);
    	
    	characterWithoutPassage.setPosX(4);
    	characterWithoutPassage.setPosY(1);
    	gameWithoutPassage.evolve(Cmd.LEFT);
    	Thread.sleep(10);
    	
    	characterWithoutPassage.setPosX(0);
    	characterWithoutPassage.setPosY(0);
    	gameWithoutPassage.evolve(Cmd.UP);
    	
    	assertEquals(0, characterWithoutPassage.getPosX(), "Le pacman ne doit pas pouvoir changer sa position en abscisse");
    	assertEquals(0, characterWithoutPassage.getPosY(), "Le pacman ne doit pas pouvoir sortir du jeu");
    	gameWithoutPassage.evolve(Cmd.LEFT);
    
    	assertEquals(0, characterWithoutPassage.getPosX(), "Le pacman ne doit pas pouvoir sortir du jeu");
    	assertEquals(0, characterWithoutPassage.getPosY(),  "Le pacman ne doit pas pouvoir changer sa position en ordonnée");
    	
    	characterWithoutPassage.setPosX(0);
    	characterWithoutPassage.setPosY(10);
    	gameWithoutPassage.evolve(Cmd.LEFT);
    	
    	assertEquals(0, characterWithoutPassage.getPosX(), "Le pacman ne doit pas pouvoir sortir du jeu");
    	assertEquals(10, characterWithoutPassage.getPosY(),  "Le pacman ne doit pas pouvoir changer sa position en ordonnée");
    	gameWithoutPassage.evolve(Cmd.DOWN);
    	
    	assertEquals(0, characterWithoutPassage.getPosX(), "Le pacman ne doit pas pouvoir changer sa position en abscisse");
    	assertEquals(10, characterWithoutPassage.getPosY(),  "Le pacman ne doit pas pouvoir sortir du jeu");
    	
    	characterWithoutPassage.setPosX(15);
    	characterWithoutPassage.setPosY(0);
    	gameWithoutPassage.evolve(Cmd.RIGHT);
    	
    	assertEquals(15, characterWithoutPassage.getPosX(), "Le pacman ne doit pas pouvoir sortir du jeu");
    	assertEquals(0, characterWithoutPassage.getPosY(), "Le pacman ne doit pas pouvoir changer sa position en ordonnée");
    	gameWithoutPassage.evolve(Cmd.UP);
    	
    	assertEquals(15, characterWithoutPassage.getPosX(), "Le pacman ne doit pas pouvoir changer sa position en abscisse");
    	assertEquals(0, characterWithoutPassage.getPosY(), "Le pacman ne doit pas pouvoir sortir du jeu");
    	
    	characterWithoutPassage.setPosX(15);
    	characterWithoutPassage.setPosY(10);
    	gameWithoutPassage.evolve(Cmd.DOWN);
    	
    	assertEquals(15, characterWithoutPassage.getPosX(),  "Le pacman ne doit pas pouvoir changer sa position en abscisse");
    	assertEquals(10, characterWithoutPassage.getPosY(), "Le pacman ne doit pas pouvoir sortir du jeu");
    	gameWithoutPassage.evolve(Cmd.RIGHT);
    	
    	assertEquals(15, characterWithoutPassage.getPosX(),  "Le pacman ne doit pas pouvoir sortir du jeu");
    	assertEquals(10, characterWithoutPassage.getPosY(), "Le pacman ne doit pas pouvoir changer sa position en ordonnée");
    	AsyncEffect.end(Effect.class);
    }
    
    /**
     * S'assurer qu'un joueur avec un effet fantôme peut traverser les murs
     * @throws InterruptedException
     */
    @Test
    void testMoveWithGhostEffectRight() throws InterruptedException {
    	AsyncEffect.end(Effect.class);
    	character.setPosX(3);
    	character.setPosY(1);
    	game.evolve(Cmd.IDLE);
    	Thread.sleep(10);
    	
    	game.evolve(Cmd.DOWN);
    	game.evolve(Cmd.DOWN);
    	
    	assertEquals(3, character.getPosX(), "Le pacman doit pouvoir bouger partout à l'intérieur du jeu");
    	assertEquals(3, character.getPosY(), "Le pacman doit pouvoir bouger partout à l'intérieur du jeu");
    	game.evolve(Cmd.UP);
    
    	assertEquals(3, character.getPosX(), "Le pacman doit pouvoir bouger partout à l'intérieur du jeu");
    	assertEquals(2, character.getPosY(), "Le pacman doit pouvoir bouger partout à l'intérieur du jeu");
    	game.evolve(Cmd.LEFT);
    	
    	assertEquals(2, character.getPosX(), "Le pacman doit pouvoir bouger partout à l'intérieur du jeu");
    	assertEquals(2, character.getPosY(), "Le pacman doit pouvoir bouger partout à l'intérieur du jeu");
    	game.evolve(Cmd.RIGHT);
    	
    	assertEquals(3, character.getPosX(), "Le pacman doit pouvoir bouger partout à l'intérieur du jeu");
    	assertEquals(2, character.getPosY(), "Le pacman doit pouvoir bouger partout à l'intérieur du jeu");
    	AsyncEffect.end(Effect.class);
    }
    
    /**
     * S'assurer qu'un joueur peut ramasser tous les effets à une vitesse normale
     * @throws InterruptedException
     */
    @Test
    void testPickupItemsWithNormalSpeed() throws InterruptedException {
    	AsyncEffect.end(Effect.class);
    	character.setPosX(1);
    	character.setPosY(1);
    	
    	for (int i = 0; i < 3; i++) {
    		character.setSpeed(1);
    		game.evolve(Cmd.RIGHT);
        	Thread.sleep(10);
    	}

    	assertEquals(3, AsyncEffect.getEffects().size(), "Tous les effets positifs doivent être ramassés");
    	
    	for (int i = 0; i < 3; i++) {
    		character.setSpeed(1);
    		game.evolve(Cmd.RIGHT);
        	Thread.sleep(10);
    	}
    	
    	assertEquals(5, AsyncEffect.getEffects().size(), "Tous les effets doivent être ramassés, seul Speed doit être désactivé");   	
    	AsyncEffect.end(Effect.class);
    }
    
    /**
     * S'assurer qu'un joueur peut ramasser tous les effets à une vitesse réduite
     * @throws InterruptedException
     */
    @Test
    void testPickupItemsWithSlowSpeed() throws InterruptedException {
    	AsyncEffect.end(Effect.class);
    	character.setPosX(1);
    	character.setPosY(1);
    	
    	for (int i = 0; i < 6; i++) {
    		character.setSpeed(0.5);
    		game.evolve(Cmd.RIGHT);
        	Thread.sleep(10);
    	}
    	
    	assertEquals(3, AsyncEffect.getEffects().size(), "Tous les effets positifs doivent être ramassés");
    	
    	for (int i = 0; i < 6; i++) {
    		character.setSpeed(0.5);
    		game.evolve(Cmd.RIGHT);
        	Thread.sleep(10);
    	}
    	
    	assertEquals(5, AsyncEffect.getEffects().size(), "Tous les effets doivent être ramassés, seul Speed doit être désactivé");   	
    	AsyncEffect.end(Effect.class);
    }
    
    /**
     * S'assurer qu'un joueur avec une vitesse normale peut se téléporter puis se déplacer et se retéléporter
     * @throws InterruptedException
     */
    @Test
    void testPassageRight() throws InterruptedException {
    	AsyncEffect.end(Effect.class);
    	
    	characterPassageTwo.setPosX(6);
    	characterPassageTwo.setPosY(0);
    	gamePassageTwo.evolve(Cmd.UP);
    	Thread.sleep(10);
    	
    	assertEquals(1, characterPassageTwo.getPosX(), "La position du pacman ne doit pas changer");
    	assertEquals(2, characterPassageTwo.getPosY(), "La position du pacman ne doit pas changer");
    	gamePassageTwo.evolve(Cmd.RIGHT);
    	
    	assertEquals(2, characterPassageTwo.getPosX(), "La position du pacman doit incrémenter suite au mouvement");
    	assertEquals(2, characterPassageTwo.getPosY(), "La position du pacman ne doit pas changer");
    	gamePassageTwo.evolve(Cmd.LEFT);
    	
    	assertEquals(6, characterPassageTwo.getPosX(), "Le pacman doit à nouveau être téléporté");
    	assertEquals(0, characterPassageTwo.getPosY(), "La position du pacman ne doit pas changer");
    	AsyncEffect.end(Effect.class);
    }
    
    /**
     * S'assurer qu'un joueur avec une vitesse lente ne se retéléporte pas directement lorsqu'il se déplace en dehors du portail
     * @throws InterruptedException
     */
    @Test
    void testPassageBoundaryOne() throws InterruptedException {
    	AsyncEffect.end(Effect.class);
    	characterPassageTwo.setSpeed(0.5);
    	characterPassageTwo.setPosX(6);
    	characterPassageTwo.setPosY(0);
    	gamePassageTwo.evolve(Cmd.UP);
    	
    	assertEquals(1, characterPassageTwo.getPosX(), "La position en abscisse du pacman doit changer suite à la téléportation");
    	assertEquals(2, characterPassageTwo.getPosY(), "La position en ordonnée du pacman doit changer suite à la téléportation");
    	gamePassageTwo.evolve(Cmd.DOWN);
    	
    	assertEquals(1, characterPassageTwo.getPosX(), "La position du pacman ne doit pas changer");
    	assertEquals(2, characterPassageTwo.getPosY(), "La position du pacman ne doit pas changer");
    	gamePassageTwo.evolve(Cmd.RIGHT);
    	
    	assertEquals(1.5, characterPassageTwo.getPosX(), "La position du pacman doit incrémenter suite au mouvement");
    	assertEquals(2, characterPassageTwo.getPosY(), "La position du pacman ne doit pas changer");
    	gamePassageTwo.evolve(Cmd.RIGHT);
    	
    	assertEquals(2, characterPassageTwo.getPosX(), "La position du pacman doit incrémenter suite au mouvement");
    	assertEquals(2, characterPassageTwo.getPosY(), "La position du pacman ne doit pas changer");
    	gamePassageTwo.evolve(Cmd.LEFT);
    	
    	assertEquals(6, characterPassageTwo.getPosX(), "Le pacman doit à nouveau être téléporté");
    	assertEquals(0, characterPassageTwo.getPosY(), "Le pacman doit à nouveau être téléporté");
    	AsyncEffect.end(Effect.class);
    }
    
    /**
     * S'assurer qu'un joueur bloqué dans un portail peut tout de même se téléporter à un autre portail
     * @throws InterruptedException
     */
    @Test
    void testPassageBoundaryTwo() throws InterruptedException {
    	AsyncEffect.end(Effect.class);
    	
    	characterPassage.setPosX(6);
    	characterPassage.setPosY(0);
    	gamePassage.evolve(Cmd.UP);
    	
    	assertEquals(1, characterPassage.getPosX(), "La position en abscisse du pacman doit changer suite à la téléportation");
    	assertEquals(2, characterPassage.getPosY(), "La position en ordonnée du pacman doit changer suite à la téléportation");
    	gamePassage.evolve(Cmd.DOWN);
    	
    	assertEquals(1, characterPassage.getPosX(), "La position du pacman doit incrémenter suite au mouvement");
    	assertEquals(2, characterPassage.getPosY(), "La position du pacman ne doit pas changer");
    	gamePassage.evolve(Cmd.RIGHT);
    	
    	assertEquals(1, characterPassage.getPosX(), "La position du pacman doit incrémenter suite au mouvement");
    	assertEquals(2, characterPassage.getPosY(), "La position du pacman ne doit pas changer");
    	gamePassage.evolve(Cmd.LEFT);
    	AsyncEffect.end(Effect.class);
    }
    
    /**
     * Test avec une carte totalement vide (le joeuur ne peut pas se déplacer)
     * @throws InterruptedException
     */
    @Test
    void testEmpty() throws InterruptedException {
    	AsyncEffect.end(Effect.class);

    	gameEmpty.evolve(Cmd.UP);
    	assertEquals(0, characterEmpty.getPosX(), "La position du pacman ne doit pas changer");
    	assertEquals(0, characterEmpty.getPosY(), "La position du pacman ne doit pas changer");

		gameEmpty.evolve(Cmd.DOWN);
    	assertEquals(0, characterEmpty.getPosX(), "La position du pacman ne doit pas changer");
    	assertEquals(0, characterEmpty.getPosY(), "La position du pacman ne doit pas changer");

		gameEmpty.evolve(Cmd.RIGHT);
    	assertEquals(0, characterEmpty.getPosX(), "La position du pacman ne doit pas changer");
    	assertEquals(0, characterEmpty.getPosY(), "La position du pacman ne doit pas changer");

		gameEmpty.evolve(Cmd.LEFT);
    	assertEquals(0, characterEmpty.getPosX(), "La position du pacman ne doit pas changer");
    	assertEquals(0, characterEmpty.getPosY(), "La position du pacman ne doit pas changer");
    	AsyncEffect.end(Effect.class);
    }
}