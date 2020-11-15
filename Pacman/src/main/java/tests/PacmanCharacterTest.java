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
    	pacman.getMovingStrategy().setFactorX(1);
    	assert(pacman.getPosX() == 5): "La position du pacman devrait être de 5";
    	pacman.mooveRight();
    	assert(pacman.getPosX() == 6): "La position du pacman devrait être de 6";
    	pacman.mooveRight();
    	pacman.mooveRight();
    	assert(pacman.getPosX() == 8): "La position du pacman devrait être de 8";
    	pacman.mooveRight(); 
    	assert(pacman.getPosX() == 9): "La position du pacman devrait être de 9";

    }

    @org.junit.jupiter.api.Test
    void mooveLeft() {
    	pacman.getMovingStrategy().setFactorX(-1);
    	assert(pacman.getPosX() == 5): "La position du pacman devrait être de 5";
    	pacman.mooveLeft();
    	assert(pacman.getPosX() == 4): "La position du pacman devrait être de 4";
    	pacman.mooveLeft();
    	pacman.mooveLeft();
    	assert(pacman.getPosX() == 2): "La position du pacman devrait être de 2";
    	pacman.mooveLeft(); 
    	assert(pacman.getPosX() == 1): "La position du pacman devrait être de 1";
    }

    @org.junit.jupiter.api.Test
    void mooveUp() {
    	pacman.getMovingStrategy().setFactorY(-1);
    	assert(pacman.getPosY() == 5): "La position du pacman devrait être de 5";
    	pacman.mooveUp();
    	assert(pacman.getPosY() == 4): "La position du pacman devrait être de 4";
    	pacman.mooveUp();
    	pacman.mooveUp();
    	assert(pacman.getPosY() == 2): "La position du pacman devrait être de 2";
    	pacman.mooveUp(); 
    	assert(pacman.getPosY() == 1): "La position du pacman devrait être de 1";
    }

    @org.junit.jupiter.api.Test
    void mooveDown() {
    	pacman.getMovingStrategy().setFactorY(1);
    	assert(pacman.getPosY() == 5): "La position du pacman devrait être de 5";
    	pacman.mooveDown();
    	assert(pacman.getPosY() == 6): "La position du pacman devrait être de 6";
    	pacman.mooveDown();
    	pacman.mooveDown();
    	assert(pacman.getPosY() == 8): "La position du pacman devrait être de 8";
    	pacman.mooveDown(); 
    	assert(pacman.getPosY() == 9): "La position du pacman devrait être de 9";
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