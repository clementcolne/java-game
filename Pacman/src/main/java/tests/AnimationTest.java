package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.NoSuchElementException;

import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import engine.Animation;

@RunWith(JUnit4.class)
class AnimationTest{

	private Animation ground, character;

	
    @org.junit.jupiter.api.BeforeEach
	protected void setUp() {
        character = new Animation("resources/Character/wraith.gif", 12);
        ground = new Animation("resources/Ground/Ground_lvl1.png", 60);
    }

    @org.junit.jupiter.api.Test
    void testGifAnimateRight() {
    	try {
    		character.animate();
			Thread.sleep(800);
			
	    	assertTrue(character.getIndex() <= character.getNumberOfFrames(), "Le numéro de l'image devrait être supérieur ou égal au nombre d'images de l'animation");
	    	assertEquals(0, ground.getIndex(), "Le sol n'est pas une animation, il ne doit contenir qu'une seule image");
	    	
	    	Thread.sleep(200);
	    	assertTrue(character.getIndex() < character.getNumberOfFrames(), "Le numéro de l'image devrait être remis à 0, donc la valeur doit être très proche");
	    	assertEquals(0, ground.getIndex(), "Le sol n'est pas une animation, il ne doit contenir qu'une seule image");
	    	
    	} catch (InterruptedException e) {
    		e.printStackTrace();
    	}
    }

    @org.junit.jupiter.api.Test
    void testGifPauseRight() {
    	try {
	    	character.animate();
	    	ground.animate();
	    	Thread.sleep(500);
	    	
	    	character.pause(true);
	    	ground.pause(true);
	    	int currentCharacterIndex = character.getIndex();
	    	int currentGroundIndex = ground.getIndex();
	    	Thread.sleep(1000);
	    	
	    	assertEquals(currentCharacterIndex, character.getIndex(), "L'animation du personnage a été mise en pause, son indice ne doit plus changer");
	    	assertEquals(currentGroundIndex, ground.getIndex(), "L'animation du sol a été mise en pause, son indice ne doit pas changer");
	    
	    	character.pause(false);
	    	ground.pause(false);
	    	
	    	Thread.sleep(20);
	    	
	    	assertTrue(character.getIndex() >= currentCharacterIndex, "Le numéro de l'image devrait être supérieur puisque l'animation n'est plus en pause");
	    	assertTrue(ground.getIndex() == currentGroundIndex, "Le sol n'est pas une animation, son numéro d'image ne doit pas changer");
    	} catch (InterruptedException e) {
    		e.printStackTrace();
    	}
    }
    	
    @org.junit.jupiter.api.Test
    void testGifStopRight() {
    	try {
    		
    		character.animate();
    		ground.animate();
    		Thread.sleep(1000);
    		
    		character.kill();
    		ground.kill();
    		
    		assertEquals(0, character.getIndex(), "L'animation du personnage a été stoppée, son indice doit être remis à zéro");
	    	assertEquals(0, ground.getIndex(), "L'animation du personnage a été stoppée, son indice doit rester à zéro");
	    
	    	testGifAnimateRight();
    	} catch (InterruptedException e) {
    		e.printStackTrace();
    	}
    }
    
    @org.junit.jupiter.api.Test
    void testChangeSpeedRight() {
    	try {
	    	character.animate();
	    	Thread.sleep(100);
	    	
	    	character.setFramesFps(1);
	    	int currentImageIndex = character.getIndex();
	    	Thread.sleep(100);
	    	
	    	assertEquals(currentImageIndex+1, character.getIndex(), "Le numéro d'image ne devrait être incrémenté que 1 suite au changement de FPS");
    	} catch (InterruptedException e) {
    		e.printStackTrace();
    	}
    }
    

    @org.junit.jupiter.api.Test
    void testChangeSpeedBoundary() {
    	try {
    		Animation boundary = new Animation("resources/Character/wraith.gif", 60);
	    	boundary.animate();
	    	Thread.sleep(1000);
	    	
	    	boundary.setFramesFps(-1);
	    	int currentImageIndex = character.getIndex();
	    	Thread.sleep(1500);
	    	
	    	assertEquals(currentImageIndex, character.getIndex(), "Le numéro d'image ne devrait pas changer suite au changement de FPS à 0");
    	} catch (InterruptedException e) {
    		e.printStackTrace();
    	}
    }
    
    @org.junit.jupiter.api.Test
    void testIncorrectGif() {
    	assertThrows(NullPointerException.class, () -> {
    		new Animation(null,60);
    	}, "Une animation avec un chemin d'image null ne peut pas exister");
    	
    	assertThrows(IllegalArgumentException.class, () -> {
    		new Animation("",60);
    	}, "Une animation avec un chemin d'image inexistant ne peut pas exister");
    
    	assertThrows(NoSuchElementException.class, () -> {
    		new Animation("resources/Map/map.txt",60);
    	}, "Une animation avec un chemin d'image ne correspondant pas à une image ne peut pas exister");
    }
    
    @org.junit.jupiter.api.Test
    void testBoundaryGifCreation() {
    	try {
	    	Animation boundary = new Animation("resources/Character/wraith.gif", 0);
	    	boundary.animate();
	    	Thread.sleep(1000);
	    	
	    	assertEquals(0, boundary.getIndex(), "Le numéro de l'image de l'animation doit rester à 0 puisque la vitesse est de 0 FPS");
	    	
	    	boundary = new Animation("resources/Character/wraith.gif", 0);
	    	Thread.sleep(1000);
	    	
	    	assertEquals(0, boundary.getIndex(), "Le numéro de l'image de l'animation doit rester à 0 puisque la vitesse est de 0 FPS");
    	} catch (InterruptedException e) {
    		e.printStackTrace();
    	}
    }
    
    
}