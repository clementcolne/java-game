package tests;

import model.PacmanCharacter;
import model.effect.Stun;
import org.junit.jupiter.api.Test;

class PacmanCharacterTest {

	private PacmanCharacter pacman;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        pacman = new PacmanCharacter(5, 5);
    }

    @org.junit.jupiter.api.Test
    void mooveRight() {
    	// Partie 1 : vitesse = 2
    	assert(pacman.getPosX() == 5): "La position du pacman devrait être de 5";
    	pacman.mooveRight();
    	assert(pacman.getPosX() == 7): "La position du pacman devrait être de 7";
    	pacman.mooveRight();
    	pacman.mooveRight();
    	assert(pacman.getPosX() == 11): "La position du pacman devrait être de 11";
    	pacman.mooveRight(); 
    	assert(pacman.getPosX() == 13): "La position du pacman devrait être de 13";
    	
    	// Partie 2 : vitesse = 1
    	pacman.setSpeed(1);
    	pacman.mooveRight();
    	assert(pacman.getPosX() == 14): "La position du pacman devrait être de 14";
    	pacman.mooveRight();
    	pacman.mooveRight();
    	assert(pacman.getPosX() == 16): "La position du pacman devrait être de 16";
    	pacman.mooveRight(); 
    	assert(pacman.getPosX() == 17): "La position du pacman devrait être de 17";
    	
    	// Partie 3 : vitesse = 0
    	pacman.setSpeed(0);
    	pacman.mooveRight();
    	assert(pacman.getPosX() == 17): "La position du pacman devrait être de 17";
    	

    	// Partie 4 : vitesse = -1
    	pacman.setSpeed(-1);
    	pacman.mooveRight();
    	assert(pacman.getPosX() == 16): "La position du pacman devrait être de 16";
    	pacman.mooveRight();
    	pacman.mooveRight();
    	assert(pacman.getPosX() == 14): "La position du pacman devrait être de 14";
    	pacman.mooveRight(); 
    	assert(pacman.getPosX() == 13): "La position du pacman devrait être de 13";
    }

    @org.junit.jupiter.api.Test
    void mooveLeft() {
    	// Partie 1 : vitesse = 2
    	assert(pacman.getPosX() == 5): "La position du pacman devrait être de 5";
    	pacman.mooveLeft();
    	assert(pacman.getPosX() == 3): "La position du pacman devrait être de 3";
    	pacman.mooveLeft();
    	pacman.mooveLeft();
    	assert(pacman.getPosX() == -1): "La position du pacman devrait être de -1";
    	pacman.mooveLeft(); 
    	assert(pacman.getPosX() == -3): "La position du pacman devrait être de -3";
    	
    	// Partie 2 : vitesse = 1
    	pacman.setSpeed(1);
    	pacman.mooveLeft();
    	assert(pacman.getPosX() == -4): "La position du pacman devrait être de -4";
    	pacman.mooveLeft();
    	pacman.mooveLeft();
    	assert(pacman.getPosX() == -6): "La position du pacman devrait être de -6";
    	pacman.mooveLeft(); 
    	assert(pacman.getPosX() == -7): "La position du pacman devrait être de -7";
    	
    	// Partie 2 : vitesse = 0
    	pacman.setSpeed(0);
    	pacman.mooveLeft();
    	assert(pacman.getPosX() == -7): "La position du pacman devrait être de -7";
    	

    	// Partie 3 : vitesse = -1
    	pacman.setSpeed(-1);
    	pacman.mooveLeft();
    	assert(pacman.getPosX() == -6): "La position du pacman devrait être de -6";
    	pacman.mooveLeft();
    	pacman.mooveLeft();
    	assert(pacman.getPosX() == -4): "La position du pacman devrait être de -4";
    	pacman.mooveLeft(); 
    	assert(pacman.getPosX() == -3): "La position du pacman devrait être de -3";
    }

    @org.junit.jupiter.api.Test
    void mooveUp() {
    	// Partie 1 : vitesse = 2
    	assert(pacman.getPosY() == 5): "La position du pacman devrait être de 5";
    	pacman.mooveUp();
    	assert(pacman.getPosY() == 3): "La position du pacman devrait être de 3";
    	pacman.mooveUp();
    	pacman.mooveUp();
    	assert(pacman.getPosY() == -1): "La position du pacman devrait être de -1";
    	pacman.mooveUp(); 
    	assert(pacman.getPosY() == -3): "La position du pacman devrait être de -3";
    	
    	// Partie 2 : vitesse = 1
    	pacman.setSpeed(1);
    	pacman.mooveUp();
    	assert(pacman.getPosY() == -4): "La position du pacman devrait être de -4";
    	pacman.mooveUp();
    	pacman.mooveUp();
    	assert(pacman.getPosY() == -6): "La position du pacman devrait être de -6";
    	pacman.mooveUp(); 
    	assert(pacman.getPosY() == -7): "La position du pacman devrait être de -7";
    	
    	// Partie 2 : vitesse = 0
    	pacman.setSpeed(0);
    	pacman.mooveUp();
    	assert(pacman.getPosY() == -7): "La position du pacman devrait être de -7";
    	

    	// Partie 3 : vitesse = -1
    	pacman.setSpeed(-1);
    	pacman.mooveUp();
    	assert(pacman.getPosY() == -6): "La position du pacman devrait être de -6";
    	pacman.mooveUp();
    	pacman.mooveUp();
    	assert(pacman.getPosY() == -4): "La position du pacman devrait être de -4";
    	pacman.mooveUp(); 
    	assert(pacman.getPosY() == -3): "La position du pacman devrait être de -3";	
    }

    @org.junit.jupiter.api.Test
    void mooveDown() {
    	// Partie 1 : vitesse = 2
    	assert(pacman.getPosY() == 5): "La position du pacman devrait être de 5";
    	pacman.mooveDown();
    	assert(pacman.getPosY() == 7): "La position du pacman devrait être de 7";
    	pacman.mooveDown();
    	pacman.mooveDown();
    	assert(pacman.getPosY() == 11): "La position du pacman devrait être de 11";
    	pacman.mooveDown(); 
    	assert(pacman.getPosY() == 13): "La position du pacman devrait être de 13";
    	
    	// Partie 2 : vitesse = 1
    	pacman.setSpeed(1);
    	pacman.mooveDown();
    	assert(pacman.getPosY() == 14): "La position du pacman devrait être de 14";
    	pacman.mooveDown();
    	pacman.mooveDown();
    	assert(pacman.getPosY() == 16): "La position du pacman devrait être de 16";
    	pacman.mooveDown(); 
    	assert(pacman.getPosY() == 17): "La position du pacman devrait être de 17";
    	
    	// Partie 3 : vitesse = 0
    	pacman.setSpeed(0);
    	pacman.mooveDown();
    	assert(pacman.getPosY() == 17): "La position du pacman devrait être de 17";
    	

    	// Partie 4 : vitesse = -1
    	pacman.setSpeed(-1);
    	pacman.mooveDown();
    	assert(pacman.getPosY() == 16): "La position du pacman devrait être de 16";
    	pacman.mooveDown();
    	pacman.mooveDown();
    	assert(pacman.getPosY() == 14): "La position du pacman devrait être de 14";
    	pacman.mooveDown(); 
    	assert(pacman.getPosY() == 13): "La position du pacman devrait être de 13";
    }

    @Test
    void setDamage() {
        assert (pacman.getLife() == 10):"La vie du pacman à l'initialisation devrait être de 10";
        pacman.setDamage(4);
        assert (pacman.getLife() == 6):"La vie du pacman après avoir reçu des dégats devrait être de 5";
        pacman.setDamage(10);
        assert (pacman.getLife() == 0):"La vie du pacman après avoir reçu des dégats devrait être de 0";
    }


	@org.junit.jupiter.api.Test
	void mooveRightStun() {
		new Stun().doEffect(pacman);
		mooveDown();
	}

	@org.junit.jupiter.api.Test
	void mooveLeftStun() {
		new Stun().doEffect(pacman);
		mooveUp();
	}

	@org.junit.jupiter.api.Test
	void mooveUpStun() {
		new Stun().doEffect(pacman);
		mooveLeft();
	}

	@org.junit.jupiter.api.Test
	void mooveDownStun() {
		new Stun().doEffect(pacman);
		mooveRight();
	}
}