package tests;

import engine.Cmd;
import engine.MapBuilder;
import model.PacmanCharacter;
import model.PacmanGame;
import model.Wall;
import model.effect.AsyncEffect;
import model.effect.Effect;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PacmanGameTest {
	
    private PacmanGame game;
    private MapBuilder map;
	private PacmanCharacter character;
	private int index = 0;

    @BeforeEach
    void setUp() {
    	map = new MapBuilder("test.txt", 16, 11);
	    game = new PacmanGame("helpFilePacman.txt", map);
    	character = game.getCharacter();
    }

    @Test
    void testInitRight() {
    	assertTrue(game.getMapBuilder().get((int)game.getCharacterPosX(), (int)game.getCharacterPosY()).isAccessible(), "La case de départ doit être accessible");
    }
    
    @Test
    void testMoveWithoutGhostEffectBoundary() throws InterruptedException {
    	AsyncEffect.end(Effect.class);
    	character.setPosX(11);
    	character.setPosY(2);
    	game.evolve(Cmd.UP);
    	Thread.sleep(10);
    	
    	assertEquals(11, character.getPosX(), "La position du pacman ne doit pas changer");
    	assertEquals(2, character.getPosY(), "La position du pacman ne doit pas changer");
    	
    	game.evolve(Cmd.DOWN);
    	assertEquals(11, character.getPosX(), "La position du pacman ne doit pas changer");
    	assertEquals(2, character.getPosY(), "La position du pacman ne doit pas changer");
    	
    	game.evolve(Cmd.RIGHT);
    	assertEquals(11, character.getPosX(), "La position du pacman ne doit pas changer");
    	assertEquals(2, character.getPosY(), "La position du pacman ne doit pas changer");
    	
    	game.evolve(Cmd.LEFT);
    	assertEquals(11, character.getPosX(), "La position du pacman ne doit pas changer");
    	assertEquals(2, character.getPosY(), "La position du pacman ne doit pas changer");
    }
    
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
    
    @Test
    void testMoveWithGhostEffectBoundary() throws InterruptedException {	
    	AsyncEffect.end(Effect.class);
    	character.setPosX(3);
    	character.setPosY(1);
    	game.evolve(Cmd.IDLE);
    	Thread.sleep(10);
    	
    	character.setPosX(0);
    	character.setPosY(0);
    	game.evolve(Cmd.UP);
    	
    	assertEquals(0, character.getPosX(), "Le pacman ne doit pas pouvoir changer sa position en abscisse");
    	assertEquals(0, character.getPosY(), "Le pacman ne doit pas pouvoir sortir du jeu");
    	game.evolve(Cmd.LEFT);
    
    	assertEquals(0, character.getPosX(), "Le pacman ne doit pas pouvoir sortir du jeu");
    	assertEquals(0, character.getPosY(),  "Le pacman ne doit pas pouvoir changer sa position en ordonnée");
    	
    	character.setPosX(0);
    	character.setPosY(10);
    	game.evolve(Cmd.LEFT);
    	
    	assertEquals(0, character.getPosX(), "Le pacman ne doit pas pouvoir sortir du jeu");
    	assertEquals(10, character.getPosY(),  "Le pacman ne doit pas pouvoir changer sa position en ordonnée");
    	game.evolve(Cmd.DOWN);
    	
    	assertEquals(0, character.getPosX(), "Le pacman ne doit pas pouvoir changer sa position en abscisse");
    	assertEquals(10, character.getPosY(),  "Le pacman ne doit pas pouvoir sortir du jeu");
    	
    	character.setPosX(15);
    	character.setPosY(0);
    	game.evolve(Cmd.RIGHT);
    	
    	assertEquals(15, character.getPosX(), "Le pacman ne doit pas pouvoir sortir du jeu");
    	assertEquals(0, character.getPosY(), "Le pacman ne doit pas pouvoir changer sa position en ordonnée");
    	game.evolve(Cmd.UP);
    	
    	assertEquals(15, character.getPosX(), "Le pacman ne doit pas pouvoir changer sa position en abscisse");
    	assertEquals(0, character.getPosY(), "Le pacman ne doit pas pouvoir sortir du jeu");
    	
    	character.setPosX(15);
    	character.setPosY(10);
    	game.evolve(Cmd.DOWN);
    	
    	assertEquals(15, character.getPosX(),  "Le pacman ne doit pas pouvoir changer sa position en abscisse");
    	assertEquals(10, character.getPosY(), "Le pacman ne doit pas pouvoir sortir du jeu");
    	game.evolve(Cmd.RIGHT);
    	
    	assertEquals(15, character.getPosX(),  "Le pacman ne doit pas pouvoir sortir du jeu");
    	assertEquals(10, character.getPosY(), "Le pacman ne doit pas pouvoir changer sa position en ordonnée");
    	AsyncEffect.end(Effect.class);
    }
    
    @Test
    void testMoveWithGhostDoubleSpeedEffectBoundary() throws InterruptedException {	
    	AsyncEffect.end(Effect.class);
    	character.setPosX(2);
    	character.setPosY(1);
    	game.evolve(Cmd.IDLE);
    	Thread.sleep(10);
    	
    	character.setPosX(3);
    	character.setPosY(1);
    	game.evolve(Cmd.IDLE);
    	Thread.sleep(10);
    	
    	character.setPosX(0);
    	character.setPosY(0);
    	game.evolve(Cmd.UP);
    	
    	assertEquals(0, character.getPosX(), "Le pacman ne doit pas pouvoir changer sa position en abscisse");
    	assertEquals(0, character.getPosY(), "Le pacman ne doit pas pouvoir sortir du jeu");
    	game.evolve(Cmd.LEFT);
    
    	assertEquals(0, character.getPosX(), "Le pacman ne doit pas pouvoir sortir du jeu");
    	assertEquals(0, character.getPosY(),  "Le pacman ne doit pas pouvoir changer sa position en ordonnée");
    	
    	character.setPosX(0);
    	character.setPosY(10);
    	game.evolve(Cmd.LEFT);
    	
    	assertEquals(0, character.getPosX(), "Le pacman ne doit pas pouvoir sortir du jeu");
    	assertEquals(10, character.getPosY(),  "Le pacman ne doit pas pouvoir changer sa position en ordonnée");
    	game.evolve(Cmd.DOWN);
    	
    	assertEquals(0, character.getPosX(), "Le pacman ne doit pas pouvoir changer sa position en abscisse");
    	assertEquals(10, character.getPosY(),  "Le pacman ne doit pas pouvoir sortir du jeu");
    	
    	character.setPosX(15);
    	character.setPosY(0);
    	game.evolve(Cmd.RIGHT);
    	
    	assertEquals(15, character.getPosX(), "Le pacman ne doit pas pouvoir sortir du jeu");
    	assertEquals(0, character.getPosY(), "Le pacman ne doit pas pouvoir changer sa position en ordonnée");
    	game.evolve(Cmd.UP);
    	
    	assertEquals(15, character.getPosX(), "Le pacman ne doit pas pouvoir changer sa position en abscisse");
    	assertEquals(0, character.getPosY(), "Le pacman ne doit pas pouvoir sortir du jeu");
    	
    	character.setPosX(15);
    	character.setPosY(10);
    	game.evolve(Cmd.DOWN);
    	
    	assertEquals(15, character.getPosX(),  "Le pacman ne doit pas pouvoir changer sa position en abscisse");
    	assertEquals(10, character.getPosY(), "Le pacman ne doit pas pouvoir sortir du jeu");
    	game.evolve(Cmd.RIGHT);
    	
    	assertEquals(15, character.getPosX(),  "Le pacman ne doit pas pouvoir sortir du jeu");
    	assertEquals(10, character.getPosY(), "Le pacman ne doit pas pouvoir changer sa position en ordonnée");
    	AsyncEffect.end(Effect.class);
    }
    
    @Test
    void testMoveWithGhostSlowSpeedEffectBoundary() throws InterruptedException {	
    	AsyncEffect.end(Effect.class);
    	character.setPosX(5);
    	character.setPosY(1);
    	game.evolve(Cmd.IDLE);
    	Thread.sleep(10);
    	
    	character.setPosX(4);
    	character.setPosY(1);
    	game.evolve(Cmd.LEFT);
    	Thread.sleep(10);
    	
    	character.setPosX(0);
    	character.setPosY(0);
    	game.evolve(Cmd.UP);
    	
    	assertEquals(0, character.getPosX(), "Le pacman ne doit pas pouvoir changer sa position en abscisse");
    	assertEquals(0, character.getPosY(), "Le pacman ne doit pas pouvoir sortir du jeu");
    	game.evolve(Cmd.LEFT);
    
    	assertEquals(0, character.getPosX(), "Le pacman ne doit pas pouvoir sortir du jeu");
    	assertEquals(0, character.getPosY(),  "Le pacman ne doit pas pouvoir changer sa position en ordonnée");
    	
    	character.setPosX(0);
    	character.setPosY(10);
    	game.evolve(Cmd.LEFT);
    	
    	assertEquals(0, character.getPosX(), "Le pacman ne doit pas pouvoir sortir du jeu");
    	assertEquals(10, character.getPosY(),  "Le pacman ne doit pas pouvoir changer sa position en ordonnée");
    	game.evolve(Cmd.DOWN);
    	
    	assertEquals(0, character.getPosX(), "Le pacman ne doit pas pouvoir changer sa position en abscisse");
    	assertEquals(10, character.getPosY(),  "Le pacman ne doit pas pouvoir sortir du jeu");
    	
    	character.setPosX(15);
    	character.setPosY(0);
    	game.evolve(Cmd.RIGHT);
    	
    	assertEquals(15, character.getPosX(), "Le pacman ne doit pas pouvoir sortir du jeu");
    	assertEquals(0, character.getPosY(), "Le pacman ne doit pas pouvoir changer sa position en ordonnée");
    	game.evolve(Cmd.UP);
    	
    	assertEquals(15, character.getPosX(), "Le pacman ne doit pas pouvoir changer sa position en abscisse");
    	assertEquals(0, character.getPosY(), "Le pacman ne doit pas pouvoir sortir du jeu");
    	
    	character.setPosX(15);
    	character.setPosY(10);
    	game.evolve(Cmd.DOWN);
    	
    	assertEquals(15, character.getPosX(),  "Le pacman ne doit pas pouvoir changer sa position en abscisse");
    	assertEquals(10, character.getPosY(), "Le pacman ne doit pas pouvoir sortir du jeu");
    	game.evolve(Cmd.RIGHT);
    	
    	assertEquals(15, character.getPosX(),  "Le pacman ne doit pas pouvoir sortir du jeu");
    	assertEquals(10, character.getPosY(), "Le pacman ne doit pas pouvoir changer sa position en ordonnée");
    	AsyncEffect.end(Effect.class);
    }
    
    @Test
    void testMoveWithGhostEffectRight() throws InterruptedException {
    	AsyncEffect.end(Effect.class);
    	character.setPosX(3);
    	character.setPosY(1);
    	game.evolve(Cmd.IDLE);
    	Thread.sleep(10);
    	
    	game.evolve(Cmd.DOWN);
    	game.evolve(Cmd.DOWN);
    	
    	assertTrue(character.getGhost(), ""+index);
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
    
    @Test
    void testPassageRight() throws InterruptedException {
    	AsyncEffect.end(Effect.class);
    	
    	MapBuilder map = game.getMapBuilder();
    	map.set(1, 3, new Wall(1,3));
    	map.set(4, 3, new Wall(4,3));
    	
    	character.setPosX(15);
    	character.setPosY(0);
    	game.evolve(Cmd.IDLE);
    	Thread.sleep(10);
    	
    	assertEquals(4, character.getPosX(), "La position en abscisse du pacman doit changer suite à la téléportation");
    	assertEquals(3, character.getPosY(), "La position en ordonnée du pacman doit changer suite à la téléportation");
    	game.evolve(Cmd.DOWN);
    	
    	assertEquals(4, character.getPosX(), "La position du pacman ne doit pas changer");
    	assertEquals(3, character.getPosY(), "La position du pacman ne doit pas changer");
    	game.evolve(Cmd.RIGHT);
    	
    	assertEquals(5, character.getPosX(), "La position du pacman doit incrémenter suite au mouvement");
    	assertEquals(3, character.getPosY(), "La position du pacman ne doit pas changer");
    	game.evolve(Cmd.LEFT);
    	
    	assertEquals(15, character.getPosX(), "Le pacman doit à nouveau être téléporté");
    	assertEquals(0, character.getPosY(), "La position du pacman ne doit pas changer");
    	AsyncEffect.end(Effect.class);
    }
    
    @Test
    void testPassageBoundary() throws InterruptedException {
    	AsyncEffect.end(Effect.class);
    	
    	character.setPosX(15);
    	character.setPosY(0);
    	game.evolve(Cmd.UP);
    	Thread.sleep(10);
    	
    	assertEquals(4, character.getPosX(), "La position en abscisse du pacman doit changer suite à la téléportation");
    	assertEquals(3, character.getPosY(), "La position en ordonnée du pacman doit changer suite à la téléportation");
    	game.evolve(Cmd.DOWN);
    	
    	assertEquals(4, character.getPosX(), "La position du pacman ne doit pas changer");
    	assertEquals(3, character.getPosY(), "La position du pacman ne doit pas changer");
    	game.evolve(Cmd.RIGHT);
    	
    	assertEquals(4.5, character.getPosX(), "La position du pacman doit incrémenter suite au mouvement");
    	assertEquals(0, character.getPosY(), "La position du pacman ne doit pas changer");
    	game.evolve(Cmd.RIGHT);
    	
    	assertEquals(5, character.getPosX(), "La position du pacman doit incrémenter suite au mouvement");
    	assertEquals(0, character.getPosY(), "La position du pacman ne doit pas changer");
    	game.evolve(Cmd.LEFT);
    	
    	assertEquals(15, character.getPosX(), "Le pacman doit à nouveau être téléporté");
    	assertEquals(0, character.getPosY(), "Le pacman doit à nouveau être téléporté");
    	game.evolve(Cmd.LEFT);
    	AsyncEffect.end(Effect.class);
    }
}